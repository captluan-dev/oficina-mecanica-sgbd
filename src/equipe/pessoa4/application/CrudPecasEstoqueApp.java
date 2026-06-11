package equipe.pessoa4.application;

import equipe.pessoa4.model.Peca;
import equipe.pessoa4.service.PecaService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CrudPecasEstoqueApp {

    // Ponto de entrada do CRUD em modo console.
    public static void main(String[] args) {
        PecaService service = new PecaService();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executar = true;

            while (executar) {
                exibirMenu();
                int opcao = lerInteiro(scanner, "Escolha uma opção: ");

                try {
                    switch (opcao) {
                        case 1 -> cadastrarPeca(scanner, service);
                        case 2 -> listarPecas(service);
                        case 3 -> atualizarPeca(scanner, service);
                        case 4 -> excluirPeca(scanner, service);
                        case 5 -> movimentarEntrada(scanner, service);
                        case 6 -> movimentarSaida(scanner, service);
                        case 0 -> executar = false;
                        default -> System.out.println("Opção inválida.");
                    }
                } catch (SQLException | RuntimeException ex) {
                    System.out.println("Erro: " + ex.getMessage());
                }
            }
        }
    }

    // Exibe o menu principal do módulo de peças e estoque.
    private static void exibirMenu() {
        System.out.println();
        System.out.println("=== CRUD Peças e Estoque ===");
        System.out.println("1 - Cadastrar peça");
        System.out.println("2 - Listar peças");
        System.out.println("3 - Atualizar peça");
        System.out.println("4 - Excluir peça");
        System.out.println("5 - Entrada de estoque");
        System.out.println("6 - Saída de estoque");
        System.out.println("0 - Sair");
    }

    // Lê os dados da peça e envia para o service.
    private static void cadastrarPeca(Scanner scanner, PecaService service) throws SQLException {
        Peca peca = lerPeca(scanner);
        service.cadastrar(peca);
        System.out.println("Peça cadastrada com sucesso.");
    }

    // Mostra todas as peças cadastradas na tela.
    private static void listarPecas(PecaService service) throws SQLException {
        List<Peca> pecas = service.listarTodas();
        if (pecas.isEmpty()) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }

        pecas.forEach(System.out::println);
    }

    // Lê a peça, define o id e envia a alteração ao service.
    private static void atualizarPeca(Scanner scanner, PecaService service) throws SQLException {
        Peca peca = lerPeca(scanner);
        peca.setId(lerLong(scanner, "Informe o id da peça: "));
        service.atualizar(peca);
        System.out.println("Peça atualizada com sucesso.");
    }

    // Remove o registro informado pelo usuário.
    private static void excluirPeca(Scanner scanner, PecaService service) throws SQLException {
        Long id = lerLong(scanner, "Informe o id da peça: ");
        service.excluir(id);
        System.out.println("Peça excluída com sucesso.");
    }

    // Adiciona saldo ao estoque da peça selecionada.
    private static void movimentarEntrada(Scanner scanner, PecaService service) throws SQLException {
        Long id = lerLong(scanner, "Informe o id da peça: ");
        int quantidade = lerInteiro(scanner, "Quantidade de entrada: ");
        System.out.print("Observação: ");
        String observacao = scanner.nextLine();
        service.darEntrada(id, quantidade, observacao);
        System.out.println("Entrada registrada com sucesso.");
    }

    // Retira saldo do estoque da peça selecionada.
    private static void movimentarSaida(Scanner scanner, PecaService service) throws SQLException {
        Long id = lerLong(scanner, "Informe o id da peça: ");
        int quantidade = lerInteiro(scanner, "Quantidade de saída: ");
        System.out.print("Observação: ");
        String observacao = scanner.nextLine();
        service.darSaida(id, quantidade, observacao);
        System.out.println("Saída registrada com sucesso.");
    }

    // Monta o objeto Peca com os dados digitados no console.
    private static Peca lerPeca(Scanner scanner) {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço unitário: ");
        double precoUnitario = parseDecimal(scanner.nextLine().trim());
        int quantidadeEstoque = lerInteiro(scanner, "Quantidade em estoque: ");

        return new Peca(codigo, nome, descricao, precoUnitario, quantidadeEstoque);
    }

    // Lê números inteiros sem depender de classes extras para tratamento.
    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return parseInteiro(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    // Lê identificadores long para usar em buscas e operações de estoque.
    private static Long lerLong(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return parseLongo(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    // Converte texto em inteiro usando lógica manual simples.
    private static int parseInteiro(String valor) {
        if (valor.isEmpty()) {
            throw new NumberFormatException("Valor vazio");
        }

        int sinal = 1;
        int indice = 0;
        if (valor.charAt(0) == '-') {
            sinal = -1;
            indice = 1;
        }

        int resultado = 0;
        for (; indice < valor.length(); indice++) {
            char caractere = valor.charAt(indice);
            if (caractere < '0' || caractere > '9') {
                throw new NumberFormatException("Valor inválido");
            }
            resultado = (resultado * 10) + (caractere - '0');
        }

        return resultado * sinal;
    }

    // Converte texto em long usando lógica manual simples.
    private static long parseLongo(String valor) {
        if (valor.isEmpty()) {
            throw new NumberFormatException("Valor vazio");
        }

        int sinal = 1;
        int indice = 0;
        if (valor.charAt(0) == '-') {
            sinal = -1;
            indice = 1;
        }

        long resultado = 0L;
        for (; indice < valor.length(); indice++) {
            char caractere = valor.charAt(indice);
            if (caractere < '0' || caractere > '9') {
                throw new NumberFormatException("Valor inválido");
            }
            resultado = (resultado * 10L) + (caractere - '0');
        }

        return resultado * sinal;
    }

    // Lê valores decimais para o preço unitário usando separador ponto ou vírgula.
    private static double parseDecimal(String valor) {
        if (valor.isEmpty()) {
            throw new NumberFormatException("Valor vazio");
        }

        String normalizado = valor.replace(',', '.');
        int ponto = normalizado.indexOf('.');

        if (ponto < 0) {
            return parseLongo(normalizado);
        }

        long parteInteira = parseLongo(normalizado.substring(0, ponto));
        String parteDecimalTexto = normalizado.substring(ponto + 1);
        if (parteDecimalTexto.isEmpty()) {
            return parteInteira;
        }

        long parteDecimal = parseLongo(parteDecimalTexto);
        double divisor = 1.0d;
        for (int indice = 0; indice < parteDecimalTexto.length(); indice++) {
            divisor *= 10.0d;
        }

        if (parteInteira < 0) {
            return parteInteira - (parteDecimal / divisor);
        }

        return parteInteira + (parteDecimal / divisor);
    }
}