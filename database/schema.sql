CREATE DATABASE IF NOT EXISTS oficina_db;
USE oficina_db;

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    email VARCHAR(100),
    endereco TEXT,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE veiculo (
    id_veiculo INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    placa VARCHAR(8) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano SMALLINT NOT NULL,
    cor VARCHAR(30),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE mecanico (
    id_mecanico INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    especialidade VARCHAR(80),
    telefone VARCHAR(15),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE peca (
    id_peca INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    fabricante VARCHAR(80),
    preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario >= 0),
    quantidade_estoque INT NOT NULL DEFAULT 0 CHECK (quantidade_estoque >= 0),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ordem_servico (
    id_os INT AUTO_INCREMENT PRIMARY KEY,
    id_veiculo INT NOT NULL,
    data_abertura DATE NOT NULL,
    data_fechamento DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'aberta',
    valor_total DECIMAL(10,2) NOT NULL DEFAULT 0.00 CHECK (valor_total >= 0),
    observacoes TEXT,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_status CHECK (status IN ('aberta', 'em andamento', 'finalizada', 'paga'))
);

CREATE TABLE os_mecanico (
    id_os INT NOT NULL,
    id_mecanico INT NOT NULL,
    PRIMARY KEY (id_os, id_mecanico),
    FOREIGN KEY (id_os) REFERENCES ordem_servico(id_os) ON DELETE CASCADE,
    FOREIGN KEY (id_mecanico) REFERENCES mecanico(id_mecanico) ON DELETE RESTRICT
);

CREATE TABLE item_servico (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_os INT NOT NULL,
    id_peca INT,
    quantidade INT NOT NULL DEFAULT 1 CHECK (quantidade > 0),
    preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario >= 0),
    descricao_servico VARCHAR(200),
    FOREIGN KEY (id_os) REFERENCES ordem_servico(id_os) ON DELETE CASCADE,
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca) ON DELETE SET NULL
);

CREATE INDEX idx_cliente_cpf ON cliente(cpf);
CREATE INDEX idx_veiculo_placa ON veiculo(placa);
CREATE INDEX idx_mecanico_cpf ON mecanico(cpf);
CREATE INDEX idx_os_status ON ordem_servico(status);