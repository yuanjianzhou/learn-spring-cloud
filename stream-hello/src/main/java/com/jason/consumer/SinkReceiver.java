package com.jason.consumer;

import com.jason.producer.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * Created by jason on 2019/4/16.
 */
@EnableBinding(Topic.class)
public class SinkReceiver {
    private static Logger log = LoggerFactory.getLogger(SinkReceiver.class);
    int count = 1;
    @StreamListener(Topic.INPUT)
    public void receive(Object payload){
        //消费失败，导致自动重试
        log.info("Received: "+ payload);
        if (count == 3) {
            count = 1;
            throw new AmqpRejectAndDontRequeueException("tried 3 times failed, send to dlq!");
        } else {
            count ++;
            throw new RuntimeException("Message consumer failed!");
        }
    }

//    //消息消费失败的降级处理逻辑
//    @ServiceActivator(inputChannel = "delay-topic.test.errors")
//    public void error(Message<?> message){
//        log.info("Message consumer failed, call fallback!");
//    }
}
