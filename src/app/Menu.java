package app;

import java.util.Scanner;

public class Menu {

    String strMenu;
    int op = -1;

    public Menu(){

        strMenu = """
                \n
                _______________________________________________________
                MENU
                _______________________________________________________
                
                (1) Cadastrar Alunos
                (2) Cadastrar Disciplinas
                (3) Excluir aluno por Matrícula
                (4) Consultar Aluno por Nome
                (5) Consultar Aluno por Matrícula
                (6) Informar pré-requisito da Disciplina
                (7) Informar Disciplinas cursada pelo Aluno
                (8) Visualizar Alunos
                (9) Visualizar Disciplina
                (0) SAIR
                
                Digite o numero da opção desejada:
                """;
    }

    public int escolha(){

        Scanner s = new Scanner(System.in);

        System.out.println(strMenu);
        op = Integer.parseInt(s.nextLine());

        while(op < 0 || op > 10){
            System.out.println("OPÇÃO INVÁLIDA");
            System.out.println(strMenu);
            op = Integer.parseInt(s.nextLine());
        }

        return op;
    }
}
