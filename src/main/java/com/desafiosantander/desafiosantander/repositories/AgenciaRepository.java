package com.desafiosantander.desafiosantander.repositories;

import com.desafiosantander.desafiosantander.entities.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
}
