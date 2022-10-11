package com.matheuslima.gerenciamentovotacao.unit;

import com.matheuslima.gerenciamentovotacao.builder.ResultadoBuilder;
import com.matheuslima.gerenciamentovotacao.repository.ResultadoRepository;
import com.matheuslima.gerenciamentovotacao.service.RegraNegocioException;
import com.matheuslima.gerenciamentovotacao.service.ResultadoService;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
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

import java.util.Optional;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.RESULTADO_NAO_ENCONTRADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ResultadoServiceTests {

    @MockBean
    private ResultadoRepository repository;

    @Autowired
    private ResultadoService service;

    @Test
    @DisplayName("Deve retornar resultado com sucesso")
    public void deveRetornarResultado(){
        Mockito.when(repository.obterPorPautaId(anyLong())).thenReturn(Optional.of(getResultado()));
        ResultadoDTO resultado = service.obterPorPautaId(1L);
        Assertions.assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar excecão quando resultado não for encontrado")
    public void deveRetornarExcecaoQuandoNaoRetornarResultado(){
        Mockito.when(repository.obterPorPautaId(anyLong())).thenReturn(Optional.empty());
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.obterPorPautaId(1L);
        });
        assertEquals(regraNegocioException.getMessage(), RESULTADO_NAO_ENCONTRADO);
    }

    private static ResultadoDTO getResultado(){
        return ResultadoBuilder.construirDTO();
    }
}
