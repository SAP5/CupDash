package com.cupdash.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cupdash.model.Cliente;


@Service
public class ClienteI {
    public List<Cliente> obtemClientes() { 
        String url = "https://cupkat-test.herokuapp.com/clientes/"; 
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "authorizationHeader");
        
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        RestTemplate templ = new RestTemplate();
        ResponseEntity<List<Cliente>> resposta = templ.exchange(url,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<List<Cliente>>() {
            });
        List<Cliente> clientes = resposta.getBody();
        return clientes;
    }

    public void save(Cliente cliente){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://cupkat-test.herokuapp.com/clientes";

        ResponseEntity<String> result = restTemplate.postForEntity(url, cliente, String.class);

        System.out.println(result);
	}

    public void delete(int id){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://cupkat-test.herokuapp.com/clientes/delete/{id}";

        restTemplate.delete(url, id, String.class);
    }

    public void update(Cliente cliente, int id){
        RestTemplate restTemplate = new RestTemplate();
        cliente.setId(id);
        String url = "https://cupkat-test.herokuapp.com/clientes/update/{id}";
        restTemplate.put(url, cliente, cliente.getId());
	}

    public Cliente getById(int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://cupkat-test.herokuapp.com/clientes/by_id/{id}";
        
        ResponseEntity<Cliente> result = restTemplate.getForEntity(url, Cliente.class, id);
        
        return result.getBody();
    }
    
    public Cliente getByEmail(String email) {
    	RestTemplate restTemplate = new RestTemplate();
    	String url = "https://cupkat-dev.herokuapp.com/clientes/by_email/{email}";
    	ResponseEntity<Cliente> result =null;
    	try {
    		 result = restTemplate.getForEntity(url, Cliente.class, email);
    		 return result.getBody();		
    	} catch (Exception e) {
			Cliente cliente=null;
			return cliente;
		}
    
    }
    
    public boolean isEmailUnique(Integer id, String email) {
    	ClienteI cliService = new ClienteI();
    	
    	Cliente clienteEmail = cliService.getByEmail(email);
    	
    	if(clienteEmail == null) return true;
    	
    	boolean isCreatingNew = (id == null);
    	
    	if (isCreatingNew) {
			if (clienteEmail != null) return false;
		} else {
			if(clienteEmail.getId() != id) {
				return false;
			}
		}
		return true;
    }
}
