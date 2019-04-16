package com.jason.controller;

import com.jason.producer.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2019/4/16.
 */
@RestController
public class ProduceConterllor {
    private static Logger log = LoggerFactory.getLogger(ProduceConterllor.class);
    @Autowired
    private Topic testTopic;

    @GetMapping("/sendMessage")
    public String messageWithMQ(@RequestParam String message) {
        log.info("Send: " + message);
        testTopic.output().send(MessageBuilder.withPayload(message).setHeader("x-delay", 5000).build());
        return "ok";
    }
}
