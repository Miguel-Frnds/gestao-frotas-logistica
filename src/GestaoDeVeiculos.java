import models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestaoDeVeiculos {
    private List<Veiculo> veiculoList;

    public GestaoDeVeiculos(){
        this.veiculoList = new ArrayList<>();
    }

    public boolean verificarPlacaDuplicada(String placaDuplicada){
        for(Veiculo veiculos : veiculoList){
            if(veiculos.getPlaca().equalsIgnoreCase(placaDuplicada)){
                return true;
            }
        }
        return false;
    }

    public void lerEntradasDoUsuario(Scanner scan){
        int escolhaStatus = -1;
        boolean entradaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean dataValida = false;
        LocalDate dataUltimaManutencao = null;

        System.out.println("Insira as informações do veículo");

        while(!entradaValida) {
            try {
                System.out.print("Placa (formato AAA-1234 ou AAA1A23): ");
                String placa = scan.nextLine();

                if(placa.isEmpty()){
                    throw new PlacaInvalidaException("Erro: A placa não pode estar vazia. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                }

                if(!placa.matches("^[A-Z]{3}-\\d{4}$") && !placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                    throw new PlacaInvalidaException("Erro: A placa contém caracteres inválidos. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                }

                entradaValida = true;
            } catch (PlacaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Marca: ");
                String marca = scan.nextLine();

                if(marca.isEmpty()) {
                    throw new MarcaInvalidaException("Erro: A marca não pode estar vazia. Por favor, insira uma marca válida.");
                }

                if(!marca.matches("[a-zA-Z ]+")){
                    throw new MarcaInvalidaException("Erro: A marca contém caracteres inválidos. Por favor, insira apenas números e espaços.");
                }
                entradaValida = true;
            } catch (MarcaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Modelo: ");
                String modelo = scan.nextLine();

                if (modelo.isEmpty()) {
                    throw new ModeloInvalidoException("Erro: O modelo não pode estar vazio. Por favor, insira um modelo válido.");
                }

                // Validação adicional, como evitar números no modelo, por exemplo
                if (!modelo.matches("[a-zA-Z0-9 ]+")) {
                    throw new ModeloInvalidoException("Erro: O modelo contém caracteres inválidos. Por favor, insira apenas letras, números e espaços.");
                }
                entradaValida = true;
            } catch (ModeloInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Ano: ");
                int ano = Integer.parseInt(scan.nextLine());

                int anoAtual = LocalDate.now().getYear();

                if(ano < 1886) {
                    throw new AnoInvalidoException("Erro: O ano contém número inválido. Por favor, insira apenas anos superiores a 1885.");
                }

                if (ano > anoAtual) {
                    throw new AnoInvalidoException("Erro: O ano não pode ser no futuro. Por favor, insira um ano válido.");
                }

                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: O ano contém caracteres inválidos. Por favor, insira apenas números.");
            } catch (AnoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida){
            try {
                System.out.println("Status (digite o número correspondente):");
                System.out.println("1 - DISPONIVEL");
                System.out.println("2 - EM MANUTENCAO");
                System.out.println("3 - EM VIAGEM");
                System.out.print("Escolha o status: ");
                escolhaStatus = Integer.parseInt(scan.nextLine());
                if(escolhaStatus < 1 || escolhaStatus > 3) {
                    throw new IllegalArgumentException("Erro: O status contém caracteres inválidos. Por favor, insira apenas números de 1 a 3.");
                }
                entradaValida = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Erro: O status contém caracteres inválidos. Por favor, insira apenas números de 1 a 3.");
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Quilometragem Atual: ");
                double quilometragemAtual = Double.parseDouble(scan.nextLine());

                if (quilometragemAtual < 0) {
                    throw new QuilometragemInvalidaException("Erro: A quilometragem contém valor negativo. Por favor, insira apenas números positivos.");
                }
                entradaValida = true;
            } catch (QuilometragemInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erro: A quilometragem contém caracteres inválidos. Por favor, insira apenas números.");
            }
        }

        while(!dataValida) {
            try {
                System.out.println("Data da última manuntenção (DD-MM-YYYY): ");
                String dataEntrada = scan.nextLine();
                dataUltimaManutencao = LocalDate.parse(dataEntrada, formatter);

                if (dataUltimaManutencao.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Erro: A data não pode ser no futuro.");
                }

                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Erro: A data está no formato errado. Por favor, insira uma data no formato DD-MM-YYYY.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
