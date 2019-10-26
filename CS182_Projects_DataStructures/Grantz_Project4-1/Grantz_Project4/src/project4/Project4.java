/**
 * @author Gaven Grantz
 * May 1, 2018
 * CS182 Project #4 - Stacks: Programmer Jones
 * Source - Project4.java
 * Description: In this simple game, the hero is the intrepid Programmer Jones. 
 * His job is to enter the Temple of Gloom and retrieve the Golden Keyboard. 
 * Jones can take any path he wishes to find the Keyboard. There is just one slight problem,
 * he must follow the EXACT same path out of the Temple or his core will be dumped.
 */



/***************************************************************
 
Project Number 4 - Comp Sci 182 - Data Structures
Start Code - Build your program starting with this code
 
Snakes! I Hate Snakes!   -   Indiana Jones
 
Copyright 2015 Christopher C. Ferguson and Aaron Black
This code may only be used with the permission of Christopher C. Ferguson
 
***************************************************************/
 
package project4;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 


public class Project4 extends Application {

  
    
    private static int xpos = 0, ypos = 0; // place window at this position
    private static int xsize = 1000, ysize = 700; // set window to this size
    private static int outputAreaX = 1000, outputAreaY = 75; //text output area size
 
    private Button pushButton, popButton, dumpButton, newGameButton;
    private Button linkedStackButton, arrayStackButton, javaStackButton, changeStackButton;
    private TextField colorField, codeField;
    private TextArea outputArea;
    private Image currentRoomImage;
    private ImageView mainImage;
    private MyStack gameStack;
    private Room currentRoom;
    private boolean obtainedKeyboard = false;
    private boolean linkedStackToggle = false;
    private boolean arrayStackToggle = false;
    private boolean javaStackToggle = false;
    private int comparisonCode = 0;
    private final String MAINMESSAGE = "Who dares enter... The Temple of Gloom! The rules are simple. "
                + "Make your way through the temple to reach the golden keyboard. "
                + ""
                + "Once you obtain the keyboard, go back exactly the way you came. "
                + "\n--Beware! If you don't correctly retrace your steps, you die. "
                + "If you enter an invalid color and/or code for the room, you die."
                + "\n--Choose a stack, then press New Game to begin your doom."; 
                            
    @Override
    public void start(Stage primaryStage) {
        
        
        // this pane contains the buttons and text fields that the user will interract with
        HBox topPane = new HBox();
        topPane.setPadding(new Insets(10, 5, 5, 5)); // sets padding around the topPane
        topPane.setSpacing(10.0); // sets spacing between items in pixels
        topPane.setAlignment(Pos.CENTER); // sets the alignment of items added to topPane
 
        // these are all of the items that are added to the HBox for display
        Label colorLabel = new Label(); // default empty label constructor
        colorLabel.setText("Enter the Color of an Adjacent Room: "); // setting the label text after creation
        topPane.getChildren().add(colorLabel); // adding the label to the HBox
 
        colorField = new TextField(); // default empty text field constructor
        colorField.setPrefWidth(50.0); // sets size of colorField in pixels
        topPane.getChildren().add(colorField); // adding the textfield to the HBox
 
        Label codeLabel = new Label("Set a Code for the Current Room: "); // label with values constructor
        topPane.getChildren().add(codeLabel); // adding
 
        codeField = new TextField("000"); // textfield with starting value constructor (it's empty in this case)
        codeField.setPrefWidth(50.0);
        topPane.getChildren().add(codeField);
        
        
        pushButton = new Button(); // default empty button constructor
        pushButton.setText("Push/Enter"); // setting the button text after creation
        pushButton.setDisable(true);
        pushButton.setOnAction(new ButtonHandler()); // assigning a class to handle events from this button
        topPane.getChildren().add(pushButton);
        
        popButton = new Button("Pop/Return"); // button with text constructor
        popButton.setDisable(true);
        popButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(popButton);
 
        dumpButton = new Button("Dump/Cheat");      //button shows stack in console
        dumpButton.setDisable(true);
        dumpButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(dumpButton);
 
        newGameButton = new Button("New Game");     //starts new game
        newGameButton.setDisable(true);
        newGameButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(newGameButton);
        
        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(new ExitHandler()); // note that this is a different class to handle the button events
        topPane.getChildren().add(exitButton);
 

        // this pane contains the output displayed based on user actions
        Pane centerPane = new Pane();
        int stackButtonWidth = xsize/4;
        
        linkedStackButton = new Button("Stack: Linked List");  //button to select linked list stack
        linkedStackButton.setOnAction(new ButtonHandler());
        linkedStackButton.setPrefWidth(stackButtonWidth);

        arrayStackButton = new Button("Stack: Array");        //button to select array stack
        arrayStackButton.setOnAction(new ButtonHandler());
        arrayStackButton.setPrefWidth(stackButtonWidth);
        arrayStackButton.relocate(stackButtonWidth,0);
        
        javaStackButton = new Button("Stack: Java");        //button to select java stack
        javaStackButton.setOnAction(new ButtonHandler());
        javaStackButton.setPrefWidth(stackButtonWidth);
        javaStackButton.relocate(stackButtonWidth*2,0);
        
        changeStackButton = new Button("Press to change Stack");    //button to change between stacks
        changeStackButton.setOnAction(new ButtonHandler());
        changeStackButton.setPrefWidth(stackButtonWidth);
        changeStackButton.relocate(stackButtonWidth*3, 0);
        
 
        // these are all of the items added to the pane for display
        
        
        outputArea = new TextArea("");
        outputArea.relocate(0, 25);
        outputArea.setEditable(false); //
        outputArea.setPrefSize(outputAreaX, outputAreaY);
        outputArea.setWrapText(true);
        outputArea.setText(MAINMESSAGE);
               
               
        mainImage = new ImageView(new Image("File:Images/temple_entrance.png"));
        mainImage.relocate(0, 25);
        mainImage.setFitWidth(xsize);
        mainImage.setFitHeight(ysize);
        mainImage.setSmooth(true);
        mainImage.setCache(true);

        centerPane.getChildren().addAll(mainImage, outputArea, linkedStackButton, arrayStackButton, javaStackButton, changeStackButton);

     
        // this pane contains the organization for the other panes
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topPane);
        mainPane.setCenter(centerPane);
 
