package com.worldex.kafka.sample;

import com.worldex.kafka.sample.dto.StudentDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaSampleApplication.class);

    @Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory batchContainerFactory() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(consumerFactory);
        container.setBatchListener(true);
        container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return container;
    }


    @KafkaListener(topics = "testTopic", containerFactory = "batchContainerFactory")
    public void onMessage(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {

        try {

        } catch (Exception e) {

        } finally {
            ack.acknowledge();
        }
    }


    @KafkaListener(topics = "string")
    public void onMessage(String input) {
        logger.info("string value: {}", input);
    }

    /*@KafkaListener(topics = "singleObject")
    public void onMessageSingleObject(String s) {

        logger.info("singleObject value: {}", s);
    }*/

    @KafkaListener(topics = "singleObject")
    public void onMessageSingleObject(ProducerRecord<String, StudentDTO> record) {

        logger.info("singleObject value: {}", record);
    }

}
