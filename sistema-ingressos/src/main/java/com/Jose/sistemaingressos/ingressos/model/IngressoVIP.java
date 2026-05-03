package com.Jose.sistemaingressos.ingressos.model;

public class IngressoVIP extends Ingresso {
    private double taxaVIP;

    @Override
    public double calcularValor() {
        return getValorBase() + taxaVIP;
    }

    @Override
    public String imprimirIngresso() {
        return "Ingresso VIP para " + getEvento() + " - Valor Final: R$ " + calcularValor();
    }

    public double getTaxaVIP() { return taxaVIP; }
    public void setTaxaVIP(double taxaVIP) { this.taxaVIP = taxaVIP; }
}