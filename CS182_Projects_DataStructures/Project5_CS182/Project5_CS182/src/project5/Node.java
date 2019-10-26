/**
 * @author Gaven Grantz
 * May 15, 2018
 * CS182 Project #5 - Binary Trees
 * Source - Node.java
 * Description: This class contains the nodes of a binary tree. The nodes contain
 * integer values.
 */
package project5;


public class Node {
    protected int intKey;
    protected Node leftChild;
    protected Node rightChild;
    
    public Node(int key){
        this.intKey = key;
        leftChild = null;
        rightChild = null;
    }
}
