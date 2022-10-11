package com.matheuslima.gerenciamentovotacao.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VotoDTO {

    @NotNull(message = "O campo 'pautaId' é obrigatório")
    private Long pautaId;
    @NotNull(message = "O campo 'cpfAssociado' é obrigatório")
    @CPF
    private String cpfAssociado;
    @NotNull(message = "O campo 'voto' é obrigatório")
    private String voto;
}
