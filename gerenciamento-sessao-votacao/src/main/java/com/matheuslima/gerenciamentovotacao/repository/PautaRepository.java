package com.matheuslima.gerenciamentovotacao.repository;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query(value = "SELECT p FROM Pauta p WHERE p.ativo = :ativo")
    List<Pauta> obterPautasPorAtivo(@Param("ativo") Boolean ativo);

    @Query("SELECT CASE WHEN count(p.id) > 0 THEN true ELSE false END FROM Pauta p"
            + " LEFT JOIN p.votos v"
            + " WHERE p.id = :pautaId"
            + " AND v.cpfAssociado = :cpfAssociado ")
    Boolean existeVotoCpfEmPauta(@Param("pautaId") Long pautaId, @Param("cpfAssociado") String cpfAssociado);
}
