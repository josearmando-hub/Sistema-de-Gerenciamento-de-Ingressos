package com.Jose.sistemaingressos.ingressos.model;

public class IngressoMeia extends Ingresso {
    private double percentualDesconto;

    @Override
    public double calcularValor() {
        return getValorBase() - (getValorBase() * (percentualDesconto / 100));
    }

    @Override
    public String imprimirIngresso() {
        return "Ingresso MEIA para " + getEvento() + " - Valor Final: R$ " + calcularValor();
    }

    public double getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(double percentualDesconto) { this.percentualDesconto = percentualDesconto; }
}
