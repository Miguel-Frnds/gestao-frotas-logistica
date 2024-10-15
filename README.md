# Gestão de Frotas para Empresas de Logística

## Descrição
Este é um sistema de **gestão de frotas** desenvolvido em Java, voltado para empresas de logística. O sistema possibilita o cadastro e gerenciamento de veículos, motoristas e rotas, além do acompanhamento das manutenções e do desempenho da frota.

## Funcionalidades
- Cadastro e gerenciamento de **veículos** (modelo, ano, placa, capacidade).
- Cadastro e gerenciamento de **motoristas** (nome, habilitação, disponibilidade).
- **Gestão de rotas** (origem, destino, duração estimada, motorista responsável).
- Controle de **manutenções** (datas de revisão, reparos, estado dos veículos).
- Relatórios de **desempenho** da frota (consumo de combustível, desempenho por rota, motoristas com melhor aproveitamento).

## Tecnologias Utilizadas
- **Java** (versão 8+)
- **Collections API** para gerenciamento eficiente de dados.
- **Tratamento de erros** para robustez do sistema.
- **Stream API** para manipulação de dados.
- **Versionamento Git** e repositório GitHub.

## Como Executar o Projeto
1. Clone o repositório e navegue até a pasta do projeto:
   ```bash
   git clone https://github.com/seuusuario/gestao-frotas-logistica.git
   cd gestao-frotas-logistica
2. Compile o projeto:
   ```bash
   javac src/*.java
3. Execute o projeto:
   ```bash
   java -cp src Main

## Estrutura do Projeto
   ```bash
gestao-frotas-logistica/
│
├── src/
│   ├── Main.java          # Classe principal para iniciar a aplicação
│   ├── models.Veiculo.java       # Classe para gerenciamento de veículos
│   ├── models.Motorista.java     # Classe para gerenciamento de motoristas
│   ├── models.Rota.java          # Classe para gerenciamento de rotas
│   └── ...                # Outras classes do sistema
│
├── docs/                  # Documentação do projeto
│   └── API.md             # Explicação das classes e métodos
│
├── tests/                 # Scripts de teste para o sistema
│   └── VeiculoTest.java    # Testes da classe models.Veiculo
│
└── README.md              # Arquivo de informações do projeto
  ```

## Como Contribuir
1. Faça um fork deste repositório.
2. Crie uma branch com a nova feature:
   ```bash
   git checkout -b feature/nome-da-feature
3. Faça o commit das suas alterações:
   ```bash
   git commit -m 'Adiciona nova funcionalidade'
4. Envie as mudanças para a branch principal:
   ```bash
   git push origin feature/nome-da-feature
5. Abra um pull request.

## Normas de Commit
- Use o tempo verbal no imperativo e seja descritivo, como por exemplo: `Corrige bug na classe de models.Motorista`, `Adiciona filtro para veículos`.
- Mantenha os commits pequenos e coesos, focando em uma mudança por commit.
- Mensagens de commit em inglês ou português, mas consistentes em todo o projeto.

## Licença

Este projeto é licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

MIT License

Copyright (c) [2024] [Miguel Pereira Fernandes]

Permissão é concedida, gratuitamente, a qualquer pessoa que obtenha uma cópia deste software e dos arquivos de documentação associados (o "Software"), para lidar no Software sem restrições, incluindo, sem limitação, os direitos de usar, copiar, modificar, mesclar, publicar, distribuir, sublicenciar e/ou vender cópias do Software, e para permitir que as pessoas a quem o Software é fornecido o façam, sujeitas às seguintes condições:

- O aviso de copyright acima e este aviso de permissão devem ser incluídos em todas as cópias ou partes substanciais do Software.

O Software é fornecido "no estado em que se encontra", sem garantia de qualquer tipo, expressa ou implícita, incluindo, mas não se limitando a garantias de comercialização, adequação a um propósito específico e não violação. Em nenhum caso os autores ou detentores de direitos autorais serão responsabilizados por qualquer reclamação, danos ou outra responsabilidade, seja em uma ação de contrato, ato ilícito ou de outra forma, resultante de, ou em conexão com, o Software ou o uso ou outros negócios no Software.
