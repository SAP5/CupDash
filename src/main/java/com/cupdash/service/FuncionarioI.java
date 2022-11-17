package com.cupdash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cupdash.model.Funcionario;

@Service
public class FuncionarioI {

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public List<Funcionario> obtemFuncionarios() {
		String url = "https://cupkat-dev.herokuapp.com/funcionarios/";

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("Authorization", "authorizationHeader");

		HttpEntity requestEntity = new HttpEntity<>(null, headers);

		RestTemplate templ = new RestTemplate();
		ResponseEntity<List<Funcionario>> resposta = templ.exchange(url, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Funcionario>>() {
				});
		List<Funcionario> funcionarios = resposta.getBody();
		return funcionarios;
	}

	public void save(Funcionario funcionario) {
//		FuncionarioI funcRepo = new FuncionarioI();
//		boolean isUpdatingUser = (funcionario.getId() != null);
//		
//		if (isUpdatingUser) {
//			Funcionario existingUser = funcRepo.getById(funcionario.getId());
//
//			if (funcionario.getSenha().isEmpty()) {
//				funcionario.setSenha(existingUser.getSenha());
//			} else {
//				encodePassword(funcionario);
//			}
//
//		} else {
//			encodePassword(funcionario);
//		}
		encodePassword(funcionario);
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cupkat-test.herokuapp.com/funcionarios";
		ResponseEntity<String> result = restTemplate.postForEntity(url, funcionario, String.class);
		System.out.println(result);
	}

	public void delete(int id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cupkat-test.herokuapp.com/funcionarios/delete/{id}";
		restTemplate.delete(url, id, String.class);
	}

	public void update(Funcionario funcionario, int id) {
		RestTemplate restTemplate = new RestTemplate();
		funcionario.setId(id);
		String url = "https://cupkat-test.herokuapp.com/funcionarios/update/{id}";
		restTemplate.put(url, funcionario, funcionario.getId());
	}

	public Funcionario getById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cupkat-test.herokuapp.com/funcionarios/by_id/{id}";
		ResponseEntity<Funcionario> result = restTemplate.getForEntity(url, Funcionario.class, id);
		return result.getBody();
	}

	public Funcionario getByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cupkat-dev.herokuapp.com/funcionarios/by_email/{email}";
		ResponseEntity<Funcionario> result = null;
		try {
			result = restTemplate.getForEntity(url, Funcionario.class, email);
			return result.getBody();
		} catch (Exception e) {
			Funcionario funcionario = null;
			return funcionario;
		}

	}

	private void encodePassword(Funcionario funcionario) {
		System.out.println(funcionario.getSenha());
		String encodedPassword = passwordEncoder.encode(funcionario.getSenha());
		System.out.println(encodedPassword);
		funcionario.setSenha(encodedPassword);
	}
}
