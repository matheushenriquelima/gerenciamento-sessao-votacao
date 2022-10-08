package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDTO {

    private Long pautaId;
    private String pautaNome;
    private Long votosSim;
    private Long votosNao;
}
