package com.lin.practiceproject.mq.comsumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * activeMQ 消息消费者
 */
@Service
public class ActiveMQConsumeImpl implements ActiveMQConsume {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKERURL = "tcp://47.94.94.163:61617";

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 从MQ中获取一条消息并消费
     */
    public TextMessage consumeMessage(int number) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer;
        try{
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("activeMQ");
            messageConsumer = session.createConsumer(destination);
            // 接收消息
            TextMessage textMessage = (TextMessage) messageConsumer.receive(number);
            session.commit();
            return textMessage;
        }finally {
            if(null != connection){
                connection.close();
            }
        }
    }

}
