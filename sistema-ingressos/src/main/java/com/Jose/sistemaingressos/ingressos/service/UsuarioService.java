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
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RuntimeException("Informe o e-mail do usuário.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new RuntimeException("Informe a senha do usuário.");
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RuntimeException("Informe o nome do usuário.");
        }
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Este e-mail já está em uso.");
        }
        if (usuario.getPerfil() == null) {
            usuario.setPerfil(com.Jose.sistemaingressos.ingressos.model.PerfilUsuario.CLIENTE);
        }
        return repository.save(usuario);
    }

    public Usuario fazerLogin(String email, String senha) {
        // Retorna o usuário se achar, ou lança um erro se as credenciais forem inválidas
        return repository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas. E-mail ou senha incorretos."));
    }
}
