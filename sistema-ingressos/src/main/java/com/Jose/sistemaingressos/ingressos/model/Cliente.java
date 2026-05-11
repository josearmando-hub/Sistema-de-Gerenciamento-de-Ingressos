package com.Jose.sistemaingressos.ingressos.model;

public class Cliente extends Usuario {

    public Cliente() {
        setPerfil(PerfilUsuario.CLIENTE);
    }

    public Cliente(String email, String senha, String nome) {
        super(email, senha, nome);
        setPerfil(PerfilUsuario.CLIENTE);
    }
}
