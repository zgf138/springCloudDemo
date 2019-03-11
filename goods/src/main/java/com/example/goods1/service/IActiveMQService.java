package com.example.goods1.service;

import javax.jms.JMSException;

public interface IActiveMQService {
    void sendMessage() throws JMSException;

    void consumeMessage() throws JMSException;
}
