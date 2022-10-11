package com.matheuslima.gerenciamentovotacao.schedule;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.domain.VotoEnum;
import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.QueueSenderService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class PautaSchedule {
    private static final String RESULTADO_PATTERN = "A pauta {0} de número {1}, obteve {2} voto(s) SIM e {3} voto(s) NÃO.";
    private final PautaService service;
    private final QueueSenderService senderService;

    @Scheduled(fixedDelay = 1000, initialDelay = 60000)
    public void fecharPautas() {
        service.obterPautasAtivas().stream()
                .filter(this::validarPauta).forEach(pauta -> {
                    log.info("Enviado resultado da pauta {}", pauta.getId());
                    ResultadoDTO resultadoDTO = new ResultadoDTO(pauta.getId(), pauta.getTitulo(), obterVotosSim(pauta), obterVotosNao(pauta));
                    senderService.enviarResultadoParaRabbit(resultadoDTO);
                    atualizarPauta(pauta);
                });
    }

    private boolean validarPauta(Pauta pauta){
        return !pauta.getEnviado() && validarTempo(pauta.getTempoDeterminado());
    }

    private boolean validarTempo(LocalDateTime tempo){
        return LocalDateTime.now().isAfter(tempo);
    }

    private void atualizarPauta(Pauta pauta) {
        pauta.setAtivo(Boolean.FALSE);
        pauta.setEnviado(Boolean.TRUE);
        log.info("Atualizando pauta {}", pauta.getId());
        service.atualizar(pauta);
    }

    private Long obterVotosSim(Pauta pauta){
        return pauta.getVotos().stream().filter(voto -> voto.getVoto().equals(VotoEnum.SIM)).count();
    }

    private Long obterVotosNao(Pauta pauta){
        return pauta.getVotos().stream().filter(voto -> voto.getVoto().equals(VotoEnum.NAO)).count();
    }
}
