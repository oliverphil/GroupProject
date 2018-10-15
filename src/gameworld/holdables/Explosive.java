package gameworld.holdables;

import gameworld.FloorObject;
import gameworld.Ladder;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Tile;
import gameworld.barriers.Barrier;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Can be used to either clear barriers or deal damage to bosses.
 * @author Dylan Ewens - ewensdyla 300423748
 * 
 */
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
        
        //if you destroy the monster with a bomb
        if (((Monster) obj).getHealth() < 1) {
          tile.removeFloorObject();
          
          //if the monster was david spawn the ladder
          if (obj.getName().equals("david")) {
            tile.setObj(new Ladder());
          }
        }
      } else if (obj instanceof Barrier) {
        tile.removeFloorObject();
      }
    }
    p.dropItem(this);
  }
}
