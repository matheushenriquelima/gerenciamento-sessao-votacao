package com.matheuslima.gerenciamentovotacao.unit;

import com.matheuslima.gerenciamentovotacao.builder.PautaBuilder;
import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.repository.PautaRepository;
import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.RegraNegocioException;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.Optional;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_ENVIADA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ATIVA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.PAUTA_NAO_ENCONTRADA;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.VOTO_DUPLICIDADE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PautaServiceTests {

    @MockBean
    private PautaRepository repository;

    @Autowired
    private PautaService service;

    @Test
    @DisplayName("Deve cadastrar pauta com sucesso")
    public void deveSalvarPauta(){
        service.cadastrar(getPautaDTO());
        Mockito.verify(repository, Mockito.times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Deve atualizar pauta com sucesso")
    public void deveAtualizarPauta(){
        service.atualizar(getPauta());
        Mockito.verify(repository, Mockito.times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Deve habilitar sessão com sucesso")
    public void deveHabilitarSessao(){
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(getPauta()));
        service.habilitarSessao(getSessao());
        Mockito.verify(repository, Mockito.times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Deve emitir excecão quando a pauta já foi enviada")
    public void deveEmitirExcecaoPautaEnviada(){
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(getPautaEnviada()));
        SessaoDTO sessaoDTO = getSessao();
        String message = MessageFormat.format(PAUTA_ENVIADA, sessaoDTO.getPautaId());
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.habilitarSessao(getSessao());
        });
        assertEquals(regraNegocioException.getMessage(), message);
    }

    @Test
    @DisplayName("Deve emitir excecão quando a pauta não foi encontrada")
    public void deveEmitirExcecaoPautaNaoEncontrada(){
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());
        SessaoDTO sessaoDTO = getSessao();
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.habilitarSessao(getSessao());
        });
        assertEquals(regraNegocioException.getMessage(), PAUTA_NAO_ENCONTRADA);
    }

    @Test
    @DisplayName("Deve emitir excecão quando a já existe o mesmo cpf em um voto de mesma pauta")
    public void deveEmitirExcecaoDuplicidadeVoto(){
        Mockito.when(repository.existeVotoCpfEmPauta(anyLong(), anyString())).thenReturn(Boolean.TRUE);
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.validarDuplicidadeVoto(1L, "11111111111");
        });
        assertEquals(regraNegocioException.getMessage(), VOTO_DUPLICIDADE);
    }

    @Test
    @DisplayName("Deve emitir excecão quando a pauta está inativa")
    public void deveEmitirExcecaoPautaInativa(){
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(getPautaInativa()));
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.validarPauta(1L);
        });
        assertEquals(regraNegocioException.getMessage(), PAUTA_NAO_ATIVA);
    }

    private PautaDTO getPautaDTO(){
        return PautaBuilder.construirDTO();
    }

    private Pauta getPauta(){
        return PautaBuilder.construirEntidade();
    }

    private SessaoDTO getSessao(){
        return PautaBuilder.construirSessao();
    }

    private Pauta getPautaEnviada(){
        Pauta pauta = getPauta();
        pauta.setEnviado(Boolean.TRUE);
        return pauta;
    }

    private Pauta getPautaInativa(){
        Pauta pauta = getPauta();
        pauta.setAtivo(Boolean.FALSE);
        return pauta;
    }
}
