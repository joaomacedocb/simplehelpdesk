package com.joao.simplehelpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.dtos.ChamadoDTO;
import com.joao.simplehelpdesk.repositories.ChamadoRepository;
import com.joao.simplehelpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById (Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado na base de dados. ID: " + id ));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
