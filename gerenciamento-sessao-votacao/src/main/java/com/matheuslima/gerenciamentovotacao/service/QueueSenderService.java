package com.matheuslima.gerenciamentovotacao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueSenderService {
    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;

    public void enviarResultadoParaRabbit(ResultadoDTO resultadoDTO){
        try {
            String json = new ObjectMapper().writeValueAsString(resultadoDTO);
            rabbitTemplate.convertAndSend(exchange.getName(), "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
