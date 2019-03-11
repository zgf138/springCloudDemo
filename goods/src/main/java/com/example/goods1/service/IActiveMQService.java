package com.example.goods1.service;

import javax.jms.JMSException;

public interface IActiveMQService {
    void sendMessage(String msg) throws JMSException;

    String consumeMessage() throws JMSException;
}
