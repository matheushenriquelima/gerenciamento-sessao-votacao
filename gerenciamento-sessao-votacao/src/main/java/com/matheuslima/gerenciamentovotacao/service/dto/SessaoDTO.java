package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SessaoDTO {

    @NotNull
    private Long pautaId;
    private Integer tempoMinutos;
}
