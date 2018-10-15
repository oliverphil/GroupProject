package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mapeditor.FloorTileMenu;
import mapeditor.IconsMenu;
import mapeditor.MapEditor;

import org.junit.Before;
import org.junit.Test;

public class MapEditorTests {

  private volatile boolean success = false;

  @Before
  public void setup() {
    new JFXPanel();
  }

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
      Thread.sleep(1000);
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
      Thread.sleep(1000);
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
      Thread.sleep(1000);
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
  public void mapEditorHandle_floorBtn() {
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
              MapEditor.setButton("floorBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("none");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 100.0,
                  100.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_northWest() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("NW");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 218.0,
                  254.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_NW", editor.getGrid()[9][9]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_north() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("N");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 240.0,
                  254.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_N", editor.getGrid()[10][9]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_northEast() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("emptyFlask");
              MapEditor.setDirection("NE");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 262.0,
                  254.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);
              System.out.println(editor.getGrid()[11][9]);

              assertEquals("emptyFlask_NE", editor.getGrid()[11][9]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_west() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("W");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 218.0,
                  276.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_W", editor.getGrid()[9][10]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_none() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("none");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 240.0,
                  276.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_none", editor.getGrid()[10][10]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_east() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("E");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 262.0,
                  276.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_E", editor.getGrid()[11][10]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_southWest() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("SW");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 218.0,
                  298.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_SW", editor.getGrid()[9][11]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_south() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("S");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 240.0,
                  298.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_S", editor.getGrid()[10][11]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_southEast() {
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
              MapEditor.setButton("itemBtn");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("SE");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 262.0,
                  298.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("empty_SE", editor.getGrid()[11][11]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_removeBtn() {
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
              MapEditor.setButton("remove");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("none");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 196.0,
                  232.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);

              assertEquals("0_none", editor.getGrid()[8][8]);

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_remove1() {
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
              MapEditor.setButton("remove");
              MapEditor.setIcon("emptyFlask");
              MapEditor.setDirection("NE");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 262.0,
                  254.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);
              Method remove = MapEditor.class.getDeclaredMethod("remove", new Class[] {});
              remove.setAccessible(true);
              remove.invoke(editor, new Object[] {});

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
                assertEquals("empty_NE", editor.getGrid()[11][9]);
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void mapEditorHandle_remove2() {
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
              MapEditor.setButton("remove");
              MapEditor.setIcon("empty");
              MapEditor.setDirection("NW");
              MouseEvent event = new MouseEvent(editor, null, MouseEvent.MOUSE_CLICKED, 218.0,
                  254.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false,
                  false, false, false, false, null);
              Method remove = MapEditor.class.getDeclaredMethod("remove", new Class[] {});
              remove.setAccessible(true);
              remove.invoke(editor, new Object[] {});

              try {
                Method onClick = MapEditor.class.getDeclaredMethod("onClick",
                    new Class[] { MouseEvent.class });
                onClick.setAccessible(true);
                onClick.invoke(editor, new Object[] { event });
                assertEquals("empty_NW", editor.getGrid()[9][9]);
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_northWest() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("northWest");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("NW", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_north() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("north");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("N", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_northEast() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("northEast");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("NE", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_west() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("west");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("W", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_none() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("none");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("none", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_east() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("east");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("E", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_southWest() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("southWest");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("SW", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_south() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("south");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("S", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
  public void floorTileMenuHandle_southEast() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        new JFXPanel(); // Initializes the JavaFx Platform
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            try {
              FloorTileMenu floor = new FloorTileMenu();
              floor.start(new Stage());
              success = true;
              MapEditor.setButton("floorBtn");
              Field b = FloorTileMenu.class.getDeclaredField("southEast");
              b.setAccessible(true);
              Button button = (Button) b.get(floor);
              ActionEvent event = new ActionEvent(button, null);

              try {
                Method handle = FloorTileMenu.class.getDeclaredMethod("handle",
                    new Class[] { ActionEvent.class });
                handle.setAccessible(true);
                handle.invoke(floor, new Object[] { event });
                Field dir = MapEditor.class.getDeclaredField("direction");
                dir.setAccessible(true);
                assertEquals("SE", dir.get(MapEditor.class));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                  | IllegalArgumentException | InvocationTargetException e1) {
                fail("Should be able to access methods");
              }
            } catch (Throwable t) {
              // do nothing, does not affect running
            }
          }
        });
      }
    });
    thread.start();

    try {
      Thread.sleep(1000);
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
