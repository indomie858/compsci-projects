/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - StackArray.java
 * Description: My custom Stack class that pushes Room objects containing colors/codes 
 * onto a stack when entering a room and pops them off when returning to a room in the Temple. 
 */

package project4;


public class StackArray implements MyStack{
    private Room[] roomStack;
    private int topRoom;
    private int max = 20;
    
    public StackArray(){
        topRoom = -1;
        roomStack = new Room[max];
    }
    

    public void push(Room roomobj){
        try {
            if (!stackIsFull()){
                roomStack[++topRoom] = roomobj;
            }
        } catch (Exception e){
            System.out.println("Stack is full");
        }
    }
    
    public Room pop(){
        return roomStack[topRoom--];
    }
    
    public Room peek(){
            return roomStack[topRoom];
    }
    
    public void clearStack(){
        roomStack = new Room[max];
        topRoom = -1;
    }
    
    public boolean empty(){
        return topRoom < 0;
    }
    
    public boolean stackIsFull(){
        return topRoom == max-1;
    }
    
    
    
    
}
