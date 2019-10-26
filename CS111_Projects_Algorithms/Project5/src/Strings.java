
import java.io.FileNotFoundException;
import static java.util.Arrays.sort;
import java.util.Scanner;

/*
 * Project #5
 * Source Code File: Strings.java
 * Programmer: Gaven Grantz
 * Due: 11/16/17
 * Description: This is a Java program that will read strings from an input txt
 * file in arrays.
 */
public class Strings {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Creates a file instance for strData.txt
        java.io.File fileStrNames = new java.io.File("test_strData.txt");
        
        
        //Creates a scanner for file
        Scanner input = new Scanner(fileStrNames);
        
        
        //Reads first int from strData.txt and assigns to the length
        // of an array called strings
        String[] strings = new String[input.nextInt()];
        
        
        //Reads remaining int values into data_array
        for (int i = 0; i < strings.length; i++) {
            strings[i] = input.nextLine();
        }
 
        
        //Passes array to sort method
        sortStrings(strings);
        
        
        //This assigns the string in the middle of the array to a variable
        String middleListName = strings[strings.length / 2];
        
        
        //Creates a file object for dataout.txt
        java.io.File dataout = new java.io.File("strDataOut.txt");
        
        
        //Creates a file
        java.io.PrintWriter output = new java.io.PrintWriter(dataout);
        
        
        //Writes count from strings to dataout.txt
        output.println("The number of names is: " + strings.length);
        
        
        //Writes strings from data_array to strDataOut.txt
        for (int i = 0; i < strings.length; i++) {
            output.println(strings[i]);
        }
        
        
        //Prints out 'middle' name
        output.println("");
        output.println("The name in the middle of the list is : " + middleListName);
        
        
        //Closes the file dataout.txt
        output.close();   
        
    }
    
    public static void sortStrings(String[] names){
        sort(names);
    }
    
}
