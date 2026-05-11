package com.Jose.sistemaingressos.ingressos.repository;

import com.Jose.sistemaingressos.ingressos.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UsuarioRepository {

    private final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null || usuario.getId().isBlank()) {
            usuario.setId(UUID.randomUUID().toString());
        }

        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public Optional<Usuario> findById(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return usuarios.values().stream()
                .filter(usuario -> usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(email))
                .filter(usuario -> usuario.getSenha() != null && usuario.getSenha().equals(senha))
                .findFirst();
    }

    public boolean existsByEmail(String email) {
        return usuarios.values().stream()
                .anyMatch(usuario -> usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(email));
    }
}
