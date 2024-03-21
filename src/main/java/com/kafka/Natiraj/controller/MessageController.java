package com.kafka.Natiraj.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Natiraj.producer.KafkaProducer;


@RestController
//@RequestMapping("/save")
public class MessageController {



    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


@GetMapping("/publish")
public ResponseEntity<String> publish(@RequestParam("message") String message) {
    if (kafkaProducer != null) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to the topic");
    } else {
        return ResponseEntity.status(500).body("KafkaProducer is not initialized");
    }
}
}