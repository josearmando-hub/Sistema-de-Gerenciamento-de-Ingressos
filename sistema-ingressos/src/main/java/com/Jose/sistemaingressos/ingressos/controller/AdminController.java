package com.Jose.sistemaingressos.ingressos.controller;

import com.Jose.sistemaingressos.ingressos.model.Ingresso;
import com.Jose.sistemaingressos.ingressos.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/eventos/{eventoId}/ingressos")
    public List<Ingresso> listarIngressos(@PathVariable String eventoId) {
        return service.listarIngressosPorEvento(eventoId);
    }

    @GetMapping("/qrcode/{codigoQr}")
    public ResponseEntity<?> verificar(@PathVariable String codigoQr) {
        try {
            return ResponseEntity.ok(service.verificarIngresso(codigoQr));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/qrcode/{codigoQr}/utilizar")
    public ResponseEntity<?> utilizar(@PathVariable String codigoQr) {
        try {
            return ResponseEntity.ok(service.utilizarIngresso(codigoQr));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/eventos/{eventoId}/relatorio")
    public ResponseEntity<?> relatorio(@PathVariable String eventoId) {
        try {
            Map<String, Object> relatorio = service.relatorioPorEvento(eventoId);
            return ResponseEntity.ok(relatorio);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
