package com.Jose.sistemaingressos.ingressos.repository;

import com.Jose.sistemaingressos.ingressos.model.EstadoIngresso;
import com.Jose.sistemaingressos.ingressos.model.Ingresso;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class IngressoRepository {

    private final Map<String, Ingresso> ingressos = new ConcurrentHashMap<>();

    public Ingresso save(Ingresso ingresso) {
        if (ingresso.getId() == null || ingresso.getId().isBlank()) {
            ingresso.setId(UUID.randomUUID().toString());
        }

        ingressos.put(ingresso.getId(), ingresso);
        return ingresso;
    }

    public List<Ingresso> findAll() {
        return List.copyOf(ingressos.values());
    }

    public Optional<Ingresso> findById(String id) {
        return Optional.ofNullable(ingressos.get(id));
    }

    public List<Ingresso> findByUsuarioId(String usuarioId) {
        return ingressos.values().stream()
                .filter(ingresso -> usuarioId.equals(ingresso.getUsuarioId()))
                .toList();
    }

    public List<Ingresso> findByEventoId(String eventoId) {
        return ingressos.values().stream()
                .filter(ingresso -> eventoId.equals(ingresso.getEventoId()))
                .toList();
    }

    public Optional<Ingresso> findByCodigoQr(String codigoQr) {
        return ingressos.values().stream()
                .filter(ingresso -> codigoQr.equals(ingresso.getCodigoQr()))
                .findFirst();
    }

    public long countByEventoIdAndEstado(String eventoId, EstadoIngresso estado) {
        return ingressos.values().stream()
                .filter(ingresso -> eventoId.equals(ingresso.getEventoId()))
                .filter(ingresso -> estado == ingresso.getEstado())
                .count();
    }
}
