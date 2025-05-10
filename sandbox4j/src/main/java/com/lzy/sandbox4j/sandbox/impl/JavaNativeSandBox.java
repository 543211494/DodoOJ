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

@Component
public class JavaNativeSandBox implements CodeSandBox {

    private static final String GLOBAL_CODE_DIR_NAME = "tempcode";

    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    private static final long TIME_LIMIT = 5000L;


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        /* 保存代码 */
        File file = this.saveCode(executeCodeRequest.getCode());

        /* 编译代码 */
        ExecuteMessage compileMessage = this.compile(file);
        if(compileMessage.getExitValue()!=0){
            ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
            executeCodeResponse.setMessage(ExecuteEnum.COMPILE_ERROR.getText());
            this.deleteFile(file);
            return executeCodeResponse;
        }

        /* 运行代码 */
        List<ExecuteMessage> executeMessages = null;
        try {
            executeMessages = this.runCode(file, executeCodeRequest.getInputList());
        } catch (IOException e) {
            ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
            executeCodeResponse.setMessage(ExecuteEnum.TIME_LIMIT_EXCEEDED.getText());
            this.deleteFile(file);
            return executeCodeResponse;
        } catch (InterruptedException e) {
            ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
            executeCodeResponse.setMessage(ExecuteEnum.SYSTEM_ERROR.getText());
            this.deleteFile(file);
            return executeCodeResponse;
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
        String userCodePath = globalDir + File.separator + UUID.randomUUID() + File.separator + GLOBAL_JAVA_CLASS_NAME;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    public ExecuteMessage compile(File codeFile){
        String compileCmd = String.format("javac -encoding utf-8 %s", codeFile.getAbsolutePath());
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
        for(String input : inputs){
            String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main", path);
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

        long maxTime = 0L;
        for(ExecuteMessage message : messages){
            if(message.getExitValue().intValue()!=0){
                executeCodeResponse.setMessage(message.getErrorMessage());
                judgeInfo.setMessage(ExecuteEnum.RUNTIME_ERROR.getText());
                judgeInfo.setTime(maxTime);
                return executeCodeResponse;
            }
            outputs.add(message.getMessage()+"\n");

            if(maxTime < message.getTime().longValue()){
                maxTime = message.getTime();
            }
        }
        executeCodeResponse.setOutputList(outputs);
        executeCodeResponse.setMessage(ExecuteEnum.SUCCESS.getText());
        judgeInfo.setMessage(ExecuteEnum.SUCCESS.getText());
        judgeInfo.setTime(maxTime);

        return executeCodeResponse;
    }
}
