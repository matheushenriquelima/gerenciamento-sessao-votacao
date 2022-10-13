package com.matheuslima.gerenciamentovotacao.service;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.repository.PautaRepository;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.ResponseCreatedDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import com.matheuslima.gerenciamentovotacao.service.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_ENVIADA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ATIVA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ENCONTRADA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.VOTO_DUPLICIDADE;

@Service
@RequiredArgsConstructor
public class PautaService {
    private final PautaRepository repository;
    private final PautaMapper mapper;

    public ResponseCreatedDTO cadastrar(PautaDTO pautaDTO){
        Pauta pauta = salvar(mapper.toEntity(pautaDTO));
        return mapper.toResponse(pauta);
    }

    public void atualizar(Pauta pauta){
        salvar(pauta);
    }

    private Pauta salvar(Pauta pauta){
        return repository.save(pauta);
    }

    public void habilitarSessao(SessaoDTO sessaoDTO){
        obterPorId(sessaoDTO.getPautaId()).ifPresentOrElse(pauta -> {
            if(!pauta.getEnviado()){
                pauta.setAtivo(Boolean.TRUE);
                pauta.setTempoDeterminado(LocalDateTime.now().plusMinutes(obterMinutos(sessaoDTO.getTempoMinutos())));
                repository.save(pauta);
            }
            else {
                emitirExcecao(MessageFormat.format(PAUTA_ENVIADA, sessaoDTO.getPautaId()));
            }
        }, () -> {
            emitirExcecao(PAUTA_NAO_ENCONTRADA);
        });
    }

    private Optional<Pauta> obterPorId(Long id) {
        return repository.findById(id);
    }

    public List<Pauta> obterPautasAtivas(){
        return repository.obterPautasPorAtivo(Boolean.TRUE);
    }

    public void validarPauta(Long pautaId){
        obterPorId(pautaId).ifPresentOrElse(PautaService::validarPautaAtiva,
                () -> {
                    emitirExcecao(PAUTA_NAO_ENCONTRADA);
                });
    }

    private static void emitirExcecao(String mensagem) {
        throw new RegraNegocioException(mensagem);
    }

    public void validarDuplicidadeVoto(Long pautaId, String cpf){
        Boolean isInvalid = repository.existeVotoCpfEmPauta(pautaId, cpf);
        if(isInvalid){
            emitirExcecao(VOTO_DUPLICIDADE);
        }
    }

    private static void validarPautaAtiva(Pauta pauta) {
        if(!pauta.getAtivo()){
            emitirExcecao(PAUTA_NAO_ATIVA);
        }
    }

    private Integer obterMinutos(Integer minutos){
        return Objects.nonNull(minutos) ? minutos : 1;
    }
}
