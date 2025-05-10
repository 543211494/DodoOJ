package com.lzy.sandbox4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        //ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
//        String[] names = run.getBeanNamesForType(RedisTemplate.class);
//        System.out.println("====================");
//        for(String name:names){
//            System.out.println(name);
//        }
    }
}
