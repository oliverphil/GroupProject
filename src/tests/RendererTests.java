package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gameworld.GameWorld;
import gameworld.ViewDescriptor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.embed.swing.JFXPanel;
import javafx.event.EventTarget;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import renderer.Music;
import renderer.Renderer;
import renderer.Renderer.ItemOnScreen;

/**
 * A suite of tests written to test the functionality of the renderer.
 * 
 * @author Philip Oliver - 300398228
 *
 */
public class RendererTests {
  private static final double ITEM_SIZE = 200;
  private static final double BOSS_SIZE = 400;
  private Renderer renderer;

  /**
   * A method to run before each test. Creates a new renderer.
   */
  @BeforeEach
  public void getNewRenderer() {
    renderer = getMutedRenderer();
  }

  private Renderer getMutedRenderer() {
    Renderer renderer = new Renderer(3, 3);
    try {
      Field music = Renderer.class.getDeclaredField("musicPlayer");
      music.setAccessible(true);
      Music player = (Music) music.get(renderer);
      player.mute();
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to mute music");
    }
    return renderer;
  }

  /**
   * Creates a new JFXPanel so that when Renderer is created there is an application for the
   * MediaPlayer to use.
   * 
   * @throws InterruptedException exception to ensure tests aren't run without a JFXPanel
   */
  @BeforeAll
  public static void initToolkit() throws InterruptedException {
    final CountDownLatch latch = new CountDownLatch(1);
    SwingUtilities.invokeLater(() -> {
      new JFXPanel();
      latch.countDown();
    });

    if (!latch.await(5L, TimeUnit.SECONDS)) {
      throw new ExceptionInInitializerError();
    }
  }

  @Test
  public void testNullViewDescriptor() {
    renderer.redraw(null);
    assertEquals(renderer, getMutedRenderer());
  }

