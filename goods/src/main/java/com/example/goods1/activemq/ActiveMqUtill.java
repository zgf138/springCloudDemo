package com.example.goods1.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Destination;


@Component
public class ActiveMqUtill {

    @Autowired
    private JmsMessagingTemplate jsmMessagingTemplate;

    @Autowired
    private static JmsMessagingTemplate jsmMessagingTemplate1;

    @PostConstruct//静态方法中应用实例bean，这个是java中在servlet实例化是创建
    public void init() {
        jsmMessagingTemplate1 = jsmMessagingTemplate;
    }

    public <T> boolean sendMess(T contents, Destination destination){
        try {
            jsmMessagingTemplate1.convertAndSend(destination, contents);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
