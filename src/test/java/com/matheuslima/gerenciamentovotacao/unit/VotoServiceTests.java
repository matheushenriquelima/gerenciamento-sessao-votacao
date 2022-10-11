package com.matheuslima.gerenciamentovotacao.unit;

import com.matheuslima.gerenciamentovotacao.builder.VotoBuilder;
import com.matheuslima.gerenciamentovotacao.domain.Voto;
import com.matheuslima.gerenciamentovotacao.repository.VotoRepository;
import com.matheuslima.gerenciamentovotacao.service.PautaService;
import com.matheuslima.gerenciamentovotacao.service.RegraNegocioException;
import com.matheuslima.gerenciamentovotacao.service.VotoService;
import com.matheuslima.gerenciamentovotacao.service.dto.UserDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import com.matheuslima.gerenciamentovotacao.service.feign.UsersClient;
import com.matheuslima.gerenciamentovotacao.service.mapper.VotoMapper;
import com.matheuslima.gerenciamentovotacao.service.utils.CpfValidoUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.STATUS_NULO;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.STATUS_UNABLE;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.VOTO_INVALIDO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class VotoServiceTests {

    @MockBean
    private VotoRepository repository;

    @MockBean
    private PautaService pautaService;

    @MockBean
    private UsersClient usersClient;

    @Autowired
    private VotoService service;

    @Autowired
    private VotoMapper mapper;

    @Test
    @DisplayName("Deve cadastrar voto com sucesso")
    public void deveSalvarVoto(){
        Mockito.when(usersClient.obterCpfStatus(anyString())).thenReturn(ResponseEntity.ok(getUserDTO()));

        service.cadastrar(getVotoDTO());
        Mockito.verify(pautaService, Mockito.times(1)).validarPauta(anyLong());
        Mockito.verify(pautaService, Mockito.times(1)).validarDuplicidadeVoto(anyLong(), anyString());
        Mockito.verify(repository, Mockito.times(1)).save(any(Voto.class));
    }

    @Test
    @DisplayName("Deve emitir excecao quando o status do cpf for nulo")
    public void deveEmitirExcecaoCpfStatusNulo(){
        UserDTO userDTO = getUserDTO();
        userDTO.setStatus(null);
        Mockito.when(usersClient.obterCpfStatus(anyString())).thenReturn(ResponseEntity.ok(userDTO));
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.cadastrar(getVotoDTO());
        });
        assertEquals(regraNegocioException.getMessage(), STATUS_NULO);
    }

    @Test
    @DisplayName("Deve emitir excecao quando cpf for desabilitado")
    public void deveEmitirExcecaoCpfDesabilitado(){
        UserDTO userDTO = getUserDTO();
        userDTO.setStatus(CpfValidoUtils.UNABLE_TO_VOTE);
        Mockito.when(usersClient.obterCpfStatus(anyString())).thenReturn(ResponseEntity.ok(userDTO));
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.cadastrar(getVotoDTO());
        });
        assertEquals(regraNegocioException.getMessage(), STATUS_UNABLE);
    }

    @Test
    @DisplayName("Deve emitir excecao quando voto for diferente de Sim ou Não")
    public void deveEmitirExcecaoVotoInvalido(){
        Mockito.when(usersClient.obterCpfStatus(anyString())).thenReturn(ResponseEntity.ok(getUserDTO()));
        VotoDTO votoDTO = getVotoDTO();
        votoDTO.setVoto("Invalido");
        RegraNegocioException regraNegocioException = Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.cadastrar(votoDTO);
        });
        assertEquals(regraNegocioException.getMessage(), VOTO_INVALIDO);
    }

    private VotoDTO getVotoDTO(){
        return VotoBuilder.construirDTO();
    }

    private UserDTO getUserDTO(){
        return VotoBuilder.construirUserDTO();
    }
}
