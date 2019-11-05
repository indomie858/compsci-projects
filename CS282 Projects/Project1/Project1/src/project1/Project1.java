/**
 * @author Gaven Grantz
 * October 5, 2018
 * CS282 Project #1 - Flat Files
 * Source - Project1.java
 * Description: This is a Java program that creates an array of MyRecord objects,
 * reads data from a file, and fills the MyRecord object attributes. After the 
 * user enters a negative value, the data in the MyRecord objects will be transferred
 * to MyFixedRecord objects. The data in the MyFixedRecord objects are then written 
 * to a text file.
 */

package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Project1 {
    
    public static final int ARRAYSIZE = 10; // Required constant at the top of the class
    public static final int HASHSIZE = 1001; // Required constant at the top of the class (# buckets or slots)
    private int totalRecords;
    private int fixedRecordLength;
    
    public Project1(){
        
    }
    
    public static void main(String[] args) {
        MyRecord[] recordArray = new MyRecord[ARRAYSIZE]; //creates Record array
        
        Project1 projectObj = new Project1(); //creates instance of Project0
        
        try {
            recordArray = projectObj.readFileToRecord(recordArray); //invokes readFileToRecord and passes recordArray as argument
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
        //recordToFixedRecord(recordArray);
        
        Scanner input = new Scanner(System.in); //keyboard input
        int recordIndex = 0;
        
        System.out.print("Welcome to your database.\nPlease enter a record number(0-" + (projectObj.totalRecords - 1) + "): ");
        
        while(recordIndex >= 0){ //loop asking user input for record number. Exit loop by entering a negative number
            try{
               recordIndex = input.nextInt();  //exception handling
            } catch (Exception e){
               System.out.print("Invalid index. Please enter a record number(0-" + (projectObj.totalRecords - 1) + "): ");
            }
            if (recordIndex >= projectObj.totalRecords){
               System.out.print("Invalid index. Please enter a record number(0-" + (projectObj.totalRecords - 1) + "): ");
            } else if (recordIndex < 0){
                break;
            } else {
                recordArray[recordIndex].printRecord(recordIndex);
                String email = recordArray[recordIndex].getEmail();
                int bucketNumber = hashFunc3(email);
                System.out.println("Key: " + email + " should be stored in bucket#: " + bucketNumber);
                System.out.println("-----End Record-----");
                System.out.print("Please enter a record number(0-" + (projectObj.totalRecords - 1) + "), or enter a negative value to exit: ");
            }
        }
        
        System.out.println("------End Database-----"); //end of records
        
        System.out.print("\nPlease enter a record size (80-128): ");
        
        projectObj.fixedRecordLength = input.nextInt(); //initializes fixed record length
        

        if (projectObj.fixedRecordLength < 80 || projectObj.fixedRecordLength > 128){ //verifies that record length is between 80 and 128
            System.out.print("Invalid record size. Please enter a record size (80-128): ");
            projectObj.fixedRecordLength = input.nextInt();
        }
        
        MyFixedRecord[] fixedRecordArray = new MyFixedRecord[ARRAYSIZE]; //created a array of FixedRecord obj
        
        fixedRecordArray = projectObj.recArrToFixedRecArr(recordArray, fixedRecordArray);
        //System.out.print(fixedRecordArray[1].getFirstName());
        
        //System.out.println(fixedRecordArray[1].getFirstName().length);
        
        try {
            projectObj.writeFixedRecordToFile(fixedRecordArray);
        } catch (FileNotFoundException e){
            System.out.println("Error: File output failed.");
        }

    }
    
    
    //This method reads the data from a text file, creates Record objects, and stores into a Record array
    private MyRecord[] readFileToRecord(MyRecord[] recArray) throws FileNotFoundException{
        
        String fileName = new String("Project1_data.txt");
        File fileObject = new File(fileName);
        Scanner input = new Scanner(fileObject);  //reads data from fileObject
        
        
        totalRecords = 0;
        String lineData;
        
        while (input.hasNextLine() && totalRecords < ARRAYSIZE){
            
            lineData = input.nextLine(); 
            String firstName, lastName, email, color;
            int idNumber;
            double balance;

            String[] data = lineData.split(", ");  //separates text data from file into fields
            firstName = data[0];
            lastName = data[1];
            email = data[2];
            idNumber = Integer.parseInt(data[3]);
            color = data[4];
            balance = Double.parseDouble(data[5]);

            /*  DEBUGGING PURPOSES
            //System.out.println(lineData);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(email);
            System.out.println(idNumber);
            System.out.println(color);
            System.out.println(balance);
            */

            recArray[totalRecords] = createRecord(firstName, lastName, email, idNumber, color, balance);
            totalRecords++;
        }
        
        
        
        /*
        for (int i = 0; i < lineCount; i++){  //assigns data to variables, then creates Record objs and stores into array
            
            lineData = input.nextLine(); 
            String firstName, lastName, email, color;
            int idNumber;
            double balance;

            String[] data = lineData.split(", ");  //separates text data from file into fields
            firstName = data[0];
            lastName = data[1];
            email = data[2];
            idNumber = Integer.parseInt(data[3]);
            color = data[4];
            balance = Double.parseDouble(data[5]);

            /*  DEBUGGING PURPOSES
            //System.out.println(lineData);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(email);
            System.out.println(idNumber);
            System.out.println(color);
            System.out.println(balance);
            

            recArray[i] = createRecord(firstName, lastName, email, idNumber, color, balance);
        }
        */
        

        input.close();
        return recArray;
    }
    
    
    //This method creates Record objects. This method is called in readFileToRecord().
    private MyRecord createRecord(String firstName, String lastName, String email, 
            int idNumber, String color, double balance){
        
        MyRecord recordObj = new MyRecord(firstName, lastName, email, idNumber, color, balance);
        
        return recordObj;
    }
    
    
        //This method adds space characters to FixedRecord fields
    private String padRight(String field, int fieldLength) {
        if (field.length() < fieldLength) {
            for (int i = field.length(); i < fieldLength; i++) {
                field += " ";
            }
        }
        return field;
    }
    
    
    //This method converts Record objects to FixedRecord objects
    private MyFixedRecord recordToFixedRecord(MyRecord recObj){
        
        int firstNameLength = 10;
        int lastNameLength = 15;
        int emailLength = 25;
        int idNumberLength = 10;
        int colorLength = 10;
        int balanceLength = 10;
        int totalLength = firstNameLength + lastNameLength + emailLength +idNumberLength + colorLength + balanceLength;
        int leftoverSpace = 0;
        
        if (fixedRecordLength > 80){ //adds extra spaces to end of record
            leftoverSpace = fixedRecordLength - totalLength;
            balanceLength += leftoverSpace;
        }
        
        String firstName = recObj.getFirstName();
        String lastName = recObj.getLastName();
        String email = recObj.getEmail();
        String idNumber = Integer.toString(recObj.getIdNumber());
        String color = recObj.getColor();
        String balance = Double.toString(recObj.getBalance());
        
        firstName = padRight(firstName, firstNameLength);
        lastName = padRight(lastName, lastNameLength);
        email = padRight(email, emailLength);
        idNumber = padRight(idNumber, idNumberLength);
        color = padRight(color, colorLength);
        balance = padRight(balance, balanceLength);
        
        //System.out.println("firstname padright length" + firstName.length());
        
        char[] firstNameArr = firstName.toCharArray();
        char[] lastNameArr = lastName.toCharArray();
        char[] emailArr = email.toCharArray();
        char[] idNumberArr = idNumber.toCharArray();
        char[] colorArr = color.toCharArray();
        char[] balanceArr = balance.toCharArray();
        
        MyFixedRecord fixedRecObj = new MyFixedRecord(firstNameArr, lastNameArr, emailArr,
            idNumberArr, colorArr, balanceArr);
        
        return fixedRecObj;
    }
    
    //This method converts Record[] to FixedRecord[]
    private MyFixedRecord[] recArrToFixedRecArr(MyRecord[] recArr, MyFixedRecord[] fixedRecArr){
        //int numLinesData = 5;
        MyFixedRecord fixedRecObj = new MyFixedRecord();
        
        for (int i = 0; i < totalRecords; i++){
            fixedRecObj = recordToFixedRecord(recArr[i]);
            fixedRecArr[i] = fixedRecObj;
        }
        
        return fixedRecArr;
    }
    
    
    //This method writes the FixedRecord objects to a text file
    private void writeFixedRecordToFile(MyFixedRecord[] fixedRecArr) throws FileNotFoundException{
        String fileName = "project1_MyFixedRecord_output.txt";
        File fileObject = new File(fileName);
        PrintWriter output = new PrintWriter(fileObject);
        
        for (int i = 0; i < totalRecords; i++){  
            char[] firstName = fixedRecArr[i].getFirstName();
            char[] lastName = fixedRecArr[i].getLastName();
            char[] email = fixedRecArr[i].getEmail();
            char[] idNumber = fixedRecArr[i].getIdNumber();
            char[] color = fixedRecArr[i].getColor();
            char[] balance = fixedRecArr[i].getBalance();
            
            output.print(firstName);
            output.print(lastName);
            output.print(email);
            output.print(idNumber);
            output.print(color);
            output.println(balance);
        }
        
        output.close();
    }
    
    
     // EVERYONE, PLEASE USE THE SAME HASH FUNCTION, DO NOT CHANGE
    public static int hashFunc3(String key) {
      int hashval = 0;
      for (int j=0; j< key.length(); j++) {
        int letter = key.charAt(j) - 96;
        if (letter > 0 ) {
          hashval = (hashval * 27 + letter) % HASHSIZE;
        }
      }
      return hashval;
    }
}
