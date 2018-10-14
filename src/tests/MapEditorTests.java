package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mapeditor.FloorTileMenu;
import mapeditor.IconsMenu;
import mapeditor.MapEditor;

import org.junit.Test;

public class MapEditorTests {

  private volatile boolean success = false;

  @Test
  public void createMapEditor() {
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

  @Test
  public void createFloorTileMenu() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            try {
              FloorTileMenu ftm = new FloorTileMenu();
              ftm.start(new Stage());
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

  @Test
  public void createIconsMenu() {

    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              IconsMenu iconsMenu = new IconsMenu();
              iconsMenu.start(new Stage());
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

  @Test
  public void mapEditorHandle_1() {
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
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 100.0,
                  100.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false,
                  false, false, false, false, false, null);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                e1.printStackTrace();
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              if (t.getCause() != null
                  && t.getCause().getClass().equals(InterruptedException.class)) {
                MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 100.0,
                    100.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false,
                    false, false, false, false, false, null);

                try {
                  Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                      new Class[] { MouseEvent.class });
                  onClick.setAccessible(true);
                  onClick.invoke(editor, new Object[] { event });
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e1) {
                  e1.printStackTrace();
                  fail("Should be able to access methods");
                }
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

  @Test
  public void mapEditorHandle_2() {
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
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 100.0,
                  100.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false,
                  false, false, false, false, false, null);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                e1.printStackTrace();
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              if (t.getCause() != null
                  && t.getCause().getClass().equals(InterruptedException.class)) {
                MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 100.0,
                    100.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false,
                    false, false, false, false, false, null);

                try {
                  Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                      new Class[] { MouseEvent.class });
                  onClick.setAccessible(true);
                  onClick.invoke(editor, new Object[] { event });
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e1) {
                  e1.printStackTrace();
                  fail("Should be able to access methods");
                }
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
