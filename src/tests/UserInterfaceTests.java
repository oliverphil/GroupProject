package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import application.UserInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import mapeditor.MapEditor;

public class UserInterfaceTests {
 
  private volatile boolean success = false;
  
  @Before
  public void setup() {
    new JFXPanel();
  }
  
  @Test
  public void createUserInterface() {
    UserInterface ui = new UserInterface();
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              ui.start(new Stage());
              success = true;
            } catch (Throwable t) {
              if (t.getCause() != null
                  && t.getCause().getClass().equals(InterruptedException.class)) {
                success = true;
                return;
              }
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // do nothing, does not affect running
    }
    thread.interrupt();
    try {
      thread.join(1);
    } catch (InterruptedException e) {
      // do nothing, does not affect running
    }

    assertTrue(success);
  }
 }
