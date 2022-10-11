package com.matheuslima.gerenciamentovotacao.service.mapper;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import com.matheuslima.gerenciamentovotacao.service.dto.PautaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    @Mapping(target = "votos", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "enviado", ignore = true)
    @Mapping(target = "tempoDeterminado", ignore = true)
    @Mapping(target = "id", ignore = true)
    Pauta toEntity(PautaDTO pautaDTO);
}
