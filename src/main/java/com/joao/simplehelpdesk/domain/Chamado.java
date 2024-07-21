package com.joao.simplehelpdesk.domain;

import java.time.LocalDate;
import com.joao.simplehelpdesk.domain.enums.Prioridade;
import com.joao.simplehelpdesk.domain.enums.Status;

public class Chamado {
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacao;
}
