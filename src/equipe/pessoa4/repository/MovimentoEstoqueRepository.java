package equipe.pessoa4.repository;

import equipe.pessoa4.model.MovimentoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentoEstoqueRepository {

    // Registra cada entrada ou saída para manter histórico do estoque.
    public void registrar(Connection connection, MovimentoEstoque movimento) throws SQLException {
        String sql = "INSERT INTO movimentos_estoque (peca_id, tipo, quantidade, data_hora, observacao) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, movimento.getPecaId());
            statement.setString(2, movimento.getTipo().name());
            statement.setInt(3, movimento.getQuantidade());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(movimento.getDataHora()));
            statement.setString(5, movimento.getObservacao());
            statement.executeUpdate();
        }
    }
}