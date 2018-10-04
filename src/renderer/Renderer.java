package renderer;

import gameWorld.GameWorld;
import gameWorld.ViewDescriptor;

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

public class Renderer extends Canvas implements Observer {

  private static final int ITEM_SIZE = 200;
  private List<Dimension> objectsOnScreen;

  public Renderer(double width, double height) {
    super(width, height);
    objectsOnScreen = new ArrayList<Dimension>();
  }

  /**
   * Redraw the player's view using the ViewDescriptor object describing what the player is looking
   * at.
   *
   * @param view A ViewDescriptor containing information about what the player is looking at
   */
  public void redraw(ViewDescriptor view) {
    // System.out.println("Drawing");
    if (view == null) {
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
          objectsOnScreen.add(new Dimension(x, 0, getWidth() / 3, getHeight() * 2 / 3, "door"));
          break;
        case "clear":
          Image openDoor = new Image(getClass().getResourceAsStream("images/openDoor.png"));
          gc.drawImage(openDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
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

    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      switch (visibleTiles.get(i)) {
        case "emptyFlask":
          Image emptyFlask = new Image(getClass().getResourceAsStream("images/emptyFlask.png"));
          gc.drawImage(emptyFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new Dimension(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "emptyFlask"));
          break;
        case "healthFlask":
          Image healthFlask = new Image(getClass().getResourceAsStream("images/healthFlask.png"));
          gc.drawImage(healthFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new Dimension(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "healthFlask"));
          break;
        case "powerFlask":
          Image powerFlask = new Image(getClass().getResourceAsStream("images/powerFlask.png"));
          gc.drawImage(powerFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
          objectsOnScreen.add(new Dimension(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
              (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, "powerFlask"));
          break;
        case "david":
          Image david = new Image(getClass().getResourceAsStream("images/pharohDavid.png"));
          gc.drawImage(david, (getWidth() / 2) - ITEM_SIZE,
              getHeight() - david.getWidth() - ITEM_SIZE);
          objectsOnScreen.add(new Dimension((getWidth() / 2) - ITEM_SIZE,
              getHeight() - david.getWidth() - ITEM_SIZE, david.getWidth(), david.getHeight(),
              "david"));
          break;
        case "woodenBlockade":
          Image woodBlock = new Image(getClass().getResourceAsStream("images/woodenBlockade.png"));
          gc.drawImage(woodBlock, (getWidth() / 2) - (woodBlock.getWidth() / 2), 0);
          objectsOnScreen.add(new Dimension((getWidth() / 2) - (woodBlock.getWidth() / 2), 0,
              woodBlock.getWidth(), woodBlock.getHeight(), "woodenBlockade"));
          break;
        case "stoneBlockade":
          Image stoneBlock = new Image(getClass().getResourceAsStream("images/stoneBlockade.png"));
          gc.drawImage(stoneBlock, (getWidth() / 2) - (stoneBlock.getWidth() / 2), 0);
          objectsOnScreen.add(new Dimension((getWidth() / 2) - (stoneBlock.getWidth() / 2), 0,
              stoneBlock.getWidth(), stoneBlock.getHeight(), "stoneBlockade"));
          break;
        default:
          break;
      }
      i++;
    }
  }

  /**
   * A method which takes a mouse click and returns a string describing the object that the player
   * has clicked on.
   *
   * @param e the mouse event containing information about the player's click
   * @return a string describing the item clicked on
   */
  public String onClick(MouseEvent e) {
    Collections.reverse(objectsOnScreen);
    for (Dimension d : objectsOnScreen) {
      if (e.getSceneX() >= d.leftX && e.getSceneX() <= d.leftX + d.width && e.getSceneY() >= d.topY
          && e.getSceneY() <= d.topY + d.height) {
        return d.toString();
      }
    }
    return "";
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    // *********OBSERVER PATTERN********* //
    if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof ViewDescriptor) {
      redraw((ViewDescriptor) arg1);
    }
  }

  private class Dimension {
    private final double leftX;
    private final double topY;
    private final double width;
    private final double height;
    private final String obj;

    private Dimension(double x, double y, double width, double height, String obj) {
      leftX = x;
      topY = y;
      this.width = width;
      this.height = height;
      this.obj = obj;
    }

    public String toString() {
      return obj;
    }
  }
}
