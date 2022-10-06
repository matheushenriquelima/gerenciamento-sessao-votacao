package com.matheuslima.gerenciamentovotacao.service;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.repository.PautaRepository;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import com.matheuslima.gerenciamentovotacao.service.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ATIVA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository repository;
    private final PautaMapper mapper;

    public void cadastrar(PautaDTO pautaDTO){
        salvar(mapper.toEntity(pautaDTO));
    }

    public void atualizar(Pauta pauta){
        salvar(pauta);
    }

    private void salvar(Pauta pauta){
        repository.save(pauta);
    }

    public void habilitarSessao(SessaoDTO sessaoDTO){
        repository.findById(sessaoDTO.getPautaId()).ifPresentOrElse(pauta -> {
            pauta.setAtivo(Boolean.TRUE);
            pauta.setTempoDeterminado(LocalDateTime.now().plusMinutes(obterMinutos(sessaoDTO.getTempoMinutos())));
            repository.save(pauta);
        }, () -> {
            throw new RegraNegocioException(PAUTA_NAO_ENCONTRADA);
        });
    }
    public List<Pauta> obterPautasAtivas(){
        return repository.obterPautasPorAtivo(Boolean.TRUE);
    }

    public void validarPauta(Long pautaId){
        repository.findById(pautaId).ifPresentOrElse(pauta -> validarPautaAtiva(pauta),
                () -> {
            throw new RegraNegocioException(PAUTA_NAO_ENCONTRADA);
        });
    }
    private static void validarPautaAtiva(Pauta pauta) {
        if(!pauta.getAtivo()){
            throw new RegraNegocioException(PAUTA_NAO_ATIVA);
        }
    }
    private Integer obterMinutos(Integer minutos){
        return Objects.nonNull(minutos) ? minutos : 1;
    }
}
