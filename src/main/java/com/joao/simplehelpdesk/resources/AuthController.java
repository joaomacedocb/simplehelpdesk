package com.joao.simplehelpdesk.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joao.simplehelpdesk.domain.Chamado;
import com.joao.simplehelpdesk.domain.Pessoa;
import com.joao.simplehelpdesk.domain.dtos.ChamadoDTO;
import com.joao.simplehelpdesk.domain.dtos.LoginRequestDTO;
import com.joao.simplehelpdesk.domain.dtos.ResponseDTO;
import com.joao.simplehelpdesk.infra.security.TokenService;
import com.joao.simplehelpdesk.repositories.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO objDTO) {
		Pessoa pessoa = this.pessoaRepository.findByEmail(objDTO.email())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		if (passwordEncoder.matches(pessoa.getEmail(), objDTO.email())) {
			String token = this.tokenService.generateToken(pessoa);
			return ResponseEntity.ok(new ResponseDTO(pessoa.getNome(),token));
		}
		return ResponseEntity.badRequest().build();
	}
}
