package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Summary:
 * Sorry I can't make the start of today's meeting!
 * It's possible that I'll be on campus and make it for the end.
 * 
 * Essentially, I've started to write the Game GUI and would like
 * some feedback on what Object we want to use as a canvas for drawing,
 * as well as the format for reading/writing files so that I can write
 * save/load data. 
 */



public class GUI extends Application implements EventHandler<ActionEvent>{

    Stage window;
    Button loadButton, saveButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("The Great Adventure");
        loadButton = new Button("Load");
        saveButton = new Button("Save");


        StackPane layout = new StackPane();
        layout.getChildren().add(loadButton);
        layout.getChildren().add(saveButton);

        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    
    }

     //**TODO: Discuss with Wanja format of reading/writing file formats
    @Override
    public void handle(ActionEvent action) {
        //decides which event caused this method to trigger
        if (action.getSource() == loadButton){
            //perform a load function
            //by reading from a file
        }
    
        if (action.getSource() == saveButton){
            //perform a save function
            //by writing to a file
        }

       // loadButton.setOnAction(e -> Notification.display("Success.", "Files loaded!"));
    }

}