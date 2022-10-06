package com.matheuslima.gerenciamentovotacao.repository;

import com.matheuslima.gerenciamentovotacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query(value = "SELECT p FROM Pauta p WHERE p.ativo = :ativo")
    List<Pauta> obterPautasPorAtivo(@Param("ativo") Boolean ativo);
}
