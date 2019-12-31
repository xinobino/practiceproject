package com.lin.practiceproject.configuation;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;


@Configuration
public class ActiveMQConnection {

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactoryBean (){
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setBrokerURL(brokerUrl);
            return connectionFactory;
        }
}
