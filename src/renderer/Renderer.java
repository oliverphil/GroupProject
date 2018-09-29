package renderer;

import gameWorld.GameWorld;
import gameWorld.ViewDescriptor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Renderer extends Canvas implements Observer {

  public Renderer(double width, double height) {
    super(width, height);
  }

  /**
   * Redraw the player's view using the ViewDescriptor object describing what the
   * player is looking at.
   * 
   * @param view A ViewDescriptor containing information about what the player is
   *             looking at
   */
  public void redraw(ViewDescriptor view) {
    //System.out.println("Drawing");
    if (view == null) {
      return;
    }
    GraphicsContext gc = getGraphicsContext2D();
    int i = 0;
    List<String> visibleTiles = view.getView();
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      switch (visibleTiles.get(i)) {
        case "door":
          Image closedDoor = new Image(getClass().getResourceAsStream("images/closedDoor.png"));
          gc.drawImage(closedDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
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
      switch (visibleTiles.get(i)) {
        case "something":
          break;
        default:
          gc.setFill(Color.BLANCHEDALMOND);
          gc.fillRect(x, getHeight() * 2 / 3, getWidth() / 3, getHeight() / 3);
          break;
      }
      i++;
    }
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    // *********OBSERVER PATTERN*********
    if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof ViewDescriptor) {
      redraw((ViewDescriptor) arg1);
    }
  }
}
