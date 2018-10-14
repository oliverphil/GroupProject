package tests;

import static org.junit.Assert.assertTrue;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import mapeditor.MapEditor;

import org.junit.Test;

public class MapEditorTests {

  private volatile boolean success = false;

  @Test
  public void setup() {
    MapEditor editor = new MapEditor();
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              editor.start(new Stage());
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
