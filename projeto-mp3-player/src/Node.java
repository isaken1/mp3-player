/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ian_honorato
 */
public class Node {
    private int valor;
    private Node esquerda;
    private Node direita;

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
    
    public static void initTree(Node novoNo, Node arvore){
        if (arvore == null){
            arvore = novoNo;
        }
        else{
            if(novoNo.valor < arvore.valor){
                
                if(arvore.esquerda == null){
                    arvore.esquerda = novoNo;
                } else{
                    initTree(novoNo, arvore.esquerda);
                }
            } else{
                if(arvore.direita == null){
                    arvore.direita = novoNo;
                } else {
                    initTree(novoNo, arvore.direita);
                }
            }
        }
    }
    
    public boolean findNode(Node arvore, int chave){
        boolean find = false;
        
        if(arvore == null){
            find = false;
        } else if(arvore.valor == valor){
            find = true;
        } else{
            if(arvore.valor > valor){
                find = findNode(arvore.esquerda, valor);
            } else{
                find = findNode(arvore.direita, valor);
            }
        }
        return find;
    }
    
    public static void removerNo(Node raiz, int valor){
        if(raiz.valor == valor){
            raiz = null;
        } else if(raiz.valor > valor){
            if(raiz.esquerda.valor == valor){
                removerNoEsquerda(raiz);
            } else {
                removerNo(raiz.esquerda, valor);
            }
        } else {
            if(raiz.direita.valor == valor){
                removerNoDireita(raiz);
            } else{
                removerNo(raiz.direita, valor);
            }
        }
    }
    
    public static void removerNoDireita(Node noPai){
        int filhos = 0;
        Node noRemover;
        noRemover = noPai.direita;
        filhos = tFilhos(noRemover);
        if(filhos == 0){
            removerSemFilhos(noPai, "D");
        } else if(filhos == 1){
            removerUmFilhoD(noPai, "E");
        } else if(filhos == 2){
            removerUmFilhoD(noPai, "D");
        } else if(filhos == 3){
            removerEsqDir(noPai.direita);
        }
    }
    
    public static void removerNoEsquerda(Node noPai){
        int filhos = 0;
        Node noRemover;
        noRemover = noPai.esquerda;
        filhos = tFilhos(noRemover);
        if(filhos == 0){
            removerSemFilhos(noPai, "E");
        } else if(filhos == 1){
            removerUmFilhoE(noPai, "E");
        } else if(filhos == 2){
            removerUmFilhoE(noPai, "D");
        } else if(filhos == 3){
            removerEsqDir(noPai.esquerda);
        }
    }
    
    public static int tFilhos(Node node){
        //0 filhos | 1 = Filho a esquerda | 2 = Filho a direita | 3 = filho a 
        //esquerda e a direita
        int total = 0;
        if(node.esquerda != null){
            total += 1;
        }
        if(node.direita != null){
            total += 2;
        }
        return total;
    }
    
    public static void removerSemFilhos(Node noPai, String subarvore){
        if(subarvore.equals("E")){
            noPai.esquerda = null;
        } else{
            noPai.direita = null;
        }
    }
    
    public static void removerUmFilhoD(Node noPai, String subarvore){
        if(subarvore.equals("E")){
            noPai.direita = noPai.direita.esquerda;
        } else {
            noPai.direita = noPai.direita.direita;
        }
    }
    
    public static void removerUmFilhoE(Node noPai, String subarvore){
        if(subarvore.equals("E")){
            noPai.esquerda = noPai.esquerda.esquerda;
        } else {
            noPai.esquerda = noPai.esquerda.direita;
        }
    }
    
    public static void removerEsqDir(Node node){
        node.valor = encontraMinimo(node.direita);
        node.direita = removeMinimo(node.direita);
    }
    
    public static Node removeMinimo(Node node){
        if(node == null){
            System.out.println("ERRO");
        } else if(node.esquerda != null){
            node.esquerda = removeMinimo(node.esquerda);
            return node;
        } else {
            return node.direita;
        }
        return null;           
    }
    
    public static int encontraMinimo(Node node){
        if(node != null){
            while(node.esquerda != null){
                node = node.esquerda;
            }
        }
        return node.valor;
    }
    
    public static void imprimirPreOrdem(Node node){
        System.out.print(node.valor + " ");
        if(node.esquerda != null){
            imprimirPreOrdem(node.esquerda);
        }
        if(node.direita != null){
            imprimirPreOrdem(node.direita);
        }
    }
    
    public static void imprimirEmOrdem(Node node){
        if(node.esquerda != null){
            imprimirEmOrdem(node.esquerda);
        }
        System.out.print(node.valor + " ");
        
        if(node.direita != null){
            imprimirEmOrdem(node.direita);
        }
    }
    
    public static void imprimirPosOrdem(Node node){
        if(node.esquerda != null){
            imprimirPosOrdem(node.esquerda);
        }
        
        if(node.direita != null){
            imprimirPosOrdem(node.direita);
        }
        
        System.out.print(node.valor + " ");
    }
    
}
