package equipe.pessoa2;

import equipe.pessoa2.model.Cliente;
import repository.ClienteRepository;
import repository.ClienteRepositoryList;

import java.util.ArrayList;
import java.util.List;

public class TesteCliente {

    public static void main(String[] args) {
        // Lista simulada como banco
        List<Cliente> banco = new ArrayList<>();

        // Repositório usando a lista
        ClienteRepositoryList repo = new ClienteRepositoryList(banco);

        // Criar um cliente
        Cliente c1 = new Cliente();
        c1.setName("João Silva");
        c1.setEmail("joao@email.com");
        c1.setCpf("123456789");
        c1.setPhone("61999999999");

        try {
            // Verificar se o CPF já existe
            if (!repo.existeCpf(c1.getCpf())) {
                repo.inserir(c1);
                System.out.println("Cliente inserido com sucesso!");
            } else {
                System.out.println("CPF já existe!");
            }

            // Tentar inserir outro cliente com o mesmo CPF
            Cliente c2 = new Cliente();
            c2.setName("Maria Souza");
            c2.setEmail("maria@email.com");
            c2.setCpf("11669672557"); // Mesmo CPF
            c2.setPhone("61888888888");

            if (!repo.existeCpf(c2.getCpf())) {
                repo.inserir(c2);
                System.out.println("Cliente inserido com sucesso!");
            } else {
                System.out.println("CPF já existe! Não foi inserido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}