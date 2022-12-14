package com.cupdash.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class Funcionario {
    private Integer id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int status = 1;
    @NotBlank(message = "Nome é requerido")
    private String nome;
    @NotBlank(message = "Email é requerido")
    private String email;
    @NotBlank(message = "Senha é requerido")
    private String senha;

    public Funcionario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Funcionario(int id, int status, String nome) {
        this.id = id;
        this.status = status;
        this.nome = nome;
    }
    public Funcionario(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
