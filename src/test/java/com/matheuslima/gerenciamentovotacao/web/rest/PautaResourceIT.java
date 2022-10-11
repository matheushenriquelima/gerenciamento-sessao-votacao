package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.builder.PautaBuilder;
import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.SessaoDTO;
import com.matheuslima.gerenciamentovotacao.util.TesteUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PautaResourceIT {

    private static final String PAUTA_URL = "/v1/pautas";
    private static final String HABILITAR_SESSAO = PAUTA_URL.concat("/habilitar-sessao");

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private PautaService service;

    @Test
    @DisplayName("Deve cadastrar pauta com sucesso")
    public void deveCadastrarPautaComSucesso() throws Exception {

        mockmvc.perform(MockMvcRequestBuilders.post(PAUTA_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(getPauta())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deve lançar erro ao enviar pauta sem titulo")
    public void deveLançarErroAoEnviarPautaSemTitulo() throws Exception {
        PautaDTO pautaDTO = getPauta();
        pautaDTO.setTitulo(null);
        mockmvc.perform(MockMvcRequestBuilders.post(PAUTA_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(pautaDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve habilitar sessão com sucesso")
    public void deveHabilitarSessaoComSucesso() throws Exception {

        mockmvc.perform(MockMvcRequestBuilders.post(HABILITAR_SESSAO)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(getSessao())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve habilitar sessão com erro quando não informar pauta id")
    public void deveObterErroAoHabilitarSessaoSemPauta() throws Exception {
        SessaoDTO sessaoDTO = getSessao();
        sessaoDTO.setPautaId(null);
        mockmvc.perform(MockMvcRequestBuilders.post(HABILITAR_SESSAO)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(sessaoDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private PautaDTO getPauta(){
        return PautaBuilder.construirDTO();
    }

    private SessaoDTO getSessao(){
        return PautaBuilder.construirSessao();
    }
}
