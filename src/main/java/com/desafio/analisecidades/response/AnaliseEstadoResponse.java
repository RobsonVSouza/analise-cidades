package com.desafio.analisecidades.response;

public class AnaliseEstadoResponse {

    private String estado;
    private int numeroMunicipios;
    private String cidadeMaisQuente;
    private double temperaturaMaisQuente;
    private String cidadeMaisFria;
    private double temperaturaMaisFria;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroMunicipios() {
        return numeroMunicipios;
    }

    public void setNumeroMunicipios(int numeroMunicipios) {
        this.numeroMunicipios = numeroMunicipios;
    }

    public String getCidadeMaisQuente() {
        return cidadeMaisQuente;
    }

    public void setCidadeMaisQuente(String cidadeMaisQuente) {
        this.cidadeMaisQuente = cidadeMaisQuente;
    }

    public double getTemperaturaMaisQuente() {
        return temperaturaMaisQuente;
    }

    public void setTemperaturaMaisQuente(double temperaturaMaisQuente) {
        this.temperaturaMaisQuente = temperaturaMaisQuente;
    }

    public String getCidadeMaisFria() {
        return cidadeMaisFria;
    }

    public void setCidadeMaisFria(String cidadeMaisFria) {
        this.cidadeMaisFria = cidadeMaisFria;
    }

    public double getTemperaturaMaisFria() {
        return temperaturaMaisFria;
    }

    public void setTemperaturaMaisFria(double temperaturaMaisFria) {
        this.temperaturaMaisFria = temperaturaMaisFria;
    }
}
