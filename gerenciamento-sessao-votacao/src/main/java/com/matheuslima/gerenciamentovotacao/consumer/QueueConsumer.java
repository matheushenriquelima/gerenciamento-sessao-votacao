package com.matheuslima.gerenciamentovotacao.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Payload;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        //TODO change to log
        System.out.println("Message " + fileBody);
    }
}