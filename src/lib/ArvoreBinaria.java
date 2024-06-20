/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;
/**
 *
 * @authors
 * brunoplazzi
 * filipesuhett
 * gscalfoni
 * laraguilar
 * victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;
    protected Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }

    @Override
    public void adicionar(T novoValor) {
        // Cria um novo nó
        No<T> node = new No<>(novoValor);

        // caso a arvore esteja vazia, nó vira raiz
        if (this.raiz == null) {
            this.raiz = node;
        } else {
            adicionarRecursivo(this.raiz, node);
        }
    }

    private void adicionarRecursivo(No<T> pai, No<T> node) {
        int comparacao = comparador.compare(node.getValor(), pai.getValor());

        if (comparacao < 0) {
            adicionarAEsquerda(pai, node);
        } else if (comparacao > 0) {
            adicionarADireita(pai, node);
        }
    }

    private void adicionarAEsquerda(No<T> pai, No<T> node) {
        if (pai.getFilhoEsquerda() == null) {
            pai.setFilhoEsquerda(node);
        } else {
            adicionarRecursivo(pai.getFilhoEsquerda(), node);
        }
    }

    private void adicionarADireita(No<T> pai, No<T> node) {
        if (pai.getFilhoDireita() == null) {
            pai.setFilhoDireita(node);
        } else {
            adicionarRecursivo(pai.getFilhoDireita(), node);
        }
    }

    @Override
    public T pesquisar(T valor) {
        No<T> atual = this.raiz;

        // verifica se o nó atual está vazio
        while (atual != null) {

            /* compara o valor do parametro com o valor do nó atual utilizando o
                comparator "padrao" definido no momento da instanciação da arvore
            */
            if (this.comparador.compare(valor, atual.getValor()) == 0) {
                return atual.getValor();
            }

            // verifica se o valor está a esquerda ou direita do nó atual
            else if (this.comparador.compare(valor, atual.getValor()) < 0) {
                atual = atual.getFilhoEsquerda();
            }
            else {
                atual = atual.getFilhoDireita();
            }
        }

        // se não encontrar o valor, retorna nulo
        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator comparador2) {
        return pesquisarRecursivo(this.raiz, valor, comparador2);
    }

    private T pesquisarRecursivo(No<T> atual, T valor, Comparator comparador2) {
        if (atual == null) {
            return null;
        }

        if (comparador2.compare(valor, atual.getValor()) == 0) {
            return atual.getValor();
        }

        T encontradoEsquerda = pesquisarRecursivo(atual.getFilhoEsquerda(), valor, comparador2);
        if (encontradoEsquerda != null) {
            return encontradoEsquerda;
        }

        return pesquisarRecursivo(atual.getFilhoDireita(), valor, comparador2);
    }
