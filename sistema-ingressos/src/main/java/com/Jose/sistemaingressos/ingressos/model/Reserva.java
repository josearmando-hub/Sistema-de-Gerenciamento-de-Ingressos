package com.Jose.sistemaingressos.ingressos.model;

import java.time.LocalDateTime;

public class Reserva {

    private String id;
    private String clienteId;
    private String eventoId;
    private String ingressoId;
    private LocalDateTime dataReserva;
    private StatusReserva status;

    public Reserva() {
        this.dataReserva = LocalDateTime.now();
        this.status = StatusReserva.RESERVADA;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getEventoId() { return eventoId; }
    public void setEventoId(String eventoId) { this.eventoId = eventoId; }
    public String getIngressoId() { return ingressoId; }
    public void setIngressoId(String ingressoId) { this.ingressoId = ingressoId; }
    public LocalDateTime getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDateTime dataReserva) { this.dataReserva = dataReserva; }
    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }
}
