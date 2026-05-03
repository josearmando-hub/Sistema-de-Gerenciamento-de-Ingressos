package com.Jose.sistemaingressos.ingressos.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IngressoNormal.class, name = "NORMAL"),
        @JsonSubTypes.Type(value = IngressoVIP.class, name = "VIP"),
        @JsonSubTypes.Type(value = IngressoMeia.class, name = "MEIA")
})
public abstract class Ingresso {

    private String id;
    private String usuarioId; // <-- Aqui está a ligação com o usuário logado!
    private String evento;
    private LocalDateTime dataEvento;
    private double valorBase;
    private EstadoIngresso estado;

    public Ingresso() {
        this.estado = EstadoIngresso.DISPONIVEL;
    }

    public abstract double calcularValor();
    public abstract String imprimirIngresso();

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }
    public LocalDateTime getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDateTime dataEvento) { this.dataEvento = dataEvento; }
    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }
    public EstadoIngresso getEstado() { return estado; }
    public void setEstado(EstadoIngresso estado) { this.estado = estado; }
}
