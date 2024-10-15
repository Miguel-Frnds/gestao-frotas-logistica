# Developer Guide

## Visão Geral do Projeto

Este projeto é um Sistema de Gestão de Frotas que permite gerenciar rotas e veículos. As principais funcionalidades incluem a criação de rotas, a associação de rotas a veículos, e a consulta das informações relacionadas a rotas e veículos.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:

```bash
gestao-frotas-logistica/
│
├── docs/                                        # Documentação do projeto
│   ├── API.md                                   # Explicação das APIs usadas
│   └── developer_guide.md                       # Guia do desenvolvedor com informações sobre o projeto
│
├── src/                                         # Código fonte da aplicação
│   ├── models/                                  # Pacote que contém as classes principais do sistema
│   │   ├── enums/                               # Enumerações utilizadas no sistema
│   │   │   ├── StatusRota.java                  # Enumeração que define os status possíveis para uma rota
│   │   │   └── StatusVeiculo.java               # Enumeração que define os status possíveis para um veículo
│   │   ├── exceptions/                          # Pacote que contém as classes de exceção personalizadas
│   │   │   ├── gestaoDeRotas/                   # Exceções específicas relacionadas à gestão de rotas
│   │   │   └── gestaoDeVeiculos/                # Exceções específicas relacionadas à gestão de veículos
│   │   ├── Rota.java                            # Classe que representa uma rota com atributos e métodos relacionados
│   │   └── Veiculo.java                         # Classe que representa um veículo com atributos e métodos relacionados
│   │
│   ├── GestaoDeRotas.java                       # Classe responsável pela gestão de rotas, permitindo operações como criação, edição e remoção de rotas
│   └── GestaoDeVeiculos.java                    # Classe responsável pela gestão de veículos, permitindo operações como criação, edição e remoção de veículos
│
├── tests/                                       # Scripts de teste para o sistema
│   └── VeiculoTest.java                         # Testes da classe models.Veiculo
│
├── .gitignore                                   # Arquivo que lista os arquivos e pastas a serem ignorados pelo Git
└── README.md                                    # Arquivo de informações do projeto
 ```


### Pacotes

- **models**: Contém as classes principais do sistema.
  - **Rota**: Representa uma rota com atributos como código, cidade de origem, cidade de destino, distância e status da rota.
  - **Veiculo**: Representa um veículo com atributos como placa, marca, modelo, ano, status, quilometragem atual, data da última manutenção e uma rota associada.

- **models.enums**: Contém as enumerações utilizadas no sistema.
  - **StatusVeiculo**: Enumeração que define os status possíveis para um veículo (DISPONIVEL, EM_MANUNTENCAO, EM_VIAGEM.).
  - **StatusRota**: Enumeração que define os status possíveis para uma rota (PLANEJADA, EM_PROGRESO, CONCLUIDA).

- **models.exceptions**: Contém as classes de exceção personalizadas.
  - **gestaoDeRotas**: Pacote que contém exceções específicas relacionadas à gestão de rotas.
  - **gestaoDeVeiculos**: Pacote que contém exceções específicas relacionadas à gestão de veículos.

# Classe `GestaoDeVeiculos`

A classe `GestaoDeVeiculos` é responsável pela gestão de veículos, permitindo a criação, edição, remoção e verificação de veículos na frota. Utiliza uma lista para armazenar os veículos e fornece métodos para interagir com o usuário.

## Métodos

### `public GestaoDeVeiculos()`
Construtor da classe que inicializa a lista de veículos.

### `public boolean verificarPlacaDuplicada(String placaDuplicada)`
Verifica se uma placa já está cadastrada na lista de veículos.

**Parâmetros:**
- `placaDuplicada`: A placa do veículo a ser verificada.

**Retorna:**
- `true` se a placa já estiver cadastrada, `false` caso contrário.

### `public Veiculo lerEntradasDoUsuario(Scanner scan)`
Lê as entradas do usuário para criar um novo veículo.

**Parâmetros:**
- `scan`: O objeto `Scanner` para capturar as entradas do usuário.

**Retorna:**
- Um objeto `Veiculo` contendo as informações fornecidas pelo usuário.

### `public void registrarVeiculo(Scanner scan)`
Registra um novo veículo na lista, utilizando o método `lerEntradasDoUsuario`.

**Parâmetros:**
- `scan`: O objeto `Scanner` para capturar as entradas do usuário.

### `public void editarVeiculo(Scanner scan)`
Permite a edição das informações de um veículo já cadastrado.

**Parâmetros:**
- `scan`: O objeto `Scanner` para capturar as entradas do usuário.

### `public void removerVeiculo(Scanner scan)`
Remove um veículo da lista com base na placa informada pelo usuário.

**Parâmetros:**
- `scan`: O objeto `Scanner` para capturar as entradas do usuário.

### Métodos Privados de Edição
- `public void editarPlaca(Scanner scan)`
- `public void editarMarca(Scanner scan)`
- `public void editarModelo(Scanner scan)`
- `public void editarAno(Scanner scan)`
- `public void editarStatus(Scanner scan)`
- `public void editarQuilometragemAtual(Scanner scan)`
- `public void editarDataUltimaManuntencao(Scanner scan)`

Estes métodos permitem a edição de campos específicos de um veículo.

