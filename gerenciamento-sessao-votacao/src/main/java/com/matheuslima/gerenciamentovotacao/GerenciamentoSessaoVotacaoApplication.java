package com.matheuslima.gerenciamentovotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GerenciamentoSessaoVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoSessaoVotacaoApplication.class, args);
    }

}
