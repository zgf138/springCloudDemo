package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.15:9092");
        props.put("group.id", "testGroup");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅的topic，多个用逗号隔开
        consumer.subscribe(Arrays.asList("basic-log"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
        //consumer.close();


        /*for (int i = 0; i < 10; i++) {
            System.out.println(String.format("this is %s",i));
        }*/
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "basic-log")
    public void process(String content) {
        //TODO
        System.out.println("监听成功成功");
    }
}
