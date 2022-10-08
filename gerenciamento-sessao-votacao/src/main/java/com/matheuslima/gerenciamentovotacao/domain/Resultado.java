package com.matheuslima.gerenciamentovotacao.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Pauta pauta;

    @Column
    private Long votosSim;

    @Column
    private Long votosNao;
}
