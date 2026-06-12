USE oficina_db;

-- 1. INSERTS DE CLIENTES
INSERT INTO cliente (nome, cpf, telefone, email, endereco) VALUES
('Leonardo Oliveira', '12345678901', '61999998888', 'leonardo@email.com', 'Ceilândia, Brasília - DF'),
('Jackson Silva', '23456789012', '61988887777', 'jackson@email.com', 'Taguatinga, Brasília - DF'),
('Luan Souza', '34567890123', '61977776666', 'luan@email.com', 'Asa Sul, Brasília - DF'),
('Jennyfer Santos', '45678901234', '61966665555', 'jennyfer@email.com', 'Guará, Brasília - DF'),
('Lucas Rocha', '55678901235', '61955554444', 'lucas@email.com', 'Águas Claras, Brasília - DF');

-- 2. INSERTS DE VEÍCULOS
INSERT INTO veiculo (id_cliente, placa, marca, modelo, ano, cor) VALUES
(1, 'ABC1D23', 'Chevrolet', 'Onix', 2022, 'Preto'),
(2, 'XYZ9K87', 'Volkswagen', 'Gol', 2018, 'Branco'),
(3, 'MNO4P56', 'Fiat', 'Palio', 2015, 'Prata'),
(4, 'JQK3L21', 'Hyundai', 'HB20', 2021, 'Cinza'),
(5, 'RET8W99', 'Ford', 'Ka', 2020, 'Vermelho');

-- 3. INSERTS DE MECÂNICOS
INSERT INTO mecanico (nome, cpf, especialidade, telefone) VALUES
('Carlos Augusto', '98765432101', 'Injeção Eletrônica', '61911112222'),
('Marcos Pereira', '87654321012', 'Suspensão e Freios', '61922223333'),
('Roberto Souza', '76543210123', 'Motor e Câmbio', '61933334444');

-- 4. INSERTS DE PEÇAS E ESTOQUE
INSERT INTO peca (nome, codigo, fabricante, preco_unitario, quantidade_estoque) VALUES
('Filtro de Óleo', 'FO-001', 'Fram', 45.00, 20),
('Pastilha de Freio Dianteira', 'PF-002', 'Fras-le', 120.00, 15),
('Amortecedor Dianteiro', 'AM-003', 'Cofap', 350.00, 8),
('Correia Dentada', 'CD-004', 'Continental', 85.00, 10),
('Vela de Ignição (Kit 4)', 'VI-005', 'NGK', 140.00, 12);

-- 5. INSERTS DE ORDENS DE SERVIÇO
INSERT INTO ordem_servico (id_veiculo, data_abertura, status, valor_total) VALUES
(1, '2026-06-01', 'paga', 165.00),
(2, '2026-06-10', 'em andamento', 470.00),
(3, '2026-06-11', 'aberta', 0.00);

-- 6. VINCULANDO MECÂNICOS ÀS ORDENS DE SERVIÇO
INSERT INTO os_mecanico (id_os, id_mecanico) VALUES
(1, 1),
(2, 2),
(2, 3),
(3, 1);

-- 7. VINCULANDO PEÇAS E SERVIÇOS ÀS ORDENS DE SERVIÇO
INSERT INTO item_servico (id_os, id_peca, quantidade, preco_unitario, descricao_servico) VALUES
(1, 1, 1, 45.00, 'Troca de óleo e filtro'),
(1, NULL, 1, 120.00, 'Mão de obra troca de óleo'),
(2, 2, 1, 120.00, 'Troca de pastilhas de freio'),
(2, 3, 1, 350.00, 'Troca de amortecedores dianteiros');