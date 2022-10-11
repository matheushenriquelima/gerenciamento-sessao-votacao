package com.matheuslima.gerenciamentovotacao.web.operations;

import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("${api.version}/votos")
public interface VotoOperations {

    @Operation(summary = "Cadastra um voto na pauta do id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description  = "O Cadastro do voto foi feito com sucesso"),
            @ApiResponse(responseCode = "400", description  = "Houve algum erro de regra de negócio"),
            @ApiResponse(responseCode = "500", description  = "Foi gerada uma exceção")
    })
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    ResponseEntity<Void> cadastrar(@Valid @RequestBody VotoDTO votoDTO);
}
