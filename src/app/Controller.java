package app;

import lib.ArvoreBinaria;
import lib.IArvoreBinaria;

import java.util.Scanner;

public class Controller {

    private IArvoreBinaria<Aluno> arvAlunos;
    private IArvoreBinaria<Disciplina> arvDisciplina;

    public Controller() {

        ComparadorAlunoPorMatricula compPorMatricula = new ComparadorAlunoPorMatricula();
        ComparadorDisciplinaPorCodigo compPorCodigo = new ComparadorDisciplinaPorCodigo();

        this.arvAlunos = new ArvoreBinaria<>(compPorMatricula);
        this.arvDisciplina = new ArvoreBinaria<>(compPorCodigo);

    }

    //metodos

    public void cadastrarAluno(Scanner s){
        try {
            //lê o nome e matrícula do aluno a ser cadastrado
            System.out.printf("Matrícula do aluno: ");
            int mat = Integer.parseInt(s.nextLine());

            System.out.printf("Nome do aluno: ");
            String nome = s.nextLine();

            //cria novo aluno e o adiciona na árvore
            Aluno aluno = new Aluno(mat, nome);
            if (this.arvAlunos.pesquisar(aluno) == null){
                this.arvAlunos.adicionar(aluno);
                System.out.println("Aluno cadastrado com sucesso");
            } else {
                System.out.println("Matrícula de aluno já existente. Novo Aluno não adicionado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Matrícula inválida. Por favor, insira um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    public void cadastrarDisciplina (Scanner s){
        try {
            //lê o nome, codigo e carga horaria da disciplina
            System.out.printf("Nome da disciplina: ");
            String nome = s.nextLine();

            System.out.printf("Código da disciplina: ");
            int cod = Integer.parseInt(s.nextLine());

            System.out.printf("Carga horária da disciplina: ");
            int carga = Integer.parseInt(s.nextLine());

            //cria nova disciplina e a adiciona na árvore
            Disciplina disciplina = new Disciplina(nome, cod, carga);
            if (this.arvDisciplina.pesquisar(disciplina) == null){
                this.arvDisciplina.adicionar(disciplina);
                System.out.println("Disciplina cadastrada com sucesso");
            } else {
                System.out.println("Código de disciplina já existente. Nova Disciplina não adicionada");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Código ou carga horária inválidos. Por favor, insira números válidos.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }


}
