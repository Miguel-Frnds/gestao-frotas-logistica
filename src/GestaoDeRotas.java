import models.Rota;
import models.Veiculo;
import models.enums.StatusRota;
import models.enums.StatusVeiculo;
import models.exceptions.gestaoDeRotas.CodigoInvalidoException;
import models.exceptions.gestaoDeRotas.DistanciaInvalidaException;
import models.exceptions.gestaoDeRotas.NomeCidadeInvalidoException;
import models.exceptions.gestaoDeRotas.StatusRotaInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestaoDeRotas {

    List<Rota> rotaList;

    public GestaoDeRotas(){
        this.rotaList = new ArrayList<>();
    }

    private String lerCodigoRota(Scanner scan) {
        String codigoRota = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Código (formato RT-XXXX): ");
                codigoRota = scan.nextLine();

                if (codigoRota.length() != 7 || !codigoRota.startsWith("RT") ||
                        codigoRota.charAt(2) != '-' || !codigoRota.substring(3).matches("\\d{4}")) {
                    throw new CodigoInvalidoException("Erro: O código da rota é inválido. Por favor, insira um código no formato RT-XXXX.");
                }
                entradaValida = true;
            } catch (CodigoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        return codigoRota;
    }

    private String lerNomeCidade(Scanner scan, String tipo) {
        String cidade = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Cidade de " + tipo + ": ");
                cidade = scan.nextLine();

                if (!validarNomeCidade(cidade)) {
                    throw new NomeCidadeInvalidoException("Erro: O nome da cidade contém caracteres inválidos. Por favor, insira apenas letras e espaços.");
                }

                entradaValida = true;
            } catch (NomeCidadeInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        return cidade;
    }

    private double lerDistancia(Scanner scan) {
        double distancia = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Distância (KM): ");
                String entrada = scan.nextLine().replace(',', '.');
                distancia = Double.parseDouble(entrada);

                if (distancia < 0) {
                    throw new DistanciaInvalidaException("Erro: A distância não pode ser um valor negativo. Por favor, insira um valor positivo.");
                }

                entradaValida = true;
            } catch (DistanciaInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erro: A distância contém caracteres inválidos. Por favor, insira apenas números.");
            }
        }

        return distancia;
    }

    private StatusRota lerStatusRota(Scanner scan) {
        int escolhaStatus = -1;
        StatusRota statusRota = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("Status (digite o número correspondente):");
                System.out.println("1 - PLANEJADA");
                System.out.println("2 - EM PROGRESSO");
                System.out.println("3 - CONCLUIDA");
                System.out.print("Escolha o status: ");
                escolhaStatus = Integer.parseInt(scan.nextLine());

                if (escolhaStatus < 1 || escolhaStatus > 3) {
                    throw new StatusRotaInvalidoException("Erro: O status informado é inválido. Escolha um número entre 1 e 3.");
                }

                switch (escolhaStatus) {
                    case 1:
                        statusRota = StatusRota.PLANEJADA;
                        break;
                    case 2:
                        statusRota = StatusRota.EM_PROGRESSO;
                        break;
                    case 3:
                        statusRota = StatusRota.CONCLUIDA;
                        break;
                }

                entradaValida = true;
            } catch (StatusRotaInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erro: O status contém caracteres inválidos. Por favor, insira apenas números entre 1 a 3.");
            }
        }

        return statusRota;
    }

    public void criarRotas(Scanner scan) {
        System.out.println("Insira as informações da rota");

        String codigoRota = lerCodigoRota(scan);
        String cidadeDeOrigem = lerNomeCidade(scan, "origem");
        String cidadeDeDestino = lerNomeCidade(scan, "destino");
        double distancia = lerDistancia(scan);
        StatusRota statusRota = lerStatusRota(scan);

        Rota novaRota = new Rota(codigoRota, cidadeDeOrigem, cidadeDeDestino, distancia, statusRota);
        rotaList.add(novaRota);
        System.out.println("Rota criada com sucesso: " + novaRota);
    }

    public void associarRotas(Scanner scan, List<Veiculo> veiculoList) {
        System.out.print("Insira o código da rota (formato RT-XXXX)");
        String codigoRota = lerCodigoRota(scan);
        Rota rotaEncontrada = null;

        for(Rota rota : rotaList) {
            if(rota.getCodigoRota().equalsIgnoreCase(codigoRota)) {
                rotaEncontrada = rota;
                break;
            }
        }

        if(rotaEncontrada == null) {
            System.out.println("Nenhuma rota com código " + codigoRota + " foi encontrada.");
            return;
        }

        System.out.println("Dados da rota encontrada: ");
        System.out.println(rotaEncontrada);

        String placa = "";
        boolean placaValida = false;

        while (!placaValida) {
            System.out.print("Insira a placa do veículo que deseja associar à rota (formato AAA-1234 ou AAA1A23): ");
            placa = scan.nextLine();

            if (validarPlaca(placa)) {
                placaValida = true;
            } else {
                System.out.println("Erro: Placa inválida. Por favor, insira uma placa no formato correto.");
            }
        }

        Veiculo veiculoEncontrado = null;

        for(Veiculo veiculo : veiculoList) {
            if(veiculo.getPlaca().equalsIgnoreCase(placa)){
                veiculoEncontrado = veiculo;
                break;
            }
        }

        if (veiculoEncontrado == null) {
            System.out.println("Nenhum veículo com a placa " + placa + " foi encontrado.");
            return;
        }

        if(veiculoEncontrado.getStatusVeiculo() != StatusVeiculo.DISPONIVEL) {
            System.out.println("O veículo com a placa " + placa + " não está disponível para associação.");
            return;
        }

        veiculoEncontrado.associarRota(rotaEncontrada);
    }

    private boolean validarPlaca(String placa) {
        if (placa.length() == 8 && placa.charAt(3) == '-') {
            String letras = placa.substring(0, 3);
            String numeros = placa.substring(4);

            if (letras.chars().allMatch(Character::isLetter) && numeros.chars().allMatch(Character::isDigit)) {
                return true;
            }
        }

        if (placa.length() == 8) {
            String letras = placa.substring(0, 3);
            String numero1 = String.valueOf(placa.charAt(3));
            String letra2 = String.valueOf(placa.charAt(4));
            String numeros = placa.substring(5);

            if (letras.chars().allMatch(Character::isLetter) &&
                    Character.isDigit(numero1.charAt(0)) &&
                    letra2.chars().allMatch(Character::isLetter) &&
                    numeros.chars().allMatch(Character::isDigit)) {
                return true;
            }
        }

        return false;
    }


    public boolean validarNomeCidade(String nomeCidade){
        for(char c : nomeCidade.toCharArray()) {
            if(!Character.isLetter(c) && c != ' '){
                return false;
            }
        }
        return true;
    }
}
