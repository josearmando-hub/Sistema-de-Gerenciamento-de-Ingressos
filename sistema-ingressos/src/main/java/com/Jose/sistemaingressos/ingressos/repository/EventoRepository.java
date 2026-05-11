package com.Jose.sistemaingressos.ingressos.repository;

import com.Jose.sistemaingressos.ingressos.model.Evento;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventoRepository {

    private final Map<String, Evento> eventos = new ConcurrentHashMap<>();

    public Evento save(Evento evento) {
        if (evento.getId() == null || evento.getId().isBlank()) {
            evento.setId(UUID.randomUUID().toString());
        }

        eventos.put(evento.getId(), evento);
        return evento;
    }

    public List<Evento> findAll() {
        return eventos.values().stream()
                .sorted(Comparator.comparing(Evento::getDataHora, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }

    public Optional<Evento> findById(String id) {
        return Optional.ofNullable(eventos.get(id));
    }

    public List<Evento> findByAtivoTrueOrderByDataHoraAsc() {
        return eventos.values().stream()
                .filter(Evento::isAtivo)
                .sorted(Comparator.comparing(Evento::getDataHora, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }
}