        // this scene makes up the main window of the project
        Scene scene = new Scene(mainPane, xsize, ysize);
        
        primaryStage.setTitle("Programmer Jones and the Temple of Gloom");
        primaryStage.setScene(scene); // adds the above scene to the window
        primaryStage.setX(xpos); // sets the window to open at this x position
        primaryStage.setY(ypos); // sets the window to open at this y position
        primaryStage.show();
        
    }
 
    // ButtonHandler is one implementation of event handling.
    // Multiple buttons are registered to it, and the class itself determines the
    //   necessary action based on who called it.
    // This approach benefits from global buttons, because the handler has to tell where the event came from.
    class ButtonHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == linkedStackButton){   //toggles linked list stack
                outputArea.setText("--You've chosen the Linked List Stack."
                        + "\n--Press New Game to begin your doom...");
                
                linkedStackToggle = true;
                arrayStackToggle = false;
                javaStackToggle = false;
                
                newGameButton.setDisable(false);
                linkedStackButton.setDisable(true);
                arrayStackButton.setDisable(true);
                javaStackButton.setDisable(true);
                
            }
            
            if (e.getSource() == arrayStackButton){   //toggles array stack
                outputArea.setText("--You've chosen the Array Stack."
                        + "\n--Press New Game to begin your doom...");
                
                arrayStackToggle = true;
                linkedStackToggle = false;
                javaStackToggle = false;
                
                newGameButton.setDisable(false);
                linkedStackButton.setDisable(true);
                arrayStackButton.setDisable(true);
                javaStackButton.setDisable(true);
            }
            
            if (e.getSource() == javaStackButton){      //toggles java stack
                outputArea.setText("--You've chosen the Java Stack."
                        + "\n--Press New Game to begin your doom...");
                
                javaStackToggle = true;
                linkedStackToggle = false;
                arrayStackToggle = false;
                
                newGameButton.setDisable(false);
                linkedStackButton.setDisable(true);
                arrayStackButton.setDisable(true);
                javaStackButton.setDisable(true);
            }
            
            if (e.getSource() == changeStackButton){    //changes stack
                outputArea.setText(MAINMESSAGE);
                mainImage.setImage(new Image("File:Images/temple_entrance.png"));
                
                javaStackToggle = false;
                linkedStackToggle = false;
                arrayStackToggle = false;
                
                popButton.setDisable(true);
                pushButton.setDisable(true);
                dumpButton.setDisable(true);
                newGameButton.setDisable(true);
                
                linkedStackButton.setDisable(false);
                arrayStackButton.setDisable(false);
                javaStackButton.setDisable(false);
            }
            
            if (e.getSource() == popButton) {       //button to pop off stack
                String newcolor = colorField.getText().toLowerCase();
                
                String newCode = codeField.getText();
                
                int code = 0;
                try {
                    code = Integer.parseInt(newCode);                             
                } catch (Exception ex){             //validates that the inputted code is an integer
                    gameOver("You typed an invalid code");
                    return;
                }
                
                if (gameStack.empty()){     //checks if stack is empty. if it's NOT empty, then pass room obj to goBack()
                    System.out.println("Stack is empty.");
                    gameOver("You popped an empty stack.");
                } else {
                    Room previousRoom = new Room();  //creates a room object for the previous room
                    previousRoom.setColor(newcolor);
                    previousRoom.setCode(code);       
                    goBack(previousRoom);        
                }
            }
 
            if (e.getSource() == pushButton) {    //button to push room obj onto stack
                String newcolor = colorField.getText().toLowerCase();
                String newCode = codeField.getText();
                
                int code = 0;
                try {
                    code = Integer.parseInt(newCode);                             
                } catch (Exception ex){         //validates that the inputted code is an integer
                    gameOver("You typed an invalid code");
                    return;
                }
                
                if (code < 0 | code >= 1000){
                    gameOver("That's not a 3-digit integer");
                    return;
                }
                
                comparisonCode = code;

                Room newRoom = new Room();
                newRoom.setColor(newcolor);
                newRoom.setCode(code);
                enterRoom(newRoom);
                
                
                
                if (currentRoom.getColor().equals("gold")){
                    pushButton.setDisable(true);
                    obtainedKeyboard = true;
                }               
            }
 
            if (e.getSource() == dumpButton) {          //show stack contents
                System.out.println("Stack Contents Dump: ");
                
                if (gameStack.empty()){
                    System.out.println("--Stack is empty.");
                } else {
                    dumpStack(gameStack);
                }
            }
            
            if (e.getSource() == newGameButton) {
                runGame();
                printCurrentRoom(currentRoom.getColor());
                popButton.setDisable(false);
                pushButton.setDisable(false);
                dumpButton.setDisable(false);
            }
        }
    }
 
    // ExitHandler is an alternate implementation of event handling
    // Each button is registered to a different handler, which performs the desired actions
    // This approach does not require global buttons.
    class ExitHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
            System.exit(0);
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    public void runGame(){      //Starts game and creates two StackArray obj and one Room obj
        outputArea.setDisable(false);
        currentRoom = new Room();       //variable tracks the current Room obj
        currentRoom.setColor("green");
        currentRoomImage = currentRoom.getImage();
        mainImage.setImage(currentRoomImage);
        
        if (arrayStackToggle){
            gameStack = new StackArray();       //using array data structure
            System.out.println("Using Array Stack");
        }
        
        if (linkedStackToggle){
            gameStack = new StackList();        //using linked list data structure
            System.out.println("Using Linked List Stack");
        }
        
        if (javaStackToggle){
            gameStack = new StackJava();        //using java stack data structure
            System.out.println("Using Vector Stack");
        }
        
        System.out.println("New Game");
        System.out.println("-------------------");
    }
    
    private void dumpStack(MyStack currentStack){   //this method creates a second stack in order to print stack contents to the console
        try{
            MyStack tempStack = new StackArray();
            Room tempRoom = new Room();
            
            while (!currentStack.empty()){      //pops rooms off stack and pushes into temp stack
                tempRoom = currentStack.pop();
                tempStack.push(tempRoom);
            }
        
            if (arrayStackToggle){
                System.out.println("---Objects in Array Stack---");
            }
        
            if (linkedStackToggle){    
                System.out.println("---Objects in Linked List Stack---");
            }
        
            if (javaStackToggle){
                System.out.println("---Objects in Java Stack---");
            }
        
            while (!tempStack.empty()){     //pops rooms off tempstack and pushes into stack while printing each room object
                tempRoom = tempStack.pop();
                currentStack.push(tempRoom);
                System.out.println(tempRoom.toString());
            }
            
        } catch (Exception x){
            gameOver("Good job! You dumped an empty stack. Try not doing that.");
        }
        
        
    }
    
    private void enterRoom(Room nRoom){      //this method handles entering a room
        Temple temple = new Temple();
        if (!temple.RoomChecker(nRoom.getColor(), currentRoom.getColor())) { 
            gameOver("You typed an invalid color.");
        } else {
            if (!gameStack.empty()){
                    if (gameStack.peek().getCode() == comparisonCode){
                        gameOver("You used the same code twice");
                        return;
                    }
                }
            
            int currentRoomCode = nRoom.getCode();
            currentRoom.setCode(currentRoomCode);
            
            gameStack.push(currentRoom);
            currentRoom = nRoom;
            currentRoomImage = currentRoom.getImage();
            mainImage.setImage(currentRoomImage);
            printCurrentRoom(currentRoom.getColor());
        }
    }
    
    private void goBack(Room previousRoom){   //this method handles returning to a previous room
        String colorInStack = gameStack.peek().getColor();
        String colorPreviousRoom = previousRoom.getColor();
        
        int codeInStack = gameStack.peek().getCode();
        int codePreviousRoom = previousRoom.getCode();
        
        if (colorInStack.equals(colorPreviousRoom) && codeInStack == codePreviousRoom) {
            currentRoom = gameStack.pop();
            currentRoomImage = currentRoom.getImage();
            mainImage.setImage(currentRoomImage);
            printCurrentRoom(currentRoom.getColor());
        } else {
            gameOver("Your color/code did not match the top of the stack.");
        }
        
        if (gameStack.empty() &&  obtainedKeyboard){
            gameWin();
        }
    }
    
    private void printCurrentRoom(String color){
        switch (color) {
            case "green":
                outputArea.setText("Instructions: The game starts in a green room within the temple. "
                        + "A 3-digit code must be entered to use the door to an adjacent room. "
                        + "That code must be re-entered on the return through the same door. "
                        + "The goal of the game is to move from room to room, each room has a unique color "
                        + "and the player will provide the code, until you reach the gold room. "
                        + "Once there you must pick up the keyboard and back track through the exact same rooms, entering the same codes, "
                        + "or you die."
                        
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: BROWN, PINK, BLUE"
                       );
                break;
            case "pink":
                outputArea.setText("Instructions: Enter the color of an adjacent room. Set a 3-digit integer code. "
                        + "This code will be used in order to return.\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: GREEN, BROWN, BLUE"
                        );
                break;
            case "brown":
                outputArea.setText("Instructions: Enter the color of an adjacent room. Set a 3-digit integer code. "
                        + "This code will be used in order to return.\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: PINK, GREEN, RED"
                        );
                break;
            case "blue":
                outputArea.setText("Instructions: Enter the color of an adjacent room. Set a 3-digit integer code. "
                        + "This code will be used in order to return.\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: GREEN, PINK, YELLOW"
                        );
                break;
            case "red":
                outputArea.setText("Instructions: Enter the color of an adjacent room. Set a 3-digit integer code. "
                        + "This code will be used in order to return.\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: BROWN, YELLOW"
                        );
                break;
            case "yellow":
                outputArea.setText("Instructions: Enter the color of an adjacent room. Set a 3-digit integer code. "
                        + "This code will be used in order to return.\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: RED, BLUE, GOLD"
                        );
                break;
            case "gold":
                outputArea.setText("You've reached the golden keyboard! Now get the hell out of here and go back the way you came!\n"
                        + "\nCurrent Location: " + color.toUpperCase() + "  ---  Adjacent Rooms: YELLOW");
                break;
        }
    }
    
    private void gameOver(String errorMessage){
        Image gameOverImage = new Image("File:Images/game_over.png");
        outputArea.setText("HAHAHAHAHAHAHA YOU'RE DEAD *_*"
                + "\nReason for death: " + errorMessage
                + "\nPress New Game to try again.");
        mainImage.setImage(gameOverImage);
        popButton.setDisable(true);
        pushButton.setDisable(true);
        dumpButton.setDisable(true);
    }
    
    private void gameWin(){
        Image winImage = new Image("File:Images/game_won.jpg");
        outputArea.setDisable(true);
        mainImage.setImage(winImage);
        popButton.setDisable(true);
        pushButton.setDisable(true);
        dumpButton.setDisable(true);
    }
    
}