package com.matheuslima.gerenciamentovotacao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {
    private static final String RESULTADO_PATTERN = "A pauta {0} de número {1}, obteve {2} votos 'Sim' e {3} votos 'Não'.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String titulo;

    @Column
    private String descricao;
    @OneToMany
    private List<Voto> votos = new ArrayList<Voto>();

    @Column
    private Boolean ativo;

    @Column
    private Boolean enviado = Boolean.FALSE;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime tempoDeterminado;

    public String getResultado(){
        return MessageFormat.format(RESULTADO_PATTERN, getTitulo(), getId(), getVotosSim(), getVotosNao());
    }

    private Long getVotosSim(){
        return votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.SIM)).count();
    }

    private Long getVotosNao(){
        return votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.NAO)).count();
    }
}
