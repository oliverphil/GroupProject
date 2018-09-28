package application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


/**
 * Previously discussed the idea of having information "pop-up";
 * This class would create alerts/notifications
 */

public class Notification {

	private static Button closeButton = new Button("Exit window");
	private String windowTitle;
	private String displayMessage;
	
	public Notification(String window, String message, String closeButton){
		this.windowTitle = window;
		this.displayMessage = message;
		changeCloseButtonContent(closeButton);
		display(windowTitle, displayMessage);
	}
	
    public static void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(50);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void changeCloseButtonContent(String newContent) {
    	closeButton.setText(newContent);
    }

}
