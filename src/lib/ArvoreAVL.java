package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...

    @Override
    protected No<T> adicionarRecursivo(No<T> pai, No<T> node){
        pai = super.adicionarRecursivo(pai, node);

        if (pai != null){
            pai = balancear(pai);
        }
        return pai;
    }

    @Override
    protected No<T> removeRec(No<T> node, T valor){
        node = super.removeRec(node, valor);
        if (node != null){
            node = balancear(node);
        }
        return node;
    }

    private No<T> balancear(No<T> node){
        if (node.fatorBalanceamento() > 1){
            if (node.getFilhoDireita().fatorBalanceamento() > 0){
                node = rotacaoEsquerda(node);
            }
            else{
                node = rotacaoDireitaEsquerda(node);
            }
        }
        else if (node.fatorBalanceamento() < -1){
            if (node.getFilhoEsquerda().fatorBalanceamento() < 0){
                node = rotacaoDireita(node);
            }
            else{
                node = rotacaoEsquerdaDireita(node);
            }
        }
        return node;
    }

    private No<T> rotacaoEsquerda(No<T> pai){
        No<T> aux = pai.getFilhoDireita();
        pai.setFilhoDireita(aux.getFilhoEsquerda());
        aux.setFilhoEsquerda(pai);
        return aux;
    }

    private No<T> rotacaoDireita(No<T> pai){
        No<T> aux = pai.getFilhoEsquerda();
        pai.setFilhoEsquerda(aux.getFilhoDireita());
        aux.setFilhoDireita(pai);
        return aux;
    }

    private No<T> rotacaoDireitaEsquerda(No<T> pai){
        pai.setFilhoDireita(rotacaoDireita(pai.getFilhoDireita()));
        return rotacaoEsquerda(pai);
    }

    private No<T> rotacaoEsquerdaDireita(No<T> pai){
        pai.setFilhoEsquerda(rotacaoEsquerda(pai.getFilhoEsquerda()));
        return rotacaoDireita(pai);
    }
}
