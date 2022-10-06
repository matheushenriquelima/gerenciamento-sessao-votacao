package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.service.VotoService;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votos")
public class VotoResource {
    private final VotoService service;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody VotoDTO votoDTO){
        service.cadastrar(votoDTO);
        return ResponseEntity.ok().build();
    }
}
