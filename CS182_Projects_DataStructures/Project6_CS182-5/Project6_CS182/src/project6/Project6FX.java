/**
 * @author Gaven Grantz
 * May 22, 2018
 * CS182 Project #6 - Hash Crash
 * Source - Project6FX.java
 * Description: In this project we will visit our new friend, the array of toons. 
 * This program will create a hash table, loading it with toon names and counting 
 * the crashes. Since the size of the array the hash table is built with has a 
 * huge impact on the probability of collisions, the program will allow the user
 * to enter the hash tables array size. 
 */
package project6;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author G
 */
public class Project6FX extends Application {
    
    private static int xpos = 0, ypos = 0; // place window at this position
    private static int xsize = 700, ysize = 800; // set window to this size
    private static int outputAreaX = 700, outputAreaY = 750; //text output area size
    
    private Button hashCrashButton;
    private TextField arraySizeField;
    private TextArea outputArea;
    private int hashArraySize;
    private HashTable hashTable;
    private int crashCount;
    
    
    private static String [] names = { "fred" , "barney", "tom", "jerry", "larry", "moe","curly",
        "betty" , "wilma", "bart", "homer", "marge", "maggie", "lisa",
        "pebbles" , "bambam", "smithers", "burns", "milhouse", "george", "astro",
        "dino" , "mickey", "minnie", "pluto", "goofy", "donald", "huey",
        "louie" , "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy",
        "dopey" , "sleepy", "bambi", "belle", "gaston", "tarzan", "jane",
        "simba" , "scar", "mufasa", "ariel", "flounder", "bugs", "daffy",
        "elmer" , "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby",
        "peggy" , "spot", "pongo", "perdy", "buzz", "potatohead", "woody",
        "chuckie" , "tommy", "phil", "lil", "angelica", "dill", "spike",
        "pepe" , "speedy", "yosemite", "sam", "tweety", "sylvester", "granny",
        "spiderman" , "batman", "superman", "supergirl", "robin", "jimmy","olsen",
        "thing" , "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman"
    }; 
    
    @Override
    public void start(Stage primaryStage) {
        
        HBox top = new HBox();
        top.setPadding(new Insets(10, 5, 5, 5));
        top.setSpacing(10.0);
        top.setAlignment(Pos.CENTER);
        
        Label hashLabel = new Label();
        hashLabel.setText("Enter the size for the array: ");
        
        arraySizeField = new TextField();
        arraySizeField.setPrefWidth(50.0);
        
        hashCrashButton = new Button();
        hashCrashButton.setText("Hash Crash");
        hashCrashButton.setOnAction(new ButtonHandler());
        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(new ExitHandler());
        
        top.getChildren().addAll(hashLabel, arraySizeField, hashCrashButton, exitButton);
        
        Pane centerPane = new Pane();
        
        outputArea = new TextArea("");
        outputArea.relocate(0, 10);
        outputArea.setEditable(false);
        outputArea.setPrefSize(outputAreaX, outputAreaY);
        outputArea.setWrapText(true);
        //outputArea.setPrefRowCount(20);
        
        centerPane.getChildren().add(outputArea);
        
        BorderPane main = new BorderPane();
        main.setTop(top);
        main.setCenter(centerPane);
        
        
        Scene scene = new Scene(main, xsize, ysize);
        
        primaryStage.setTitle("HASH CRASH!");
        primaryStage.setScene(scene);
        primaryStage.setX(xpos);
        primaryStage.setY(ypos);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class ButtonHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == hashCrashButton){  
                //outputArea.setText("--Hash Crash button pressed");
                String strArraySize = arraySizeField.getText();
                int size = 0;
                try {
                    size = Integer.parseInt(strArraySize);
                    hashArraySize = size;
                    if (hashArraySize > 90){
                    createHashTable();
                
                    printCrashesConsole();
                    
                    printCountWindow();
                    
                    printCrashesToWindow();
                    } else {
                        outputArea.setText("Array size needs to be greater than 90");
                    }
                        
                } catch (Exception x){
                    outputArea.setText("Not a valid input. Please enter an integer value for hash array size");
                }
            }
        }
    }    

    class ExitHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
            System.exit(0);
        }
    }
    
    
    
    private void createHashTable(){    //this method creates the hash table
        hashTable = new HashTable(hashArraySize);
        hashTable.populateHashTable(names);
    }
    
    private void printCrashesConsole(){   //this method prints collisions to the console for debugging
        hashTable.collisionPrintToConsole(names);
    }
    
    private void printCountWindow(){  //this method displays the number of crashes to the centerPane
        crashCount = 0;
        for (int i = 0; i < names.length; i++){
            DataItem crashObj = hashTable.checkCollisionObj(names[i]);
            
            if (crashObj != null){
                crashCount++;
            }
            outputArea.setText("Number of Crashes: " + crashCount);
        }
    }
    
    private void printCrashesToWindow(){  //this method displays the collisions to the centerPane
        int j = 0;
        for (int i = 0; i < names.length; i++){
            DataItem crashObj = hashTable.checkCollisionObj(names[i]);
            
            if (crashObj != null){
                j++;
                int calculatedHash = hashTable.hashFunc3(names[i]);
                int position = crashObj.getActualPosition();  
                outputArea.appendText("\nHash Crash:" + "\t\t" + crashObj.getKeyString() + "\t\tshould be at---->" + calculatedHash + "\t\tfound at---->" + position);
            }
            //outputArea.setText("Number of Crashes: " + crashCount);
        }
    }
    
}
