/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - Room.java
 * Description: The Room class has a String member variable for 
 * the color and an integer member variable for the 3-digit numeric code. 
 */


package project4;

import javafx.scene.image.Image;

public class Room {
    private String color;
    private int code;
    private Image roomImage;
    protected Room next = null;
    
    
    public Room(){
        color = "default";
        code = 000;
        roomImage = new Image("File:Images/game_over.png");
    }
    
    
    public Image getImage(){
        return roomImage;
    }
    
    
    private void setImage(String roomColor){
           roomImage = new Image("File:Images/" + roomColor + "_room.jpg");
    }
    
    private boolean checkRoomColor(String roomColor){
        
        switch (roomColor){
            case "green":
                return true;
            case "red":
                return true;
            case "pink":
                return true;
            case "brown":
                return true;
            case "blue":
                return true;
            case "yellow":
                return true;
            case "gold":
                return true;
        } return false;
    }
 
    public String getColor(){
        return color;
    }
    
    public void setColor(String newColor){
        
        boolean check = checkRoomColor(newColor);
        if (check == true){
            color = newColor;
            setImage(color);
        } else {
            System.out.println("Invalid color.");
        }        
    }
    
    public int getCode(){
        return code;
    }
    
    public void setCode(int newCode){
        if (newCode >=0 && newCode <1000){
            code = newCode;
        }
    }
    
    public String toString(){
        String codeString;
        if (code >= 0 && code < 10){
            codeString = "00" + code;
        } else if (code >= 11 && code < 100){
            codeString = "0" + code;
        } else {
            codeString = "" + code;
        }
        
        return "Color: " + color + "  Code: " + codeString;
    }
    
}

