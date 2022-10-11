package com.matheuslima.gerenciamentovotacao.builder;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;

public class PautaBuilder {

    public static Pauta construirEntidade() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Titulo");
        pauta.setDescricao("Descrição");
        return pauta;
    }

    public static PautaDTO construirDTO() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setTitulo("Titulo");
        pautaDTO.setDescricao("Descrição");
        return pautaDTO;
    }

    public static SessaoDTO construirSessao(){
        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setPautaId(1L);
        sessaoDTO.setTempoMinutos(2);
        return sessaoDTO;
    }
}
