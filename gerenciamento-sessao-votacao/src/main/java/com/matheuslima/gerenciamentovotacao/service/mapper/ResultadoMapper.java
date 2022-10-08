package com.matheuslima.gerenciamentovotacao.service.mapper;

import com.matheuslima.gerenciamentovotacao.domain.Resultado;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResultadoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "pautaId", target = "pauta.id")
    Resultado toEntity(ResultadoDTO resultadoDTO);
}
