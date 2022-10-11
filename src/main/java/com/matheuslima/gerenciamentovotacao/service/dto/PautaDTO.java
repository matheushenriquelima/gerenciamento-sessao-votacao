package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PautaDTO {

    @NotNull(message = "Campo 'título' é obrigatório")
    @Size(max = 100)
    private String titulo;
    @Size(max = 255)
    private String descricao;
}
