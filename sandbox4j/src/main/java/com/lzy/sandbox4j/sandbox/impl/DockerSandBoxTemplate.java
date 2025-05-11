package com.lzy.sandbox4j.sandbox.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.lzy.sandbox4j.enums.ExecuteEnum;
import com.lzy.sandbox4j.sandbox.dto.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DockerSandBoxTemplate extends SandBoxTemplate{

    protected static DockerClient dockerClient;

    static {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .build();
        dockerClient = DockerClientImpl.getInstance(config, httpClient);
    }

    public String imageName(){
        throw new UnsupportedOperationException("This method must be overridden by subclasses.");
    }

    @Override
    public ExecuteMessage compile(File codeFile){
        String image = imageName();
        this.downloadImage(image);
        String containerId = this.createAndStartContainer(image);

        String[] files = codeFile.getAbsolutePath().split(File.separator);
        String path = File.separator + "app" + File.separator + files[files.length-2] + File.separator + files[files.length-1];
        String compileCmd = createCompileCmd(path);
        ExecuteMessage executeMessage = this.executeCmd(containerId,compileCmd,null);
        return executeMessage;
    }

    @Override
    public List<ExecuteMessage> runCode(File codeFile, List<String> inputs){
        String image = imageName();
        this.downloadImage(image);
        String containerId = this.createAndStartContainer(image);

        List<ExecuteMessage> messages = new ArrayList<>();
        String[] files = codeFile.getAbsolutePath().split(File.separator);
        String path = File.separator + "app" + File.separator + files[files.length-2];
        String runCmd = this.createRunCmd(path);
        for(String input : inputs){
            ExecuteMessage executeMessage = this.executeCmd(containerId, runCmd, input);
            messages.add(executeMessage);
            if(executeMessage.getExitValue().intValue()!=0){
                break;
            }

        }
        return messages;
    }

    public ExecuteMessage executeCmd(String containerId, String cmd,String input){
        String[] cmdArray = cmd.split(" ");
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withCmd(cmdArray)
                .withAttachStderr(true)
                .withAttachStdin(true)
                .withAttachStdout(true)
                .exec();
        //System.out.println("创建执行命令：" + execCreateCmdResponse);

        ExecuteMessage executeMessage = new ExecuteMessage();
        final String[] message = {null};
        final String[] errorMessage = {null};
        long time = 0L;
        // 判断是否超时
        final boolean[] timeout = {true};
        String execId = execCreateCmdResponse.getId();
        ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback() {
            @Override
            public void onComplete() {
                // 如果执行完成，则表示没超时
                timeout[0] = false;
                super.onComplete();
            }

            @Override
            public void onNext(Frame frame) {
                StreamType streamType = frame.getStreamType();
                if (StreamType.STDERR.equals(streamType)) {
                    errorMessage[0] = new String(frame.getPayload());
                    //System.out.println("输出错误结果：" + errorMessage[0]);
                } else {
                    message[0] = new String(frame.getPayload());
                    //System.out.println("输出结果：" + message[0]);
                }
                super.onNext(frame);
            }
        };

        final long[] maxMemory = {0L};

        // 获取占用的内存
        StatsCmd statsCmd = dockerClient.statsCmd(containerId);
        ResultCallback<Statistics> statisticsResultCallback = statsCmd.exec(new ResultCallback<Statistics>() {

            @Override
            public void onNext(Statistics statistics) {
                //System.out.println("内存占用：" + statistics.getMemoryStats().getUsage());
                maxMemory[0] = Math.max(statistics.getMemoryStats().getUsage(), maxMemory[0]);
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public void onStart(Closeable closeable) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        statsCmd.exec(statisticsResultCallback);
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            if(input!=null){
                dockerClient.execStartCmd(execId)
                        .withStdIn(new ByteArrayInputStream(input.getBytes()))
                        .exec(execStartResultCallback)
                        .awaitCompletion(TIME_LIMIT, TimeUnit.MILLISECONDS);
            }else{
                dockerClient.execStartCmd(execId)
                        .exec(execStartResultCallback)
                        .awaitCompletion(TIME_LIMIT, TimeUnit.MILLISECONDS);
            }
            stopWatch.stop();
            time = stopWatch.getLastTaskTimeMillis();
            statsCmd.close();
        } catch (InterruptedException e) {
            System.out.println("程序执行异常");
            throw new RuntimeException(e);
        }
        executeMessage.setMessage(message[0]);
        executeMessage.setErrorMessage(errorMessage[0]);
        executeMessage.setTime(time);
        executeMessage.setMemory(maxMemory[0]/1024);
        if(errorMessage[0]!=null){
            executeMessage.setExitValue(-1);
        }else{
            executeMessage.setExitValue(0);
        }
        if(timeout[0]){
            executeMessage.setExitValue(-1);
            executeMessage.setErrorMessage(ExecuteEnum.TIME_LIMIT_EXCEEDED.getText());
        }
//        System.out.println(executeMessage);
        return executeMessage;
    }

    public boolean downloadImage(String image){
        /* 如果已经有镜像就不下载了 */
        List<Image> images = dockerClient.listImagesCmd().exec();
        for(Image ietm : images){
            if(ietm.getRepoTags()[0].equals(image)){
                return true;
            }
        }
        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
        /* 镜像拉取回调 */
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
            @Override
            public void onNext(PullResponseItem item) {
//                System.out.println("下载镜像：" + item.getStatus());
                super.onNext(item);
            }
        };
        try {
            pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String createAndStartContainer(String image){
        /* 有正在运行的镜像就不创建新的了 */
        List<Container> containers = dockerClient.listContainersCmd().exec();
        for(Container container : containers){
            if(container.getImage().equals(image)){
                if(container.getState().equals("running")){
                    return container.getId();
                }
            }
        }

        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
        String globalDir = System.getProperty("user.dir") + File.separator + GLOBAL_CODE_DIR_NAME;
        HostConfig hostConfig = new HostConfig();
        hostConfig.withMemory(100 * 1000 * 1000L);
        hostConfig.withMemorySwap(0L);
        hostConfig.withCpuCount(1L);
//        hostConfig.withSecurityOpts(Arrays.asList("seccomp=安全管理配置字符串"));
        hostConfig.setBinds(new Bind(globalDir, new Volume("/app")));
        CreateContainerResponse createContainerResponse = containerCmd
                .withHostConfig(hostConfig)
                .withNetworkDisabled(true)
                .withReadonlyRootfs(true)
                .withAttachStdin(true)
                .withAttachStderr(true)
                .withAttachStdout(true)
                .withTty(true)
                .exec();
        System.out.println("abc"+createContainerResponse);
        String containerId = createContainerResponse.getId();
        dockerClient.startContainerCmd(containerId).exec();
        return containerId;
    }
}
