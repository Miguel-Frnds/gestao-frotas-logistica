package models;

import models.enums.StatusVeiculo;

import java.time.LocalDate;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private StatusVeiculo statusVeiculo;
    private double quilometragemAtual;
    private LocalDate dataUltimaManutencao;
    private Rota veiculoRota;

    public Veiculo(String placa, String marca, String modelo, int ano, StatusVeiculo statusVeiculo, double quilometragemAtual, LocalDate dataUltimaManutencao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.statusVeiculo = statusVeiculo;
        this.quilometragemAtual = quilometragemAtual;
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public double getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(double quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(LocalDate dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public Rota getVeiculoRota() {
        return veiculoRota;
    }

    public void associarRota(Rota rota) {
        if(rota == null) {
            throw new IllegalArgumentException("Erro: A rota não pode ser nula.");
        }

        this.veiculoRota = rota;

        this.statusVeiculo = StatusVeiculo.EM_VIAGEM;

        System.out.println("Rota " + rota.getCodigoRota() + " associada ao veículo " + this.placa + " com sucesso.");
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "Placa: '" + placa + '\'' +
                ", Marca: '" + marca + '\'' +
                ", Modelo: '" + modelo + '\'' +
                ", Ano: " + ano +
                ", Status : " + statusVeiculo +
                ", Quilometragem Atual: " + quilometragemAtual + " km" +
                ", Data da última manutenção: " + dataUltimaManutencao +
                ", Rota do veículo: " + veiculoRota +
                '}';
    }
}