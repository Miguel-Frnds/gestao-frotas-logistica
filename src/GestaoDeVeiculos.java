import models.Veiculo;
import models.enums.StatusVeiculo;
import models.exceptions.gestaoDeVeiculos.*;

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

    public Veiculo lerEntradasDoUsuario(Scanner scan){
        int escolhaStatus = -1;
        boolean entradaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean dataValida = false;
        LocalDate dataUltimaManutencao = null;
        String placa = "";
        String marca = "";
        String modelo = "";
        int ano = 0;
        StatusVeiculo statusVeiculo = null;
        double quilometragemAtual = 0;
        String dataEntrada = "";

        System.out.println("Insira as informações do veículo");

        while(!entradaValida) {
            try {
                System.out.print("Placa (formato AAA-1234 ou AAA1A23): ");
                placa = scan.nextLine();

                while(verificarPlacaDuplicada(placa)){
                    System.out.println("Erro: Placa '" + placa + "' já existente. Por favor, insira uma nova placa.");
                    System.out.print("Placa (formato AAA-1234 ou AAA1A23): ");
                    placa = scan.nextLine();
                }

                if(placa.isEmpty()){
                    throw new PlacaInvalidaException("Erro: A placa não pode estar vazia. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                }

                if(!placa.matches("^[A-Z]{3}-\\d{4}$") && !placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                    throw new PlacaInvalidaException("Erro: A placa '" + placa + "' contém caracteres inválidos. Formato esperado: AAA-1234 ou AAA1A23.");
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
                marca = scan.nextLine();

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
                modelo = scan.nextLine();

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
                ano = Integer.parseInt(scan.nextLine());

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
                    throw new StatusVeiculoInvalidoException("Erro: O status informado é inválido. Escolha um número entre 1 e 3.");
                }
                switch(escolhaStatus) {
                    case 1:
                        statusVeiculo = StatusVeiculo.DISPONIVEL;
                        break;
                    case 2:
                        statusVeiculo = StatusVeiculo.EM_MANUNTENCAO;
                        break;
                    case 3:
                        statusVeiculo = StatusVeiculo.EM_VIAGEM;
                        break;
                }
                entradaValida = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Erro: O status contém caracteres inválidos. Por favor, insira apenas números entre 1 a 3.");
            }
            catch (StatusVeiculoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Quilometragem Atual: ");
                quilometragemAtual = Double.parseDouble(scan.nextLine());

                if (quilometragemAtual < 0) {
                    throw new QuilometragemInvalidaException("Erro: A quilometragem '" + quilometragemAtual + "' é inválida. Por favor, insira um número positivo.");
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
                dataEntrada = scan.nextLine();
                dataUltimaManutencao = LocalDate.parse(dataEntrada, formatter);

                if (dataUltimaManutencao.isAfter(LocalDate.now())) {
                    throw new DataInvalidaException("Erro: A data '" + dataEntrada + "' não pode ser no futuro.");
                }

                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Erro: A data '" + dataEntrada + "' está no formato errado. Formato esperado: DD-MM-YYYY.");
            } catch (DataInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        return new Veiculo(placa, marca, modelo, ano, statusVeiculo, quilometragemAtual, dataUltimaManutencao);
    }

    public void registrarVeiculo(Scanner scan){
        System.out.println("Registrando novo veículo");
        Veiculo novoVeiculo = lerEntradasDoUsuario(scan);

        if(novoVeiculo != null) {
            veiculoList.add(novoVeiculo);
            System.out.println("Veículo adicionado à frota com sucesso!");
            System.out.println("Dados do veículo: " + novoVeiculo);
        } else {
            System.out.println("Erro ao adicionar o veículo. Por favor, tente novamente.");
        }
    }

    public void editarVeiculo(Scanner scan){
        if(veiculoList.isEmpty()) {
            System.out.println("Não existem veículos para editar.");
            return;
        }

        boolean entradaValida = false;
        Veiculo veiculoEncontrado = null;
        String placaParaEditar = "";

            while (!entradaValida) {
                try {
                    System.out.print("Digite a placa do veículo que deseja editar (formato AAA-1234 ou AAA1A23): ");
                    placaParaEditar = scan.nextLine();


                    if (placaParaEditar.isEmpty()) {
                        throw new PlacaInvalidaException("Erro: A placa não pode estar vazia. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                    }

                    if (!placaParaEditar.matches("^[A-Z]{3}-\\d{4}$") && !placaParaEditar.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                        throw new PlacaInvalidaException("Erro: A placa '" + placaParaEditar + "' contém caracteres inválidos. Formato esperado: AAA-1234 ou AAA1A23.");
                    }

                    entradaValida = true;
                } catch (PlacaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }

            for (Veiculo veiculos : veiculoList) {
                if (veiculos.getPlaca().equalsIgnoreCase(placaParaEditar)) {
                    veiculoEncontrado = veiculos;
                    break;
                }
            }

            if (veiculoEncontrado == null) {
                System.out.println("Veículo com placa '" + placaParaEditar + "' não encontrado.");
                return;
            }

            System.out.println("Veículo encontrado: " + veiculoEncontrado);
            boolean continuarEdicao = true;

            while (continuarEdicao) {
                try {
                    System.out.println("Selecione os campos que deseja editar:");
                    System.out.println("1 - Placa");
                    System.out.println("2 - Marca");
                    System.out.println("3 - Modelo");
                    System.out.println("4 - Ano");
                    System.out.println("5 - Status");
                    System.out.println("6 - Quilometragem");
                    System.out.println("7 - Data da Última Manutenção");
                    System.out.println("0 - Sair");

                    int escolhaEdicao = Integer.parseInt(scan.nextLine());

                    switch (escolhaEdicao) {
                        case 1:
                            editarPlaca(scan);
                            break;
                        case 2:
                            editarMarca(scan);
                            break;
                        case 3:
                            editarModelo(scan);
                            break;
                        case 4:
                            editarAno(scan);
                            break;
                        case 5:
                            editarStatus(scan);
                            break;
                        case 6:
                            editarQuilometragemAtual(scan);
                            break;
                        case 7:
                            editarDataUltimaManuntencao(scan);
                            break;
                        case 0:
                            continuarEdicao = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    System.out.println("Edição concluída. Dados atualizados: " + veiculoEncontrado);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: A escolha contém caracteres inválidos. Por favor, insira apenas números entre 0 a 7.");
            }
        }
    }

    public void removerVeiculo(Scanner scan) {
        if(veiculoList.isEmpty()){
            System.out.println("Não existem veículos para editar.");
            return;
        }

        Veiculo veiculoEncontrado = null;
        boolean entradaValida = false;
        String placaParaRemover = "";

        while (!entradaValida) {
            try {
                System.out.print("Digite a placa do veículo que deseja remover (formato AAA-1234 ou AAA1A23): ");
                placaParaRemover = scan.nextLine();


                if (placaParaRemover.isEmpty()) {
                    throw new PlacaInvalidaException("Erro: A placa não pode estar vazia. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                }

                if (!placaParaRemover.matches("^[A-Z]{3}-\\d{4}$") && !placaParaRemover.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                    throw new PlacaInvalidaException("Erro: A placa '" + placaParaRemover + "' contém caracteres inválidos. Formato esperado: AAA-1234 ou AAA1A23.");
                }

                entradaValida = true;
            } catch (PlacaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        for(Veiculo veiculo : veiculoList) {
            if(veiculo.getPlaca().equalsIgnoreCase(placaParaRemover)){
                veiculoEncontrado = veiculo;
                break;
            }
        }

        if(veiculoEncontrado == null) {
            System.out.println("Nenhum veículo com placa '" + placaParaRemover + "' encontrado.");
            return;
        }

        System.out.println("Veículo encontrado.");
        System.out.println("Dados do veículo: " + veiculoEncontrado);
        veiculoList.remove(veiculoEncontrado);
        System.out.println("Veículo Removido com sucesso.");
    }

    public void editarPlaca(Scanner scan){
        String placa = "";
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Nova Placa (formato AAA-1234 ou AAA1A23): ");
                placa = scan.nextLine();

                while(verificarPlacaDuplicada(placa)){
                    System.out.println("Erro: Placa '" + placa + "' já existente. Por favor, insira uma nova placa.");
                    System.out.print("Placa (formato AAA-1234 ou AAA1A23): ");
                    placa = scan.nextLine();
                }

                if(placa.isEmpty()){
                    throw new PlacaInvalidaException("Erro: A placa não pode estar vazia. Por favor, insira uma placa no formato AAA-1234 ou AAA1A23.");
                }

                if(!placa.matches("^[A-Z]{3}-\\d{4}$") && !placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                    throw new PlacaInvalidaException("Erro: A placa '" + placa + "' contém caracteres inválidos. Formato esperado: AAA-1234 ou AAA1A23.");
                }

                entradaValida = true;
            } catch (PlacaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void editarMarca(Scanner scan){
        String marca = "";
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Nova Marca: ");
                marca = scan.nextLine();

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
    }

    public void editarModelo(Scanner scan){
        String modelo = "";
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Novo Modelo: ");
                modelo = scan.nextLine();

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
    }

    public void editarAno(Scanner scan){
        int ano = 0;
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Novo Ano: ");
                ano = Integer.parseInt(scan.nextLine());

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
    }

    public void editarStatus(Scanner scan){
        int escolhaStatus = -1;
        StatusVeiculo statusVeiculo = null;
        boolean entradaValida = false;

        while(!entradaValida){
            try {
                System.out.println("Novo Status (digite o número correspondente):");
                System.out.println("1 - DISPONIVEL");
                System.out.println("2 - EM MANUTENCAO");
                System.out.println("3 - EM VIAGEM");
                System.out.print("Escolha o status: ");
                escolhaStatus = Integer.parseInt(scan.nextLine());

                if(escolhaStatus < 1 || escolhaStatus > 3) {
                    throw new StatusVeiculoInvalidoException("Erro: O status informado é inválido. Escolha um número entre 1 e 3.");
                }
                switch(escolhaStatus) {
                    case 1:
                        statusVeiculo = StatusVeiculo.DISPONIVEL;
                        break;
                    case 2:
                        statusVeiculo = StatusVeiculo.EM_MANUNTENCAO;
                        break;
                    case 3:
                        statusVeiculo = StatusVeiculo.EM_VIAGEM;
                        break;
                }
                entradaValida = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Erro: O status contém caracteres inválidos. Por favor, insira apenas números entre 1 a 3.");
            }
            catch (StatusVeiculoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void editarQuilometragemAtual(Scanner scan){
        double quilometragemAtual = 0;
        boolean entradaValida = false;

        while(!entradaValida) {
            try {
                System.out.print("Nova Quilometragem: ");
                quilometragemAtual = Double.parseDouble(scan.nextLine());

                if (quilometragemAtual < 0) {
                    throw new QuilometragemInvalidaException("Erro: A quilometragem '" + quilometragemAtual + "' é inválida. Por favor, insira um número positivo.");
                }
                entradaValida = true;
            } catch (QuilometragemInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erro: A quilometragem contém caracteres inválidos. Por favor, insira apenas números.");
            }
        }
    }

    public void editarDataUltimaManuntencao(Scanner scan){
        boolean entradaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean dataValida = false;
        LocalDate dataUltimaManutencao = null;
        String dataEntrada = "";

        while(!dataValida) {
            try {
                System.out.println("Data da última manuntenção (DD-MM-YYYY): ");
                dataEntrada = scan.nextLine();
                dataUltimaManutencao = LocalDate.parse(dataEntrada, formatter);

                if (dataUltimaManutencao.isAfter(LocalDate.now())) {
                    throw new DataInvalidaException("Erro: A data '" + dataEntrada + "' não pode ser no futuro.");
                }

                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Erro: A data '" + dataEntrada + "' está no formato errado. Formato esperado: DD-MM-YYYY.");
            } catch (DataInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
