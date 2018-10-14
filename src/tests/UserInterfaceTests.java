package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import application.UserInterface;
import javafx.application.Application;
import mapeditor.MapEditor;

public class UserInterfaceTests {
 
  private volatile boolean success = false;
  
  @Test
  public void userInterfaceLaunchTest() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Application.launch(UserInterface.class);
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
