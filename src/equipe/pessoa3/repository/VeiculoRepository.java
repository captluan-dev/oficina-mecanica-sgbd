package equipe.pessoa3.repository;

import equipe.pessoa3.model.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    private Connection conn;

    public VeiculoRepository(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Veiculo v) throws SQLException {
        String sql = "INSERT INTO veiculo (placa, modelo, marca, ano, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getMarca());
            stmt.setInt(4, v.getAno());
            stmt.setInt(5, v.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public boolean placaExiste(String placa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculo WHERE placa = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public List<Veiculo> listar() throws SQLException {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id_veiculo"));
                v.setPlaca(rs.getString("placa"));
                v.setModelo(rs.getString("modelo"));
                v.setMarca(rs.getString("marca"));
                v.setAno(rs.getInt("ano"));
                v.setIdCliente(rs.getInt("id_cliente"));
                lista.add(v);
            }
        }
        return lista;
    }

    public Veiculo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE id_veiculo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veiculo v = new Veiculo();
                    v.setId(rs.getInt("id_veiculo"));
                    v.setPlaca(rs.getString("placa"));
                    v.setModelo(rs.getString("modelo"));
                    v.setMarca(rs.getString("marca"));
                    v.setAno(rs.getInt("ano"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    return v;
                }
            }
        }
        return null;
    }

    public void atualizar(Veiculo v) throws SQLException {
        String sql = "UPDATE veiculo SET placa=?, modelo=?, marca=?, ano=?, id_cliente=? WHERE id_veiculo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getMarca());
            stmt.setInt(4, v.getAno());
            stmt.setInt(5, v.getIdCliente());
            stmt.setInt(6, v.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM veiculo WHERE id_veiculo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}