package com.lzy.sandbox4j.utils;

import cn.hutool.core.util.StrUtil;
import com.lzy.sandbox4j.sandbox.dto.ExecuteMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {

    public static ExecuteMessage runProcess(Process runProcess) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        BufferedReader bufferedReader = null;
        BufferedReader errorBufferedReader = null;
        /* 计时 */
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            /* 执行程序 */
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);

            /* 获取正常输出 */
            bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            List<String> outputStrList = new ArrayList<>();
            String compileOutputLine;
            while ((compileOutputLine = bufferedReader.readLine()) != null) {
                outputStrList.add(compileOutputLine);
            }
            executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));

            /* 读取错误输出 */
            if(exitValue!=0){
                errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                List<String> errorOutputStrList = new ArrayList<>();
                String errorCompileOutputLine;
                while ((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorOutputStrList.add(errorCompileOutputLine);
                }
                executeMessage.setErrorMessage(StringUtils.join(errorOutputStrList, "\n"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(errorBufferedReader!=null){
                    errorBufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            runProcess.destroy();
        }

        stopWatch.stop();
        executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        return executeMessage;
    }

    public static ExecuteMessage runInteractProcess(Process runProcess, String params) throws IOException, InterruptedException {
        ExecuteMessage executeMessage = new ExecuteMessage();

        /* 计时 */
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedReader errorBufferedReader = null;

        try {
            /* 向控制台输入程序 */
            OutputStream outputStream = runProcess.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            if(!params.endsWith("\n")){
                params = params + "\n";
            }
            outputStreamWriter.write(params);
            /* 执行输入的发送 */
            outputStreamWriter.flush();

            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);

            /* 读取正常输出 */
            InputStream inputStream = runProcess.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> outputStrList = new ArrayList<>();
            String compileOutputLine;
            while ((compileOutputLine = bufferedReader.readLine()) != null) {
                outputStrList.add(compileOutputLine);
            }
            executeMessage.setMessage(StringUtils.join(outputStrList,"\n"));

            /* 获取错误信息 */
            if(exitValue!=0){
                InputStream errorStream = runProcess.getErrorStream();
                errorBufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                List<String> errorOutputStrList = new ArrayList<>();
                String errorCompileOutputLine;
                while ((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorOutputStrList.add(errorCompileOutputLine);
                }
                executeMessage.setErrorMessage(StringUtils.join(errorOutputStrList, "\n"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            /* 释放资源 */
            try {
                if(outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(errorBufferedReader!=null){
                    errorBufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            runProcess.destroy();
        }

        stopWatch.stop();
        executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        executeMessage.setMemory(-1L);
        return executeMessage;
    }
}
