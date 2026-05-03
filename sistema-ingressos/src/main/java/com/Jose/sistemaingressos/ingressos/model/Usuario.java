package com.Jose.sistemaingressos.ingressos.model;

public class Usuario {

    private String id;
    private String email;
    private String senha;
    private String nome;

    // Construtor vazio (obrigatório para o Spring)
    public Usuario() {}

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
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
}
