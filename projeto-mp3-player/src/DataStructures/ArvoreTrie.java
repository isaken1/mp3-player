
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ian
 * Esta classe tem como função instanciar Arvore Trie, para autocomplete nas 
 * pesquisas
 */
public class ArvoreTrie {

    private char palavraChar[];
    private Node raiz;
    private int numDeNodos;

    /**
     * Construtor Padrão
     */
    public ArvoreTrie(int numDeNodes) {
        this.numDeNodos = numDeNodes;
        raiz = new Node(numDeNodos);
        raiz.isFinal = false;
        raiz.numeroDePrefixo = 0;
        raiz.prev = null;
    }

    /**
     * Função que insere os caracteres do vetor de char na arvore
     * @param string Vetor de char ao qual será inserido na arvore
     */
    public boolean inserirString(char[] string) {
        Node nodo = raiz;
        nodo.numeroDePrefixo++;	//Diz que ali tem mais um nodo

        for (int a = 0; a < string.length; a++) {
            int intChar = this.posicaoDoChar(string[a]);
            if (nodo.nodes[intChar] == null) {
                try {
                    nodo.nodes[intChar] = new Node(numDeNodos);
                } catch (Exception erro) {
                    System.gc();
                    System.runFinalization();
                }

                if (nodo.nodes[intChar].nodes == null) {
                    return false;
                }
                nodo.nodes[intChar].prev = nodo;
            }
            nodo.nodes[intChar].numeroDePrefixo++;
            nodo = nodo.nodes[intChar];
        }
        nodo.isFinal = true;
        return nodo.isFinal;
    }

    /**
     * Função responsável por remover string da arvore. usado quando remover uma 
     * musica
     * @param string Vetor de char a ser removido
     */
    public boolean removerString(char[] string) {
        Node nodo = raiz;
        /*
		 * Primeiro passo do remover é buscar a string até o final. 
		 * Isso faz com que o nodo esteja no nodo mais distante da string
		 * 	para que se possa voltar deletando
         */
        int a;
        for (a = 0; a < string.length; a++) {
            if (nodo.nodes[this.posicaoDoChar(string[a])] != null) {
                nodo = nodo.nodes[this.posicaoDoChar(string[a])];
            } else {
                return false;
            }
        }

        /*
		 *      Segundo passo é marcar esse nodo como não final, pois
		 * 	estaremos removendo a palavra que está até aqui. Depois
		 * 	enquanto ele não tiver prev null (raiz)
         */
        if (nodo.isFinal != true) {
            return false;
        }
        nodo.isFinal = false;
        Node temp;

        while (nodo.prev != null) {
            if (nodo.numeroDePrefixo <= 0) {
                temp = nodo;
                nodo = nodo.prev;
                nodo.numeroDePrefixo--;
                temp.finalize();
            } else {
                nodo = nodo.prev;
            }
        }
        /*
        Se o nodo é igual a raiz, então houve sucesso no retornar
         */
        if (nodo.equals(raiz)) {
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * Função responsável por fazer a busca da String na arvore
     * @param string Vetor de char a ser buscado.
     */
    public boolean buscarString(char[] string) {
        Node nodo = raiz;

        for (int a = 0; a < string.length; a++) {
            if (nodo.nodes[this.posicaoDoChar(string[a])] != null) {
                nodo = nodo.nodes[this.posicaoDoChar(string[a])];
            } else {
                return false;
            }
        }
        return nodo.isFinal;
    }
    /**
    *Função auxiliar da função de busca
    *@param letra Char que está sendo iterado na arvore 
    */
    public int posicaoDoChar(char letra) {
        return (int) letra - 97;
    }
    
    /**
     * Função de entrada da string, recebe a string da musica
     * e converte para vetor de char
     * @param palavra String com a palavra a ser quebrada em vetor de char e inserida na arvore
     * @return palavraChar Vetor de char.
     */
    public char[] entrada(String palavra){
        StringBuilder stringAcaoPalavra = new StringBuilder();
        stringAcaoPalavra.append(palavra);
        palavraChar = new char[stringAcaoPalavra.length()];
        palavraChar = stringAcaoPalavra.toString().toCharArray();
        
        return palavraChar;
    }
}
