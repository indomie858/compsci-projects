/**
 * @author Gaven Grantz
 * October 26, 2018
 * CS282 Project #2 - 234 Tress and B Trees
 * Source - Tree234App.java
 * Description: This Java application creates and populates a default 234 tree.
 * The user can insert, search, or display items in the tree. The user can also
 * change the order of the tree, creating a new tree in the process that is either
 * a 234 tree or a Order "n" B-Tree.
 */


package Tree234B;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tree234App {

    public static void main(String[] args) throws IOException {

        long value;

        //Node.setOrder(5);
        Tree234 theTree = new Tree234();

        theTree.insert(50);

        theTree.insert(40);

        theTree.insert(60);

        theTree.insert(30);

        theTree.insert(70);

        while (true) {

            System.out.print("Enter first letter of ");

            System.out.print("read, show, insert, find, change, or quit: ");

            char choice = getChar();

            switch (choice) {

                case 's':

                    theTree.displayTree();

                    break;

                case 'i':

                    System.out.print("Enter value to insert: ");

                    value = getInt();

                    theTree.insert(value);

                    break;

                case 'f':

                    System.out.print("Enter value to find: ");

                    value = getInt();

                    int found = theTree.find(value);

                    if (found != -1) {
                        System.out.println("Found " + value);
                    } else {
                        System.out.println("Could not find " + value);
                    }

                    break;

                case 'q':

                    System.out.println("Application closed");

                    System.exit(0);

                    break;

                case 'c':

                    boolean validOrder = false;

                    while (!validOrder) {

                        System.out.print("Enter the order for the new tree: ");

                        int treeOrder = getInt();

                        if (treeOrder == 4) {

                            Node.setOrder(treeOrder);

                            theTree = new Tree234();

                            validOrder = true;

                            System.out.println("New tree is now an order " + treeOrder + " tree.");

                            //////////////Debugging////////
                            theTree.insert(50);

                            theTree.insert(40);

                            theTree.insert(60);

                            theTree.insert(30);

                            theTree.insert(70);

                            theTree.insert(55);

                            theTree.insert(45);

                            theTree.insert(65);

                            theTree.insert(35);

                            theTree.insert(75);
                            ////////////////////////////

                        } else if (treeOrder >= 5) {

                            Node.setOrder(treeOrder);

                            theTree = new BTree();

                            validOrder = true;

                            System.out.println("New tree is now an order " + treeOrder + " tree.");

                            //////Debugging//////////
                            theTree.insert(50);

                            theTree.insert(40);

                            theTree.insert(60);

                            theTree.insert(30);

                            theTree.insert(70);

                            theTree.insert(55);

                            theTree.insert(45);

                            theTree.insert(65);

                            theTree.insert(35);

                            theTree.insert(75);
                            //////////////////////////

                        } else {

                            System.out.print("Invalid order. Order must be greater ");

                            System.out.print("than or equal to 4. ");

                        }

                    }

                    break;

                case 'r':
                    theTree = readData();
                    //System.out.println("Reading data file...");
                    break;

                default:

                    System.out.print("Invalid entry\n");

            }  // end switch

        }  // end while

    }  // end main()

//--------------------------------------------------------------
    public static String getString() throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(isr);

        String s = br.readLine();

        return s;

    }

//--------------------------------------------------------------
    public static char getChar() throws IOException {

        String s = getString();

        return s.charAt(0);

    }

//-------------------------------------------------------------
    public static int getInt() throws IOException {

        String s = getString();

        return Integer.parseInt(s);

    }

//-------------------------------------------------------------
    private static Tree234 readData() {  //this method reads data from a file and creates a tree structure
                                         // using that data

        Tree234 theTree = new Tree234();  //default tree

        try {    //beginning of try catch block
 
            System.out.println("The current order is " + Node.getOrder());

            System.out.print("Would you like to change it (y/n)?: ");

            char orderChoice = getChar();

            switch (orderChoice) {  //switch block handles user choice for order
                case 'y':
                    boolean validOrder = false;

                    while (!validOrder) {

                        System.out.print("Enter the order for the new tree: ");

                        int treeOrder = getInt();

                        if (treeOrder == 4) {  //creates 234 tree if order = 4

                            Node.setOrder(treeOrder);

                            theTree = new Tree234();
                            validOrder = true;

                            System.out.println("New tree is now an order " + treeOrder + " tree.");

                        } else if (treeOrder >= 5) {  //creates BTree if order >=5

                            Node.setOrder(treeOrder);

                            theTree = new BTree();
                            validOrder = true;

                            System.out.println("New tree is now an order " + treeOrder + " tree.");

                        } else {

                            System.out.print("Invalid order. Order must be greater ");

                            System.out.print("than or equal to 4. ");

                        }

                    }
                    break;
                
                case 'n':
                    theTree = new Tree234();  //no change, reset default
                    break;
                    
                default:
                    System.out.println("Invalid character.");
                    break;
            }
            
            System.out.print("Enter the name of the text file: ");

            String fileName = getString();  //user input file name for text file
            
            File fileObject = new File(fileName);

            Scanner input = new Scanner(fileObject);

            long userID;

            String record, lineData;

            while (input.hasNextLine()) { 

                lineData = input.nextLine();

                String[] data = lineData.split(",", 2); //seperates integer values from text file

                userID = Integer.parseInt(data[0]);
                record = data[1];

                theTree.insert(userID, record);  //inserts new dataitem objects containing data from text file

                //System.out.print(userID + " ");
                //System.out.println(record);
            }
            
            System.out.println("Reading data file...");
            
            System.out.println("File read complete.");
            
            return theTree;
            
        } catch (FileNotFoundException ex) {    //catches a file not found error
            
            //Logger.getLogger(Tree234App.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Invalid file name.");
            
        } catch (IOException ex)            //catchs a user input string error
        {
            //Logger.getLogger(Tree234App.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Invalid file name.");
            
        }
        
        return theTree;

    }

    private static String testreadData() {  //method used for testing and debugging
        String fileName = new String("data2_debug.txt");

        Node.setOrder(6);
        return fileName;
    }

}  // end class Tree234App

