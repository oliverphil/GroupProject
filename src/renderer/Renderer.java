package renderer;

import application.UserInterface;
import gameworld.GameWorld;
import gameworld.ViewDescriptor;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
  private boolean muted = false;
  private boolean won = false;
  private boolean dead = false;
  private boolean testing = false;

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

    // for each tile on the wall
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      String tile = visibleTiles.get(i);
      if (tile.equals("door")) {
        Image closedDoor = new Image(
            getClass().getResource("images" + File.separator + "closedDoor.png").toString());
        gc.drawImage(closedDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
        objectsOnScreen.add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "door"));
      } else if (tile.equals("clear")) {
        Image openDoor = new Image(
            getClass().getResource("images" + File.separator + "openDoor.png").toString());
        gc.drawImage(openDoor, x, 0, getWidth() / 3, getHeight() * 2 / 3);
        objectsOnScreen
            .add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "clear"));
      } else {
        Image wall = new Image(
            getClass().getResource("images" + File.separator + "wall.png").toString());
        gc.drawImage(wall, x, 0, getWidth() / 3, getHeight() * 2 / 3);
      }
      i++;
    }

    // draw the floor tiles
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      Image floor = new Image(
          getClass().getResource("images" + File.separator + "floor.png").toString());
      gc.drawImage(floor, x, getHeight() * 2 / 3, getWidth() / 3, getHeight() / 3);
    }

    // draw a separating line between the wall and the floor
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(3);
    gc.strokeLine(0, getHeight() * 2 / 3 + 1, getWidth(), getHeight() * 2 / 3 + 1);

    boolean boss = false;

    // for each item on the floor
    for (double x = 0; x < getWidth(); x += getWidth() / 3) {
      String visibleTile = visibleTiles.get(i);
      if (visibleTile.equals("emptyFlask")) {
        Image emptyFlask = new Image(
            getClass().getResource("images" + File.separator + "emptyFlask.png").toString());
        gc.drawImage(emptyFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
            "emptyFlask"));
      } else if (visibleTile.equals("healthFlask")) {
        Image healthFlask = new Image(
            getClass().getResource("images" + File.separator + "healthFlask.png").toString());
        gc.drawImage(healthFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
            "healthFlask"));
      } else if (visibleTile.equals("powerFlask")) {
        Image powerFlask = new Image(
            getClass().getResource("images" + File.separator + "powerFlask.png").toString());
        gc.drawImage(powerFlask, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
            "powerFlask"));
      } else if (visibleTile.equals("crowbar")) {
        Image crowbar = new Image(
            getClass().getResource("images" + File.separator + "crowbar.png").toString());
        gc.drawImage(crowbar, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "crowbar"));
      } else if (visibleTile.equals("pickaxe")) {
        Image pickaxe = new Image(
            getClass().getResource("images" + File.separator + "pickaxe.png").toString());
        gc.drawImage(pickaxe, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "pickaxe"));
      } else if (visibleTile.equals("boltCutters")) {
        Image boltCutters = new Image(
            getClass().getResource("images" + File.separator + "boltCutters.png").toString());
        gc.drawImage(boltCutters, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1,
            "boltCutters"));
      } else if (visibleTile.equals("khopesh")) {
        Image khopesh = new Image(
            getClass().getResource("images" + File.separator + "khopesh.png").toString());
        gc.drawImage(khopesh, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "khopesh"));
      } else if (visibleTile.equals("torch")) {
        Image torch = new Image(
            getClass().getResource("images" + File.separator + "torch.png").toString());
        gc.drawImage(torch, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "torch"));
      } else if (visibleTile.equals("hammer")) {
        Image hammer = new Image(
            getClass().getResource("images" + File.separator + "hammer.png").toString());
        gc.drawImage(hammer, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "hammer"));
      } else if (visibleTile.equals("bomb")) {
        Image bomb = new Image(
            getClass().getResource("images" + File.separator + "bomb.png").toString());
        gc.drawImage(bomb, x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - ITEM_SIZE) / 2,
            (getHeight() * 2 / 3) - (ITEM_SIZE / 4), ITEM_SIZE, ITEM_SIZE, i % 3 + 1, "bomb"));
      } else if (visibleTile.equals("david")) {
        Image david = new Image(
            getClass().getResource("images" + File.separator + "pharohDavid.png").toString());
        gc.drawImage(david, (getWidth() / 2) - ITEM_SIZE,
            getHeight() - david.getWidth() - ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, david.getWidth(),
            getHeight(), 2, "david"));
        boss = true;
      } else if (visibleTile.equals("marco")) {
        Image marco = new Image(
            getClass().getResource("images" + File.separator + "mummyMarco.png").toString());
        gc.drawImage(marco, (getWidth() / 2) - ITEM_SIZE,
            getHeight() - marco.getWidth() - ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, marco.getWidth(),
            getHeight(), 2, "marco"));
        boss = true;
      } else if (visibleTile.equals("thomas")) {
        Image thomas = new Image(
            getClass().getResource("images" + File.separator + "tombstoneThomas.png").toString());
        gc.drawImage(thomas, (getWidth() / 2) - ITEM_SIZE,
            getHeight() - thomas.getWidth() - ITEM_SIZE);
        objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - ITEM_SIZE, 0, thomas.getWidth(),
            getHeight(), 2, "thomas"));
        boss = true;
      } else if (visibleTile.equals("woodenBlockade")) {
        Image woodBlock = new Image(
            getClass().getResource("images" + File.separator + "woodenBlockade.png").toString());
        gc.drawImage(woodBlock, (getWidth() / 2) - (woodBlock.getWidth() / 2), 0);
        objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - (woodBlock.getWidth() / 2), 0,
            woodBlock.getWidth(), woodBlock.getHeight(), 2, "woodenBlockade"));
      } else if (visibleTile.equals("stoneBlockade")) {
        Image stoneBlock = new Image(
            getClass().getResource("images" + File.separator + "stoneBlockade.png").toString());
        gc.drawImage(stoneBlock, getWidth() / 3, 100, getWidth() / 3, stoneBlock.getHeight());
        objectsOnScreen.add(new ItemOnScreen(getWidth() / 3, 0, stoneBlock.getWidth(),
            getHeight() * 2 / 3, 2, "stoneBlockade"));
      } else if (visibleTile.equals("chainBlockade")) {
        Image chainBlock = new Image(
            getClass().getResource("images" + File.separator + "chainBlockade.png").toString());
        gc.drawImage(chainBlock, (getWidth() / 2) - (chainBlock.getWidth() / 2), 0);
        objectsOnScreen.add(new ItemOnScreen((getWidth() / 2) - (chainBlock.getWidth() / 2), 0,
            chainBlock.getWidth(), chainBlock.getHeight(), 2, "chainBlockade"));
      } else if (visibleTile.equals("healthFountain")) {
        Image healthFountain = new Image(
            getClass().getResource("images" + File.separator + "healthFountain.png").toString());
        gc.drawImage(healthFountain, x + ((getWidth() / 3) - healthFountain.getWidth()) / 2,
            getHeight() - 400);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - healthFountain.getWidth()) / 2,
            getHeight() - 400, healthFountain.getWidth(), healthFountain.getHeight(), i % 3 + 1,
            "healthFountain"));
      } else if (visibleTile.equals("powerFountain")) {
        Image powerFountain = new Image(
            getClass().getResource("images" + File.separator + "powerFountain.png").toString());
        gc.drawImage(powerFountain, x + ((getWidth() / 3) - powerFountain.getWidth()) / 2,
            getHeight() - 400);
        objectsOnScreen.add(new ItemOnScreen(x + ((getWidth() / 3) - powerFountain.getWidth()) / 2,
            getHeight() - 400, powerFountain.getWidth(), powerFountain.getHeight(), i % 3 + 1,
            "powerFountain"));
      } else if (visibleTile.equals("ladder")) {
        Image ladder = new Image(
            getClass().getResource("images" + File.separator + "ladder.png").toString());
        gc.drawImage(ladder, x, 0, getWidth() / 3, getHeight() * 2 / 3);
        objectsOnScreen
            .add(new ItemOnScreen(x, 0, getWidth() / 3, getHeight() * 2 / 3, 2, "ladder"));
      }
      i++;
    }

    // if there is a boss, draw the health bar
    if (boss && view.getMonsterHealth() != -1) {
      double scale = 200.0 / 250.0;
      gc.setFill(Color.GREEN);
      gc.fillRect((getWidth() / 2) - 100, 50, view.getMonsterHealth() * scale, 10);
      gc.setFill(Color.RED);
      gc.fillRect(((getWidth() / 2) - 100) + (view.getMonsterHealth() * scale), 50,
          (250 - view.getMonsterHealth()) * scale, 10);
      gc.setStroke(Color.BLACK);
      gc.strokeRect((getWidth() / 2) - 100, 50, 200, 10);
    }

    // if there is a music file and directions
    if (visibleTiles.size() == 8) {

      // update the music
      String musicFile = visibleTiles.get(7);
      if (musicFile.equals("")) {
        musicFile = "tunnels";
      }
      musicPlayer.update(musicFile);
      if (muted) {
        musicPlayer.mute();
      } else {
        musicPlayer.unmute();
      }

      // update the direction
      String dir = visibleTiles.get(6);
      String dirIcon = "";
      if (dir.equals("north")) {
        dirIcon = "N";
      } else if (dir.equals("south")) {
        dirIcon = "S";
      } else if (dir.equals("west")) {
        dirIcon = "W";
      } else if (dir.equals("east")) {
        dirIcon = "E";
      }

      gc.setFill(Color.BLACK);
      gc.fillText(dirIcon, getWidth() - 30, 30);
    }

    Collections.reverse(objectsOnScreen);
  }

  /**
   * Mute the music player.
   */
  public void mute() {
    muted = true;
    musicPlayer.mute();
  }

  /**
   * Unmute the music player.
   */
  public void unmute() {
    muted = false;
    musicPlayer.unmute();
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
        UserInterface.animateLabel(d.toString());
        return d;
      }
    }
    return new ItemOnScreen(0, 0, 0, 0, 0, "");
  }

  private void toBlack() {
    GraphicsContext gc = getGraphicsContext2D();
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, getWidth(), getHeight());
    gc.setStroke(Color.RED);
    gc.setLineWidth(1);
    gc.strokeText("You Died", (getWidth() / 2) - 20, getHeight() / 2);
  }

  private void toWhite() {
    GraphicsContext gc = getGraphicsContext2D();
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, getWidth(), getHeight());
    gc.setStroke(Color.BLANCHEDALMOND.darker());
    gc.strokeText("You Won", (getWidth() / 2) - 20, getHeight() / 2);
    if (!testing) {
      credits();
    }
  }

  /**
   * Open a new window and display the credits.
   */
  private void credits() {
    File creditFolder = new File("src" + File.separator + "renderer" + File.separator + "credits");
    File[] icons = creditFolder.listFiles();
    if (icons == null) {
      return;
    }
    List<Image> credits = new ArrayList<Image>();
    for (int i = 0; i < icons.length; i++) {
      String fileName = icons[i].toString()
          .replace("src" + File.separator + "renderer" + File.separator + "", "");
      credits.add(new Image(getClass().getResource(fileName).toString()));
    }

    Pane pane = new Pane();
    Timeline timeline = new Timeline();
    Duration timepoint = Duration.ZERO;
    Duration pause = Duration.seconds(2);

    for (Image i : credits) {
      KeyFrame keyFrame = new KeyFrame(timepoint, e -> pane.getChildren().add(new ImageView(i)));
      timeline.getKeyFrames().add(keyFrame);
      timepoint = timepoint.add(pause);
    }

    KeyFrame keyFrame = new KeyFrame(timepoint, e -> {
    });
    timeline.getKeyFrames().add(keyFrame);

    Scene scene = new Scene(pane);
    Stage s = new Stage();
    s.setScene(scene);
    s.setHeight(700);
    s.setWidth(700);
    s.setResizable(false);
    s.show();

    timeline.setOnFinished(e -> s.close());
    timeline.play();
  }

  /**
   * Ensure the game redraws correctly on a restart.
   */
  public void restartGame() {
    won = false;
    dead = false;
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    // *********OBSERVER PATTERN********* //
    if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof ViewDescriptor && !dead
        && !won) {
      redraw((ViewDescriptor) arg1);
    } else if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof String) {
      String s = (String) arg1;
      if (s.equals("won")) {
        won = true;
        toWhite();
      } else if (s.equals("dead")) {
        dead = true;
        toBlack();
      }
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

  /**
   * A class to hold information about tiles on the screen.
   * 
   * @author Philip Oliver - 300398228
   *
   */
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
     * @param tile   an integer representing which tile on the board this item is on
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

    /**
     * A method to check if a mouse event happens on this item.
     * 
     * @param e the mouse event
     * @return true if the mouse event occured on this item
     */
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
