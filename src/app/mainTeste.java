package app;

import java.util.Scanner;
import java.lang.NumberFormatException;

public class mainTeste {

    public static void main(String[] args){
        Menu menu = new Menu();
        Controller controller = new Controller();
        Scanner s = new Scanner(System.in);

        int op = -1;

        try{

        } catch (NumberFormatException e){

        }

        while(op != 0){
            try {
                op = menu.escolha();

                if (op == 1) {
                    System.out.println("CADASTRAR ALUNO\n");
                    controller.cadastrarAluno(s);

                } else if (op == 2) {
                    System.out.println("CADASTRAR DISCIPLINA\n");
                    controller.cadastrarDisciplina(s);

                } else if (op == 3) {
                    System.out.println("EXCLUIR ALUNO\n");
                    controller.excluirAlunoPorMatricula(s);

                } else if (op == 4) {
                    System.out.println("CONSULTAR ALUNO POR NOME\n");
                    controller.consultarAlunoPorNome(s);

                } else if (op == 5) {
                    System.out.println("CONSULTAR ALUNO POR MATRÍCULA\n");
                    Aluno resultado = controller.consultarAlunoPorMatricula(s);
                    if (resultado != null){
                        resultado.consultar();
                    } else {
                        System.out.println("Aluno não encontrado");
                    }

                } else if (op == 6) {
                    System.out.println("INFORMAR PRÉ-REQUISITO DA DISCIPLINA\n");
                    controller.informarPreRequisitoDisciplina(s);

                } else if (op == 7) {
                    System.out.println("INFORMAR DISCIPLINA CURSADA PELO ALUNO\n");
                    controller.informarDisciplinasCursadasPorAluno(s);

                } else if (op == 8) {
                    System.out.println("VISUALIZAR ALUNOS");
                    controller.vizualizarAlunos();

                } else if (op == 9) {
                    System.out.println("VISUALIZAR DISCIPLINAS");
                    controller.vizualizarDisciplinas();

                }
            } catch (NumberFormatException e){
                System.out.println("Formato invalido, tente novamente");
                op = -1;
            }
        }
    }
}
