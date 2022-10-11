package com.matheuslima.gerenciamentovotacao.web.operations;

import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("${api.version}/pautas")
public interface PautaOperations {

    @Operation(summary = "Cadastra uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description  = "O Cadastro da pauta foi feito com sucesso"),
            @ApiResponse(responseCode = "400", description  = "Houve algum erro de regra de negócio"),
            @ApiResponse(responseCode = "500", description  = "Foi gerada uma exceção")
    })
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    ResponseEntity<Void> cadastrar(@Valid @RequestBody PautaDTO pautaDTO);

    @Operation(summary = "Habilita uma sessão de votação na pauta do id informado e com o tempo informado, caso não informe o tempo ele será de 1 minuto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description  = "A sessão foi habilitada com sucesso"),
            @ApiResponse(responseCode = "400", description  = "Houve algum erro de regra de negócio"),
            @ApiResponse(responseCode = "500", description  = "Foi gerada uma exceção")
    })
    @RequestMapping(value = "/habilitar-sessao", method = RequestMethod.POST, consumes="application/json")
    ResponseEntity<Void> habilitarSessao(@Valid @RequestBody SessaoDTO sessaoDTO);
}
