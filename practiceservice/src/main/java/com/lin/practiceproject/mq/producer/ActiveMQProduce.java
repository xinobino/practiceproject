package com.lin.practiceproject.mq.producer;

import javax.jms.JMSException;

/**
 * activeMQ  消息生产者
 */
public interface ActiveMQProduce {



    /**
     * 定义消息并发送
     */
    void produceMessage(String data) throws JMSException;

}
