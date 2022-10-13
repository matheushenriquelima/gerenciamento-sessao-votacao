package com.matheuslima.gerenciamentovotacao.service.mapper;

import com.matheuslima.gerenciamentovotacao.service.dto.ResponseCreatedDTO;

public interface ResponseMapper<E> {

    ResponseCreatedDTO toResponse(E entity);
}
