package com.example.kafka;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration implements InitializingBean {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        String logicTopicName = "basic-log";
        System.setProperty("topicName", logicTopicName);
        logger.info("#########  system config topic:{} ########", logicTopicName);
    }

    public static void main(String[] args) {
        for (int i = 10; i <100; i++) {
            logger.info("kafkaProducer---" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}