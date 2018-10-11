package gameworld.holdables;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.FloorObject;
import gameworld.GameWorld;
import gameworld.Ladder;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Tile;
import gameworld.barriers.Barrier;

@XmlRootElement
public class Explosive extends Item {

  public Explosive() {
    setWeight(3);
  }

  public String toString() {
    return this.getName();
  }

  @Override
  public void use(Player p, Tile tile) {
    FloorObject obj = tile.getObj();

    if (obj != null) {
      if (obj instanceof Monster) {
        ((Monster) obj).removeHealth(30);
        if (((Monster) obj).getHealth() < 1) {
          tile.removeFloorObject();
          if (obj.getName().equals("david")) {
            tile.setObj(new Ladder());
          }
        }
      } else if (obj instanceof Barrier) {
        tile.removeFloorObject();
      }
    }
  }
}
