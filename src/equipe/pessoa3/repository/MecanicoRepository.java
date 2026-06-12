package equipe.pessoa3.repository;

import equipe.pessoa3.model.Mecanico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicoRepository {

    private Connection conn;

    public MecanicoRepository(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Mecanico m) throws SQLException {
        String sql = "INSERT INTO mecanico (nome, especialidade, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEspecialidade());
            stmt.setString(3, m.getTelefone());
            stmt.executeUpdate();
        }
    }

    public List<Mecanico> listar() throws SQLException {
        List<Mecanico> lista = new ArrayList<>();
        String sql = "SELECT * FROM mecanico";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mecanico m = new Mecanico();
                m.setId(rs.getInt("id_mecanico"));
                m.setNome(rs.getString("nome"));
                m.setEspecialidade(rs.getString("especialidade"));
                m.setTelefone(rs.getString("telefone"));
                lista.add(m);
            }
        }
        return lista;
    }

    public Mecanico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM mecanico WHERE id_mecanico = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Mecanico m = new Mecanico();
                    m.setId(rs.getInt("id_mecanico"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    m.setTelefone(rs.getString("telefone"));
                    return m;
                }
            }
        }
        return null;
    }

    public void atualizar(Mecanico m) throws SQLException {
        String sql = "UPDATE mecanico SET nome=?, especialidade=?, telefone=? WHERE id_mecanico=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEspecialidade());
            stmt.setString(3, m.getTelefone());
            stmt.setInt(4, m.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM mecanico WHERE id_mecanico=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}