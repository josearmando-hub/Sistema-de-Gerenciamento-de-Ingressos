package com.Jose.sistemaingressos.ingressos.controller;

import com.Jose.sistemaingressos.ingressos.model.Reserva;
import com.Jose.sistemaingressos.ingressos.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<?> reservar(@RequestBody Map<String, String> dados) {
        try {
            Reserva reserva = service.reservar(dados.get("clienteId"), dados.get("eventoId"), dados.get("tipoIngresso"));
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Reserva> listarPorCliente(@PathVariable String clienteId) {
        return service.listarPorCliente(clienteId);
    }

    @PutMapping("/{reservaId}/cancelar/{clienteId}")
    public ResponseEntity<?> cancelar(@PathVariable String reservaId, @PathVariable String clienteId) {
        try {
            return ResponseEntity.ok(service.cancelar(reservaId, clienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
