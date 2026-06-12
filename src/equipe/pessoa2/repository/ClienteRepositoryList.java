package repository;

import equipe.pessoa2.model.Cliente;

import java.util.ArrayList;
import java.util.List;


// Esta classe ClienteRepositoryLista, é uma implementação do repositório que usa uma lista
// em memória para armazenar clientes.
// Ao contrário do repositório que se conecta ao banco,
// este é usado apenas para testes ou simulações.
// Todos os métodos (inserir, buscar, atualizar, excluir) operam diretamente na lista,
// permitindo validar a lógica de negócio sem um banco real.





public class ClienteRepositoryList {

    private List<Cliente> banco;

    public ClienteRepositoryList(List<Cliente> banco) {
        this.banco = banco;
    }

    public void inserir(Cliente cliente) {
        banco.add(cliente);
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : banco) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public void atualizar(Cliente cliente) {
        for (int i = 0; i < banco.size(); i++) {
            if (banco.get(i).getCpf().equals(cliente.getCpf())) {
                banco.set(i, cliente); // Atualiza o cliente
                return;
            }
        }
    }

    public void excluir(String cpf) {
        banco.removeIf(c -> c.getCpf().equals(cpf));
    }

    public boolean existeCpf(String cpf) {
        for (Cliente c : banco) {
            if (c.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}