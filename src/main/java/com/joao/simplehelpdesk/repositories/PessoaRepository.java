package com.joao.simplehelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.simplehelpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
}
