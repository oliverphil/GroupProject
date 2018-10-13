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
import gameworld.holdables.ContentsStrategy;
import gameworld.holdables.Flask;
import gameworld.holdables.Item;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

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
//  @Test
//  public void testInteract_9() {
//    GameWorld game = new GameWorld();
//
//    game.interact("emptyFlask", 3);
//    game.interact("powerFountain", 0);
//    assert (game.getPlayer().getBag().get(0).toString().equals("powerFlask"));
//
//    game.useItem(game.getPlayer().getBag().get(0));
//
//    // sleep to register the time change
//    try {
//      Thread.sleep(10000);
//    } catch (InterruptedException e) {
//      assert (false);
//      e.printStackTrace();
//    }
//
//    // check player health
//    assert (!game.getPlayer().isStrengthened());
//    assert (((Flask) game.getPlayer().getBag().get(0)).isEmpty());
//  }

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

    Tool boltCutters = new Tool();
    boltCutters.setMaterial("boltCutters");
    boltCutters.setName("boltCutters");
    boltCutters.setWeight(4);
    boltCutters.setLocation(new Point(8, 6));
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(boltCutters);

    // pick up a tool while already holding one
    game.interact("crowbar", 1);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("pickaxe"));
    game.interact("pickaxe", 2);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("crowbar"));
    game.interact("crowbar", 2);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("pickaxe"));
    game.interact("boltCutters", 3);

    game.dropItem(game.getPlayer().getBag().get(0));

    game.interact("pickaxe", 2);
    assert (game.getBoard().getBoard()[6][8].getObj().getName().equals("crowbar"));

    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("boltCutters", 3);

    // make sure there is still just 1 tool in the bag
    assertEquals(1, game.getPlayer().getBag().size());
  }

  @Test
  public void testInteract_11() {
    GameWorld game = new GameWorld();

    Weapon torch = new Weapon();
    torch.setName("torch");
    torch.setWeight(4);
    torch.setLocation(new Point(6, 6));
    game.getBoard().getBoard()[6][6].setObj(torch);

    Weapon hammer = new Weapon();
    hammer.setName("hammer");
    hammer.setWeight(4);
    hammer.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(hammer);

    Weapon khopesh = new Weapon();
    khopesh.setName("khopesh");
    khopesh.setWeight(3);
    khopesh.setLocation(new Point(8, 6));
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(khopesh);

    // pick up a weapon while already holding one
    game.interact("torch", 1);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("hammer"));
    game.interact("hammer", 2);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("torch"));
    game.interact("torch", 2);
    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("hammer"));
    game.interact("khopesh", 3);

    game.dropItem(game.getPlayer().getBag().get(0));

    game.interact("hammer", 2);
    assert (game.getBoard().getBoard()[6][8].getObj().getName().equals("torch"));

    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("khopesh", 3);

    // test equals
    assert (!torch.equals(hammer));
    assert (hammer.equals(hammer));
  }

  @Test
  public void testInteract_12() {
    GameWorld game = new GameWorld();

    Tool crowbar = new Tool();
    crowbar.setMaterial("woodenBlockade");
    crowbar.setName("crowbar");
    crowbar.setWeight(4);
    crowbar.setLocation(new Point(6, 6));
    game.getBoard().getBoard()[6][6].setObj(crowbar);

    Barrier woodenBar = new Barrier();
    woodenBar.setName("woodenBlockade");
    woodenBar.setStrat(new WoodenPlanksStrategy());
    woodenBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(woodenBar);

    game.interact("crowbar", 1);
    game.interact("woodenBlockade", 0);

    assert (game.getBoard().getBoard()[6][7].getObj() == null);
  }

  @Test
  public void testInteract_13() {
    GameWorld game = new GameWorld();

    Tool boltCutters = new Tool();
    boltCutters.setMaterial("boltCutters");
    boltCutters.setName("boltCutters");
    boltCutters.setWeight(4);
    boltCutters.setLocation(new Point(8, 6));
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(boltCutters);

    Barrier chainBar = new Barrier();
    chainBar.setName("chainBlockade");
    chainBar.setStrat(new WoodenPlanksStrategy());
    chainBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(chainBar);

    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("chainBlockade"));

    game.interact("boltCutters", 3);
    game.interact("chainBlockade", 0);

    assert (game.getBoard().getBoard()[6][7].getObj() == null);
  }

  @Test
  public void testInteract_14() {
    GameWorld game = new GameWorld();
    Tool pickaxe = new Tool();
    pickaxe.setMaterial("stoneBlockade");
    pickaxe.setName("pickaxe");
    pickaxe.setWeight(4);
    pickaxe.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(pickaxe);

    Barrier stoneBar = new Barrier();
    stoneBar.setName("stoneBlockade");
    stoneBar.setStrat(new WoodenPlanksStrategy());
    stoneBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(stoneBar);

    assert (game.getBoard().getBoard()[6][7].getObj().getName().equals("stoneBlockade"));

    game.interact("pickaxe", 3);
    game.interact("stoneBlockade", 0);

    assert (game.getBoard().getBoard()[6][7].getObj() == null);
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

    flask1.setStrat(null);
    assert (!flask1.equals(flask2));

    assert (flask1.hashCode() != flask2.hashCode());
    assert (flask1.equals(flask1));

    flask1.setWeight(5);
    assert (!flask1.equals(flask2));

    Item tool = new Tool();
    assert (!flask1.equals(tool));

    assert (tool.equals(tool));
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
