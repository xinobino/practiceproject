package com.lin.practiceproject.controller;

import com.lin.practiceproject.mq.comsumer.ActiveMQConsume;
import com.lin.practiceproject.mq.producer.ActiveMQProduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.TextMessage;

@RequestMapping(value = "/mq")
@RestController
public class MqController {

    @Autowired
    ActiveMQProduce activeMQProduce;

    @Autowired
    ActiveMQConsume activeMQConsume;

    @RequestMapping(value = "/activeMQ/sendMsg",method = RequestMethod.GET)
    public String activeMQSendMsg(String data) {
        try{
            activeMQProduce.produceMessage(data);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }


    @RequestMapping(value = "/activeMQ/consumeMsg",method = RequestMethod.GET)
    public String activeMQSendMsg() {
        TextMessage data;
        try{
            data = activeMQConsume.consumeMessage(1000);
            if(data != null){
                return data.getText();
            }
            return "暂无数据";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
