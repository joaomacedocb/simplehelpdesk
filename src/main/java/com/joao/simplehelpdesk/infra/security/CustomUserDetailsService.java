package com.joao.simplehelpdesk.infra.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.domain.Pessoa;
import com.joao.simplehelpdesk.repositories.PessoaRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        var authorities = pessoa.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(pessoa.getEmail(), pessoa.getSenha(), authorities);
    }
}
