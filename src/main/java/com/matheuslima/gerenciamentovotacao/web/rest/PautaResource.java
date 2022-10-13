package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.ResponseCreatedDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import com.matheuslima.gerenciamentovotacao.web.operations.PautaOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PautaResource implements PautaOperations {
    private final PautaService service;

    @Override
    public ResponseEntity<ResponseCreatedDTO> cadastrar(PautaDTO pautaDTO){
        log.debug("Requisição rest para cadastrar uma pauta: {}", pautaDTO);
        ResponseCreatedDTO body =service.cadastrar(pautaDTO);
        return ResponseEntity.created(URI.create("/api/pautas")).body(body);
    }

    @Override
    public ResponseEntity<Void> habilitarSessao(SessaoDTO sessaoDTO){
        log.debug("Requisição rest para habilitar uma sessão: {}", sessaoDTO);
        service.habilitarSessao(sessaoDTO);
        return ResponseEntity.ok().build();
    }
}
