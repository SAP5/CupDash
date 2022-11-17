package com.cupdash.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cupdash.model.Funcionario;

public class CupkatUserDetails implements UserDetails {
	
	private Funcionario funcionario;

	public CupkatUserDetails(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return funcionario.getSenha();
	}

	@Override
	public String getUsername() {
		return funcionario.getEmail();
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
		if (funcionario.getStatus()==1) {
			return true;
		}
		return false;
	}
	
	public String getFullname() {
		return this.funcionario.getNome();
	}

}
