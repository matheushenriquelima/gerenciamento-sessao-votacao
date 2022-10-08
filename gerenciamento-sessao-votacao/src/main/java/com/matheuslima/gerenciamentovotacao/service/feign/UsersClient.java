package com.matheuslima.gerenciamentovotacao.service.feign;

import com.matheuslima.gerenciamentovotacao.service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "x-users",url = "${application.feign.url-users}")
public interface UsersClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{cpf}", produces = "application/json")
    ResponseEntity<UserDTO> obterCpfStatus(@PathVariable("cpf") String cpf);
}
