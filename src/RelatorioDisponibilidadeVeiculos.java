import models.Veiculo;
import models.enums.StatusVeiculo;

import java.util.List;

public class RelatorioDisponibilidadeVeiculos {

    public void exibirVeiculosPorStatus(List<Veiculo> listaVeiculos, StatusVeiculo status){
        long contador = listaVeiculos.stream()
                .filter(veiculo -> veiculo.getStatusVeiculo().equals(status))
                .count();
        System.out.println("Veículos em " + status + ": " + contador);

        System.out.printf("%-10s | %-10s | %-10s | %-15s\n", "Placa", "Marca", "Modelo", "Quilometragem");
        System.out.println("--------------------------------------------");

        for(Veiculo veiculo : listaVeiculos){
            if(veiculo.getStatusVeiculo().equals(status)) {
                System.out.printf("%-10s | %-10s | %-10s | %-15s\n",
                        veiculo.getPlaca(),
                        veiculo.getMarca(),
                        veiculo.getModelo(),
                        veiculo.getQuilometragemAtual() + " km");
            }
        }
    }

    public void exibirDisponibilidadeDosVeiculos(List<Veiculo> listaVeiculos){
        System.out.println("Relatório de Disponibilidade dos Veículos");
        exibirVeiculosPorStatus(listaVeiculos, StatusVeiculo.DISPONIVEL);
        exibirVeiculosPorStatus(listaVeiculos, StatusVeiculo.EM_MANUNTENCAO);
        exibirVeiculosPorStatus(listaVeiculos, StatusVeiculo.EM_VIAGEM);
    }
}
