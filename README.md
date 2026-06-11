# Sistema de Gerenciamento de Oficina Mecânica 🔧🚗

Sistema de gerenciamento de oficina mecânica desenvolvido como requisito de avaliação para a disciplina de Laboratório de Banco de Dados.

O objetivo do projeto é aplicar conceitos de modelagem de banco de dados, SQL, integridade referencial e desenvolvimento orientado a objetos por meio da implementação de um sistema capaz de gerenciar clientes, veículos, mecânicos, peças, estoque e ordens de serviço.

---

## 🏗️ Arquitetura do Projeto

O sistema foi estruturado seguindo uma arquitetura em camadas:

### model

Entidades do sistema responsáveis pela representação dos dados.

Exemplos:

* Cliente
* Veiculo
* Mecanico
* Peca
* OrdemServico
* ItemServico

### repository

Camada responsável pela comunicação com o banco de dados MySQL.

Responsabilidades:

* Consultas SQL
* Inserções
* Atualizações
* Exclusões
* Controle de conexão JDBC

### service

Camada responsável pelas regras de negócio do sistema.

Exemplos:

* Verificação de CPF duplicado
* Verificação de placa duplicada
* Controle de estoque
* Cálculo automático da Ordem de Serviço
* Validação de status

### util

Classes auxiliares do projeto.

Exemplos:

* Conexão com banco de dados
* Validações
* Menus do sistema
* Tratamento de exceções

### application

Camada principal responsável pela execução do sistema e interação via console.

---

## 👥 Divisão de Responsabilidades da Equipe

Para garantir uma divisão equilibrada das atividades e integração entre os módulos, as responsabilidades foram distribuídas da seguinte forma:

### Pessoa 1 – Banco de Dados

Responsável por:

* Levantamento dos requisitos
* DER (Diagrama Entidade-Relacionamento)
* Modelo Relacional
* Criação do banco MySQL
* Scripts SQL
* Criação das tabelas
* Definição de PKs e FKs
* Constraints e integridade referencial
* Dados iniciais para testes

Entregáveis:

* DER
* Modelo Relacional
* schema.sql
* inserts.sql

---

### Pessoa 2 – CRUD Cliente

Responsável por:

* Cadastro de clientes
* Consulta de clientes
* Atualização de clientes
* Exclusão de clientes

Funcionalidades:

* Inserir cliente
* Buscar cliente
* Listar clientes
* Atualizar cadastro
* Excluir cliente

---

### Pessoa 3 – CRUD Veículo e Mecânico

Responsável por:

#### Veículo

* Cadastrar veículo
* Consultar veículo
* Atualizar veículo
* Excluir veículo

#### Mecânico

* Cadastrar mecânico
* Consultar mecânico
* Atualizar mecânico
* Excluir mecânico

Regras:

* Veículo deve possuir proprietário
* Não permitir placas duplicadas

---

### Pessoa 4 – CRUD Peças e Estoque

Responsável por:

* Cadastro de peças
* Controle de estoque
* Entrada de peças
* Saída de peças
* Consulta de estoque

Regras:

* Não permitir estoque negativo
* Atualização automática da quantidade disponível

---

### Pessoa 5 – Integração e Relatórios

Responsável por:

#### Integração

* Associação entre Cliente e Veículo
* Associação entre Ordem de Serviço e Mecânico
* Associação entre Ordem de Serviço e Peças
* Atualização automática do estoque
* Cálculo automático do valor total da OS

#### Relatórios

* Clientes cadastrados
* Veículos por cliente
* Histórico de serviços
* Peças mais utilizadas
* Ordens de serviço finalizadas
* Faturamento total

---

## 🛠️ Tecnologias Utilizadas

### Linguagem

* Java 21

### Banco de Dados

* MySQL 8

### Persistência

* JDBC

### Modelagem

* dbdiagram.io
* MySQL Workbench

### Versionamento

* Git
* GitHub

---

## 📂 Estrutura do Projeto

```text
oficina-mecanica/

├── database/
│   ├── schema.sql
│   ├── inserts.sql
│   └── consultas.sql
│
├── src/
│   ├── equipe/
│   │   ├── pessoa1/
│   │   ├── pessoa2/
│   │   ├── pessoa3/
│   │   ├── pessoa4/
│   │   └── pessoa5/
│   │
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── util/
│   └── application/
│
├── docs/
│   ├── DER.png
│   ├── ModeloRelacional.pdf
│   ├── AVISOS_DA_EQUIPE.md
│   └── Apresentacao.pptx
│
└── README.md
```

### Estrutura de trabalho por integrante

Cada integrante deve trabalhar dentro da sua própria pasta em `src/equipe/` para evitar mistura de entregas e facilitar a organização das tarefas.

* Pessoa 1: `src/equipe/pessoa1/`
* Pessoa 2: `src/equipe/pessoa2/`
* Pessoa 3: `src/equipe/pessoa3/`
* Pessoa 4: `src/equipe/pessoa4/`
* Pessoa 5: `src/equipe/pessoa5/`

Os avisos gerais da equipe ficam centralizados em [docs/AVISOS_DA_EQUIPE.md](docs/AVISOS_DA_EQUIPE.md).

---

## ⚠️ Avisos por Integrante

### Pessoa 1 – Banco de Dados

Aviso: esta é a base do sistema. Antes de qualquer implementação em Java, entregue o DER, o modelo relacional e os scripts SQL com PKs, FKs e constraints corretos.

### Pessoa 2 – CRUD Cliente

Aviso: o cadastro de clientes precisa manter a regra de CPF único e permitir todas as operações CRUD sem inconsistências.

### Pessoa 3 – CRUD Veículo e Mecânico

Aviso: nenhum veículo pode existir sem cliente associado e a placa não pode ser duplicada. O CRUD de mecânicos também precisa ficar completo.

### Pessoa 4 – CRUD Peças e Estoque

Aviso: controle entradas e saídas com atenção total ao estoque para não permitir saldo negativo.

### Pessoa 5 – Integração e Relatórios

Aviso: esta parte fecha o sistema. Faça a integração entre as camadas, calcule a ordem de serviço automaticamente e entregue os relatórios finais sem pendências.

---

## 📋 Regras de Negócio

* Um veículo não pode ser cadastrado sem um cliente associado.
* Não podem existir dois clientes com o mesmo CPF.
* Não podem existir dois veículos com a mesma placa.
* Uma Ordem de Serviço deve possuir pelo menos um mecânico responsável.
* O valor total da Ordem de Serviço deve ser calculado automaticamente.
* Toda peça utilizada em um serviço deve ser descontada do estoque.
* Não permitir estoque negativo.
* Ordens de Serviço finalizadas e pagas não poderão ser alteradas.
* O veículo só poderá ser entregue após confirmação do pagamento.

---

## 🎯 Objetivo Acadêmico

Aplicar conceitos relacionados a:

* Modelagem de Dados
* Diagrama Entidade-Relacionamento (DER)
* Modelo Relacional
* SQL
* Integridade Referencial
* CRUD
* Programação Orientada a Objetos
* JDBC
* Banco de Dados Relacional
* Trabalho colaborativo utilizando Git e GitHub
