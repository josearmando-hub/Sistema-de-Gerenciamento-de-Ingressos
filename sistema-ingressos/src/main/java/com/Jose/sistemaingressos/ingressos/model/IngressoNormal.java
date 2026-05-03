package com.Jose.sistemaingressos.ingressos.model;

public class IngressoNormal extends Ingresso {

    @Override
    public double calcularValor() {
        return getValorBase(); // Valor sem alteração
    }

    @Override
    public String imprimirIngresso() {
        return "Ingresso NORMAL para " + getEvento() + " - Valor: R$ " + calcularValor();
    }
}
