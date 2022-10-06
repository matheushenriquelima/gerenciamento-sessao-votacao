package com.matheuslima.gerenciamentovotacao.service.feign;

import com.matheuslima.gerenciamentovotacao.service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "x-users",url = "${application.feign.url-users}")
public interface UsersClient {

    @GetMapping("/{cpf}")
    ResponseEntity<UserDTO> obterCpfStatus(@PathVariable("cpf") String cpf);
}
