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

		Tecnico tec1 = new Tecnico(null, "Joao", "03029092062", "joao@mail.com", "123455");
		tec1.addPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Maria", "66587045030", "maria@mail.com", "123455");
		tec2.addPerfil(Perfil.TECNICO);

		Tecnico tec3 = new Tecnico(null, "Carlos", "84984999098", "carlos@mail.com", "123455");
		tec3.addPerfil(Perfil.TECNICO);

		Tecnico tec4 = new Tecnico(null, "Ana", "53866904002", "ana@mail.com", "123455");
		tec4.addPerfil(Perfil.TECNICO);

		Tecnico tec5 = new Tecnico(null, "Lucas", "19595463094", "lucas@mail.com", "123455");
		tec5.addPerfil(Perfil.TECNICO);
		

		Cliente cli1 = new Cliente(null, "Pedro", "51744627053", "cliente1@mail.com", "123455");
		Cliente cli2 = new Cliente(null, "Mariana", "60716668050", "cliente2@mail.com", "123455");
		Cliente cli3 = new Cliente(null, "Roberto", "21940448093", "cliente3@mail.com", "123455");
		Cliente cli4 = new Cliente(null, "Clara", "86774612059", "cliente4@mail.com", "123455");
		Cliente cli5 = new Cliente(null, "Felipe", "76737341098", "cliente5@mail.com", "123455");
		Cliente cli6 = new Cliente(null, "Julia", "77071584079", "cliente6@mail.com", "123455");
		Cliente cli7 = new Cliente(null, "Renato", "29751602084", "cliente7@mail.com", "123455");
		Cliente cli8 = new Cliente(null, "Sofia", "98586452017", "cliente8@mail.com", "123455");
		Cliente cli9 = new Cliente(null, "Vinicius", "84156528014", "cliente9@mail.com", "123455");
		Cliente cli10 = new Cliente(null, "Larissa", "25404273089", "cliente10@mail.com", "123455");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Credenciais", "Primeiro chamado aberto", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Conexão de rede", "Problemas de conexão", tec2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Backup", "Backup realizado com sucesso", tec3, cli3);
		Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Erro de sistema", "Erro ao iniciar o sistema", tec4, cli4);
		Chamado c5 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Impressora", "Impressora não funciona", tec5, cli5);
		Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Atualização", "Atualização de software realizada", tec1, cli6);
		Chamado c7 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Senha", "Reset de senha", tec2, cli7);
		Chamado c8 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Falha de hardware", "HD com falha", tec3, cli8);
		Chamado c9 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Configuração", "Configuração de e-mail concluída", tec4, cli9);
		Chamado c10 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Segurança", "Configuração de firewall", tec5, cli10);

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9, cli10));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));

	}

}
