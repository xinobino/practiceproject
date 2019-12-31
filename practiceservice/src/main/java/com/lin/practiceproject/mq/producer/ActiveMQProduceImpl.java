package com.lin.practiceproject.mq.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * activeMQ  消息生产者
 */
@Service
public class ActiveMQProduceImpl implements ActiveMQProduce {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

//    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String BROKERURL = "tcp://47.94.94.163:61617";

    /**
     * 定义消息并发送
     */
    public void produceMessage (String data) throws JMSException{

        // 消息中间件的连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        // 连接
        Connection connection = null;
        // 会话
        Session session = null;
        // 消息的目的地
        Destination destination = null;
        // 消息生产者
        MessageProducer messageProducer = null;
        try{
            // 通过连接工厂获取链接
            connection = connectionFactory.createConnection();
            // 步骤 1.创建会话 2.是否启用事务 3.设置自定签收
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            // 创建消息队列
            destination = session.createQueue("activeMQ");
            // 创建一个生产者
            messageProducer = session.createProducer(destination);
            // 设置持久化/非持久化  如果非持久化 MQ 重启后消息被丢失
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 发送消息
            TextMessage textMessage = session.createTextMessage(data);
            messageProducer.send(textMessage);
            // 如果设置了事务 ，就必须提交
            session.commit();
        }finally {
            if(null != connection){
                // 释放链接
                connection.close();
            }

        }
    }


}
