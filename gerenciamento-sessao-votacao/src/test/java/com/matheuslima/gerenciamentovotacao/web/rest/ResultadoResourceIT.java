package com.matheuslima.gerenciamentovotacao.web.rest;

import com.matheuslima.gerenciamentovotacao.builder.ResultadoBuilder;
import com.matheuslima.gerenciamentovotacao.service.ResultadoService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
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
public class ResultadoResourceIT {

    private static final String RESULTADOS_URL = "/v1/resultados";

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private ResultadoService service;

    @Test
    @DisplayName("Deve obter resultado com sucesso")
    public void deveObterResultadoComSucesso() throws Exception {

        mockmvc.perform(MockMvcRequestBuilders.get(RESULTADOS_URL)
                        .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                        .param("pautaId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static ResultadoDTO getResultado(){
        return ResultadoBuilder.construirDTO();
    }
}
