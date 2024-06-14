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

    public void informarPreRequisitoDisciplina(Scanner s){
        try {
            System.out.printf("Digite o código da Disciplina pré-requisito a ser procurada: ");
            int codPreReq = Integer.parseInt(s.nextLine());
            System.out.println("Agora digite o código da Disciplina que terá esse pré requisito: ");
            int codDisciplina = Integer.parseInt(s.nextLine());

            Disciplina preReq = this.arvDisciplina.pesquisar(new Disciplina("", codPreReq, 0));
            Disciplina disc = this.arvDisciplina.pesquisar(new Disciplina("", codDisciplina, 0));

            if (preReq != null && disc != null){
                disc.adicionarPreRequisito(preReq.getNome());
                System.out.println("Requisito adicionado");
            } else {
                System.out.println("Alguma disciplina não existe. Primeiro crie a disciplina para depois manipular a mesma");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Código de disciplina inválido. Por favor, insira números válidos.");
        } catch (Exception e) {
            System.out.println("Erro ao informar pré-requisito da disciplina: " + e.getMessage());
        }
    }

    public void informarDisciplinasCursadasPorAluno(Scanner s) {
        try {
            System.out.printf("Digite a matrícula do aluno: ");
            int matInformada = Integer.parseInt(s.nextLine());

            System.out.printf("Digite o código da disciplina: ");
            int codDis = Integer.parseInt(s.nextLine());

            Aluno resultado = this.arvAlunos.pesquisar(new Aluno(matInformada, ""));

            if (resultado != null){
                Disciplina disc = this.arvDisciplina.pesquisar(new Disciplina("", codDis, 0));

                if (disc != null){
                    resultado.adicionarDiscCurs(disc);
                } else {
                    System.out.println("Disciplina não existe.");
                }
            } else {
                System.out.println("Aluno não encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Matrícula ou código de disciplina inválido. Por favor, insira números válidos.");
        } catch (Exception e) {
            System.out.println("Erro ao informar disciplinas cursadas pelo aluno: " + e.getMessage());
        }
    }


}
