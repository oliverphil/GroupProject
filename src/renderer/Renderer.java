package renderer;

import gameworld.GameWorld;
import gameworld.ViewDescriptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Implementation of a Canvas which draws images on the screen based on a ViewDescriptor object from
 * GameWorld. Uses the Observer pattern to receive ViewDescriptor
 * 
 * @author Philip Oliver - 300398228
 *
 */
public class Renderer extends Canvas implements Observer {

  private static final int ITEM_SIZE = 200;
  private List<ItemOnScreen> objectsOnScreen;
  private Music musicPlayer;

  /**
   * Create a new Renderer object, which extends javafx.Canvas.
   * 
   * @param width  the width of the renderer
   * @param height the height of the renderer
   */
  public Renderer(double width, double height) {
    super(width, height);
    objectsOnScreen = new ArrayList<ItemOnScreen>();
    musicPlayer = new Music();
  }

  /**
   * Redraw the player's view using the ViewDescriptor object describing what the player is looking
   * at.
   *
   * @param view A ViewDescriptor containing information about what the player is looking at
   */
  public void redraw(ViewDescriptor view) {
    if (view == null || view.getView().size() < 6) {
      return;
    }
    objectsOnScreen.clear();
    GraphicsContext gc = getGraphicsContext2D();
    int i = 0;
    List<String> visibleTiles = view.getView();
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      switch (visibleTiles.get(i)) {
        case "door":
          Image closedDoor = new Image(getClass().getResourceAsStream("images/closedDoor.png"));
          gc.drawImage(closedDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
          objectsOnScreen
              .add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "door"));
          break;
        case "clear":
          Image openDoor = new Image(getClass().getResourceAsStream("images/openDoor.png"));
          gc.drawImage(openDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
          objectsOnScreen
              .add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "clear"));
          break;
        default:
          Image wall = new Image(getClass().getResourceAsStream("images/wall.png"));
          gc.drawImage(wall, x, 0, getWidth() / 3, getHeight() * 2 / 3);
          break;
      }
      i++;
    }
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      Image floor = new Image(getClass().getResourceAsStream("images/floor.png"));
      gc.drawImage(floor, x, getHeight() * 2 / 3, getWidth() / 3, getHeight() / 3);
    }

    gc.setFill(Color.BLACK);
    gc.setLineWidth(3);
    gc.strokeLine(0, getHeight() * 2 / 3 + 1, getWidth(), getHeight() * 2 / 3 + 1);

    String musicFile = "";

    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      switch (visibleTiles.get(i)) {
        case "emptyFlask":
          Image emptyFlask = new Image(getClass().getResourceAsStream("images/emptyFlask.png"));
          gc.drawImage(emptyFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
              "emptyFlask"));
          break;
        case "healthFlask":
          Image healthFlask = new Image(getClass().getResourceAsStream("images/healthFlask.png"));
          gc.drawImage(healthFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
              "healthFlask"));
          break;
        case "powerFlask":
          Image powerFlask = new Image(getClass().getResourceAsStream("images/powerFlask.png"));
          gc.drawImage(powerFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
              "powerFlask"));
          break;
        case "crowbar":
          Image crowbar = new Image(getClass().getResourceAsStream("images/crowbar.png"));
          gc.drawImage(crowbar, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "crowbar"));
          break;
        case "pickaxe":
          Image pickaxe = new Image(getClass().getResourceAsStream("images/pickaxe.png"));
          gc.drawImage(pickaxe, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "pickaxe"));
          break;
        case "boltCutters":
          Image boltCutters = new Image(getClass().getResourceAsStream("images/boltCutters.png"));
          gc.drawImage(boltCutters, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
              "boltCutters"));
          break;
        case "khopesh":
          Image khopesh = new Image(getClass().getResourceAsStream("images/khopesh.png"));
          gc.drawImage(khopesh, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "khopesh"));
          break;
        case "torch":
          Image torch = new Image(getClass().getResourceAsStream("images/torch.png"));
          gc.drawImage(torch, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "torch"));
          break;
        case "hammer":
          Image hammer = new Image(getClass().getResourceAsStream("images/hammer.png"));
          gc.drawImage(hammer, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "hammer"));
          break;
        case "david":
          Image david = new Image(getClass().getResourceAsStream("images/pharohDavid.png"));
          gc.drawImage(david, (getWidth() / 2) - ITEM_SIZE,
              getHeight() - david.getWidth() - ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, david.getWidth(),
              getHeight(), 2, "david"));
          musicFile = "boss";
          break;
        case "marco":
          Image marco = new Image(getClass().getResourceAsStream("images/mummyMarco.png"));
          gc.drawImage(marco, (getWidth() / 2) - ITEM_SIZE,
              getHeight() - marco.getWidth() - ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, marco.getWidth(),
              getHeight(), 2, "marco"));
          musicFile = "boss";
          break;
        case "thomas":
          Image thomas = new Image(getClass().getResourceAsStream("images/tombstoneThomas.png"));
          gc.drawImage(thomas, (getWidth() / 2) - ITEM_SIZE,
              getHeight() - thomas.getWidth() - ITEM_SIZE);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, thomas.getWidth(),
              getHeight(), 2, "thomas"));
          musicFile = "boss";
          break;
        case "woodenBlockade":
          Image woodBlock = new Image(getClass().getResourceAsStream("images/woodenBlockade.png"));
          gc.drawImage(woodBlock, (getWidth() / 2) - (woodBlock.getWidth() / 2), 0);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - (woodBlock.getWidth() / 2), 0,
              woodBlock.getWidth(), woodBlock.getHeight(), 2, "woodenBlockade"));
          break;
        case "stoneBlockade":
          Image stoneBlock = new Image(getClass().getResourceAsStream("images/stoneBlockade.png"));
          gc.drawImage(stoneBlock, (getWidth() / 2) - (stoneBlock.getWidth() / 2), 0);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - (stoneBlock.getWidth() / 2), 0,
              stoneBlock.getWidth(), stoneBlock.getHeight(), 2, "stoneBlockade"));
          break;
        case "chainBlockade":
          Image chainBlock = new Image(getClass().getResourceAsStream("images/chainBlockade.png"));
          gc.drawImage(chainBlock, (getWidth() / 2) - (chainBlock.getWidth() / 2), 0);
          objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - (chainBlock.getWidth() / 2), 0,
              chainBlock.getWidth(), chainBlock.getHeight(), 2, "chainBlockade"));
          break;
        case "healthFountain":
          Image healthFountain = new Image(
              getClass().getResourceAsStream("images/healthFountain.png"));
          gc.drawImage(healthFountain, x + ((getWidth() / 3) - healthFountain.getWidth()) / 2,
              getHeight() - 400);
          objectsOnScreen.add(new ItemOnScreen(
              x + ((getWidth() / 3) - healthFountain.getWidth()) / 2, getHeight() - 400,
              healthFountain.getWidth(), healthFountain.getHeight(), i % 3 + 1, "healthFountain"));
          musicFile = "mysteries";
          break;
        case "powerFountain":
          Image powerFountain = new Image(
              getClass().getResourceAsStream("images/powerFountain.png"));
          gc.drawImage(powerFountain, x + ((getWidth() / 3) - powerFountain.getWidth()) / 2,
              getHeight() - 400);
          objectsOnScreen.add(new ItemOnScreen(
              x + ((getWidth() / 3) - powerFountain.getWidth()) / 2, getHeight() - 400,
              powerFountain.getWidth(), powerFountain.getHeight(), i % 3 + 1, "powerFountain"));
          musicFile = "mysteries";
          break;
        case "ladder":
          Image ladder = new Image(getClass().getResourceAsStream("images/ladder.png"));
          gc.drawImage(ladder, x, 0, getWidth() / 3, getHeight() * 2 / 3);
          objectsOnScreen
              .add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "ladder"));
          musicFile = "escape";
          break;
        default:
          break;
      }
      i++;
    }

    if (musicFile.equals("")) {
      musicFile = "tunnels";
    }
    musicPlayer.update(musicFile);

    Collections.reverse(objectsOnScreen);
  }

  /**
   * A method which takes a mouse click and returns a string describing the object that the player
   * has clicked on.
   *
   * @param e the mouse event containing information about the player's click
   * @return a string describing the item clicked on
   */
  public ItemOnScreen onClick(MouseEvent e) {
    for (ItemOnScreen d : objectsOnScreen) {
      if (d.on(e)) {
        return d;
      }
    }
    return null;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    // *********OBSERVER PATTERN********* //
    if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof ViewDescriptor) {
      redraw((ViewDescriptor) arg1);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((objectsOnScreen == null) ? 0 : objectsOnScreen.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Renderer other = (Renderer) obj;
    if (objectsOnScreen == null) {
      if (other.objectsOnScreen != null) {
        return false;
      }
    } else if (!objectsOnScreen.equals(other.objectsOnScreen)) {
      return false;
    }
    return true;
  }

  public class ItemOnScreen {
    private final double leftX;
    private final double topY;
    private final double width;
    private final double height;
    private final int tile;
    private final String obj;

    /**
     * Create a new dimension object.
     * 
     * @param x      the top-left x value
     * @param y      the top-left y value
     * @param width  the width
     * @param height the height
     * @param obj    a String describing the object on the screen
     */
    public ItemOnScreen(double x, double y, double width, double height, int tile, String obj) {
      leftX = x;
      topY = y;
      this.width = width;
      this.height = height;
      this.tile = tile;
      this.obj = obj;
    }

    public String toString() {
      return obj;
    }

    private boolean on(MouseEvent e) {
      return e.getSceneX() >= leftX && e.getSceneX() <= leftX + width && e.getSceneY() >= topY
          && e.getSceneY() <= topY + height;
    }

    /**
     * Get the tile that this item is on. 1-3 from left to right.
     * 
     * @return 1 for left, 2 for middle, 3 for right
     */
    public int getTile() {
      return tile;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      long temp;
      temp = Double.doubleToLongBits(height);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(leftX);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      result = prime * result + ((obj == null) ? 0 : obj.hashCode());
      result = prime * result + tile;
      temp = Double.doubleToLongBits(topY);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(width);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      ItemOnScreen other = (ItemOnScreen) obj;
      if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height)) {
        return false;
      }
      if (Double.doubleToLongBits(leftX) != Double.doubleToLongBits(other.leftX)) {
        return false;
      }
      if (this.obj == null) {
        if (other.obj != null) {
          return false;
        }
      } else if (!this.obj.equals(other.obj)) {
        return false;
      }
      if (tile != other.tile) {
        return false;
      }
      if (Double.doubleToLongBits(topY) != Double.doubleToLongBits(other.topY)) {
        return false;
      }
      if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width)) {
        return false;
      }
      return true;
    }
  }

}
