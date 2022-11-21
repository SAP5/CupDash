package com.cupdash.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


public class ProdutoDTO {
    private int id;
    private int status;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;
    @NotNull
    private double lucro;
    @NotNull
	private BigDecimal custo;
    @PositiveOrZero
    private int estoque;
    @NotEmpty
    private Set<Cor> cores;
    @NotEmpty
    private Set<Tamanho> tamanhos;
    @NotEmpty
    private Set<Categoria> categorias;
    @NotNull
    private Modelo modelo;
    
    private String imagem;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getLucro() {
        return lucro;
    }
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    public Set<Cor> getCores() {
        return cores;
    }
    public void setCores(Set<Cor> cores) {
        this.cores = cores;
    }
    public Set<Tamanho> getTamanhos() {
        return tamanhos;
    }
    public void setTamanhos(Set<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }
    public Set<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(@NotEmpty Set<Categoria> categorias) {
        this.categorias = categorias;
    }
    public Modelo getModelo() {
        return modelo;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    


    
}
