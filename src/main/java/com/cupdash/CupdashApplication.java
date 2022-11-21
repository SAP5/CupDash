package com.cupdash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cupdash.service.ProdutoI;

@SpringBootApplication
public class CupdashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdashApplication.class, args);
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		FuncionarioI func = new FuncionarioI();
//		Funcionario f = new Funcionario();
//		f.setNome("Ola teste 4");
//		f.setEmail("olateste4@gmail.com");
//		f.setSenha("olateste1244");
//		String encodedPassword = passwordEncoder.encode(f.getSenha());
//		f.setSenha(encodedPassword);
//		System.out.println(encodedPassword);
//		func.save(f);
	}

}
