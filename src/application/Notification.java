package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Information in the form of a "pop-up". 
 * Used for alerts and notifications to the player.
 */
public class Notification {

  private static Button closeButton = new Button("Exit window");
  private String windowTitle;
  private String displayMessage;

  /**
   * Creates a new notification via launching a window.
   * @param window window title to appear
   * @param message message contained by the window
   * @param closeButton message written on the 'close' button
   */
  public Notification(String window, String message, String closeButton) {
    this.windowTitle = window;
    this.displayMessage = message;
    changeCloseButtonContent(closeButton);
    display(windowTitle, displayMessage);
  }

  /**
   * Calls for the launch of a window.
   * @param title title of window
   * @param message message contained by window
   */
  public static void display(String title, String message) {
    Stage window = new Stage();

    // Block events to other windows
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);

    Label label = new Label();
    label.setText(message);
    closeButton.setOnAction(e -> window.close());

    VBox layout = new VBox(50);
    layout.getChildren().addAll(label, closeButton);
    layout.setAlignment(Pos.CENTER);

    // Display window and wait for it to be closed before returning
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();
  }

  /**
   * Changes the contents of button's text.
   * @param newContent new content to be displayed inside button
   */
  public void changeCloseButtonContent(String newContent) {
    closeButton.setText(newContent);
  }

}
