package com.worldex.kafka.sample.controller;

import com.alibaba.fastjson.JSONObject;
import com.worldex.kafka.sample.KafkaSampleApplication;
import com.worldex.kafka.sample.dto.StudentDTO;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaTestController {

    private final Logger logger = LoggerFactory.getLogger(KafkaTestController.class);

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @RequestMapping("/send/{input}")
    public String sendString(@PathVariable String input) {

        this.template.send("string", input);

        return "SUCCESS";
    }


    /*@RequestMapping("/send/singleObject")
    public String sendSingleObject(@RequestBody StudentDTO studentDTO) {


        String s = JSONObject.toJSONString(studentDTO);
        logger.info("single object jsonString : {}", s);
        this.template.send("singleObject", s);

        return "SUCCESS";
    }*/


    @RequestMapping("/send/singleObject")
    public String sendSingleObject(@RequestBody StudentDTO studentDTO) {


        ProducerRecord<String, StudentDTO> record = new ProducerRecord<>("singleObject", studentDTO);


        logger.info("single object jsonString : {}", record);
        this.template.send("singleObject", record);

        return "SUCCESS";
    }

}