  @Test
  public void testEmptyViewDescriptor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return new ArrayList<String>();
      }
    });
    assertEquals(renderer, getMutedRenderer());
  }

  @Test
  public void testSmallViewDescriptor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "" });
      }
    });
    assertEquals(renderer, getMutedRenderer());
  }

  @Test
  public void testNoItems() {

    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    assertEquals(renderer, other);
  }

  @Test
  public void testAddDoor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "door", "", "", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen(1, 0, 1, 2, 2, "door") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddOpenDoor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "clear", "", "", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen(1, 0, 1, 2, 2, "clear") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddEmptyFlask01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "emptyFlask", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "emptyFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddEmptyFlask02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "emptyFlask" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "emptyFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHealthFlask01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "healthFlask", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "healthFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHealthFlask02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "healthFlask" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "healthFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPowerFlask01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "powerFlask", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "powerFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPowerFlask02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "powerFlask" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "powerFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddCrowbar01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "crowbar", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "crowbar") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddCrowbar02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "crowbar" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "crowbar") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPickaxe01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "pickaxe", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "pickaxe") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPickaxe02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "pickaxe" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "pickaxe") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddBoltCutters01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "boltCutters", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "boltCutters") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddBoltCutters02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "boltCutters" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "boltCutters") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddKhopesh01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "khopesh", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "khopesh") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddKhopesh02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "khopesh" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "khopesh") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddTorch01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "torch", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "torch") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddTorch02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "torch" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "torch") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHammer01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "hammer", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((1.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 1, "hammer") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHammer02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "hammer" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new ItemOnScreen[] { other.new ItemOnScreen((5.0 - ITEM_SIZE) / 2,
              2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, 3, "hammer") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddDavid() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "david", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, 2, "david") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddMarco() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "marco", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, 2, "marco") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddThomas() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "thomas", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, 2, "thomas") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddWoodenBlockade() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "woodenBlockade", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "woodenBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddStoneBlockade() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "stoneBlockade", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (500.0 / 2), 0, 500.0, 500.0, 2, "stoneBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddChainBlockade() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHealthFountain() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "healthFountain", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((1.0 - 266.0) / 2, 3 - 400, 266.0, 300.0, 1, "healthFountain") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPowerFountain() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "powerFountain", "", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((1.0 - 266.0) / 2, 3 - 400, 266.0, 300.0, 1, "powerFountain") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddLadder() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "ladder", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays
          .asList(new ItemOnScreen[] { other.new ItemOnScreen(1, 0, 1, 3 * 2 / 3, 2, "ladder") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testOnClick01() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1.5 - 150.0, 200.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false,
            false, false, false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testOnClick02() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 0, -1, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testOnClick03() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1000, 0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false,
            false, false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testOnClick04() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1000, -1, 0, 0.0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testOnClick05() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 0, 1000, 0, 0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testOnClick06() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, -1000, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)).toString());
  }

  @Test
  public void testEquals01() {
    Renderer other = getMutedRenderer();
    assertTrue(other.equals(renderer));
    assertTrue(renderer.equals(other));
    assertEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals02() {
    assertTrue(renderer.equals(renderer));
    assertEquals(renderer.hashCode(), renderer.hashCode());
  }

  @Test
  public void testEquals03() {
    assertFalse(renderer.equals(null));
  }

  @Test
  public void testEquals04() {
    assertFalse(renderer.equals("hello"));
    assertNotEquals(renderer.hashCode(), "hello".hashCode());
  }

  @Test
  public void testEquals05() {
    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }
    assertFalse(renderer.equals(other));
    assertNotEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals06() {
    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(renderer, null);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertFalse(renderer.equals(other));
    assertNotEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals07() {
    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(renderer, null);
      objects.set(other, null);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertTrue(renderer.equals(other));
    assertEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals08() {
    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      List<ItemOnScreen> objs = new ArrayList<ItemOnScreen>();
      objs.add(renderer.new ItemOnScreen(0, 0, 0, 0, 0, null));
      objects.set(renderer, objs);
      objects.set(other, null);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertFalse(renderer.equals(other));
    assertNotEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testUpdate01() {
    renderer.update(new GameWorld(), new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testUpdate02() {

    renderer.update(new Observable(), new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testUpdate03() {

    renderer.update(new Observable(), "hello");

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testUpdate04() {

    renderer.update(new GameWorld(), "hello");

    Renderer other = getMutedRenderer();
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new ItemOnScreen[] {
          other.new ItemOnScreen((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, 2, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testDimensionEquals01() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertEquals(d, d);
  }

  @Test
  public void testDimensionEquals02() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, null);
  }

  @Test
  public void testDimensionEquals03() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, "Hello");
  }

  @Test
  public void testDimensionEquals04() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, renderer.new ItemOnScreen(1, 0, 0, 0, 2, null));
  }

  @Test
  public void testDimensionEquals05() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, renderer.new ItemOnScreen(0, 1, 0, 0, 2, null));
  }

  @Test
  public void testDimensionEquals06() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, renderer.new ItemOnScreen(0, 0, 1, 0, 2, null));
  }

  @Test
  public void testDimensionEquals07() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, renderer.new ItemOnScreen(0, 0, 0, 1, 2, null));
  }

  @Test
  public void testDimensionEquals08() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, null);
    assertNotEquals(d, renderer.new ItemOnScreen(0, 0, 0, 0, 2, ""));
  }

  @Test
  public void testDimensionEquals09() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 2, " ");
    assertNotEquals(d, renderer.new ItemOnScreen(0, 0, 0, 0, 2, ""));
  }

  @Test
  public void testDimensionEquals10() {
    ItemOnScreen d = renderer.new ItemOnScreen(0, 0, 0, 0, 1, "");
    assertNotEquals(d, renderer.new ItemOnScreen(0, 0, 0, 0, 2, ""));
  }

  @Test
  public void testMusicUpdate01() {
    try {
      Field music = Renderer.class.getDeclaredField("musicPlayer");
      music.setAccessible(true);
      Music player = (Music) music.get(renderer);
      player.update("tunnels");
      player.mute();
      Field track = Music.class.getDeclaredField("currentFile");
      track.setAccessible(true);
      assertEquals("tunnels", (String) track.get(music));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access music");
    }
  }

  @Test
  public void testMusicUpdate02() {
    try {
      Field music = Renderer.class.getDeclaredField("musicPlayer");
      music.setAccessible(true);
      Music player = (Music) music.get(renderer);
      player.update("hello");
      player.mute();
      Field track = Music.class.getDeclaredField("currentFile");
      track.setAccessible(true);
      assertEquals("tunnels", (String) track.get(music));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access music");
    }
  }

  @Test
  public void testMusicUpdate03() {
    try {
      Field music = Renderer.class.getDeclaredField("musicPlayer");
      music.setAccessible(true);
      Music player = (Music) music.get(renderer);
      player.update("escape");
      player.mute();
      Field track = Music.class.getDeclaredField("currentFile");
      track.setAccessible(true);
      assertEquals("escape", (String) track.get(music));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access music");
    }
  }

  @Test
  public void testGetTile() {
    ItemOnScreen i = renderer.new ItemOnScreen(0, 0, 0, 0, 0, "hello");
    assertEquals(0, i.getTile());
  }
}
