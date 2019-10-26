/**
 * @author Gaven Grantz
 * May 15, 2018
 * CS182 Project #5 - Binary Trees
 * Source - Tree.java
 * Description: This class contains the code that builds a linked list style binary tree.
 * This class also contains the operations on the binary tree.
 */
package project5;


public class Tree {
    protected Node root;
    private Node childNode;
    
    
    public Tree(int[] arrayData){  //constructor that builds tree from an array of integer data
        root = null;
        for (int i = 0; i < arrayData.length; i++){
            insert(arrayData[i]);
        }    
    }
    
    
    public Tree(Node anchNode, Node ChildNode){
        root = anchNode;
        childNode = ChildNode;
    }
    
   
    private Node recInsert(Node currentNode, int key){     //this method recursively inserts nodes into a binary tree
        if (currentNode == null){
            currentNode = new Node(key);
            return currentNode;
        }
        
        if (key < currentNode.intKey){
            currentNode.leftChild = recInsert(currentNode.leftChild, key);
        } else if (key > currentNode.intKey){
            currentNode.rightChild = recInsert(currentNode.rightChild, key);
        } else {
            return currentNode;
        }
        return currentNode;
    }
    
    
    public void insert(int key){         //this method passes a key to recInsert();
        root = recInsert(root, key);
    }
   
    
    public boolean isEmpty(){      //this method checks if tree is empty
        return root == null;
    }
    
    
    public void printInOrderTraversal(Node node) {   //this method does an in order traversal on the tree and prints to the console
        if (node != null) {
            printInOrderTraversal(node.leftChild);
            System.out.print(" " + node.intKey);
            printInOrderTraversal(node.rightChild);
        }
    }
    
    
    public int numNodes(Node node){  //this method computes the number of nodes recursively
        if (node == null){
            return 0;
        }
        return 1 + numNodes(node.leftChild) + numNodes(node.rightChild);

    }
    
    
    public int heightTree(Node node){   //this method computes the height of the tree recursively
        if (node == null){
            return 0;
        }
        
        int leftBranch = heightTree(node.leftChild);
        int rightBranch = heightTree(node.rightChild);
        
        if (leftBranch < rightBranch){
            return 1 + rightBranch;
        } else {
            return 1 + leftBranch;
        }
    }
    
    
    public int maxElement(Node node){    //this method computes the maximum element recursively
        int max;
        if (node == null){
            return 0;
        } else {
            int maxLeft = maxElement(node.leftChild);
            int maxRight = maxElement(node.rightChild);
            
            if (maxLeft < maxRight){
                max = maxRight;
            } else {
                max = maxLeft;
            }
            
            if (node.intKey > max){
                max = node.intKey;
            }
        }
        return max;
    }
    
    
    public int sumOfElements(Node node){  //this method computes the sum of all the elements recursively
        if (node == null){
            return 0;
        }
        return (node.intKey + sumOfElements(node.leftChild) + sumOfElements(node.rightChild));
    }
    
    
    public int avgOfElements(Node node){  //this method computes the average of all elements recursively
        if (node == null){
            return 0;
        }
        return sumOfElements(node)/numNodes(node);
    }
    
    
    private Node find(int key){    //this method searches for a node using the key
        Node current = root;
        while (current.intKey!=key){
            if (key < current.intKey){
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            
            if (current == null){
                return null;
            }
        }
        return current;
    }
    
    
    public boolean findItem(int key, Node node){  //this method calls find() and returns true if a specific node is in the tree
        if (node == null){
            return false;
        }
        
        Node temp = find(key);
        
        if (temp == null){
            return false;
        }
       
        if (temp.intKey == key){
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean passKeyToAncestorCheck(int parentKey, int nodeKey){  //this method passes parentKey and childKey to isAncestorOf();
        Node parent = find(parentKey);
        Node child = find(nodeKey);
        Tree myTestTree2 = new Tree(root,child);
        boolean test = myTestTree2.isAncestorOf(parent, child);
        return test;
    }
    
    
    private boolean ancestorCheck(Node node) {   //this method recursively checks if one node is the ancestor of another node
        if (node == null)
            return false;

        if (node.leftChild != null && node.rightChild != null) {
            if (node.leftChild.intKey == childNode.intKey | node.rightChild.intKey == childNode.intKey)
                return true;
            else {
                return (ancestorCheck(node.leftChild) || ancestorCheck(node.rightChild));
            }
        }
        else if (node.leftChild != null) {
            if (node.leftChild.intKey == childNode.intKey)
                return true;
            else {
                return (ancestorCheck(node.leftChild));
            }
        }
        else if (node.rightChild != null) {
            if (node.rightChild.intKey == childNode.intKey)
                return true;
            else {
                return (ancestorCheck(node.rightChild));
            }
        }
        else
            return false;
    }
    
    
    private boolean isAncestorOf(Node anchNode, Node ChildNode) { //this method creates a test comparison tree and passes two nodes to ancestorCheck()
            Tree tree = new Tree(anchNode, ChildNode);
            return tree.ancestorCheck(anchNode);
    }
    
    
    private boolean isTreeFull(Node node){
        if (node == null){
            return true;
        }
        
        if (node.leftChild == null && node.rightChild == null){
            return true;
        }
        
        if (node.leftChild != null && node.rightChild != null){
            return isTreeFull(node.leftChild) && isTreeFull(node.rightChild);
        }
        
        return false;
    }
    
    
    public int highestFullLevel(Node node){
        int treeLevel = 0;
        int heightLeftBranch, heightRightBranch;
        
        if (node != null){
            heightLeftBranch = highestFullLevel(node.leftChild);
            
            heightRightBranch = highestFullLevel(node.rightChild);
            
            if(heightLeftBranch <= heightRightBranch){
                treeLevel = heightLeftBranch + 1;
            } else {
                treeLevel = heightRightBranch +1;
            }
        }
        
        return treeLevel;
        
            
        
        /*
        if (!isTreeFull(node)){
            return 0;
        } else {
            return 1 + highestFullLevel(node.leftChild) + highestFullLevel(node.rightChild);
        } 
        */
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     /* 
    
    public void createTree(int[] intArray){   //this method creates the binary tree
        binaryTree = new Tree();
        
        for (int i = 0; i < intArray.length; i++){
            binaryTree.insert(intArray[i]);
        }
    }
    
    public void inOrder(){
        binaryTree.inOrderTraversal(binaryTree.root);
    }
    
    public int countNodes(){
        Node left;
        Node right;
        int count = 0;
        
        if (binaryTree.root != null){
            count++;
        }
        
        while (!binaryTree.isEmpty()){
            
            left = binaryTree.root.leftChild;
            right = binaryTree.root.rightChild;
            if (left != null){
                count++;
            }
            if (right != null){
                count++;
            }
            
        }
      
        return count;
    }
    
    */
    
    
    
    
    
    
    /*
    public int countNodes(){
        Node left;
        Node right;
        int count = 0;
        
        if (root != null){
            count++;
            left = root.leftChild;
            right = root.rightChild;
        }
        
        while (!isEmpty()){
            
            left = root.leftChild;
            right = root.rightChild;
            if (left != null){
                count++;
            }
            if (right != null){
                count++;
            }
            
            if (left == null)
            
        }
      
        return count;
    }
   
    */
    
    /*
    public void insert(int key){
         dataNode = new Data();
        
        dataNode.setNodeKey(key);
        //dataNode.setIntData(data);
        
        if (root==null){
            root = dataNode;
        } 
        else {
            Data current = root;
            Data parent;
            
            while(true){
                parent = current;
                
                if (key<current.getNodeKey()){
                    current = (Data) current.getLeftChild();
                    if (current == null){
                        parent.setLeftChild(dataNode);
                        return;
                    }
                }
                else {
                    current = (Data) current.getRightChild();
                    if (current == null){
                        parent.setRightChild(dataNode);
                        return;
                    }
                }
            }
        } 
    }
    */
    /*
    public void delete(int key){
        
    }
    */
}
