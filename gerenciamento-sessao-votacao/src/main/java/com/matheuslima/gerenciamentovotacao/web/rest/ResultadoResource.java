package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.service.ResultadoService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import com.matheuslima.gerenciamentovotacao.web.operations.ResultadoOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ResultadoResource implements ResultadoOperations {
    private final ResultadoService service;

    @Override
    public ResponseEntity<ResultadoDTO> obterPorPautaId(Long pautaId){
        log.debug("Requisição rest para habilitar uma sessão: {}", pautaId);
        return ResponseEntity.ok(service.obterPorPautaId(pautaId));
    }
}
