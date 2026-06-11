package equipe.pessoa4.repository;

import equipe.pessoa4.model.Peca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PecaRepository {

    // Insere uma peça nova e recupera o id gerado pelo banco.
    public void inserir(Connection connection, Peca peca) throws SQLException {
        String sql = "INSERT INTO pecas (codigo, nome, descricao, preco_unitario, quantidade_estoque) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, peca.getCodigo());
            statement.setString(2, peca.getNome());
            statement.setString(3, peca.getDescricao());
            statement.setDouble(4, peca.getPrecoUnitario());
            statement.setInt(5, peca.getQuantidadeEstoque());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    peca.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    // Atualiza os dados da peça localizada pelo id.
    public void atualizar(Connection connection, Peca peca) throws SQLException {
        String sql = "UPDATE pecas SET codigo = ?, nome = ?, descricao = ?, preco_unitario = ?, quantidade_estoque = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, peca.getCodigo());
            statement.setString(2, peca.getNome());
            statement.setString(3, peca.getDescricao());
            statement.setDouble(4, peca.getPrecoUnitario());
            statement.setInt(5, peca.getQuantidadeEstoque());
            statement.setLong(6, peca.getId());
            statement.executeUpdate();
        }
    }

    // Remove a peça do cadastro.
    public void excluir(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM pecas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Busca uma peça pelo id; retorna null quando não encontra registro.
    public Peca buscarPorId(Connection connection, Long id) throws SQLException {
        String sql = "SELECT id, codigo, nome, descricao, preco_unitario, quantidade_estoque FROM pecas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapear(resultSet);
                }
            }
        }

        return null;
    }

    // Busca por código para validar duplicidade antes de inserir ou atualizar.
    public Peca buscarPorCodigo(Connection connection, String codigo) throws SQLException {
        String sql = "SELECT id, codigo, nome, descricao, preco_unitario, quantidade_estoque FROM pecas WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapear(resultSet);
                }
            }
        }

        return null;
    }

    // Carrega todas as peças para exibição na listagem do console.
    public List<Peca> listarTodas(Connection connection) throws SQLException {
        String sql = "SELECT id, codigo, nome, descricao, preco_unitario, quantidade_estoque FROM pecas ORDER BY nome";
        List<Peca> pecas = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                pecas.add(mapear(resultSet));
            }
        }

        return pecas;
    }

    // Consulta o saldo atual para permitir bloqueio de saída maior que o estoque.
    public int consultarQuantidadeEmEstoque(Connection connection, Long pecaId) throws SQLException {
        String sql = "SELECT quantidade_estoque FROM pecas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, pecaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("quantidade_estoque");
                }
            }
        }

        throw new SQLException("Peça não encontrada: " + pecaId);
    }

    // Soma ou subtrai do estoque com base no delta recebido.
    public void ajustarEstoque(Connection connection, Long pecaId, int quantidadeDelta) throws SQLException {
        String sql = "UPDATE pecas SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantidadeDelta);
            statement.setLong(2, pecaId);
            statement.executeUpdate();
        }
    }

    // Converte a linha do ResultSet em objeto Java.
    private Peca mapear(ResultSet resultSet) throws SQLException {
        return new Peca(
                resultSet.getLong("id"),
                resultSet.getString("codigo"),
                resultSet.getString("nome"),
                resultSet.getString("descricao"),
                resultSet.getDouble("preco_unitario"),
                resultSet.getInt("quantidade_estoque")
        );
    }
}