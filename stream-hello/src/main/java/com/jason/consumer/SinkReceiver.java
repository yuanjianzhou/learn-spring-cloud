package com.jason.consumer;

import com.jason.producer.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by jason on 2019/4/16.
 */
@EnableBinding(Topic.class)
public class SinkReceiver {
    private static Logger log = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Topic.INPUT)
    public void receive(Object payload){
        log.info("Received: "+ payload);
    }
}
