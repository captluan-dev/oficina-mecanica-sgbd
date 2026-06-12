package repository;

import equipe.pessoa2.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seu_banco";
        String usuario = "seu_usuario";
        String senha = "sua_senha";
        return DriverManager.getConnection(url, usuario, senha);
    }

    //metodo de buscar,listar,atualizar cadastro e excluir

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (name, email, cpf, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getPhone());
            stmt.executeUpdate();
        }
    }

    //buscar por cpf(unico)
    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setCpf(rs.getString("cpf"));
                    c.setPhone(rs.getString("phone"));
                    return c;
                } else {
                    return null; // Não encontrado
                }
            }
        }
    }

    //atualizar
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET name = ?, email = ?, phone = ? WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getPhone());
            stmt.setString(4, cliente.getCpf());
            stmt.executeUpdate();
        }
    }

    //Excluir
    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    // Este método verifica se o CPF já existe na base de dados antes de inserir um novo cliente.
    //  Ele deve ser chamado antes de qualquer operação de inserção para garantir a integridade dos dados e evitar duplicidade de CPF.
    public boolean existeCpf(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Se for maior que zero, já existe
                }
            }
        }
        return false; // Não existe
    }
}