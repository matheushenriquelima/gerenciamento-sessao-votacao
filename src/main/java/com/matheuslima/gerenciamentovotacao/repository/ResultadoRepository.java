package com.matheuslima.gerenciamentovotacao.repository;

import com.matheuslima.gerenciamentovotacao.domain.Resultado;
import com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    @Query("SELECT new com.matheuslima.gerenciamentovotacao.service.dto.ResultadoDTO(p.id, p.titulo, r.votosSim, r.votosNao) FROM Resultado r"
            + " INNER JOIN r.pauta p"
            + " WHERE p.id = :pautaId")
    Optional<ResultadoDTO> obterPorPautaId(@Param("pautaId") Long pautaId);
}
