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
  private List<String> view;
  private int monsterHealth;

  /**
   * Constructs a new ViewDescriptor.
   *
   * @param p the current player
   * @param b the current board
   */
  public ViewDescriptor(Player p, Board b, boolean isWon) {
    view = new ArrayList<String>();
    generate(p, b, isWon);
  }

  public ViewDescriptor() {
    view = new ArrayList<>();
    generate(new Player(), new Board(), false);
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
   * @param player the current player
   * @param board the current board
   */
  private void generate(Player p, Board b, boolean isWon) {
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
              ? b.getBoard()[y - 1][x - 1 + i].getObj().getName()
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
              ? b.getBoard()[y - 1 + i][x + 1].getObj().getName()
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
              ? b.getBoard()[y + 1][x + 1 - i].getObj().getName()
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
              ? b.getBoard()[y + 1 - i][x - 1].getObj().getName()
              : "clear");
        }
        break;
      default:
        break;
    }
    //this will pass the direction for the compass in the corner
    view.add(dir);

    Point pos = p.getLocation();

    //this determines the song that will play in the background
    if (((pos.x == 1 && pos.y == 1) || (pos.x == 13 && pos.y == 1)
        || (pos.x == 1 && pos.y == 13)) && !isWon) {
      view.add("boss");
    } else if (((pos.x == 13 && pos.y == 13)
        || (pos.x == 4 && pos.y == 10)) && !isWon) {
      view.add("mysteries");
    } else if (isWon) {
      view.add("escape");
    } else {
      view.add("tunnels");
    }

    if (b.getfacingTile(p).getObj() instanceof Monster) {
      monsterHealth =  ((Monster) b.getfacingTile(p).getObj()).getHealth();
    } else {
      monsterHealth = -1;
    }

  }

  /**
   * Adds the specified string to the view descriptor.
   *
   * @param s the string to pass
   */
  public void addString(String s) {
    if (view.size() < 6) {
      view.add(s);
    }
  }

  public int getMonsterHealth() {
    return monsterHealth;
  }

  public void setMonsterHealth(int monsterHealth) {
    this.monsterHealth = monsterHealth;
  }

}
