package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.builder.VotoBuilder;
import com.matheuslima.gerenciamentovotacao.service.VotoService;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
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
public class VotoResourceIT {

    private static final String VOTO_URL = "/v1/votos";

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private VotoService service;

    @Test
    @DisplayName("Deve cadastrar voto com sucesso")
    public void deveCadastrarVotoComSucesso() throws Exception {

        mockmvc.perform(MockMvcRequestBuilders.post(VOTO_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(getVoto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deve lançar erro ao enviar voto sem voto")
    public void deveLançarErroAoEnviarPautaSemVoto() throws Exception {
        VotoDTO voto = getVoto();
        voto.setVoto(null);
        mockmvc.perform(MockMvcRequestBuilders.post(VOTO_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(voto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro ao enviar voto sem cpf associado")
    public void deveLançarErroAoEnviarPautaSemCpfAssociado() throws Exception {
        VotoDTO voto = getVoto();
        voto.setCpfAssociado(null);
        mockmvc.perform(MockMvcRequestBuilders.post(VOTO_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(voto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro ao enviar voto sem pauta id")
    public void deveLançarErroAoEnviarPautaSemPautaId() throws Exception {
        VotoDTO voto = getVoto();
        voto.setPautaId(null);
        mockmvc.perform(MockMvcRequestBuilders.post(VOTO_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(voto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro ao enviar voto sem pauta id")
    public void deveLançarErroAoEnviarPautaSemCpfValido() throws Exception {
        VotoDTO voto = getVoto();
        voto.setCpfAssociado("11111111111");
        mockmvc.perform(MockMvcRequestBuilders.post(VOTO_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .content(TesteUtil.convertObjectToJsonBytes(voto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private VotoDTO getVoto(){
        return VotoBuilder.construirDTO();
    }
}
