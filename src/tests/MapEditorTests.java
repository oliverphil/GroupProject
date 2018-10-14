package tests;

import static org.junit.Assert.assertTrue;

import javafx.application.Application;

import mapeditor.MapEditor;

import org.junit.Test;

public class MapEditorTests {

  private volatile boolean success = false;

  @Test
  public void setup() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Application.launch(MapEditor.class);
          success = true;
        } catch (Throwable t) {
          if (t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
            success = true;
            return;
          }
        }
      }
    };
    thread.setDaemon(true);
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
