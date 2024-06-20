package app;

import java.util.ArrayList;

/**
 *
 * @author victoriocarvalho
 *
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos
 * programas de teste para redigir os relatórios.
 */

public class Aluno  {
    private int matricula;
    private String nome;
    private ArrayList<Disciplina> disciplinasCurs;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplinasCurs = new ArrayList<>();
    }

    public String toString() {
        return "Matricula: " + this.matricula + " - Nome: " + this.nome;
    }

    public void consultar(){
        System.out.println(this.toString());
        for (int i = 0; i < this.disciplinasCurs.size(); i++) {
            System.out.println(this.disciplinasCurs.get(i).toString());
        }
    }

    public void adicionarDiscCurs (Disciplina disciplina) {
        if (verficarDiscCurs(disciplina.getPreRequisito())) {
            disciplinasCurs.add(disciplina);
            System.out.println("Disciplina adicionada com sucesso!");
        }
        else {
            System.out.println("Disciplina " + disciplina.getNome() + " não pode ser cursada");
        }
    }

    public boolean verficarDiscCurs (ArrayList<String> preRequi) {
        boolean vef = true;
        ArrayList<String> disciplinasCursNome = getDisciplinasCursNome();
        for (String d : preRequi) {
            if (!disciplinasCursNome.contains(d)) {
                vef = false;
                System.out.println("Disciplina " + d + " não cursada");
            }
        }
        return vef;
    }

    public ArrayList<String> getDisciplinasCursNome() {
        ArrayList<String> nomes = new ArrayList<>();
        for (int i = 0; i < disciplinasCurs.size(); i++) {
            nomes.add(disciplinasCurs.get(i).getNome());
        }
        return nomes;
    }



    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
