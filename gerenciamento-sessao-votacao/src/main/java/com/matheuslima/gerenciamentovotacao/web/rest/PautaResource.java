package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pautas")
public class PautaResource {
    private final PautaService service;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody PautaDTO pautaDTO){
        service.cadastrar(pautaDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/habilitar-sessao")
    public ResponseEntity<Void> habilitarSessao(@Valid @RequestBody SessaoDTO sessaoDTO){

        return ResponseEntity.ok().build();
    }
}
