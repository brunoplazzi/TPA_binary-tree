/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
  
    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }
    
    @Override
    public void adicionar(T novoValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pesquisar(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public T pesquisar(T valor, Comparator comparador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    
    @Override
    public int quantidadeNos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null) {
            return "[]";
        }

        StringBuilder resultado = new StringBuilder("[");
        Queue<No<T>> fila = new LinkedList<>();
        fila.add(raiz);

        caminharEmNivelRec(fila, resultado);

        resultado.append("]");
        return resultado.toString();
    }

    private void caminharEmNivelRec(Queue<No<T>> fila, StringBuilder resultado) {
        if (fila.isEmpty()) {
            return;
        }

        No<T> no = fila.poll();
        resultado.append(no.getValor().toString()).append(" \n ");

        if (no.getFilhoEsquerda() != null) {
            fila.add(no.getFilhoEsquerda());
        }
        if (no.getFilhoDireita() != null) {
            fila.add(no.getFilhoDireita());
        }

        caminharEmNivelRec(fila, resultado);
    }


    @Override
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder("[");
        caminharEmOrdem(raiz, resultado);
        resultado.append("]");
        return resultado.toString();
    }

    private void caminharEmOrdem(No<T> no, StringBuilder resultado) {
        if (no != null) {
            caminharEmOrdem(no.getFilhoEsquerda(), resultado);
            resultado.append(no.getValor().toString()).append(" \n ");
            caminharEmOrdem(no.getFilhoDireita(), resultado);
        }
    }
}
