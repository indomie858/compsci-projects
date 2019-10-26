
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Project #5
 * Source Code File: Numbers.java
 * Programmer: Gaven Grantz
 * Due: 11/16/17
 * Description: This is a Java program that will use data from an input txt
 * file in arrays.
 */
public class Numbers {

   
    public static void main(String[] args) throws FileNotFoundException {
        
        //Creates a file instance for numbers.txt
        java.io.File fileNumbers = new java.io.File("numbers.txt");
        
        
        //Creates a scanner for file
        Scanner input = new Scanner(fileNumbers);
        
        
        //Reads first int from numbers.txt and assigns to the length
        // of an array called data_array
        int[] data_array = new int[input.nextInt()];
        
        
        //Reads remaining int values into data_array
        for (int i = 0; i < data_array.length; i++) {
            data_array[i] = input.nextInt();
        }
        
        
        //Passes array to sort method
        sort(data_array);

        
        //Computes sum of values in data_array
        double sumOfValues = 0, avgOfValues = 0;
        for (int i = 0; i < data_array.length; i++){
            sumOfValues += data_array[i];
        }

        
        //Computes average of values
        avgOfValues = sumOfValues / data_array.length;
        
        
        //Creates a file object for dataout.txt
        java.io.File dataout = new java.io.File("dataout.txt");

        
        //Creates a file
        java.io.PrintWriter output = new java.io.PrintWriter(dataout);

        
        //Writes count from data_array to dataout.txt
        output.println("The number of values is: " + data_array.length);

        
        //Writes int values from data_array to dataout.txt
        for (int i = 0; i < data_array.length; i++) {
            output.println(data_array[i]);
        }

        
        output.println("The average of all the values is : " + avgOfValues);

        
        //Closes the file dataout.txt
        output.close();   
        
    }
    
    //Method that takes an array as a parameter, and sorts it from low to high
    public static void sort(int[] list){

        for (int i = 0; i < list.length - 1; i++){

            int currentMin = list[i];
            int currentMinIndex = i;
            
            for (int j = i + 1; j < list.length; j++){
                if (currentMin > list[j]){
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            
            if (currentMinIndex !=i){
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
    
}
