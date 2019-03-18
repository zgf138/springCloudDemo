package com.example.goods.service.impl;

import com.example.goods.service.IActiveMQService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class ActiceMQServiceImpl implements IActiveMQService {

    @Override
    public void sendMessage(String msg) throws JMSException {
        //1、创建 ActiveMQ 连接，设置 brokerURL
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        //2、创建 JMS 连接
        Connection connection = connectionFactory.createConnection();

        //3、创建会话 Session（参数一 transacted：是否是事务  参数二：自动确认）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /**
         * 4、创建消息目的:
         * 第一种 Queue：点对点消息
         * 第二种 Topic：广播式消息
         */
        Destination destination = session.createQueue("test");

        //5、创建消息生产者
        MessageProducer messageProducer = session.createProducer(destination);

        //6、创建消息 - 文本消息
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText(msg);
        //7、发送文本消息
        messageProducer.send(message);

        //8、关闭消息发送者、Session、Connection
        messageProducer.close();
        session.close();
        connection.close();
    }

    @Override
    public String consumeMessage() throws JMSException {
        //1、创建 ActiveMQ 连接，设置 brokerURL
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        //2、创建 JMS 连接
        Connection connection = connectionFactory.createConnection();

        //3、启动连接（这里需要手动启动）
        connection.start();

        //4、创建会话 Session（参数一 transacted：是否是事务  参数二：自动确认）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /**
         * 5、创建消息目的:
         * 第一种 Queue：点对点消息
         * 第二种 Topic：广播式消息
         */

        Destination destination = session.createTopic("name");

        //6、创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7、获取消息
        Message message = consumer.receive(100);
        String msg = "";
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("----------------------------------");
            System.out.println("消费消息：" + textMessage.getText());
            msg = textMessage.getText();
        }

        //8、关闭消息发送者、Session、Connection
        consumer.close();
        session.close();
        connection.close();
        return msg;
    }
}
