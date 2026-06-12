package equipe.pessoa2.repository;

import equipe.pessoa2.model.Cliente;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteRepositoryTest {

    @BeforeAll
    public static void setupDatabase() throws Exception {
        // Use H2 in-memory DB for tests
        System.setProperty("db.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        System.setProperty("db.user", "sa");
        System.setProperty("db.password", "");

        try (Connection conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"), System.getProperty("db.password"))) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE cliente (id_cliente INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100) NOT NULL, cpf CHAR(11) NOT NULL UNIQUE, telefone VARCHAR(15), email VARCHAR(100), endereco VARCHAR(255), criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            }
        }
    }

    @Test
    public void testInserirEConsultarPorCpf() throws Exception {
        ClienteRepository repo = new ClienteRepository();
        Cliente c = new Cliente();
        c.setNome("Teste Unit");
        c.setEmail("teste@example.com");
        c.setCpf("99988877766");
        c.setTelefone("61900001111");

        repo.inserir(c);

        Cliente resultado = repo.buscarPorCpf("99988877766");
        assertNotNull(resultado);
        assertEquals("Teste Unit", resultado.getNome());
        assertEquals("teste@example.com", resultado.getEmail());
        assertEquals("61900001111", resultado.getTelefone());
    }
}
