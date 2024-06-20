package app;

import java.util.ArrayList;
import java.util.Locale;

public class Disciplina {
    private int codigo;
    private String nome;
    private int cargaHoraria;
    private ArrayList<String> preRequisito;

    public Disciplina(String nome, int cod, int cargaHoraria) {
        this.setNome(nome.toLowerCase());
        this.setCodigo(cod);
        this.setCargaHoraria(cargaHoraria);
        this.setPreRequisito(new ArrayList<>());
    }

    public String toString() {
        return "Nome da Disciplina: " + this.getNome() + " - Código: " + this.getCodigo();
    }

    public void adicionarPreRequisito (String requisito){

        this.preRequisito.add(requisito);
    }

//    public void consultar(){
//        System.out.println("código: " + this.getCodigo());
//        System.out.println("nome: " + this.getNome());
//        System.out.println("cargaHoraria: " + this.getCargaHoraria());
//        for (int i = 0; i < this.preRequisito.size(); i++) {
//            System.out.println(this.preRequisito.get(i));
//        }
//    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return this.cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public ArrayList<String> getPreRequisito() {
        return this.preRequisito;
    }

    public void setPreRequisito(ArrayList<String> preRequisito) {
        this.preRequisito = preRequisito;
    }
}

