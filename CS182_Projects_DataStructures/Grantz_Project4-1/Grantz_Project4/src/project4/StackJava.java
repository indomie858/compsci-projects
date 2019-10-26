/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - StackJava.java
 * Description: This java class is a stack using the Java Stack Class data structure.
 */
package project4;
import java.util.Stack;



public class StackJava implements MyStack{

    private Stack<Room> stack;
    
    public StackJava(){
        stack = new Stack<Room>();
    }
    
    
    public void push(Room obj){
        stack.push(obj);
    }
    
    
    public Room peek(){
        return stack.peek();
    }
    
    public Room pop(){
        return stack.pop();
    }
    
    public boolean empty(){
        return stack.empty();
    }
}
