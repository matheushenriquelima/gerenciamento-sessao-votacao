package com.matheuslima.gerenciamentovotacao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum VotoEnum {
    SIM("Sim"),
    NAO("Não");

    private String value;

    public static VotoEnum of(String valor) {
        return Stream.of(VotoEnum.values())
                .filter(p -> p.getValue().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
