package com.Jose.sistemaingressos.ingressos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    private String id;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String nome;
    private PerfilUsuario perfil;

    // Construtor vazio (obrigatório para o Spring)
    public Usuario() {
        this.perfil = PerfilUsuario.CLIENTE;
    }

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.perfil = PerfilUsuario.CLIENTE;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }
}
