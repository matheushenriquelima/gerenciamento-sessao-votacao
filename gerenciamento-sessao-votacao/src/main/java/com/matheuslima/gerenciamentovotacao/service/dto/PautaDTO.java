package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PautaDTO {

    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
}
