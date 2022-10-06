package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VotoDTO {

    @NotNull
    private Long pautaId;
    @NotNull
    @CPF
    private String cpfAssociado;
    @NotNull
    private String voto;
}
