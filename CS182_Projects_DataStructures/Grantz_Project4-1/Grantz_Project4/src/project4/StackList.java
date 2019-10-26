/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - StackList.java
 * Description: This java class is a stack using linked list data structure.
 */
package project4;


public class StackList implements MyStack{
    private Room top;
    private int numLinks;
    
    public StackList(){
        top = null;
        numLinks = 0;
    }
    
    
    public void push(Room obj){
        obj.next = null;
        if (top == null){
            top = obj;
        }
        else {
            obj.next = top;
            top = obj;
        }
        numLinks++; 
    }
    
    public Room pop(){
        Room roomObj = top;
        top = roomObj.next;
        numLinks--;
        return roomObj;
    }
    
    public Room peek(){
        return top;
    }
    
    public boolean empty(){
        return top == null;
    }
    
 
}
