package visao;

import aplicacao.*;
import persistencia.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        int opcao, id, i;
        String nome, telefone, email;
        Conexao con = new Conexao();
        ContatoDAO.setCon(con);
        ArrayList<Contato> relatorio = new ArrayList<Contato>();
        Contato contato = new Contato(null, null, null);
    
    do{
        System.out.println("------------------------");
        System.out.println("1 - Buscar Contato");
        System.out.println("2 - Incluir Contato");
        System.out.println("3 - Alterar Contato");
        System.out.println("4 - Excluir Contato");
        System.out.println("5 - Relatório de Contatos");
        System.out.println("6 - Sair do Sistema");
        System.out.println("Opção desejada: ");
        opcao = teclado.nextInt();

        switch(opcao){
            case 1:
            System.out.println("------------------------");
            System.out.println("1 - Buscar pelo ID");
            System.out.println("2 - Buscar pelo nome");
            System.out.println("3 - Voltar ao menu principal");
            System.out.println("Opção desejada: ");
            opcao = teclado.nextInt();
                if (opcao == 1){
                    System.out.println("Digite o id: ");
                    id = teclado.nextInt();
                    contato = ContatoDAO.BuscaID(id);
                    System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome() + ", Telefone: " + contato.getTelefone() + ", Email: " + contato.getEmail());
                } else if (opcao == 2){
                    teclado.nextLine();
                    System.out.println("Digite o nome: ");
                    nome = teclado.nextLine();
                    contato = ContatoDAO.BuscaNome(nome);
                    System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome() + ", Telefone: " + contato.getTelefone() + ", Email: " + contato.getEmail());
                }
                else{
                    break;
                }
            break;
            
            case 2:
            teclado.nextLine();
            System.out.println("Digite o nome do contato: ");
            nome = teclado.nextLine();
            System.out.println("Digite o telefone do contato: ");
            telefone = teclado.nextLine();
            System.out.println("Digite o email do contato: ");
            email = teclado.nextLine();
        
            Contato c1 = new Contato(nome, telefone, email);
            ContatoDAO.inserirContato(c1);
            break;

            case 3:
            teclado.nextLine();
            System.out.println("Digite o id para alterar: ");
            id = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Digite o novo nome do contato: ");
            nome = teclado.nextLine();
            System.out.println("Digite o novo telefone do contato: ");
            telefone = teclado.nextLine();
            System.out.println("Digite o novo email do contato: ");
            email = teclado.nextLine();
            Contato c2 = new Contato(nome, telefone, email);
            ContatoDAO.Alterar(id, c2);
            break;
            
            case 4:
            teclado.nextLine();
            System.out.println("Digite o id para excluir: ");
            id = teclado.nextInt();
            teclado.nextLine();
            ContatoDAO.Excluir(id);
            break;

            case 5:
            relatorio = ContatoDAO.Relatorio();
            for (i = 0; i < relatorio.size(); i++){
                Contato c3 = relatorio.get(i);
                System.out.println("ID: " + c3.getId() + ", Nome: " + c3.getNome() + ", Telefone: " + c3.getTelefone() + ", Email: " + c3.getEmail());
            }

            break;

            case 6:
              System.out.println("Fim do programa");
            break;
        }
    }while(opcao != 6);


        

       teclado.close();
    }
}
