package com.joao.simplehelpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Tecnico;
import com.joao.simplehelpdesk.repositories.TecnicoRepository;
import com.joao.simplehelpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id)); 
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
}
