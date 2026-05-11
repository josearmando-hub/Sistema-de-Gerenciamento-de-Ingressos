package com.Jose.sistemaingressos.ingressos.repository;

import com.Jose.sistemaingressos.ingressos.model.Reserva;
import com.Jose.sistemaingressos.ingressos.model.StatusReserva;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReservaRepository {

    private final Map<String, Reserva> reservas = new ConcurrentHashMap<>();

    public Reserva save(Reserva reserva) {
        if (reserva.getId() == null || reserva.getId().isBlank()) {
            reserva.setId(UUID.randomUUID().toString());
        }

        reservas.put(reserva.getId(), reserva);
        return reserva;
    }

    public Optional<Reserva> findById(String id) {
        return Optional.ofNullable(reservas.get(id));
    }

    public List<Reserva> findByClienteIdOrderByDataReservaDesc(String clienteId) {
        return reservas.values().stream()
                .filter(reserva -> clienteId.equals(reserva.getClienteId()))
                .sorted(Comparator.comparing(Reserva::getDataReserva, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();
    }

    public List<Reserva> findByEventoId(String eventoId) {
        return reservas.values().stream()
                .filter(reserva -> eventoId.equals(reserva.getEventoId()))
                .toList();
    }

    public Optional<Reserva> findByIngressoId(String ingressoId) {
        return reservas.values().stream()
                .filter(reserva -> ingressoId.equals(reserva.getIngressoId()))
                .findFirst();
    }

    public boolean existsByClienteIdAndEventoIdAndStatusIn(String clienteId, String eventoId, Collection<StatusReserva> status) {
        return reservas.values().stream()
                .anyMatch(reserva -> clienteId.equals(reserva.getClienteId())
                        && eventoId.equals(reserva.getEventoId())
                        && status.contains(reserva.getStatus()));
    }

    public long countByEventoIdAndStatus(String eventoId, StatusReserva status) {
        return reservas.values().stream()
                .filter(reserva -> eventoId.equals(reserva.getEventoId()))
                .filter(reserva -> status == reserva.getStatus())
                .count();
    }
}
