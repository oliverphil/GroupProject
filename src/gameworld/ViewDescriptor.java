package gameworld;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Provides a description of what the player can see given the room and direction.
 *
 * @author Dylan
 */
@XmlRootElement
public class ViewDescriptor {
  // need to have left, middle, right walls or door
  // need to know what items are on the floor + monsters/weapons
  List<String> view;

  /**
   * Constructs a new ViewDescriptor.
   *
   * @param p the current player
   * @param b the current board
   */
  public ViewDescriptor(Player p, Board b) {
    view = new ArrayList<String>();
    generate(p, b);
  }

  public ViewDescriptor() {
    view = new ArrayList<>();
    generate(new Player(), new Board());
  }

  public List<String> getView() {
    return new ArrayList<String>(view);
  }

  @XmlElementWrapper(name = "view")
  @XmlElement(name = "item")
  public void setView(List<String> view) {
    this.view = view;
  }

  /**
   * Uses the player and board to determine what the player's view will be.
   *
   * @param player
   * @param board
   */
  private void generate(Player p, Board b) {
    String dir = p.getDirection();
    int y = p.getLocation().y;
    int x = p.getLocation().x;

    // add the 2 visible walls
    view.add("wall");

    switch (dir) {
      case "north":
        // add the centre tile
        if (b.getBoard()[y - 1][x].hasWall(dir)) {
          view.add("wall");
        } else if (b.getBoard()[y - 1][x].hasDoor(dir)) {
          view.add("door");
        } else {
          view.add("clear");
        }
        view.add("wall");

        // check the 3 floor tiles in front of the player
        for (int i = 0; i < 3; i++) {
          view.add(b.getBoard()[y - 1][x - 1 + i].hasObject()
              ? b.getBoard()[y - 1][x - 1 + i].getFloorObject().getName()
              : "clear");
        }
        break;
      case "east":
        // add the centre tile
        if (b.getBoard()[y][x + 1].hasWall(dir)) {
          view.add("wall");
        } else if (b.getBoard()[y][x + 1].hasDoor(dir)) {
          view.add("door");
        } else {
          view.add("clear");
        }
        view.add("wall");

        // check the 3 floor tiles in front of the player
        for (int i = 0; i < 3; i++) {
          view.add(b.getBoard()[y - 1 + i][x + 1].hasObject()
              ? b.getBoard()[y - 1 + i][x + 1].getFloorObject().getName()
              : "clear");
        }
        break;
      case "south":
        // add the centre tile
        if (b.getBoard()[y + 1][x].hasWall(dir)) {
          view.add("wall");
        } else if (b.getBoard()[y + 1][x].hasDoor(dir)) {
          view.add("door");
        } else {
          view.add("clear");
        }
        view.add("wall");

        // check the 3 floor tiles in front of the player
        for (int i = 0; i < 3; i++) {
          view.add(b.getBoard()[y + 1][x + 1 - i].hasObject()
              ? b.getBoard()[y + 1][x + 1 - i].getFloorObject().getName()
              : "clear");
        }
        break;
      case "west":
        // add the centre tile
        if (b.getBoard()[y][x - 1].hasWall(dir)) {
          view.add("wall");
        } else if (b.getBoard()[y][x - 1].hasDoor(dir)) {
          view.add("door");
        } else {
          view.add("clear");
        }
        view.add("wall");

        // check the 3 floor tiles in front of the player
        for (int i = 0; i < 3; i++) {
          view.add(b.getBoard()[y + 1 - i][x - 1].hasObject()
              ? b.getBoard()[y + 1 - i][x - 1].getFloorObject().getName()
              : "clear");
        }
        break;
      default:
        break;
    }
  }

  /**
   * Adds the specified string to the view descriptor.
   *
   * @param s
   */
  public void addString(String s) {
    if (view.size() < 6) {
      view.add(s);
    }
  }

}
