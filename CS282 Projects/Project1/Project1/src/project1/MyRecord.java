/**
 * @author Gaven Grantz
 * October 5, 2018
 * CS282 Project #1 - Flat Files
 * Source - MyRecord.java
 * Description: MyRecord.java contains the attributes for Record objects.
 */
package project1;

/**
 *
 * @author gafaa
 */
public class MyRecord {
       //Data stored in record objects
    private String firstName, lastName, email, color;
    private int idNumber;
    private double balance;

    
    //Constructor that creates record objects with data parameters
    public MyRecord(String firstName, String lastName, String email, 
            int idNumber, String color, double balance) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.color = color;
        this.idNumber = idNumber;
        this.balance = balance;
    }
    
    
    /////////////Beginning of getters and setters for private member variables//////////////////
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    /////////////End of getters and setters for private member variables//////////////////
    
    
    //printRecord displays the data stored in Record objects to the console
    public void printRecord(int recordIndex){
        System.out.println("-----Record #" + recordIndex + " -----");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("ID Number: " + idNumber);
        System.out.println("Color: " + color);
        System.out.println("Balance: " + balance);
        //System.out.println("-----End Record-----");
    }
    
     
}
