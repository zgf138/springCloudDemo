package com.example.goods.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;


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

    public <T> boolean cresteQueue(T contents, String queue) throws JMSException {
        Session session = cresteSession();
        Destination destination =  session.createQueue(queue);
        try {
            jsmMessagingTemplate1.convertAndSend(destination, contents);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public <T> boolean cresteTopic(T contents, String topic) throws JMSException {
        Session session = cresteSession();
        Destination destination =  session.createTopic(topic);
        try {
            jsmMessagingTemplate1.convertAndSend(destination, contents);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Session cresteSession() throws JMSException {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        //2、创建 JMS 连接
        Connection connection = connectionFactory.createConnection();

        //3、启动连接（这里需要手动启动）
        connection.start();

        //4、创建会话 Session（参数一 transacted：是否是事务  参数二：自动确认）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        return session;
    }

}
