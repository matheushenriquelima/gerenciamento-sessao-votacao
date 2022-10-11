package com.matheuslima.gerenciamentovotacao.repository;

import com.matheuslima.gerenciamentovotacao.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
