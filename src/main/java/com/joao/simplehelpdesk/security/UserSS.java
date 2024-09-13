package com.joao.simplehelpdesk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joao.simplehelpdesk.domain.enums.Perfil;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis != null ? perfis.stream()
				.map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toSet())
				: Set.of();
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities != null ? authorities : Set.of();
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
