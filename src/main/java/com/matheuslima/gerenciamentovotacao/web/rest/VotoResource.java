package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.service.VotoService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResponseCreatedDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import com.matheuslima.gerenciamentovotacao.web.operations.VotoOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VotoResource implements VotoOperations {
    private final VotoService service;

    @Override
    public ResponseEntity<ResponseCreatedDTO> cadastrar(VotoDTO votoDTO){
        log.debug("Requisição rest para cadastrar voto em uma pauta: {}", votoDTO);
        ResponseCreatedDTO body = service.cadastrar(votoDTO);
        return ResponseEntity.created(URI.create("/api/votos"))
                .body(body);
    }
}
