package com.example.kafka.controllers;

//import com.example.kafka.KafkaConsumer;
import com.example.kafka.KafkaConsumer;
import com.example.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @GetMapping("/")
    @KafkaListener(topics = "Topick_1")
    private String listen() throws InterruptedException {
        kafkaProducer.run();
//        iter();

        return "run";
    }

    @GetMapping("/getlog")
//    @KafkaListener(topics = "Topick_1")
    private String getlog() {

        kafkaConsumer.runListen();

        return "getlog";
    }
}
