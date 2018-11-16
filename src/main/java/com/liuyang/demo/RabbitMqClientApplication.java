package com.liuyang.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RabbitMqClientApplication {

    @Autowired
    private HelloClient helloClient;

    @Bean
    public Queue helloQueue() {
        return new Queue("hello-queue");
    }

    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            helloClient.send("liuyang");
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

//    @PostConstruct
//    public void init(){
//
//    }
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqClientApplication.class, args);
    }
}

