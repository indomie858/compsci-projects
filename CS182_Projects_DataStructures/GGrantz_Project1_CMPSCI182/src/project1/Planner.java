/*
 * @author Gaven Grantz
 * CMPSCI 182
 * Professor Ferguson
 */

/* Extra method: monthToIntValue(String month)
 * What is it? - This method converts the month into an integer value.
 * Why did I use it? - I used the method in my compareAppointment method to 
 * ensure that the appointment objects are inserted in the proper position.
 */
package project1;

public class Planner {
    private Appointment[] apptArray = new Appointment[20];
    
    public Planner(){
        Appointment defObj1 = new Appointment("Mar", 4, 17, 30, "Quiz 1");
        Appointment defObj2 = new Appointment("Apr", 1, 17, 30, "Midterm");
        Appointment defObj3 = new Appointment("May", 6, 17, 30, "Quiz 2");
        Appointment defObj4 = new Appointment("Jun", 3, 17, 30, "Final");

        apptArray[0] = defObj1;
        apptArray[1] = defObj2;
        apptArray[2] = defObj3;
        apptArray[3] = defObj4;
    }
    
    public static void main(String[] args){
        Planner plannerObj = new Planner();
        System.out.println("Welcome! Please select one of the following: ");
        plannerObj.run();
    }
    
    public void run(){
        System.out.print("A)dd Appointment | D)elete Appointment | L)ist Appointment | E)xit : ");
        char select = UserInput.getChar();
        switch (select) {
            case 'a':
            case 'A':
                addAppointment();
                break;
            case 'd':
            case 'D':
                deleteAppointment();
                break;
            case 'l':
            case 'L':
                listAppointment();
                break;
            case 'e':
            case 'E':
                System.exit(0);
            default:
                System.out.println("Invalid value. Please choose one of the following: ");
                run();
        }
    }
    
    public void addAppointment(){
        Appointment addAppt = new Appointment();
        addAppt.inputAppointment();
        insertAppointment(addAppt);
        run();
    }
    
    public void deleteAppointment(){
        System.out.print("Delete Appointment: Please enter the position number from the list: ");
        int appPosition = UserInput.getInt(1, apptArray.length) - 1;
        for (int j=appPosition; j<apptArray.length - 1; j++) {
            apptArray[j] = apptArray[j+1]; 
        }  
        run();
    }
    
    public void listAppointment(){
        for (int i = 0; i < apptArray.length; i++){
            System.out.println((i + 1) + ".  " + apptArray[i] + " " );
        }
        run();
    }
    
    public void insertAppointment(Appointment A1){
        try {
        for (int i = apptArray.length -1; i > -1; i--){
            if (apptArray[i] != null){
                if (compareAppointment(A1, apptArray[i]) == true){
                    apptArray[i+1]= apptArray[i];
                    apptArray[i] =  A1;
                } else{
                    apptArray[i+1] = A1;
                    break;
                } 
            }
        }
        if (apptArray[apptArray.length - 1] != null){
            System.out.println("You've reached the maximum amount of appointments.");
            System.out.println("To add another appointment, please delete an existing one.");
        }
        } catch (Exception e){
            System.out.print("Error: ");
        }
    }
      
    public boolean compareAppointment(Appointment A1, Appointment A2){ 
        boolean compare, compareMonth, compareDay, compareHour, compareMinute, compareMessage;
        String message1 = A1.getMessage();
        String message2 = A2.getMessage();
        
        compareMonth = monthToIntValue(A1.getMonth()) < monthToIntValue(A2.getMonth());
       
        compareDay = A1.getDay() < A2.getDay();  
        
        compareHour = A1.getHour() < A2.getHour();
                
        compareMinute = A1.getMinute() < A2.getMinute();    
        
        compareMessage = message1.charAt(0) < message2.charAt(0);
        
        if (compareMonth)
            return true;
        
        else if (monthToIntValue(A1.getMonth()) == monthToIntValue(A2.getMonth()) && compareDay)
            return true;

        else if (monthToIntValue(A1.getMonth()) == monthToIntValue(A2.getMonth()) && (A1.getDay() == A2.getDay()) && compareHour)
            return true;
        
        else if (monthToIntValue(A1.getMonth()) == monthToIntValue(A2.getMonth()) && (A1.getDay() == A2.getDay()) && (A1.getHour() == A2.getHour()) && compareMinute)
            return true;
        
        else if (monthToIntValue(A1.getMonth()) == monthToIntValue(A2.getMonth()) && (A1.getDay() == A2.getDay()) && (A1.getHour() == A2.getHour()) && (A1.getMinute() == A2.getMinute()) && compareMessage)
            return true;
        
        return false;
    }
    
    private int monthToIntValue(String month){ //This method converts the month into an integer value for comparisonAppointment()
        int numMonth;
        switch (month.toUpperCase()){
            case "JAN" :
                numMonth = 1;
                break;
            case "FEB" :
                numMonth = 2;
                break;
            case "MAR" :
                numMonth = 3;
                break;
            case "APR" :
                numMonth = 4;
                break;
            case "MAY" :
                numMonth = 5;
                break;
            case "JUN" :
                numMonth = 6;
                break;
            case "JUL" :
                numMonth = 7;
                break;
            case "AUG" :
                numMonth = 8;
                break;
            case "SEP" :
                numMonth = 9;
                break;
            case "OCT" :
                numMonth = 10;
                break;
            case "NOV" :
                numMonth = 11;
                break;
            case "DEC" :
                numMonth = 12;
                break;
            default:
                numMonth = 0;
                break;   
        }
        return numMonth;
    }
}