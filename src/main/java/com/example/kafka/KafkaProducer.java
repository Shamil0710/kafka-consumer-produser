package com.example.kafka;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
@EnableKafka
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void run() {
        Long key = 0l;
        for (int i = 0; i < 10000; i++) {
            key++;
            ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("Topick_1", key, String.valueOf(i));
//            future.addCallback(System.out::println, System.err::println);
        }
    }
}
