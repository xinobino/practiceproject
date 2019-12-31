package com.lin.practiceproject.mq.comsumer;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * activeMQ 消息消费者
 */
public interface ActiveMQConsume {

    /**
     * 从MQ中获取一条消息并消费
     */
    TextMessage consumeMessage(int number) throws JMSException;

}
