package com.matheuslima.gerenciamentovotacao.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheuslima.gerenciamentovotacao.service.ResultadoService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResultadoQueueConsumer {

    @Autowired
    private ResultadoService resultadoService;

    @RabbitListener(queues = {"${queue.name}"})
    public void consumir(@Payload String mensagem) throws JsonProcessingException {
        log.info("Consumindo mensagem {}", mensagem);
        resultadoService.cadastrar(obterResultadoDaMensagem(mensagem));
    }

    private ResultadoDTO obterResultadoDaMensagem(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(message, ResultadoDTO.class);
    }
}