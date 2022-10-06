package com.matheuslima.gerenciamentovotacao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
@RequiredArgsConstructor
public class QueueSenderService {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void send(String mensagem) {
        rabbitTemplate.convertAndSend(this.queue.getName(), mensagem);
    }
}
