package com.joao.simplehelpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.Cliente;
import com.joao.simplehelpdesk.domain.Tecnico;
import com.joao.simplehelpdesk.domain.enums.Perfil;
import com.joao.simplehelpdesk.domain.enums.Prioridade;
import com.joao.simplehelpdesk.domain.enums.Status;
import com.joao.simplehelpdesk.repositories.ChamadoRepository;
import com.joao.simplehelpdesk.repositories.ClienteRepository;
import com.joao.simplehelpdesk.repositories.TecnicoRepository;

@Service
public class DbService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDb() {

		Tecnico tec1 = new Tecnico(null, "Joao", "03029092062", "joao@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Bruce Wayne", "12345678901", "bruce@wayneindustries.com", "batman");
		Tecnico tec3 = new Tecnico(null, "Tony Stark", "23456789012", "tony@starkindustries.com", "ironman");
		Tecnico tec4 = new Tecnico(null, "Peter Parker", "34567890123", "peter@dailybugle.com", "spiderman");
		Tecnico tec5 = new Tecnico(null, "Clark Kent", "45678901234", "clark@dailyplanet.com", "superman");

		Cliente cli1 = new Cliente(null, "Pedro", "69299871078", "cliente@email.com", "123");

		Cliente cli2 = new Cliente(null, "Frodo Baggins", "56789012345", "frodo@shire.com", "ringbearer");
		Cliente cli3 = new Cliente(null, "Harry Potter", "67890123456", "harry@hogwarts.com", "expelliarmus");
		Cliente cli4 = new Cliente(null, "Luke Skywalker", "78901234567", "luke@rebellion.com", "theforce");
		Cliente cli5 = new Cliente(null, "Indiana Jones", "89012345678", "indy@adventures.com", "whip");
		Cliente cli6 = new Cliente(null, "Marty McFly", "90123456789", "marty@timetravel.com", "delorean");
		Cliente cli7 = new Cliente(null, "James Bond", "01234567890", "bond@mi6.com", "007");
		Cliente cli8 = new Cliente(null, "Sherlock Holmes", "12345098765", "sherlock@bakerstreet.com", "detective");
		Cliente cli9 = new Cliente(null, "Ellen Ripley", "23456098765", "ripley@nostromo.com", "aliens");
		Cliente cli10 = new Cliente(null, "Sarah Connor", "34567098765", "sarah@resistance.com", "terminator");
		Cliente cli11 = new Cliente(null, "Neo", "45678098765", "neo@matrix.com", "chosenone");
		Cliente cli12 = new Cliente(null, "John Wick", "56789098765", "john@assassin.com", "babaYaga");
		Cliente cli13 = new Cliente(null, "Lara Croft", "67890109876", "lara@raider.com", "tomb");
		Cliente cli14 = new Cliente(null, "Ethan Hunt", "78901210987", "ethan@impossible.com", "mission");
		Cliente cli15 = new Cliente(null, "Jack Sparrow", "89012321098", "jack@blackpearl.com", "pirate");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Credenciais", "Primeiro chamado aberto",
				tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9, cli10, cli11, cli12, cli13, cli14, cli15));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}

}
