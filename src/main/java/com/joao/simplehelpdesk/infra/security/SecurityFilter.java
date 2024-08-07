package com.joao.simplehelpdesk.infra.security;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.joao.simplehelpdesk.repositories.PessoaRepository;
import com.joao.simplehelpdesk.domain.Pessoa;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recoverToken(request);
        String email = tokenService.validateToken(token);

        if (email != null) {
            Pessoa pessoa = pessoaRepository.findByEmail(email).orElse(null);
            if (pessoa != null) {
                var authorities = pessoa.getPerfis().stream()
                        .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                        .collect(Collectors.toList());

                var authentication = new UsernamePasswordAuthenticationToken(pessoa, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Erro: Usuário não encontrado.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
