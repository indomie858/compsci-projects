/**
 * @author Gaven Grantz
 * October 5, 2018
 * CS282 Project #1 - Flat Files
 * Source - MyFixedRecord.java
 * Description: MyFixedRecord.java contains the attributes for FixedRecord objects.
 */
package project1;


public class MyFixedRecord {
    private final int FIRSTNAMELENGTH = 10;
    private final int LASTNAMELENGTH = 15;
    private final int EMAILLENGTH = 25;
    private final int IDNUMBERLENGTH = 10;
    private final int COLORLENGTH = 10;
    private final int BALANCELENGTH = 10;
    
    private char[] firstName ;
    private char[] lastName ;
    private char[] email ;
    private char[] idNumber;
    private char[] color;
    private char[] balance;
/*
    private char[] firstName = new char[FIRSTNAMELENGTH];
    private char[] lastName = new char[LASTNAMELENGTH];
    private char[] email = new char[EMAILLENGTH];
    private char[] idNumber = new char[IDNUMBERLENGTH];
    private char[] color = new char[COLORLENGTH];
    private char[] balance = new char[BALANCELENGTH];
    */
    public MyFixedRecord() {
    }
    
    
    public MyFixedRecord(char[] firstName, char[] lastName, char[] email, char[] idNumber, char[] color, char[] balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idNumber = idNumber;
        this.color = color;
        this.balance = balance;
    }
    
    
    public char[] getFirstName() {
        return firstName;
    }

    public void setFirstName(char[] firstName) {
        this.firstName = firstName;
    }

    public char[] getLastName() {
        return lastName;
    }

    public void setLastName(char[] lastName) {
        this.lastName = lastName;
    }

    public char[] getEmail() {
        return email;
    }

    public void setEmail(char[] email) {
        this.email = email;
    }

    public char[] getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(char[] idNumber) {
        this.idNumber = idNumber;
    }

    public char[] getColor() {
        return color;
    }

    public void setColor(char[] color) {
        this.color = color;
    }

    public char[] getBalance() {
        return balance;
    }

    public void setBalance(char[] balance) {
        this.balance = balance;
    }
    
    
}
