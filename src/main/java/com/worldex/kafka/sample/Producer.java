package com.worldex.kafka.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;




    public void sendChannelMessage(String channel, String message) {

        kafkaTemplate.send(channel, message);
    }


}
