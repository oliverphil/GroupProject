package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import application.UserInterface;
import gameworld.GameWorld;
import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.HealthFlaskStrategy;
import gameworld.holdables.Item;
import gameworld.holdables.PowerFlaskStrategy;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;

/**
 * A test suite for the user interface
 * @author Philip Oliver - 300398228
 *
 */
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
              fail();
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
  public void testAddEmptyFlask() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);
              Item i = new Flask();
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddHealthFlask() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);
              Flask i = new Flask();
              i.setStrat(new HealthFlaskStrategy());
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddPowerFlask() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);
              Flask i = new Flask();
              i.setStrat(new PowerFlaskStrategy());
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddCrowbar() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Tool i = new Tool();
              i.setName("crowbar");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddPickaxe() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Tool i = new Tool();
              i.setName("pickaxe");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddBoltCutters() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Tool i = new Tool();
              i.setName("boltCutters");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddKhopesh() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Weapon i = new Weapon();
              i.setName("khopesh");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddTorch() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Weapon i = new Weapon();
              i.setName("torch");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddHammer() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Weapon i = new Weapon();
              i.setName("hammer");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddBomb() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Explosive i = new Explosive();
              i.setName("bomb");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAddInvalid() {
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

              Field f = UserInterface.class.getDeclaredField("game");
              f.setAccessible(true);
              GameWorld game = (GameWorld) f.get(ui);

              Weapon i = new Weapon();
              i.setName("invalid");
              game.getPlayer().addToBag(i);

              Method update = UserInterface.class.getDeclaredMethod("update", new Class[] {});
              update.setAccessible(true);
              update.invoke(ui, new Object[] {});

              Field item = UserInterface.class.getDeclaredField("itemInPack");
              item.setAccessible(true);
              assertEquals(i, item.get(ui));
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_1() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("emptyFlask");

              assertEquals("An empty flask", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_2() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("powerFlask");

              assertEquals("A full flask. The liquid looks powerful", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_3() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("healthFlask");

              assertEquals("A full flask. The liquid looks invigorating", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_4() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("crowbar");

              assertEquals("A rusty crowbar", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_5() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("pickaxe");

              assertEquals("A sharp pickaxe. Good for mining", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_6() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("boltCutters");

              assertEquals("A pair of bolt cutters", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_7() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("khopesh");

              assertEquals("A mysterious and powerful ancient sword...", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_8() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("torch");

              assertEquals("A blazing torch", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_9() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("hammer");

              assertEquals("A heavy hammer", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_10() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("bomb");

              assertEquals("An odd bomb", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_11() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("david");

              assertEquals("You attacked Pharoh Pearce!", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_12() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("marco");

              assertEquals("You attacked Mummy Marco!", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_13() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("thomas");

              assertEquals("You attacked Tombstone Thomas!", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_14() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("woodenBlockade");

              assertEquals("Some wooden planks. Looks like they might pry away",
                  itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_15() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("stoneBlockade");

              assertEquals("Crumbled stones block your path", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_16() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("chainBlockade");

              assertEquals("Rusted chains are covering the stone door", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_17() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("healthFountain");

              assertEquals("A spring bubbling with life!", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_18() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("powerFountain");

              assertEquals("A spring bubbling with power!", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_19() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("ladder");

              assertEquals("The old ladder takes you to the surface...", itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testAnimateLabel_20() {
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

              Field f = UserInterface.class.getDeclaredField("itemLabel");
              f.setAccessible(true);
              Label itemLabel = (Label) f.get(ui);

              UserInterface.animateLabel("helpDirective");

              assertEquals("\"Use 'WASD' or Button Pad (LEFT) for movement.\"",
                  itemLabel.getText());
            } catch (Throwable t) {
              fail();
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
  public void testMovement_1() {
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

              Field f = UserInterface.class.getDeclaredField("window");
              f.setAccessible(true);
              Stage s = (Stage) f.get(ui);

              KeyEvent k = new KeyEvent(s, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.W,
                  false, false, false, false);

              Method onKey = UserInterface.class.getDeclaredMethod("onKey",
                  new Class[] { KeyEvent.class });
              onKey.setAccessible(true);
              onKey.invoke(ui, new Object[] { k });
            } catch (Throwable t) {
              fail();
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
  public void testMovement_2() {
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

              Field f = UserInterface.class.getDeclaredField("window");
              f.setAccessible(true);
              Stage s = (Stage) f.get(ui);

              KeyEvent k = new KeyEvent(s, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.S,
                  false, false, false, false);

              Method onKey = UserInterface.class.getDeclaredMethod("onKey",
                  new Class[] { KeyEvent.class });
              onKey.setAccessible(true);
              onKey.invoke(ui, new Object[] { k });
            } catch (Throwable t) {
              fail();
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
  public void testMovement_3() {
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

              Field f = UserInterface.class.getDeclaredField("window");
              f.setAccessible(true);
              Stage s = (Stage) f.get(ui);

              KeyEvent k = new KeyEvent(s, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.A,
                  false, false, false, false);

              Method onKey = UserInterface.class.getDeclaredMethod("onKey",
                  new Class[] { KeyEvent.class });
              onKey.setAccessible(true);
              onKey.invoke(ui, new Object[] { k });
            } catch (Throwable t) {
              fail();
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
  public void testMovement_4() {
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

              Field f = UserInterface.class.getDeclaredField("window");
              f.setAccessible(true);
              Stage s = (Stage) f.get(ui);

              KeyEvent k = new KeyEvent(s, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.D,
                  false, false, false, false);

              Method onKey = UserInterface.class.getDeclaredMethod("onKey",
                  new Class[] { KeyEvent.class });
              onKey.setAccessible(true);
              onKey.invoke(ui, new Object[] { k });
            } catch (Throwable t) {
              fail();
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
