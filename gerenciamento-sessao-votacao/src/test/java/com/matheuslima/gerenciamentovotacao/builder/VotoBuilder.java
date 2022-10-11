package com.matheuslima.gerenciamentovotacao.builder;

import com.matheuslima.gerenciamentovotacao.domain.Voto;
import com.matheuslima.gerenciamentovotacao.domain.VotoEnum;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;

public class VotoBuilder {

    public static Voto construirEntidade() {
        Voto voto = new Voto();
        voto.setId(1L);
        voto.setVoto(VotoEnum.SIM);
        voto.setPauta(null);
        voto.setCpfAssociado("85670414063");
        return voto;
    }

    public static VotoDTO construirDTO() {
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setPautaId(1L);
        votoDTO.setCpfAssociado("85670414063");
        votoDTO.setVoto("Sim");
        return votoDTO;
    }
}
