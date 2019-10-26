package project1;

import static java.lang.Character.toUpperCase;
import java.util.Scanner;

/*
 * @author Gaven Grantz
 * CMPSCI 182
 * Professor Ferguson
 */
public class UserInput {

      
    public static int getInt(){
        try {
	Scanner input = new Scanner(System.in);
        return input.nextInt();
        }
        catch (Exception e) {
            System.out.print("Error: ");
        }
        return 0;
    }

    public static int getInt(int min, int max){
        int inputInt = getInt();
        while (inputInt < min || inputInt > max){
            System.out.print("Invalid value. Please enter an integer"
                    + " between " + min + " and " + max + ": ");
            inputInt = getInt();
        }
        return inputInt;               
    }

    public static char getChar(){
        try {
        Scanner input = new Scanner(System.in);
        char inputChar = input.next().charAt(0);
        return inputChar;
        }
        catch (Exception e) {
            System.out.print("Error: ");
        }
        return 0;
    }

    public static char getChar(char min, char max){
        char inputChar = toUpperCase(getChar());
        while (inputChar < toUpperCase(min) || inputChar > toUpperCase(max)){
            System.out.print("Invalid value. Please enter a character from "
                    + " between " + min + " and " + max + ": ");
            inputChar = toUpperCase(getChar());
        }
        return inputChar;
    }

    public static double getDouble(){
        try {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
        }
        catch (Exception e) {
            System.out.print("Error: ");
        }
        return 0;
    }

    public static double getDouble(double min, double max){
        double inputDouble = getDouble();
        while (inputDouble < min || inputDouble > max){
            System.out.print("Invalid value. " + 
                    "Please enter a floating point number" + " between " + 
                    min + " and " + max + ": ");
            inputDouble = getDouble();
        }
        
        return inputDouble;               
    }

    public static String getString(){
        try {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
        }
        catch (Exception e) {
            System.out.print("Error: ");
        }
        return "";
    }

    public static String getString(int min, int max){
        String inputString = getString();
        while (inputString.length() < min || inputString.length() > max){
            System.out.print("Invalid value. Please enter a string"+ 
                    " between " + min + " and " + max + " characters: ");
            inputString = getString();
        }
        return inputString;
    }

    
}
