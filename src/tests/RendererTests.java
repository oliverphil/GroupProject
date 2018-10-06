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

import javafx.embed.swing.JFXPanel;
import javafx.event.EventTarget;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import renderer.Renderer;
import renderer.Renderer.Dimension;

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

  @BeforeEach
  public void setup() {
    renderer = new Renderer(3, 3);
  }

  @Test
  public void testNullViewDescriptor() {
    renderer.redraw(null);
    assertEquals(renderer, new Renderer(3, 3));
  }

  @Test
  public void testEmptyViewDescriptor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return new ArrayList<String>();
      }
    });
    assertEquals(renderer, new Renderer(3, 3));
  }

  @Test
  public void testSmallViewDescriptor() {
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "" });
      }
    });
    assertEquals(renderer, new Renderer(3, 3));
  }

  @Test
  public void testNoItems() {
    // stop error being thrown
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    assertEquals(renderer, other);
  }

  @Test
  public void testAddDoor() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "door", "", "", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new Dimension[] { other.new Dimension(1, 0, 1, 2, "door") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddOpenDoor() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "clear", "", "", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other,
          Arrays.asList(new Dimension[] { other.new Dimension(1, 0, 1, 2, "clear") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddEmptyFlask01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "emptyFlask", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "emptyFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddEmptyFlask02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "emptyFlask" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "emptyFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHealthFlask01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "healthFlask", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "healthFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHealthFlask02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "healthFlask" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "healthFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPowerFlask01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "powerFlask", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "powerFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPowerFlask02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "powerFlask" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "powerFlask") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddCrowbar01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "crowbar", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "crowbar") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddCrowbar02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "crowbar" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "crowbar") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPickaxe01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "pickaxe", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "pickaxe") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddPickaxe02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "pickaxe" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "pickaxe") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddBoltCutters01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "boltCutters", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "boltCutters") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddBoltCutters02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "boltCutters" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "boltCutters") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddKhopesh01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "khopesh", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "khopesh") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddKhopesh02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "khopesh" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "khopesh") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddTorch01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "torch", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "torch") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddTorch02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "torch" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "torch") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHammer01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "hammer", "", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((1.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "hammer") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddHammer02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "", "hammer" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] { other.new Dimension((5.0 - ITEM_SIZE) / 2,
          2 - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "hammer") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddDavid() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "david", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, "david") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddMarco() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "marco", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, "marco") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddThomas() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "thomas", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 - BOSS_SIZE) / 2, 0, BOSS_SIZE, 3.0, "thomas") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddWoodenBlockade() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "woodenBlockade", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "woodenBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddStoneBlockade() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "stoneBlockade", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (500.0 / 2), 0, 500.0, 500.0, "stoneBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testAddChainBlockade() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testOnClick01() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1.5 - 150.0, 200.0, 0.0, 0.0, MouseButton.PRIMARY, 1, false,
            false, false, false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testOnClick02() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 0, -1, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testOnClick03() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1000, 0, 0.0, 0.0, MouseButton.PRIMARY, 1, false, false,
            false, false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testOnClick04() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 1000, -1, 0, 0.0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testOnClick05() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, 0, 1000, 0, 0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testOnClick06() {
    new JFXPanel();
    renderer.redraw(new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });
    assertNotEquals("chainBlockade",
        renderer.onClick(new MouseEvent((Object) renderer, (EventTarget) null,
            MouseEvent.MOUSE_CLICKED, -1000, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false,
            false, false, false, false, false, false, false, null)));
  }

  @Test
  public void testEquals01() {
    new JFXPanel();
    Renderer other = new Renderer(3, 3);
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
    new JFXPanel();
    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }
    assertFalse(renderer.equals(other));
    assertNotEquals(renderer.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals06() {
    new JFXPanel();
    Renderer other = new Renderer(3, 3);
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
    new JFXPanel();
    Renderer other = new Renderer(3, 3);
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
    new JFXPanel();
    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      List<Dimension> objs = new ArrayList<Dimension>();
      objs.add(renderer.new Dimension(0, 0, 0, 0, null));
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
    new JFXPanel();
    renderer.update(new GameWorld(), new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertEquals(renderer, other);
  }

  @Test
  public void testUpdate02() {
    new JFXPanel();
    renderer.update(new Observable(), new ViewDescriptor() {
      public List<String> getView() {
        return Arrays.asList(new String[] { "", "", "", "", "chainBlockade", "" });
      }
    });

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testUpdate03() {
    new JFXPanel();
    renderer.update(new Observable(), "hello");

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testUpdate04() {
    new JFXPanel();
    renderer.update(new GameWorld(), "hello");

    Renderer other = new Renderer(3, 3);
    Field objects;
    try {
      objects = Renderer.class.getDeclaredField("objectsOnScreen");
      objects.setAccessible(true);
      objects.set(other, Arrays.asList(new Dimension[] {
          other.new Dimension((3.0 / 2) - (300.0 / 2), 0, 300.0, 500.0, "chainBlockade") }));
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      fail("Should be able to access fields");
    }

    assertNotEquals(renderer, other);
  }

  @Test
  public void testDimensionEquals01() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertEquals(d, d);
  }

  @Test
  public void testDimensionEquals02() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, null);
  }

  @Test
  public void testDimensionEquals03() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, "Hello");
  }

  @Test
  public void testDimensionEquals04() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, renderer.new Dimension(1, 0, 0, 0, null));
  }

  @Test
  public void testDimensionEquals05() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, renderer.new Dimension(0, 1, 0, 0, null));
  }

  @Test
  public void testDimensionEquals06() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, renderer.new Dimension(0, 0, 1, 0, null));
  }

  @Test
  public void testDimensionEquals07() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, renderer.new Dimension(0, 0, 0, 1, null));
  }

  @Test
  public void testDimensionEquals08() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, null);
    assertNotEquals(d, renderer.new Dimension(0, 0, 0, 0, ""));
  }

  @Test
  public void testDimensionEquals09() {
    Dimension d = renderer.new Dimension(0, 0, 0, 0, " ");
    assertNotEquals(d, renderer.new Dimension(0, 0, 0, 0, ""));
  }
}
