package com.Jose.sistemaingressos.ingressos.service;

import com.Jose.sistemaingressos.ingressos.model.Usuario;
import com.Jose.sistemaingressos.ingressos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(Usuario usuario) {
        validarDados(usuario.getEmail(), usuario.getSenha());

        usuario.setEmail(usuario.getEmail().trim());
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RuntimeException("Informe seu nome para criar a conta.");
        }

        usuario.setNome(usuario.getNome().trim());
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Este e-mail ja esta em uso.");
        }

        return repository.save(usuario);
    }

    public Usuario fazerLogin(String email, String senha) {
        validarDados(email, senha);

        return repository.findByEmailAndSenha(email.trim(), senha)
                .orElseThrow(() -> new RuntimeException("Credenciais invalidas. E-mail ou senha incorretos."));
    }

    private void validarDados(String email, String senha) {
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Informe o e-mail.");
        }

        if (senha == null || senha.isBlank()) {
            throw new RuntimeException("Informe a senha.");
        }
    }
}
