package com.matheuslima.gerenciamentovotacao.web.operations;

import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

@RequestMapping("/api/resultados")
public interface ResultadoOperations {

    @Operation(summary = "Obtem o resultado da pauta informado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description  = "O resultado foi obtido com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResultadoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description  = "Houve algum erro de regra de negócio", content = @Content),
            @ApiResponse(responseCode = "500", description  = "Foi gerada uma exceção", content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    ResponseEntity<ResultadoDTO> obterPorPautaId(@PathParam("pautaId") Long pautaId);
}
