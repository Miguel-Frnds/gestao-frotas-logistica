package models;

import models.enums.StatusRota;

public class Rota {

    private String codigoRota;
    private String cidadeDeOrigem;
    private String cidadeDeDestino;
    private double distancia;
    private StatusRota statusRota;

    public Rota(String codigoRota, String cidadeDeOrigem, String cidadeDeDestino, double distancia, StatusRota statusRota) {
        this.codigoRota = codigoRota;
        this.cidadeDeOrigem = cidadeDeOrigem;
        this.cidadeDeDestino = cidadeDeDestino;
        this.distancia = distancia;
        this.statusRota = statusRota;
    }

    public String getCodigoRota() {
        return codigoRota;
    }

    public void setCodigoRota(String codigoRota) {
        this.codigoRota = codigoRota;
    }

    public String getCidadeDeOrigem() {
        return cidadeDeOrigem;
    }

    public void setCidadeDeOrigem(String cidadeDeOrigem) {
        this.cidadeDeOrigem = cidadeDeOrigem;
    }

    public double getDistanciaKM() {
        return distancia;
    }

    public void setDistanciaKM(double distanciaKM) {
        this.distancia = distancia;
    }

    public StatusRota getStatusRota() {
        return statusRota;
    }

    public void setStatusRota(StatusRota statusRota) {
        this.statusRota = statusRota;
    }

    @Override
    public String toString() {
        return "Rota{" +
                "Código: '" + codigoRota + '\'' +
                ", Cidade de Origem: '" + cidadeDeOrigem + '\'' +
                ", Cidade de Destino: '" + cidadeDeDestino + '\'' +
                ", Distância: " + distancia + " km" +
                ", Status: " + statusRota +
                '}';
    }
}
