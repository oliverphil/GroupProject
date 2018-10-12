package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameworld.FloorObject;
import gameworld.GameWorld;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Point;
import gameworld.barriers.Barrier;
import gameworld.barriers.WoodenPlanksStrategy;
import gameworld.holdables.Flask;
import gameworld.holdables.Tool;

/**
 * Tests for the GameWorld. *************There is a 10 second wait it is not an infinite
 * loop*********************
 * 
 * @author Dylan
 *
 */
public class GameWorldTests {

  @Test
  public void testStartPos() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    assertEquals(7, player.getLocation().getX());
    assertEquals(7, player.getLocation().getY());
  }

  @Test
  public void testValidMovement_1() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement commands
    game.openDoor();
    game.moveForward();

    assertEquals(7, player.getLocation().getX());
    assertEquals(4, player.getLocation().getY());
  }

  @Test
  public void testValidMovement_2() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement command
    game.rotateRight();
    assertEquals("east", player.getDirection());

    // movement command
    game.rotateRight();
    assertEquals("south", player.getDirection());

    // movement command
    game.rotateRight();
    assertEquals("west", player.getDirection());

    // movement command
    game.rotateRight();
    assertEquals("north", player.getDirection());
  }

  @Test
  public void testValidMovement_3() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement command
    game.rotateLeft();
    assertEquals("west", player.getDirection());

    // movement command
    game.rotateLeft();
    assertEquals("south", player.getDirection());

    // movement command
    game.rotateLeft();
    assertEquals("east", player.getDirection());

    // movement command
    game.rotateLeft();
    assertEquals("north", player.getDirection());
  }

  @Test
  public void testValidMovement_4() {
    GameWorld game = new GameWorld();

    // movement command
    game.openDoor();
    game.rotateLeft();
    game.rotateLeft();
    game.moveBackwards();

    Player player = game.getPlayer();
    assertEquals(7, player.getLocation().getX());
    assertEquals(4, player.getLocation().getY());
  }

  @Test
  public void testInteract_1() {
    GameWorld game = new GameWorld();

    // make sure the flask is there
    FloorObject obj = game.getBoard().getBoard()[6][8].getObj();
    assert (obj instanceof Flask);

    // pick up the flask infront to the right
    game.interact("emptyFlask", 3);

    // make sure the flask is no longer there
    obj = game.getBoard().getBoard()[6][8].getObj();
    assert (obj == null);
  }

  @Test
  public void testInteract_2() {
    GameWorld game = new GameWorld();

    assert (game.getBoard().getBoard()[7][6].hasDoor("west"));

    // rotate left then open the door
    game.rotateLeft();
    game.interact("door", 0);

    // make sure that tile no longer has a door
    assert (!game.getBoard().getBoard()[7][6].hasDoor("west"));
  }

  @Test
  public void testInteract_3() {
    GameWorld game = new GameWorld();

    // check player health
    assert (game.getPlayer().getHealth() == 100);

    Monster david = new Monster();
    david.setLocation(new Point(0, 13));
    david.setName("david");
    david.setDamage(25);
    david.setHealth(250);

    game.getBoard().getBoard()[6][7].setObj(david);

    game.interact("david", 2);

    // check player health
    assert (game.getPlayer().getHealth() == 75);
  }

  @Test
  public void testInteract_4() {
    GameWorld game = new GameWorld();

    // check player health
    assert (game.getPlayer().getHealth() == 100);

    Monster marco = new Monster();
    marco.setLocation(new Point(0, 13));
    marco.setName("marco");
    marco.setDamage(20);
    marco.setHealth(250);

    game.getBoard().getBoard()[6][7].setObj(marco);

    game.interact("marco", 2);

    // check player health
    assert (game.getPlayer().getHealth() == 80);
  }

  @Test
  public void testInteract_5() {
    GameWorld game = new GameWorld();

    // check player health
    assert (game.getPlayer().getHealth() == 100);

    Monster thomas = new Monster();
    thomas.setLocation(new Point(0, 13));
    thomas.setName("thomas");
    thomas.setDamage(15);
    thomas.setHealth(250);

    game.getBoard().getBoard()[6][7].setObj(thomas);

    game.interact("thomas", 2);

    // check player health
    assert (game.getPlayer().getHealth() == 85);
  }

  @Test
  public void testInteract_6() {
    GameWorld game = new GameWorld();

    game.getPlayer().setHealth(80);

    // check player health
    assert (game.getPlayer().getHealth() == 80);

    game.interact("emptyFlask", 3);
    game.interact("healthFountain", 0);
    game.useItem(game.getPlayer().getBag().get(0));

    // check player health
    assert (game.getPlayer().getHealth() == 100);
    assert (((Flask) game.getPlayer().getBag().get(0)).isEmpty());

    // attempt to use an empty flask
    game.useItem(game.getPlayer().getBag().get(0));
    assert (game.getPlayer().getBag().get(0).toString().equals("emptyFlask"));
  }

  @Test
  public void testInteract_7() {
    GameWorld game = new GameWorld();

    game.getPlayer().setHealth(82);

    // check player health
    assert (game.getPlayer().getHealth() == 82);

    game.interact("emptyFlask", 3);
    game.interact("healthFountain", 0);
    assert (game.getPlayer().getBag().get(0).toString().equals("healthFlask"));

    // drop and pick up item
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("healthFlask", 1);

    game.useItem(game.getPlayer().getBag().get(0));

    // check player health
    assert (game.getPlayer().getHealth() == 100);
    assert (((Flask) game.getPlayer().getBag().get(0)).isEmpty());
  }

  @Test
  public void testInteract_8() {
    GameWorld game = new GameWorld();

    game.interact("emptyFlask", 3);
    game.interact("powerFountain", 0);
    assert (game.getPlayer().getBag().get(0).toString().equals("powerFlask"));

    // drop and pick up item
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("powerFlask", 1);

    game.useItem(game.getPlayer().getBag().get(0));

    // sleep to register the time change
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      assert (false);
      e.printStackTrace();
    }

    // check player health
    assert (game.getPlayer().isStrengthened());
    assert (((Flask) game.getPlayer().getBag().get(0)).isEmpty());
  }

  // *************There is a 10 second wait it is not an infinite loop*********************
  @Test
  public void testInteract_9() {
    GameWorld game = new GameWorld();

    game.interact("emptyFlask", 3);
    game.interact("powerFountain", 0);
    assert (game.getPlayer().getBag().get(0).toString().equals("powerFlask"));

    game.useItem(game.getPlayer().getBag().get(0));

    // sleep to register the time change
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      assert (false);
      e.printStackTrace();
    }

    // check player health
    assert (!game.getPlayer().isStrengthened());
    assert (((Flask) game.getPlayer().getBag().get(0)).isEmpty());
  }

  @Test
  public void testInteract_10() {
    GameWorld game = new GameWorld();

    Tool crowbar = new Tool();
    crowbar.setMaterial("woodenBlockade");
    crowbar.setName("crowbar");
    crowbar.setWeight(4);
    crowbar.setLocation(new Point(6, 6));
    game.getBoard().getBoard()[6][6].setObj(crowbar);

    Tool pickaxe = new Tool();
    pickaxe.setMaterial("stoneBlockade");
    pickaxe.setName("pickaxe");
    pickaxe.setWeight(4);
    pickaxe.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(pickaxe);

    // pick up a tool while already holding one
    game.interact("crowbar", 1);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("pickaxe"));
    game.interact("pickaxe", 2);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("crowbar"));

    // make sure the crowbar is in the bag
    assertEquals(1, game.getPlayer().getBag().size());
  }

  @Test
  public void testEquals_1() {
    GameWorld game1 = new GameWorld();
    GameWorld game2 = new GameWorld();

    assert (game1.equals(game2));
  }

  @Test
  public void testEquals_2() {
    GameWorld game1 = new GameWorld();
    GameWorld game2 = new GameWorld();

    assert (game1.hashCode() != game2.hashCode());
  }

  @Test
  public void testEquals_3() {
    Flask flask1 = new Flask();
    Flask flask2 = new Flask();

    assert (flask1.hashCode() != flask2.hashCode());
  }

  @Test
  public void testEquals_4() {
    Barrier bar1 = new Barrier();

    // make sure barriers start with no strategy
    assert (bar1.getStrat() == null);

    bar1.setStrat(new WoodenPlanksStrategy());
    Barrier bar2 = new Barrier();
    assert (!bar2.equals(bar1));
    assert (bar1.hashCode() != bar2.hashCode());
    bar2.setStrat(new WoodenPlanksStrategy());

    assert (bar1.hashCode() != bar2.hashCode());
    assert (bar1.equals(bar1));
    
    
    Player pl = new Player();
    assert (!bar1.equals(pl));
    
    Tool tool = new Tool();
    assert (!bar1.equals(tool));
  }
}
