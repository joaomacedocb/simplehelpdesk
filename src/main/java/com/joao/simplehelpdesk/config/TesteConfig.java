package com.joao.simplehelpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joao.simplehelpdesk.services.DbService;

@Configuration
@Profile("teste")
public class TesteConfig {
	
	@Autowired
	private DbService dbService;

    @Bean
    Boolean instanciaDb() {
        try {
            this.dbService.instanciaDb();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao instanciar o banco de dados: " + e.getMessage());
            return false;
        }
	}

}
