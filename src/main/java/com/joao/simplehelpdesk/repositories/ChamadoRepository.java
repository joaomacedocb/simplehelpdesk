package com.joao.simplehelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.simplehelpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
	
}
