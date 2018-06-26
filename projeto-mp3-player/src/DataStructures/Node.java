/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ian
 */
public class Node {

    protected boolean isFinal;
    protected Node nodes[];
    protected int numeroDePrefixo;
    protected Node prev;

    public Node(int numDeNodes) {
        isFinal = false;
        nodes = new Node[numDeNodes];
        numeroDePrefixo = 0;
        prev = null;
    }

    @Override
    protected void finalize() {
        isFinal = false;
    }

}
