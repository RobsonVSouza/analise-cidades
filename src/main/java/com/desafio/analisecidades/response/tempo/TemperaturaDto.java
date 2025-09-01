package com.desafio.analisecidades.response.tempo;

public class TemperaturaDto {

    private final String cidade;
    private final double temperaturaAtual;

    public TemperaturaDto(String cidade, double temperaturaAtual) {
        this.cidade = cidade;
        this.temperaturaAtual = temperaturaAtual;
    }

    public String getCidade() {
        return cidade;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }
}
