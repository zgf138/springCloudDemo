package com.example.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleConsumer1 {

    @KafkaListener(topics = {"basic-log"})
    public void receive(String message) throws InterruptedException {
        //Thread.sleep(5000);
        System.out.println("app_log--消费消息:" + message);
    }

}
