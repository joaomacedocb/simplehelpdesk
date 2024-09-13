package com.joao.simplehelpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.Cliente;
import com.joao.simplehelpdesk.domain.Tecnico;
import com.joao.simplehelpdesk.domain.dtos.ChamadoDTO;
import com.joao.simplehelpdesk.domain.enums.Prioridade;
import com.joao.simplehelpdesk.domain.enums.Status;
import com.joao.simplehelpdesk.repositories.ChamadoRepository;
import com.joao.simplehelpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado na base de dados. ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return repository.save(novoChamado(objDTO));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = novoChamado(objDTO);

		return repository.save(oldObj);
	}

	private Chamado novoChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		Chamado chamado = new Chamado();

		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}

		if (obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setDataAbertura(obj.getDataAbertura());
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacao(obj.getObservacao());

		return chamado;

	}
}