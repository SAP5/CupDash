package com.cupdash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cupdash.model.Funcionario;
import com.cupdash.service.FuncionarioI;

public class CupkatUserDetailsService implements UserDetailsService {
	
	@Autowired
	private FuncionarioI funcRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Funcionario funcionario = funcRepo.getByEmail(email);
		
		if(funcionario != null) {
			return new CupkatUserDetails(funcionario);
		}
		
		throw new UsernameNotFoundException("Não foi encontrado usuário com esse email: " + email);
		
	}

}
