package com.matheuslima.gerenciamentovotacao.schedule;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.QueueSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class PautaSchedule {

    private final PautaService service;
    private final QueueSenderService senderService;

    @Scheduled(fixedDelay = 1000)
    public void fecharPautas() {
        service.obterPautasAtivas().stream()
                .filter(pauta -> !pauta.getEnviado()).forEach(pauta -> {
                    senderService.send(pauta.getResultado());
                    pauta.setAtivo(Boolean.FALSE);
                    pauta.setEnviado(Boolean.TRUE);
                    service.atualizar(pauta);
                });
    }

    private boolean validarPauta(Pauta pauta){
        return !pauta.getEnviado() && validarTempo(pauta.getTempoDeterminado());
    }

    private boolean validarTempo(LocalDateTime tempo){
        return LocalDateTime.now().isAfter(tempo);
    }
}
