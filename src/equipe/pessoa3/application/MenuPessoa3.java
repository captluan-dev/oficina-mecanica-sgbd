package equipe.pessoa3.application;

import java.util.Scanner;

public class MenuPessoa3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== OFICINA (MÓDULO VEÍCULOS E MECÂNICOS) ===");
            System.out.println("1-Cadastrar Veículo");
            System.out.println("2-Listar Veículos");
            System.out.println("3-Atualizar Veículo");
            System.out.println("4-Excluir Veículo");
            System.out.println("5-Cadastrar Mecânico");
            System.out.println("6-Listar Mecânicos");
            System.out.println("7-Atualizar Mecânico");
            System.out.println("8-Excluir Mecânico");
            System.out.println("0-Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch(opcao){
                case 1:
                    System.out.println("Executando: Cadastrar veículo...");
                    break;
                case 2:
                    System.out.println("Executando: Listar veículos...");
                    break;
                case 3:
                    System.out.println("Executando: Atualizar veículo...");
                    break;
                case 4:
                    System.out.println("Executando: Excluir veículo...");
                    break;
                case 5:
                    System.out.println("Executando: Cadastrar mecânico...");
                    break;
                case 6:
                    System.out.println("Executando: Listar mecânicos...");
                    break;
                case 7:
                    System.out.println("Executando: Atualizar mecânico...");
                    break;
                case 8:
                    System.out.println("Executando: Excluir mecânico...");
                    break;
                case 0:
                    System.out.println("Encerrando módulo da Pessoa 3...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 0);
        sc.close();
    }
}