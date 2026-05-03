package com.Jose.sistemaingressos.ingressos.controller;

import com.Jose.sistemaingressos.ingressos.model.Usuario;
import com.Jose.sistemaingressos.ingressos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = service.cadastrar(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credenciais) {
        try {
            Usuario usuarioLogado = service.fazerLogin(credenciais.getEmail(), credenciais.getSenha());
            return ResponseEntity.ok(usuarioLogado); // Credenciais Válidas (Sim no fluxograma)
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Exibir Erro (Não no fluxograma)
        }
    }
}