package equipe.pessoa4.service;

import equipe.pessoa4.model.MovimentoEstoque;
import equipe.pessoa4.model.MovimentoEstoque.TipoMovimento;
import equipe.pessoa4.model.Peca;
import equipe.pessoa4.repository.MovimentoEstoqueRepository;
import equipe.pessoa4.repository.PecaRepository;
import equipe.pessoa4.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PecaService {

    // Validações de negócio e persistência ficam centralizadas aqui.
    private final PecaRepository pecaRepository = new PecaRepository();
    private final MovimentoEstoqueRepository movimentoRepository = new MovimentoEstoqueRepository();

    // Cadastra peça nova, impede código duplicado e registra o estoque inicial quando existir.
    public void cadastrar(Peca peca) throws SQLException {
        validarPeca(peca);

        try (Connection connection = DatabaseConnection.getConnection()) {
            // A transação garante que peça e movimentação sejam gravadas juntas.
            connection.setAutoCommit(false);
            try {
                if (pecaRepository.buscarPorCodigo(connection, peca.getCodigo()) != null) {
                    throw new IllegalArgumentException("Já existe uma peça com o código informado.");
                }

                pecaRepository.inserir(connection, peca);

                if (peca.getQuantidadeEstoque() > 0) {
                    movimentoRepository.registrar(connection, new MovimentoEstoque(
                            peca.getId(),
                            TipoMovimento.ENTRADA,
                            peca.getQuantidadeEstoque(),
                            "Cadastro inicial"
                    ));
                }

                connection.commit();
            } catch (SQLException | RuntimeException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }

    // Atualiza a peça sem permitir que o código seja repetido em outro registro.
    public void atualizar(Peca peca) throws SQLException {
        if (peca.getId() == null) {
            throw new IllegalArgumentException("Informe o id da peça para atualização.");
        }

        validarPeca(peca);

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Peca existente = pecaRepository.buscarPorCodigo(connection, peca.getCodigo());
                if (existente != null && !existente.getId().equals(peca.getId())) {
                    throw new IllegalArgumentException("Já existe outra peça com o mesmo código.");
                }

                pecaRepository.atualizar(connection, peca);
                connection.commit();
            } catch (SQLException | RuntimeException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }

    // Remove a peça informada pelo id.
    public void excluir(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("Informe o id da peça para exclusão.");
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try {
                pecaRepository.excluir(connection, id);
                connection.commit();
            } catch (SQLException | RuntimeException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }

    // Lista todas as peças para o menu de consulta.
    public List<Peca> listarTodas() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return pecaRepository.listarTodas(connection);
        }
    }

    // Busca uma peça específica pelo id.
    public Peca buscarPorId(Long id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return pecaRepository.buscarPorId(connection, id);
        }
    }

    // Registra entrada de peças no estoque.
    public void darEntrada(Long pecaId, int quantidade, String observacao) throws SQLException {
        movimentarEstoque(pecaId, quantidade, TipoMovimento.ENTRADA, observacao);
    }

    // Registra saída de peças do estoque.
    public void darSaida(Long pecaId, int quantidade, String observacao) throws SQLException {
        movimentarEstoque(pecaId, quantidade, TipoMovimento.SAIDA, observacao);
    }

    // Atualiza o saldo da peça e grava o histórico da movimentação.
    private void movimentarEstoque(Long pecaId, int quantidade, TipoMovimento tipoMovimento, String observacao) throws SQLException {
        if (pecaId == null) {
            throw new IllegalArgumentException("Informe o id da peça.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int estoqueAtual = pecaRepository.consultarQuantidadeEmEstoque(connection, pecaId);
                int novoSaldo = tipoMovimento == TipoMovimento.ENTRADA
                        ? estoqueAtual + quantidade
                        : estoqueAtual - quantidade;

                if (novoSaldo < 0) {
                    throw new IllegalStateException("Operação bloqueada para evitar estoque negativo.");
                }

                int delta = tipoMovimento == TipoMovimento.ENTRADA ? quantidade : -quantidade;
                pecaRepository.ajustarEstoque(connection, pecaId, delta);
                movimentoRepository.registrar(connection, new MovimentoEstoque(pecaId, tipoMovimento, quantidade, observacao));
                connection.commit();
            } catch (SQLException | RuntimeException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }

    // Valida campos obrigatórios antes de falar com o banco.
    private void validarPeca(Peca peca) {
        if (peca == null) {
            throw new IllegalArgumentException("Informe os dados da peça.");
        }

        if (isBlank(peca.getCodigo())) {
            throw new IllegalArgumentException("O código da peça é obrigatório.");
        }

        if (isBlank(peca.getNome())) {
            throw new IllegalArgumentException("O nome da peça é obrigatório.");
        }

        if (peca.getPrecoUnitario() < 0) {
            throw new IllegalArgumentException("O preço unitário deve ser informado e não pode ser negativo.");
        }

        if (peca.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
    }

    // Ajuda a validar strings vazias sem depender de bibliotecas externas.
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}