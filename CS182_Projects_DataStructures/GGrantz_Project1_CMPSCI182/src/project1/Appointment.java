
package project1;
/*
 * @author Gaven Grantz
 * CMPSCI 182
 * Professor Ferguson
 */

public class Appointment {
    private int day, hour, minute;
    private String month, message;
    
    /* Main Method test
    public static void main(String[] args){
        Appointment obj1Test = new Appointment();
        obj1Test.inputAppointment();
        obj1Test.toString();
        
        Appointment obj2Test = new Appointment("mar", 5, 9, 7, "test");
    }
    */
    
    public Appointment(){
	month = "JAN";
	day = 1;
	hour = 0;
	minute = 0;
	message = "_"; 
    }

    public Appointment(String month, int day, int hour, int minute, String message){
	setMonth(month);
	setDay(day);
	setHour(hour);
	setMinute(minute);
	setMessage(message);
    }
    
    public int getDay(){
        return day;
    }
    
    public int getHour(){
        return hour;
    }
    
    public int getMinute(){
        return minute;
    }
    
    public String getMonth(){
        return month;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setDay(int inputDay){
        int max;
        if (getMonth() == "FEB"){
            max = 28;
        } else {
            max = 31;
        }       
        
        if (inputDay >= 1 && inputDay <= max){
	    day = inputDay;
	} else {
            System.out.print("Invalid value. Enter valid integer for day: ");
	}
    }
    
    public void setHour(int inputHour){
        if (inputHour >= 0 && inputHour < 24){
	    hour = inputHour;
	} else {
	    System.out.print("Invalid value. Enter valid integer for hour: ");
	}
    }
    
    public void setMinute(int inputMinute){
        if (inputMinute >= 0 && inputMinute < 60){
	    minute = inputMinute;
	} else {
            System.out.print("Invalid value. Enter valid integer for minute: ");
	}
    }
    
    public boolean setMonth(String inputMonth){
        String validMonths = new String ("jan feb mar apr may jun jul aug sep oct nov dec");
        if (inputMonth.length() == 3){
            if (validMonths.contains(inputMonth.toLowerCase()) == true){
                    month = inputMonth.toUpperCase();
                    return true;
            } else{
                System.out.print("Invalid value. ");
                return false;
            }
	} else {
            System.out.print("Invalid value. ");
            return false;
	}
        
    }
 
    public void setMessage(String inputMessage){
        if (inputMessage.length() >= 0 && inputMessage.length() <= 40){
	    message = inputMessage;
	} else {
            System.out.print("Invalid value. Enter valid message with a maximum of 40 characters: ");
	}
    }

    public String toString(){
	String formatAppointment, formatHour, formatMinute;
	
	if (hour < 10){
	    formatHour = "0" + hour;
	} else {
	    formatHour = Integer.toString(hour);
	}

	if (minute < 10){
	    formatMinute = "0" + minute;
	} else {
	    formatMinute = Integer.toString(minute);
	}
	
	formatAppointment = "Month-" + month + " Day-" + day + " Time-" + formatHour + 
                ":" + formatMinute + " Message-" + message;

	return formatAppointment;
    }

    public void inputAppointment(){
        boolean x = false;
        do{
        System.out.print("Please enter the first 3 letters of the month: ");
	x = setMonth(UserInput.getString(3, 3));
        } while (x == false);
              
        System.out.print("Please enter an integer value for day: ");
	setDay(UserInput.getInt(1, 31));
        
        System.out.print("Please enter an integer value for hour: ");
	setHour(UserInput.getInt(0, 24));
        
        System.out.print("Please enter an integer value for minutes: ");
	setMinute(UserInput.getInt(0, 60));
        
        System.out.print("Please enter a message containing between 1 and 40 characters: ");
	setMessage(UserInput.getString(0, 40));
    }
}
