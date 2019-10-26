/**
 * @author Gaven Grantz
 * May 15, 2018
 * CS182 Project #5 - Binary Trees
 * Source - Project5.java
 * Description: This program will build a binary tree and perform several tasks
 * on the data, such as computing height and finding elements.
 */
package project5;

import project0.UserInput;



public class Project5 {

    private static final int[] TESTDATA = {50, 30, 60, 10, 80, 55, 40};     //test data to populate binary tree
    private static final int [] ADDITIONALTESTDATA1 = { 80, 70, 60, 50, 90, 40, 30, 20, 10 };   //additional data to populate binary tree for testing
    private static final int [] ADDITIONALTESTDATA2 = { 50, 30, 60, 10, 80, 55, 40, 85, 75, 65, 55, 95, 45, 35, 25, 15 };   //additional data to populate binary tree for testing
       
            
    public static void main(String[] args) {
        
        
        Tree binaryTree = new Tree(TESTDATA);
        System.out.print("In Order Traversal of Binary Tree: ");
        binaryTree.printInOrderTraversal(binaryTree.root);
        System.out.println();
        System.out.println("------Binary Tree------");
        
        System.out.println("A. Number of Nodes: " + binaryTree.numNodes(binaryTree.root));   //computes number of nodes
        
        System.out.println("B. Height of Tree: " + binaryTree.heightTree(binaryTree.root));   //computes height of tree
        
        System.out.println("C. Maximum Element: " + binaryTree.maxElement(binaryTree.root));   //computes max element
        
        System.out.println("D. Sum of Elements: " + binaryTree.sumOfElements(binaryTree.root));  //computes sum of elements
        
        System.out.println("E. Average of Elements: " + binaryTree.avgOfElements(binaryTree.root));  //computes average of elements
        
        
        String messageF;
        System.out.print("F. Input an integer value to search: ");
        int numToSearch = UserInput.getInt();    //specific item to search for problem f 
        if (binaryTree.findItem(numToSearch, binaryTree.root)){        //searchs for specific node using key from variable numToSearch
            messageF = "Yes, " + numToSearch + " is in the binary tree.";
        } else {
            messageF = "No, " + numToSearch + " is not in the binary tree.";
        }
        System.out.println("--->Searching for item "+ numToSearch + ": " + messageF);
        
        
        String messageG;
        System.out.print("G. Enter a key for a ancestor node: ");
        int parentkey = UserInput.getInt();    //key to check ancestor
        System.out.print("-Enter a key for a descendant node: ");
        int ancestorCheckKey = UserInput.getInt();   //key to check if node is decendant of ancestor
        if (binaryTree.passKeyToAncestorCheck(parentkey, ancestorCheckKey)){     //checks if one node is an ancestor of another node
            messageG = "Yes, " + parentkey + " is an ancestor to " + ancestorCheckKey;
        } else {
            messageG = "No, " + parentkey + " is not an ancestor to " + ancestorCheckKey;
        }
        System.out.println("---> Is " + parentkey + " an ancestor to " + ancestorCheckKey + "? " + messageG);
        
        
        
        System.out.println("H. The highest full level in the tree is: " + binaryTree.highestFullLevel(binaryTree.root));
    }




































/*
        int[] testData = {50, 30, 60, 10, 80, 55, 40};
        Project5 obj = new Project5();
        obj.createTree(testData);
        obj.inOrder();
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
    
        
    /*
    private int[] sortArray(int[] arrayToSort){
        int[] tempArray = arrayToSort;
        for (int outer = tempArray.length - 1; outer > 1; outer--){
            for (int inner = 0; inner < outer; inner++){
                if (tempArray[inner] > tempArray[inner + 1]) {
                    int tempInt = tempArray[inner];
                    tempArray[inner] = tempArray[inner + 1];
                    tempArray[inner + 1] = tempInt;
                }
            }
        }
        return tempArray;
    }
    */


