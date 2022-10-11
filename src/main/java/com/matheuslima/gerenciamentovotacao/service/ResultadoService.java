package com.matheuslima.gerenciamentovotacao.service;

import com.matheuslima.gerenciamentovotacao.repository.ResultadoRepository;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import com.matheuslima.gerenciamentovotacao.service.mapper.ResultadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.matheuslima.gerenciamentovotacao.service.utils.MessageUtils.RESULTADO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;
    private final ResultadoMapper mapper;

    public void cadastrar(ResultadoDTO resultadoDTO){
        repository.save(mapper.toEntity(resultadoDTO));
    }

    public ResultadoDTO obterPorPautaId(Long pautaId){
        return repository.obterPorPautaId(pautaId).orElseThrow(() -> new RegraNegocioException(RESULTADO_NAO_ENCONTRADO));
    }
}
