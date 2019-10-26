/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - Temple.java
 * Description: This class provides the layout of the temple using string arrays.
 * Its purpose is to return a value of true if the current room and destination room
 * are adjacent.
 */

package project4;


public class  Temple {
    private String[] green = {"brown", "pink", "blue"}; 
    private String[] pink = {"green", "brown", "blue"};
    private String[] brown = {"pink", "green", "red"};
    private String[] blue = {"green", "pink", "yellow"};  
    private String[] red = {"brown", "yellow"};
    private String[] yellow = {"red", "blue", "gold"};
    private String[] gold = {"yellow"};
    
    public boolean RoomChecker(String destRoomColor, String currentRoomColor) {
        switch (currentRoomColor) {
            case "green":
                return CheckingARoom(destRoomColor, green);
            case "pink":
                return CheckingARoom(destRoomColor, pink);
            case "brown":
                return CheckingARoom(destRoomColor, brown);
            case "blue":
                return CheckingARoom(destRoomColor, blue);
            case "red":
                return CheckingARoom(destRoomColor, red);
            case "yellow":
                return CheckingARoom(destRoomColor, yellow);
            case "gold":
                return CheckingARoom(destRoomColor, gold);

        }
        return false;
    }

       
      private boolean CheckingARoom(String destRoom, String[] adjacentRooms) {

          for (int i=0; i<adjacentRooms.length; i++) {
              if (adjacentRooms[i].equals(destRoom)) {
                  return true;
              }
          }
          return false;
      }  
    
    
}
