package com.matheuslima.gerenciamentovotacao.service;

import com.matheuslima.gerenciamentovotacao.domain.Voto;
import com.matheuslima.gerenciamentovotacao.domain.VotoEnum;
import com.matheuslima.gerenciamentovotacao.repository.VotoRepository;
import com.matheuslima.gerenciamentovotacao.service.dto.ResponseCreatedDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.UserDTO;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import com.matheuslima.gerenciamentovotacao.service.feign.UsersClient;
import com.matheuslima.gerenciamentovotacao.service.mapper.VotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.matheuslima.gerenciamentovotacao.service.utils.CpfValidoUtils.UNABLE_TO_VOTE;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.STATUS_NULO;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.STATUS_UNABLE;
import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.VOTO_INVALIDO;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository repository;
    private final PautaService pautaService;
    private final VotoMapper mapper;
    private final UsersClient usersClient;

    public ResponseCreatedDTO cadastrar(VotoDTO votoDTO){
        pautaService.validarPauta(votoDTO.getPautaId());
        pautaService.validarDuplicidadeVoto(votoDTO.getPautaId(), votoDTO.getCpfAssociado());
        validarCpfStatus(Objects.requireNonNull(usersClient.obterCpfStatus(votoDTO.getCpfAssociado()).getBody()));
        Voto voto = mapper.toEntity(votoDTO);
        voto.setVoto(getVotoEnum(votoDTO.getVoto()));
        return mapper.toResponse(repository.save(voto));
    }

    private void validarCpfStatus(UserDTO userDTO){
        if(Objects.isNull(userDTO.getStatus())){
            throw new RegraNegocioException(STATUS_NULO);
        }else {
            validarCpfHabilitado(userDTO.getStatus());
        }
    }

    private void validarCpfHabilitado(String status){
        if(status.equals(UNABLE_TO_VOTE)){
            throw new RegraNegocioException(STATUS_UNABLE);
        }
    }

    private VotoEnum getVotoEnum(String voto){
        VotoEnum votoEnum;
        try {
            votoEnum = VotoEnum.of(voto);
        } catch (IllegalArgumentException e){
            throw new RegraNegocioException(VOTO_INVALIDO);
        }
        return votoEnum;
    }

}
