package DataStructures;

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
