/**
 * @author Gaven Grantz
 * March 16, 2018
 * CS182 Project 2 Recursion
 * Source - RecursionMethods.java
 * Description: This program contains 3 recursive methods. For each method,
 * the main method asks for user input and then call the recursive method, 
 * passing it the parameter(s) needed as input to the method. Here are 
 * the 3 recursive methods: sumInts(), minChar(), payDay()
 */


package project2;
import project0.UserInput;



public class RecursionMethods {

     public static void main(String[] args) {
       RecursionMethods obj = new RecursionMethods(); //Create RecursionMethods obj
       

       //Declaring all variables used in main method
       int firstInt, secondInt, sum, intDay;
       double wages;
       String input;
       char minChar;
       
       //Statements that get user input and calls the sumInts recursive method
       System.out.print("Please enter an integer value: ");
       firstInt = UserInput.getInt();
       System.out.print("Please enter a second integer value: ");
       secondInt = UserInput.getInt();
       sum = obj.sumInts(firstInt, secondInt);
       System.out.println("The sum of integer values between " + firstInt + " and " + secondInt + " is: " + sum);
       
       //Statements that get user input and calls the minChar method
       System.out.print("Please enter a string of characters: ");
       input = UserInput.getString();
       minChar = obj.minChar(input, 'z');
       System.out.println("The minimum character from " + "'" + input + "'" + " is: " + minChar);
       
       //Statements that get user input and call the payDay method
       System.out.print("Please enter an integer for a day between 1 and 31: ");
       intDay = UserInput.getInt(1, 31);
       wages = obj.payDay(intDay);
       System.out.println("On day #" + intDay + " of this month, you will earn: $" + wages);
       
       
    }
    
    //sumInts takes two integer arguments and adds the values from begin to end
    private int sumInts(int begin, int end){
    if (begin < end){
            return begin + sumInts(begin + 1, end);
        } else {
            return begin;
        }
    }
    
    //minChar returns the lowest character value from a string    
    private char minChar(String inputString, char lastMinValue) {

        if (inputString.length() == 1) {
            if ( inputString.charAt(0) <  lastMinValue){
                return inputString.charAt(0);
            } else{
                return lastMinValue; 
            }
        } else {
            
        char firstChar = inputString.charAt(0);
        
        if (firstChar < lastMinValue) 
            return minChar(inputString.substring(1, inputString.length() ), (firstChar)); 
        else
            return minChar(inputString.substring(1, inputString.length() ), (lastMinValue));
        }
    }
    
    
    //payDay returns the compounded salary of $0.01 at a rate of *2 per day.
    private double payDay(int day){
        if (day == 1){
            return 0.01;
        } else {
            return 2 * payDay(day - 1);
        }
    }
    
}
