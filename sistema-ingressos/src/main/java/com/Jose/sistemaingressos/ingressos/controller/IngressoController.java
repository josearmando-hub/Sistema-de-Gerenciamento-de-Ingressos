package com.Jose.sistemaingressos.ingressos.controller;

import com.Jose.sistemaingressos.ingressos.model.Ingresso;
import com.Jose.sistemaingressos.ingressos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService service;

    @PostMapping
    public Ingresso cadastrarIngresso(@RequestBody Ingresso ingresso) {
        return service.salvar(ingresso);
    }

    @GetMapping
    public List<Ingresso> listarIngressos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Ingresso consultarIngresso(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/{id}/valor")
    public double calcularValor(@PathVariable String id) {
        return service.calcularValor(id);
    }

    // ATUALIZADO: Agora usa o método correto do Service
    @GetMapping("/usuario/{usuarioId}")
    public List<Ingresso> listarMeusIngressos(@PathVariable String usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    // NOVA ROTA: Solicitar cancelamento
    @PutMapping("/{id}/cancelar/{usuarioId}")
    public org.springframework.http.ResponseEntity<?> cancelarIngresso(@PathVariable String id, @PathVariable String usuarioId) {
        try {
            Ingresso ingressoCancelado = service.cancelar(id, usuarioId);
            return org.springframework.http.ResponseEntity.ok(ingressoCancelado); // Caminho "Sim" do diagrama
        } catch (RuntimeException e) {
            return org.springframework.http.ResponseEntity.badRequest().body(e.getMessage()); // Caminho "Não" (Exibir motivo da recusa)
        }
    }
    
    // NOVA ROTA: Solicitar Devolução
    @PutMapping("/{id}/devolver/{usuarioId}")
    public org.springframework.http.ResponseEntity<?> devolverIngresso(@PathVariable String id, @PathVariable String usuarioId) {
        try {
            Ingresso ingressoDevolvido = service.devolver(id, usuarioId);
            return org.springframework.http.ResponseEntity.ok(ingressoDevolvido);
        } catch (RuntimeException e) {
            return org.springframework.http.ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}