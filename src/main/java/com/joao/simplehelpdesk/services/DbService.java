package com.joao.simplehelpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.Cliente;
import com.joao.simplehelpdesk.domain.Tecnico;
import com.joao.simplehelpdesk.domain.enums.Perfil;
import com.joao.simplehelpdesk.domain.enums.Prioridade;
import com.joao.simplehelpdesk.domain.enums.Status;
import com.joao.simplehelpdesk.repositories.ChamadoRepository;
import com.joao.simplehelpdesk.repositories.PessoaRepository;

@Service
public class DbService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	BCryptPasswordEncoder encoder;

	public void instanciaDb() {

		Tecnico tec1 = new Tecnico(null, "Joao", "03029092062", "joao@mail.com", encoder.encode("1234"));
		tec1.addPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Maria", "66587045030", "maria@mail.com", encoder.encode("1234"));
		tec2.addPerfil(Perfil.TECNICO);

		Cliente cli1 = new Cliente(null, "Pedro", "51744627053", "cliente1@mail.com", encoder.encode("1234"));

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Credenciais", "Primeiro chamado aberto",
				tec1, cli1);

		pessoaRepository.saveAll(Arrays.asList(cli1, tec1, tec2));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}
}