package com.matheuslima.gerenciamentovotacao.builder;

import com.matheuslima.gerenciamentovotacao.domain.Resultado;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;

public class ResultadoBuilder {

    public static Resultado construirEntidade() {
        Resultado resultado = new Resultado();
        resultado.setId(1L);
        resultado.setVotosSim(1L);
        resultado.setVotosNao(1L);
        resultado.setPauta(PautaBuilder.construirEntidade());
        return resultado;
    }

    public static ResultadoDTO construirDTO() {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setPautaId(1L);
        resultadoDTO.setVotosSim(1L);
        resultadoDTO.setVotosNao(1L);
        resultadoDTO.setPautaNome("Titulo");
        return resultadoDTO;
    }
}
