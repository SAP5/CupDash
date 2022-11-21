package com.cupdash.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cupdash.model.Modelo;
import com.cupdash.model.Produto;
import com.cupdash.model.ProdutoDTO;

@Service
public class ProdutoI {

    public List<ProdutoDTO> obtemProdutos() {
        String url = "https://cupkat-dev.herokuapp.com/produtos/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "authorizationHeader");

        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        RestTemplate templ = new RestTemplate();
        ResponseEntity<List<ProdutoDTO>> resposta = templ.exchange(url,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<List<ProdutoDTO>>() {
            });
        List<ProdutoDTO> produtos = resposta.getBody();
        System.out.println(produtos);
        return produtos;
    }
    
    public void save(Produto produto){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://cupkat-test.herokuapp.com/produtos";

        ResponseEntity<String> result = restTemplate.postForEntity(url, produto, String.class);

        System.out.println(result);
	}

    public void delete(int id){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://cupkat-dev.herokuapp.com/produtos/delete/{id}";

        restTemplate.delete(url, id, String.class);
    }

    public void update(Produto produto, int id){
        RestTemplate restTemplate = new RestTemplate();
        produto.setId(id);
        String url = "https://cupkat-test.herokuapp.com/produtos/update/{id}";

        restTemplate.put(url, produto, produto.getId());
	}

    public ProdutoDTO getById(int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://cupkat-dev.herokuapp.com/produtos/by_id/{id}";
        
        ResponseEntity<ProdutoDTO> result = restTemplate.getForEntity(url, ProdutoDTO.class, id);
        System.out.println(result.getBody());
        return result.getBody();
    }
    
    public List<Modelo> obtemModelos() { 
        String url = "https://cupkat-test.herokuapp.com/modelos/"; 
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "authorizationHeader");
        
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        RestTemplate templ = new RestTemplate();
        ResponseEntity<List<Modelo>> resposta = templ.exchange(url,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<List<Modelo>>() {
            });
        List<Modelo> modelos = resposta.getBody();
        System.out.println(modelos);
        return modelos;
	}
}
