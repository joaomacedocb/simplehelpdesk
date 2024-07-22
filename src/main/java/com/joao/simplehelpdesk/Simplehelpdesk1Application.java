package com.joao.simplehelpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.Cliente;
import com.joao.simplehelpdesk.domain.Tecnico;
import com.joao.simplehelpdesk.domain.enums.Perfil;
import com.joao.simplehelpdesk.domain.enums.Prioridade;
import com.joao.simplehelpdesk.domain.enums.Status;
import com.joao.simplehelpdesk.repositories.ChamadoRepository;
import com.joao.simplehelpdesk.repositories.ClienteRepository;
import com.joao.simplehelpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class Simplehelpdesk1Application implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;



	public static void main(String[] args) {
		SpringApplication.run(Simplehelpdesk1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Joao", "03029092062", "joao@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Pedro", "69299871078", "cliente@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Credenciais", "Primeiro chamado aberto", tec1, cli1); 
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
