package com.joao.simplehelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.simplehelpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
