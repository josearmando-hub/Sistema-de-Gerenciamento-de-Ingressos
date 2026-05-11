package com.Jose.sistemaingressos.ingressos.model;

import java.time.LocalDateTime;

public class Evento {

    private String id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;
    private String local;
    private int quantidadeIngressosDisponiveis;
    private double valorIngresso;
    private boolean ativo;

    public Evento() {
        this.ativo = true;
    }

    public boolean possuiIngressoDisponivel() {
        return ativo && quantidadeIngressosDisponiveis > 0;
    }

    public void reservarUmaVaga() {
        if (!possuiIngressoDisponivel()) {
            throw new RuntimeException("Não há ingressos disponíveis para este evento.");
        }
        quantidadeIngressosDisponiveis--;
    }

    public void liberarUmaVaga() {
        quantidadeIngressosDisponiveis++;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public int getQuantidadeIngressosDisponiveis() { return quantidadeIngressosDisponiveis; }
    public void setQuantidadeIngressosDisponiveis(int quantidadeIngressosDisponiveis) { this.quantidadeIngressosDisponiveis = quantidadeIngressosDisponiveis; }
    public double getValorIngresso() { return valorIngresso; }
    public void setValorIngresso(double valorIngresso) { this.valorIngresso = valorIngresso; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
