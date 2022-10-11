package com.matheuslima.gerenciamentovotacao.service.mapper;

import com.matheuslima.gerenciamentovotacao.domain.Voto;
import com.matheuslima.gerenciamentovotacao.service.dto.VotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "voto", ignore = true)
    @Mapping(source = "pautaId", target = "pauta.id")
    Voto toEntity(VotoDTO votoDTO);
}
