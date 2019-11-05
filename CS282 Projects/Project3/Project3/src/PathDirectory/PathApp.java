/**
 * @author Gaven Grantz
 * November 19, 2018
 * CS282 Project #3 - Direct Weighted Graphs
 * Source - PathApp.java
 * Description: This java program is an updated version from the textbook that
 * models a wireless computer network. Each vertex will be a node on the network
 * and each edge will be a communications link. The communication links are one way,
 * so it will be a directed graph.
 */
package PathDirectory;

import java.io.*;
import java.util.Scanner;

////////////////////////////////////////////////////////////////
public class PathApp {

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A'); // 0 (start)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 3, 80); // AD 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 4, 40); // CE 40
        theGraph.addEdge(3, 2, 20); // DC 20
        theGraph.addEdge(3, 4, 70); // DE 70
        theGraph.addEdge(4, 1, 50); // EB 50
        System.out.println("Shortest paths");
        theGraph.path(); // shortest paths
        System.out.println();

        PathApp projectObj = new PathApp();

        System.out.println("-----Welcome to the wireless computer network-----");

        while (true) {  //main menu
            System.out.print("Type the first letter of Change, Add, Delete, Find, Write, Read, or Quit: ");

            char menuInput = getCharUppercase();

            switch (menuInput) {

                case 'C':
                    theGraph = projectObj.changeCommand(theGraph);  //changes weight of edge
                    break;

                case 'A':
                    theGraph = projectObj.addCommand(theGraph);  //adds a new edge
                    break;

                case 'D':
                    theGraph = projectObj.deleteCommand(theGraph);  //deletes an edge
                    break;

                case 'F':
                    projectObj.findCommand(theGraph);  //finds a vertex and the path to it
                    break;

                case 'R':
                    theGraph = projectObj.readCommand(theGraph);    //reads Graph object from serialized file
                    theGraph.path();
                    
                    break;

                case 'W':
                    projectObj.writeCommand(theGraph); //writes Graph object to a serialized file
                    break;

                case 'Q':
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid character");
                    break;
            }
        }

    } // end main()

    public static char getCharUppercase() {  //this method returns a user inputted character

        Scanner input = new Scanner(System.in); //user input

        String text = input.next().toUpperCase();   //turns all characters to uppercase

        return text.charAt(0);  //returns first character

    }

    public static int getInt() {    //this method returns a user inputted integer

        try {

            Scanner input = new Scanner(System.in);  //user input

            return input.nextInt();   

        } catch (Exception e) {

            System.out.println("Invalid entry.");

        }

        return -1;
    }

    public static String getString() {  //this method returns a user inputted String

        Scanner input = new Scanner(System.in);     //user input

        return input.nextLine();
    }

    public int charToInt(char letter) { //this method converts char to int; e.g. A=0, B=1, C=2...

        final int MAXLETTER = 4;    //last index with vertex 

        int numLetter;

        numLetter = letter - 'A';   //subtracts ascii char from the char argument

        if (numLetter >= 0 && numLetter <= MAXLETTER) { 

            return numLetter;   //condition ensures method returns 0 to MAXLETTER

        } else {

            System.out.println("Invalid entry.");

            return -1;

        }

    }

    public Graph changeCommand(Graph theGraph) {    //this method changes the weight of an existing edge

        int fromVertex, toVertex, weight;

        char choice = ' ';

        do {  //loops if the fromVertex is invalid

            System.out.print("Enter a 'from' vertex: ");

            choice = getCharUppercase();    //user input char

            fromVertex = charToInt(choice);    //converts char to int

        } while (fromVertex < 0);

        do {  //loops if toVertex is invalid

            System.out.print("Enter a 'to' vertex: ");

            choice = getCharUppercase();    //user input char

            toVertex = charToInt(choice);   //converts char to int

        } while (toVertex < 0);

        do {    //loops if weight is invalid

            System.out.print("Enter a weight for the edge: ");

            weight = getInt();  //user input int

        } while (weight < 0);

        if (theGraph.hasEdge(fromVertex, toVertex)) {  //condition to see if an edge exists between fromVertex and toVertex

            theGraph.addEdge(fromVertex, toVertex, weight);     //changes existing edge

            System.out.println("Successful change.");

            theGraph.path();    //updates graph lowest cost paths

        } else {

            System.out.println("Edge does not exist.");

            theGraph.displayPaths();

        }

        return theGraph;

    }

    public Graph addCommand(Graph theGraph) {   //this method adds a new edge if one does not exist already

        int fromVertex, toVertex, weight;

        char choice = ' ';

        do {    //loops if the fromVertex is invalid

            System.out.print("Enter a 'from' vertex: ");

            choice = getCharUppercase();    //user input char

            fromVertex = charToInt(choice);     //converts char to int

        } while (fromVertex < 0);

        do {    //loops if the toVertex is invalid

            System.out.print("Enter a 'to' vertex: ");

            choice = getCharUppercase();    //user input char

            toVertex = charToInt(choice);   //converts char to int

        } while (toVertex < 0);

        do {    //loops if the weight is invalid

            System.out.print("Enter a weight for the edge: ");

            weight = getInt();      //user input int

        } while (weight < 0);

        if (theGraph.hasEdge(fromVertex, toVertex)) {

            System.out.println("Edge already exists.");

            theGraph.displayPaths();

        } else {    //condition to verify there is no existing edge

            theGraph.addEdge(fromVertex, toVertex, weight);     //adds new edge

            System.out.println("Successful change.");

            theGraph.path();    //updates graphs lowest cost paths

        }

        return theGraph;

    }

    public Graph deleteCommand(Graph theGraph) {

        int fromVertex, toVertex;

        char choice = ' ';

        do {    //loops if the fromVertex is invalid

            System.out.print("Enter a 'from' vertex: ");

            choice = getCharUppercase();

            fromVertex = charToInt(choice);

        } while (fromVertex < 0);

        do {    //loops if the toVertex is invalid

            System.out.print("Enter a 'to' vertex: ");

            choice = getCharUppercase();

            toVertex = charToInt(choice);

        } while (toVertex < 0);

        if (theGraph.hasEdge(fromVertex, toVertex)) {   //condition to verify there is an edge

            theGraph.deleteEdge(fromVertex, toVertex);  //deletes edge from graph

            System.out.println("Successful change.");

            theGraph.path();

        } else {

            System.out.println("Edge does not exist");

            theGraph.displayPaths();

        }

        return theGraph;
    }

    public void findCommand(Graph theGraph) {   //method that finds a vertex and displays lowest cost path

        System.out.print("Enter the vertex you wish to find: ");

        char choice = getCharUppercase();

        theGraph.findVertex(choice);    //finds vertex and displays lowest cost path

        theGraph.displayPaths();

    }

    public void writeCommand(Graph theGraph) {  //this method writes a Graph object to a serialized file

        try {

            System.out.print("Enter a name for the .ser file to be written to the disk: ");

            String fileName = getString();    //user inputs the filename

            FileOutputStream fileOut = new FileOutputStream(fileName);

            ObjectOutputStream output = new ObjectOutputStream(fileOut);

            output.writeObject(theGraph);

            output.close();

            fileOut.close();

            System.out.println("Serialized data is saved in " + fileName);

        } catch (IOException i) {   //catches any IOExceptions

            i.printStackTrace();

        }
    }

    public Graph readCommand(Graph theGraph) {  //this method reads a graph object from a serialized file

        try {

            System.out.print("Enter the name of the .ser file to be read into memory: ");

            String fileName = getString();  //user inputs the filename

            FileInputStream fileIn = new FileInputStream(fileName);

            ObjectInputStream input = new ObjectInputStream(fileIn);

            theGraph = (Graph) input.readObject();

            input.close();

            fileIn.close();

            System.out.println("File read complete.");

            return theGraph;

        } catch (IOException i) {    //catches any IOException

            i.printStackTrace();

            return null;

        } catch (ClassNotFoundException c) {    //catches ClassNotFoundExeption

            System.out.println("Graph class not found");

            c.printStackTrace();

            return null;

        }

    }

} // end class PathApp
////////////////////////////////////////////////////////////////
