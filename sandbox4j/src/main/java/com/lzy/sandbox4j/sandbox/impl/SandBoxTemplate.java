package com.lzy.sandbox4j.sandbox.impl;

import cn.hutool.core.io.FileUtil;
import com.lzy.sandbox4j.enums.ExecuteEnum;
import com.lzy.sandbox4j.sandbox.dto.ExecuteMessage;
import com.lzy.sandbox4j.sandbox.CodeSandBox;
import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeRequest;
import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeResponse;
import com.lzy.sandbox4j.sandbox.dto.JudgeInfo;
import com.lzy.sandbox4j.utils.ProcessUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SandBoxTemplate implements CodeSandBox {

    protected static final String GLOBAL_CODE_DIR_NAME = "tempcode";

    protected static final long TIME_LIMIT = 5000L;


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        /* 保存代码 */
        File file = this.saveCode(executeCodeRequest.getCode());

        /* 编译代码 */
        ExecuteMessage compileMessage = this.compile(file);
        if(compileMessage.getExitValue()!=0){
            this.deleteFile(file);
            return createErrorResponse(ExecuteEnum.COMPILE_ERROR);
        }

        /* 运行代码 */
        List<ExecuteMessage> executeMessages = null;
        try {
            executeMessages = this.runCode(file, executeCodeRequest.getInputList());
        } catch (IOException e) {
            return createErrorResponse(ExecuteEnum.TIME_LIMIT_EXCEEDED);
        } catch (Exception e) {
            return createErrorResponse(ExecuteEnum.SYSTEM_ERROR);
        } finally {
            this.deleteFile(file);
        }

        /* 删除文件 */
        this.deleteFile(file);
        ExecuteCodeResponse response = this.getResponse(executeMessages);
        return response;
    }

    public File saveCode(String code){
        String globalDir = System.getProperty("user.dir") + File.separator + GLOBAL_CODE_DIR_NAME;
        if (!FileUtil.exist(globalDir)){
            FileUtil.mkdir(globalDir);
        }
        String userCodePath = globalDir + File.separator + UUID.randomUUID() + File.separator + codeFileName();
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    public ExecuteMessage compile(File codeFile){
        String compileCmd = this.createCompileCmd(codeFile.getAbsolutePath());
        try {
            Process process = Runtime.getRuntime().exec(compileCmd);
            ExecuteMessage executeMessage = ProcessUtils.runProcess(process);
            return executeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ExecuteMessage> runCode(File codeFile, List<String> inputs) throws IOException, InterruptedException {
        String path = codeFile.getParentFile().getAbsolutePath();
        List<ExecuteMessage> executeMessageList = new ArrayList<>();
        String runCmd = this.createRunCmd(path);
        for(String input : inputs){
            try {
                boolean flag = false;
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                new Thread(() -> {
                    /* 防止超时 */
                    try {
                        Thread.sleep(TIME_LIMIT);
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtils.runInteractProcess(runProcess,input);
//                System.out.println(executeMessage);
                executeMessageList.add(executeMessage);
            } catch (IOException e) {
                throw e;
            }catch (Exception e) {
                throw e;
            }
        }

        return executeMessageList;
    }

    public boolean deleteFile(File userCodeFile) {
        if (userCodeFile.getParentFile() != null) {
            String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
            return FileUtil.del(userCodeParentPath);
        }
        return true;
    }

    public ExecuteCodeResponse getResponse(List<ExecuteMessage> messages){
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        JudgeInfo judgeInfo = new JudgeInfo();
        executeCodeResponse.setJudgeInfo(judgeInfo);
        List<String> outputs = new ArrayList<>();

        long maxTime = -1L;
        long maxMemory = -1L;
        for(ExecuteMessage message : messages){
            if(message.getExitValue().intValue()!=0){
                executeCodeResponse.setMessage(ExecuteEnum.RUNTIME_ERROR.getText());
                judgeInfo.setMessage(ExecuteEnum.RUNTIME_ERROR.getText());
                judgeInfo.setTime(maxTime);
                return executeCodeResponse;
            }
            if(!message.getMessage().endsWith("\n")){
                outputs.add(message.getMessage()+"\n");
            }else{
                outputs.add(message.getMessage());
            }

            if(maxTime < message.getTime().longValue()){
                maxTime = message.getTime();
            }

            if(maxMemory < message.getMemory().longValue()){
                maxMemory = message.getMemory();
            }
        }
        executeCodeResponse.setOutputList(outputs);
        executeCodeResponse.setMessage(ExecuteEnum.SUCCESS.getText());
        judgeInfo.setMessage(ExecuteEnum.SUCCESS.getText());
        judgeInfo.setTime(maxTime);
        /* 暂未统计内存占用 */
        judgeInfo.setMemory(maxMemory);

        return executeCodeResponse;
    }

    public ExecuteCodeResponse createErrorResponse(ExecuteEnum errorEnum){
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage(errorEnum.getText());
        return executeCodeResponse;
    }

    public String createCompileCmd(String filePath){
        throw new UnsupportedOperationException("This method must be overridden by subclasses.");
    }

    public String createRunCmd(String path){
        throw new UnsupportedOperationException("This method must be overridden by subclasses.");
    }

    public String codeFileName(){
        throw new UnsupportedOperationException("This method must be overridden by subclasses.");
    }
}