## Exceções
A classe lança exceções personalizadas em caso de entradas inválidas, como `PlacaInvalidaException`, `MarcaInvalidaException`, `ModeloInvalidoException`, `AnoInvalidoException`, `StatusVeiculoInvalidoException`, `QuilometragemInvalidaException` e `DataInvalidaException`.

# Classe `Veiculo`

A classe `Veiculo` representa um veículo na frota, contendo informações como placa, marca, modelo, ano, status, quilometragem e data da última manutenção.

## Atributos

- `private String placa`: A placa do veículo.
- `private String marca`: A marca do veículo.
- `private String modelo`: O modelo do veículo.
- `private int ano`: O ano de fabricação do veículo.
- `private StatusVeiculo status`: O status atual do veículo.
- `private double quilometragemAtual`: A quilometragem atual do veículo.
- `private LocalDate dataUltimaManutencao`: A data da última manutenção realizada.

## Construtor

### `public Veiculo(String placa, String marca, String modelo, int ano, StatusVeiculo status, double quilometragemAtual, LocalDate dataUltimaManutencao)`
Construtor da classe que inicializa um novo veículo com as informações fornecidas.

**Parâmetros:**
- `placa`: A placa do veículo.
- `marca`: A marca do veículo.
- `modelo`: O modelo do veículo.
- `ano`: O ano de fabricação do veículo.
- `status`: O status atual do veículo.
- `quilometragemAtual`: A quilometragem atual do veículo.
- `dataUltimaManutencao`: A data da última manutenção realizada.

## Métodos

### `public String getPlaca()`
Retorna a placa do veículo.

### `public String getMarca()`
Retorna a marca do veículo.

### `public String getModelo()`
Retorna o modelo do veículo.

### `public int getAno()`
Retorna o ano de fabricação do veículo.

### `public StatusVeiculo getStatus()`
Retorna o status atual do veículo.

### `public double getQuilometragemAtual()`
Retorna a quilometragem atual do veículo.

### `public LocalDate getDataUltimaManutencao()`
Retorna a data da última manutenção realizada.

### `@Override public String toString()`
Retorna uma representação em String do veículo, contendo suas informações.

## Problemas Conhecidos

### Repetição de Código na Classe `GestaoDeVeiculos`

Atualmente, a classe `GestaoDeVeiculos` apresenta uma quantidade significativa de código repetido, especialmente nos métodos de edição de atributos do veículo. Essa repetição pode levar a dificuldades na manutenção do código e a um aumento na probabilidade de erros.

**Recomendações:**
- **Refatoração:** Considere refatorar os métodos de edição para extrair a lógica comum em um método auxiliar. Isso não apenas melhorará a legibilidade do código, mas também facilitará futuras alterações e melhorias.
- **Utilização de Estruturas de Dados:** Avalie a possibilidade de usar estruturas de dados que possam reduzir a duplicação de código e melhorar a eficiência do sistema.

Identificar e resolver esses problemas conhecidos é um passo importante para garantir que o sistema continue a ser escalável e de fácil manutenção.

## Normas de Commits

O projeto deve ser versionado no GitHub com boas práticas de versionamento e organização de commits.

### Commits Granulares
Cada commit deve conter uma única mudança coesa. Por exemplo, se você está implementando a funcionalidade de cadastro de veículos, faça isso em um único commit.

### Mensagens de Commit Padronizadas
- Comece a mensagem com um verbo no infinitivo (por exemplo, "Adicionar", "Remover", "Corrigir").
- Use um padrão como: `[Escopo]: Descrição curta e objetiva do commit`.

**Exemplos:**
- `[Veiculo]: Adicionar funcionalidade de cadastro de veículos`
- `[Rota]: Implementar associar rota a veículo`
- `[Relatório]: Gerar relatório de veículos em manutenção`

### Commits de Correção
Se precisar corrigir um bug ou refatorar código, o commit deve refletir claramente o que foi ajustado.

**Exemplo:**
- `[BugFix]: Corrigir duplicação de placas no cadastro de veículos.`

### Branching
- Use a branch `main` para manter o código mais estável.
- Crie branches para cada funcionalidade ou correção, como:
  - `feature/gestao-veiculos`
  - `feature/gestao-rotas`
  - `bugfix/correcao-relatorio`

### Pull Requests
Para cada nova funcionalidade ou ajuste, abra um Pull Request (PR) para a branch `main`, revise e depois faça o merge.


## Como Configurar o Ambiente

1. **Instalação do JDK**: Certifique-se de ter o JDK (Java Development Kit) instalado. A versão recomendada é a 11 ou superior.
  
2. **IDE**: Utilize uma IDE como IntelliJ IDEA ou Eclipse para facilitar o desenvolvimento.

3. **Clone o repositório**: Clone o repositório para sua máquina local usando o comando:
   ```bash
   git clone <url-do-repositorio>

### Dicas para Personalização

- Adicione ou remova seções conforme necessário para se adequar melhor ao seu projeto.
- Você pode incluir exemplos de uso para as classes ou métodos, se achar útil.
- Caso tenha regras ou padrões específicos que você ou sua equipe seguem, inclua-os na seção de Guia de Contribuição.

Se precisar de mais alguma coisa, é só avisar!