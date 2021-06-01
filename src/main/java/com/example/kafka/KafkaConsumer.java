package com.example.kafka;

import com.example.kafka.config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
@EnableKafka
public class KafkaConsumer {
    @Autowired
    private KafkaConsumerConfig config;




//    @KafkaListener(id = "app.1", topics = "Topick_1")
//    public void listen(String message) {
//        System.out.println("Consuming the message: " + message);
//    }

    public Consumer consumeCreate() {
        Consumer<Long, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(config.consumerConfigs());
        consumer.subscribe(Collections.singletonList("Topick_1"));

        return consumer;
    }

    @KafkaListener(id = "app.1", topics = "Topick_1")
    public void runListen() {
        Consumer<Long, String> consumer = consumeCreate();
        ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

        consumerRecords.forEach(rec -> {
            System.out.println("Key = " + rec.key());
            System.out.println("Value = " + rec.value());
        });
    }
}
