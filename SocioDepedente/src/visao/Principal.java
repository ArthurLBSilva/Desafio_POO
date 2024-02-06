package visao;

import aplicacao.*;
import persistencia.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        int opcao, cpf, i, opcao2, idade, id;
        boolean teste;
        String nome, data;
        Conexao con = new Conexao();
        SocioDAO.setCon(con);
        DependenteDAO.setCon(con);
        ArrayList<Socio> relatorio = new ArrayList<Socio>();
        ArrayList<Dependente> relatorio2 = new ArrayList<Dependente>();
        Socio c1 = new Socio();
        Dependente d4 = new Dependente();

        do {
            System.out.println("------------------------");
            System.out.println("1 - Buscar Sócio");
            System.out.println("2 - Incluir Sócio");
            System.out.println("3 - Alterar Sócio");
            System.out.println("4 - Excluir Sócio");
            System.out.println("5 - Relatório de Sócios");
            System.out.println("6 - Sair do Sistema");
            System.out.println("Opção desejada: ");
            opcao = teclado.nextInt();
            teclado.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Qual será o cpf?");
                    cpf = teclado.nextInt();
                    c1 = SocioDAO.BuscaCPF(cpf);
                    System.out.println(
                            "CPF: " + c1.getCpf_s() + ", Nome: " + c1.getNome_s() + ", Datas: " + c1.getData_adm());
                    System.out.println("------------------------");
                    System.out.println("1 - Buscar Dependente");
                    System.out.println("2 - Incluir Dependente");
                    System.out.println("3 - Alterar Dependente");
                    System.out.println("4 - Excluir Dependente");
                    System.out.println("5 - Relatório de Dependentes");
                    System.out.println("6 - Sair do Sistema");
                    System.out.println("Opção desejada: ");
                    opcao2 = teclado.nextInt();
                    teclado.nextLine();
                    switch (opcao2) {
                        case 1:
                            System.out.println("Qual será o Id?");
                            int id2 = teclado.nextInt();
                            d4 = DependenteDAO.BuscaID(id2);
                            System.out.println("ID: " + d4.getId() + ", Nome: " + d4.getNome_d() + ", Idade: " + d4.getIdade());
                            break;

                        case 2:
                            System.out.println("Qual será a idade? ");
                            idade = teclado.nextInt();
                            teclado.nextLine();
                            System.out.println("Qual será o nome do Dependente? ");
                            nome = teclado.nextLine();
                            Dependente d1 = new Dependente(idade, nome);
                            DependenteDAO.inserirDependente(d1, cpf);
                            break;

                        case 3:
                            teste = true;
                            do {
                                System.out.println("Qual será o id?");
                                id = teclado.nextInt();
                                teclado.nextLine();
                                System.out.println("Qual será o novo nome do Dependente?");
                                nome = teclado.nextLine();
                                System.out.println("Qual será a nova idade do Dependente?");
                                idade = teclado.nextInt();
                                System.out.println("Qual será o novo CPF do novo Socio?");
                                cpf = teclado.nextInt();
                                teste = DependenteDAO.BuscaCPF(cpf);
                                if (teste == true) {
                                    Dependente d2 = new Dependente(idade, nome);
                                    DependenteDAO.alterarDependente(cpf, d2, id);
                                } else {
                                    System.out.println("Não existe Socio com esse CPF");
                                }
                            } while (teste == false);
                            break;

                        case 4:
                            System.out.println("Digite o id para excluir: ");
                            id = teclado.nextInt();
                            teclado.nextLine();
                            DependenteDAO.Excluir(id);
                            break;
                        case 5:
                            relatorio2 = DependenteDAO.Relatorio(cpf);
                            for (i = 0; i < relatorio2.size(); i++) {
                                Dependente c3 = relatorio2.get(i);
                                System.out.println("ID: " + c3.getId() + ", Nome: " + c3.getNome_d() + ", Idade: " + c3.getIdade());
                            }
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Qual será o cpf? ");
                    cpf = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Qual será o nome do Sócio? ");
                    nome = teclado.nextLine();
                    System.out.println("Qual é a data? ");
                    data = teclado.nextLine();
                    Socio s1 = new Socio(cpf, nome, data);
                    SocioDAO.inserirSocio(s1);
                    break;

                case 3:
                    System.out.println("Digite o cpf para alterar: ");
                    cpf = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Digite o novo nome do socio: ");
                    nome = teclado.nextLine();
                    System.out.println("Digite a nova data: ");
                    data = teclado.nextLine();
                    Socio s2 = new Socio(cpf, nome, data);
                    SocioDAO.alterarSocio(cpf, s2);
                    break;

                case 4:
                    System.out.println("Digite o cpf para excluir: ");
                    cpf = teclado.nextInt();
                    teclado.nextLine();
                    SocioDAO.Excluir(cpf);
                    break;

                case 5:
                    relatorio = SocioDAO.Relatorio();
                    for (i = 0; i < relatorio.size(); i++) {
                        Socio c3 = relatorio.get(i);
                        System.out.println(
                                "CPF: " + c3.getCpf_s() + ", Nome: " + c3.getNome_s() + ", Datas: " + c3.getData_adm());
                    }
                    break;
            }

        } while (opcao != 6);

        teclado.close();
    }
}