//    @Override
//    public T pesquisar(T valor, Comparator comparador2) {
//        No<T> atual = this.raiz;
//
//        // verifica se o nó atual está vazio
//        while (atual != null) {
//
//            // compara o valor do parametro com o valor do nó atual utilizando o comparator passado como parametro
//            if (comparador2.compare(valor, atual.getValor()) == 0) {
//                return atual.getValor();
//
//                // verifica se o valor está a esquerda ou direita do nó atual
//            } else if (comparador2.compare(valor, atual.getValor()) < 0) {
//                atual = atual.getFilhoEsquerda();
//            } else {
//                atual = atual.getFilhoDireita();
//            }
//        }
//
//        // se não encontrar o valor, retorna nulo
//        return null;
//    }

    //método recursivo utilizado para remover elemento da árvore
    private No<T> removeRec(No<T> pai, T valor) {
        if (pai == null) {
            return null;
        }

        //se o valor é menor ou maior que o valor do nó, segue pelo ramo da árvore
        if (comparador.compare(valor, pai.getValor()) < 0) {
            pai.setFilhoEsquerda(removeRec(pai.getFilhoEsquerda(), valor));
        } else if (comparador.compare(valor, pai.getValor()) > 0) {
            pai.setFilhoDireita(removeRec(pai.getFilhoDireita(), valor));
        } else {
            //se chegou até aqui, o valor é igual e o nó será removido
            pai = removerNo(pai);
        }

        return pai;
    }

    private No<T> removerNo(No<T> no) {
        //caso1: nó não tem filhos
        if (no.getFilhoEsquerda() == null && no.getFilhoDireita() == null) {
            return null;
        }
        //caso 2: o nó possui apenas 1 filho
        else if (no.getFilhoEsquerda() == null) {
            return no.getFilhoDireita();
        } else if (no.getFilhoDireita() == null) {
            return no.getFilhoEsquerda();
        }
        //caso 3: o nó possui 2 filhos
        else {
            No<T> aux = encontrarMaior(no.getFilhoEsquerda());
            no.setValor(aux.getValor());
            no.setFilhoEsquerda(removeRec(no.getFilhoEsquerda(), no.getValor()));
            return no;
        }
    }

    private No<T> encontrarMaior(No<T> no) {
        while (no.getFilhoDireita() != null) {
            no = no.getFilhoDireita();
        }
        return no;
    }

    @Override
    public T remover(T valor) {
        //procura o valor na arvore antes de remover
        T remover = pesquisar(valor);

        //chama o método recursivo removeRec, passando a raiz e o valor
        if (remover != null) {
            this.raiz = removeRec(this.raiz, valor);
            if (this.raiz == null) {
                return null;
            } else {
                return remover;
            }
        } else {
            return null;
        }
    }

    @Override
    public int altura() {
        No<T> atual = this.raiz;
        //arvore vazia
        if(atual == null) {
            return -1;
        }
        else{
            //recebe o valor da recursiva e retira o nó correspondente ao valor da altura
            return alturaRec(atual)-1;
        }

    }

    private int alturaRec(No<T> node){

        if (node==null) return 0;

        //se chegou a uma folha, retorna 1
        if(node.getFilhoEsquerda() == null && node.getFilhoDireita() == null){
            return 1;
        }
        //caso tenha um filho ou mais, retorna o maior valor do ramo (esquerdo ou direito) + a altura do nó atual
        return 1 + Math.max(alturaRec(node.getFilhoDireita()), alturaRec(node.getFilhoEsquerda()));
    }


    @Override
    public int quantidadeNos() {
        //chama método recursivo privado passando a raiz
        return qntNos(this.raiz);
    }

    //método utilizado pelo método quantidadeNos()
    private int qntNos(No<T> node){
        if(node == null){
            return 0;
        }
        else{
            return 1 + qntNos(node.getFilhoEsquerda()) + qntNos(node.getFilhoDireita());
        }
    }

    @Override
    // Este método percorre a árvore em nível, visitando os nós de cada nível, começando pela raiz.
    public String caminharEmNivel() {
        // verifica se a arvore está vazia
        if (raiz == null) {
            return "[]";
        }

        StringBuilder resultado = new StringBuilder("[");
        Queue<No<T>> fila = new LinkedList<>();
        fila.add(raiz);
        // chama o método recursivo
        caminharEmNivelRec(fila, resultado);

        // adiciona o fechamento do array e retorna
        resultado.append("]");
        return resultado.toString();
    }

    private void caminharEmNivelRec(Queue<No<T>> fila, StringBuilder resultado) {
        // verifica se a fila está vazia
        if (fila.isEmpty()) {
            return;
        }

        No<T> no = fila.poll();
        resultado.append(no.getValor().toString()).append(" \n ");

        // Adiciona os filhos do nó à fila para visitá-los posteriormente
        if (no.getFilhoEsquerda() != null) {
            fila.add(no.getFilhoEsquerda());
        }
        if (no.getFilhoDireita() != null) {
            fila.add(no.getFilhoDireita());
        }

        // chama o método recursivo
        caminharEmNivelRec(fila, resultado);
    }


    @Override
    // Este método percorre a árvore em ordem, visitando os nós em uma ordem específica (esquerda, raiz, direita).
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder("[");

        // chama o método recursivo passando a raiz
        caminharEmOrdem(raiz, resultado);

        // adiciona o fechamento do array e retorna
        resultado.append("]");
        return resultado.toString();
    }

    private void caminharEmOrdem(No<T> no, StringBuilder resultado) {
        // verifica se o nó é nulo
        if (no != null) {
            // chama o método recursivo para o filho esquerdo
            caminharEmOrdem(no.getFilhoEsquerda(), resultado);

            // adiciona o valor do nó ao resultado
            resultado.append(no.getValor().toString()).append(" \n ");

            // chama o método recursivo para o filho direito
            caminharEmOrdem(no.getFilhoDireita(), resultado);
        }
    }
}
