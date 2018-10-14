package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gameworld.Board;
import gameworld.FloorObject;
import gameworld.GameWorld;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Point;
import gameworld.Tile;
import gameworld.barriers.Barrier;
import gameworld.barriers.WoodenPlanksStrategy;
import gameworld.holdables.ContentsStrategy;
import gameworld.holdables.EmptyFlaskStrategy;
import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.Item;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

import org.junit.Test;

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
  public void testValidMovement_5() {
    GameWorld game = new GameWorld();

    Player player = game.getPlayer();
    
    // movement command
    game.openDoor();
    game.rotateLeft();
    game.openDoor();
    game.rotateLeft();
    
    game.moveBackwards();
    assertEquals(7, player.getLocation().getX());
    assertEquals(4, player.getLocation().getY());
    game.moveForward();
    
    game.openDoor();
    game.rotateLeft();
    
    game.moveBackwards();
    assertEquals(4, player.getLocation().getX());
    assertEquals(7, player.getLocation().getY());
    game.moveForward();
    
    game.openDoor();
    game.rotateLeft();
    
    game.moveBackwards();
    assertEquals(7, player.getLocation().getX());
    assertEquals(10, player.getLocation().getY());
    game.moveForward();
    
    game.openDoor();
    game.rotateLeft();
    
    game.moveBackwards();
    assertEquals(10, player.getLocation().getX());
    assertEquals(7, player.getLocation().getY());
    game.moveForward();
  }

  @Test
  public void testInteract_1() {
    GameWorld game = new GameWorld();

    // make sure the flask is there
    FloorObject obj = game.getBoard().getBoard()[6][8].getObj();
    assertTrue(obj instanceof Flask);

    // pick up the flask infront to the right
    game.interact("emptyFlask", 3);

    // make sure the flask is no longer there
    obj = game.getBoard().getBoard()[6][8].getObj();
    assertEquals(null, obj);
  }

  @Test
  public void testInteract_2() {
    GameWorld game = new GameWorld();

    assertTrue(game.getBoard().getBoard()[7][6].hasDoor("west"));

    // rotate left then open the door
    game.rotateLeft();
    game.interact("door", 0);
    
    game.rotateLeft();
    game.interact("door", 0);
    
    game.rotateLeft();
    game.interact("door", 0);

    // make sure that tile no longer has a door
    assertFalse(game.getBoard().getBoard()[7][6].hasDoor("west"));
  }

  @Test
  public void testInteract_3() {
    GameWorld game = new GameWorld();

    // check player health
    assertTrue(game.getPlayer().getHealth() == 100);

    Monster david = new Monster();
    david.setLocation(new Point(0, 13));
    david.setName("david");
    david.setDamage(25);
    david.setHealth(250);
    game.getBoard().getBoard()[6][7].setObj(david);

    game.interact("david", 2);

    // check player health
    assertEquals(75, game.getPlayer().getHealth());
  }

  @Test
  public void testInteract_4() {
    GameWorld game = new GameWorld();

    // check player health
    assertTrue(game.getPlayer().getHealth() == 100);

    Monster marco = new Monster();
    marco.setLocation(new Point(0, 13));
    marco.setName("marco");
    marco.setDamage(20);
    marco.setHealth(250);
    game.getBoard().getBoard()[6][7].setObj(marco);

    game.interact("marco", 2);

    // check player health
    assertEquals(80, game.getPlayer().getHealth());
  }

  @Test
  public void testInteract_5() {
    GameWorld game = new GameWorld();

    // check player health
    assertTrue(game.getPlayer().getHealth() == 100);

    Monster thomas = new Monster();
    thomas.setLocation(new Point(0, 13));
    thomas.setName("thomas");
    thomas.setDamage(15);
    thomas.setHealth(250);
    game.getBoard().getBoard()[6][7].setObj(thomas);

    game.interact("thomas", 2);

    // check player health
    assertEquals(85, game.getPlayer().getHealth());
  }

  @Test
  public void testInteract_6() {
    GameWorld game = new GameWorld();

    game.getPlayer().setHealth(80);

    // check player health
    assertEquals(80, game.getPlayer().getHealth());

    game.interact("emptyFlask", 3);
    game.interact("healthFountain", 0);
    game.useItem(game.getPlayer().getBag().get(0));

    // check player health
    assertEquals(100, game.getPlayer().getHealth());
    assertTrue(((Flask) game.getPlayer().getBag().get(0)).isEmpty());

    // attempt to use an empty flask
    game.useItem(game.getPlayer().getBag().get(0));
    assertTrue(game.getPlayer().getBag().get(0).toString().equals("emptyFlask"));
  }

  @Test
  public void testInteract_7() {
    GameWorld game = new GameWorld();

    game.getPlayer().setHealth(82);

    // check player health
    assertEquals(82, game.getPlayer().getHealth());

    game.interact("emptyFlask", 3);
    game.interact("healthFountain", 0);
    assertTrue(game.getPlayer().getBag().get(0).toString().equals("healthFlask"));

    // drop and pick up item
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("healthFlask", 1);

    game.useItem(game.getPlayer().getBag().get(0));

    // check player health
    assertEquals(100, game.getPlayer().getHealth());
    assertTrue(((Flask) game.getPlayer().getBag().get(0)).isEmpty());
  }

  @Test
  public void testInteract_8() {
    GameWorld game = new GameWorld();

    game.interact("emptyFlask", 3);
    game.interact("powerFountain", 0);
    assertTrue(game.getPlayer().getBag().get(0).toString().equals("powerFlask"));

    // drop and pick up item
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("powerFlask", 1);

    game.useItem(game.getPlayer().getBag().get(0));

    // sleep to register the time change
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      fail();
      e.printStackTrace();
    }

    // check player health
    assertTrue(game.getPlayer().isStrengthened());
    assertTrue(((Flask) game.getPlayer().getBag().get(0)).isEmpty());
  }

  // *************There is a 10 second wait it is not an infinite loop*********************
//  @Test
//  public void testInteract_9() {
//    GameWorld game = new GameWorld();
//
//    game.interact("emptyFlask", 3);
//    game.interact("powerFountain", 0);
//    assertTrue(game.getPlayer().getBag().get(0).toString().equals("powerFlask"));
//
//    game.useItem(game.getPlayer().getBag().get(0));
//
//    // sleep to register the time change
//    try {
//      Thread.sleep(10000);
//    } catch (InterruptedException e) {
//      assertTrue(false);
//      e.printStackTrace();
//    }
//
//    // check player health
//    assertTrue(!game.getPlayer().isStrengthened());
//    assertTrue(((Flask) game.getPlayer().getBag().get(0)).isEmpty());
//  }

  @Test
  public void testInteract_10() {
    Tool crowbar = new Tool();
    crowbar.setMaterial("woodenBlockade");
    crowbar.setName("crowbar");
    crowbar.setWeight(4);
    crowbar.setLocation(new Point(6, 6));
    GameWorld game = new GameWorld();
    game.getBoard().getBoard()[6][6].setObj(crowbar);

    Tool pickaxe = new Tool();
    pickaxe.setMaterial("stoneBlockade");
    pickaxe.setName("pickaxe");
    pickaxe.setWeight(4);
    pickaxe.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(pickaxe);

    Tool boltCutters = new Tool();
    boltCutters.setName("boltCutters");
    boltCutters.setWeight(4);
    boltCutters.setLocation(new Point(8, 6));
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(boltCutters);

    // pick up a tool while already holding one
    game.interact("crowbar", 1);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("pickaxe"));
    game.interact("pickaxe", 2);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("crowbar"));
    game.interact("crowbar", 2);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("pickaxe"));
    game.interact("boltCutters", 3);

    game.dropItem(game.getPlayer().getBag().get(0));

    game.interact("pickaxe", 2);
    game.useItem(pickaxe);
    assertTrue(game.getBoard().getBoard()[6][8].getObj().getName().equals("crowbar"));

    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("boltCutters", 3);

    // make sure there is still just 1 tool in the bag
    assertEquals(1, game.getPlayer().getBag().size());
    assertFalse(pickaxe.equals(crowbar));
    assertTrue(pickaxe.equals(pickaxe));
  }

  @Test
  public void testInteract_11() {
    Weapon torch = new Weapon();
    torch.setName("torch");
    torch.setWeight(4);
    torch.setLocation(new Point(6, 6));
    GameWorld game = new GameWorld();
    game.getBoard().getBoard()[6][6].setObj(torch);

    Weapon hammer = new Weapon();
    hammer.setName("hammer");
    hammer.setWeight(4);
    hammer.setDamage(10);
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
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("hammer"));
    game.interact("hammer", 2);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("torch"));
    game.interact("torch", 2);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("hammer"));
    game.interact("khopesh", 3);

    game.dropItem(game.getPlayer().getBag().get(0));

    game.interact("hammer", 2);
    game.useItem(hammer);
    assertTrue(game.getBoard().getBoard()[6][8].getObj().getName().equals("torch"));

    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("khopesh", 3);

    // test equals
    assertFalse(torch.equals(hammer));
    assertTrue(hammer.equals(hammer));

    assertTrue(hammer.getDamage() == 10);
  }

  @Test
  public void testInteract_12() {
    Tool crowbar = new Tool();
    crowbar.setMaterial("woodenBlockade");
    crowbar.setName("crowbar");
    crowbar.setWeight(4);
    crowbar.setLocation(new Point(6, 6));

    GameWorld game = new GameWorld();
    game.getBoard().getBoard()[6][6].setObj(crowbar);

    Barrier woodenBar = new Barrier();
    woodenBar.setName("woodenBlockade");
    woodenBar.setStrat(new WoodenPlanksStrategy());
    woodenBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(woodenBar);

    game.interact("crowbar", 1);
    game.interact("woodenBlockade", 0);

    assertEquals(null, game.getBoard().getBoard()[6][7].getObj());
  }

  @Test
  public void testInteract_13() {
    Tool boltCutters = new Tool();
    boltCutters.setMaterial("boltCutters");
    boltCutters.setName("boltCutters");
    boltCutters.setWeight(4);
    boltCutters.setLocation(new Point(8, 6));

    GameWorld game = new GameWorld();
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(boltCutters);

    Barrier chainBar = new Barrier();
    chainBar.setName("chainBlockade");
    chainBar.setStrat(new WoodenPlanksStrategy());
    chainBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(chainBar);

    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("chainBlockade"));

    game.interact("boltCutters", 3);
    game.interact("chainBlockade", 0);

    assertEquals(null, game.getBoard().getBoard()[6][7].getObj());
  }

  @Test
  public void testInteract_14() {
    Tool pickaxe = new Tool();
    pickaxe.setMaterial("stoneBlockade");
    pickaxe.setName("pickaxe");
    pickaxe.setWeight(4);
    pickaxe.setLocation(new Point(7, 6));

    GameWorld game = new GameWorld();
    game.getBoard().getBoard()[6][8].setObj(null);
    game.getBoard().getBoard()[6][8].setObj(pickaxe);

    Barrier stoneBar = new Barrier();
    stoneBar.setName("stoneBlockade");
    stoneBar.setStrat(new WoodenPlanksStrategy());
    stoneBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(stoneBar);

    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("stoneBlockade"));

    game.interact("pickaxe", 3);
    game.interact("stoneBlockade", 0);

    assertEquals(null, game.getBoard().getBoard()[6][7].getObj());
  }

  @Test
  public void testInteract_15() {
    GameWorld game = new GameWorld();

    Barrier stoneBar = new Barrier();
    stoneBar.setName("stoneBlockade");
    stoneBar.setStrat(new WoodenPlanksStrategy());
    stoneBar.setLocation(new Point(7, 6));
    game.getBoard().getBoard()[6][7].setObj(stoneBar);

    game.getBoard().getBoard()[6][7].setObj(stoneBar);

    Explosive bomb = new Explosive();
    bomb.setName("bomb");
    bomb.setLocation(new Point(6, 6));
    game.getBoard().getBoard()[6][6].setObj(bomb);

    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("stoneBlockade"));

    game.interact("bomb", 1);
    assertTrue(game.getPlayer().getBag().get(0).toString().equals("bomb"));

    game.useItem(bomb);
    assertTrue(game.getBoard().getBoard()[6][7].getObj() == null);

    // use on null
    game.useItem(bomb);

    Monster david = new Monster();
    david.setLocation(new Point(7, 6));
    david.setName("david");
    david.setDamage(25);
    david.setHealth(40);
    game.getBoard().getBoard()[6][7].setObj(david);

    game.useItem(bomb);
    assertTrue(david.getHealth() == 10);
    game.useItem(bomb);
    assertTrue(game.getBoard().getBoard()[6][7].getObj().getName().equals("ladder"));
    game.interact("ladder", 2);
    assertTrue(game.isWon());
  }

  @Test
  public void testAttack_Marco() {
    GameWorld game = new GameWorld();

    game.interact("powerFlask", 1);
    game.useItem(game.getPlayer().getBag().get(0));
        
    game.interact("marco", 1);
    game.interact("torch", 1);
    game.interact("marco", 1);
    game.interact("hammer", 1);
    game.interact("marco", 1);

    // sleep to register the time change
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      fail();
      e.printStackTrace();
    }
    assertTrue(game.getPlayer().isStrengthened());
    game.dropItem(game.getPlayer().getBag().get(0));
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("marco", 1);
    game.interact("torch", 1);
    game.interact("marco", 1);
    game.interact("hammer", 1);
    game.interact("marco", 1);
    game.interact("torch", 1);
    game.interact("marco", 1);
    assertTrue(game.getBoard().getBoard()[0][1].getObj() == null);
  }
  
  @Test
  public void testAttack_David() {
    GameWorld game = new GameWorld();

    game.interact("powerFlask", 1);
    game.useItem(game.getPlayer().getBag().get(0));
        
    game.interact("david", 1);
    game.interact("khopesh", 1);
    game.interact("david", 1);
    game.interact("hammer", 1);
    game.interact("david", 1);

    // sleep to register the time change
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      fail();
      e.printStackTrace();
    }
    assertTrue(game.getPlayer().isStrengthened());
    game.dropItem(game.getPlayer().getBag().get(0));
    game.dropItem(game.getPlayer().getBag().get(0));
    game.interact("david", 1);
    game.interact("khopesh", 1);
    game.interact("david", 1);
    game.interact("hammer", 1);
    game.interact("david", 1);
    game.interact("khopesh", 1);
    game.interact("david", 1);
    assertFalse(game.isPlayerAlive());
    assertTrue(game.getBoard().getBoard()[13][0].getObj().toString().equals("ladder"));
  }
  
  @Test
  public void testAttack_Thomas() {
    GameWorld game = new GameWorld();

    game.interact("powerFlask", 1);
    game.useItem(game.getPlayer().getBag().get(0));
        
    game.interact("thomas", 1);
    game.interact("hammer", 1);
    game.interact("thomas", 1);
    game.interact("khopesh", 1);
    game.interact("thomas", 1);

    // sleep to register the time change
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      fail();
      e.printStackTrace();
    }
    assertTrue(game.getPlayer().isStrengthened());
    game.dropItem(game.getPlayer().getBag().get(0));
    game.dropItem(game.getPlayer().getBag().get(0));
    
    game.interact("thomas", 1);
    game.interact("hammer", 1);
    game.interact("thomas", 1);
    game.interact("khopesh", 1);
    game.interact("thomas", 1);
    game.interact("hammer", 1);
    game.interact("thomas", 1);
    assertTrue(game.getBoard().getTile(1, 14).getObj() == null);
  }
  
  @Test
  public void testBoardPlacing() {
    GameWorld game = new GameWorld();
    
    assertTrue(game.getBoard().getBoard()[6][6].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[6][6].getObj() != null);
    
    assertTrue(game.getBoard().getBoard()[8][6].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[8][6].getObj() != null);
    
    assertTrue(game.getBoard().getBoard()[8][8].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[8][8].getObj() != null);
  }
  
  @Test
  public void testBoardRemoving() {
    GameWorld game = new GameWorld();
    
    assertTrue(game.getBoard().getBoard()[6][6].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[6][6].getObj() != null);
    game.getBoard().removeObject(game.getPlayer(), 3);
    assertTrue(game.getBoard().getBoard()[6][6].getObj() == null);
    
    assertTrue(game.getBoard().getBoard()[8][6].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[8][6].getObj() != null);
    game.getBoard().removeObject(game.getPlayer(), 3);
    assertTrue(game.getBoard().getBoard()[8][6].getObj() == null);
    
    assertTrue(game.getBoard().getBoard()[8][8].getObj() == null);
    game.rotateLeft();
    game.getBoard().place(game.getPlayer(), new Flask(), 3);   
    assertTrue(game.getBoard().getBoard()[8][8].getObj() != null);
    game.getBoard().removeObject(game.getPlayer(), 3);
    assertTrue(game.getBoard().getBoard()[8][8].getObj() == null);
  }
  
  @Test
  public void testBoardSizes() {
    GameWorld game = new GameWorld();
    
    assertTrue(game.getBoard().getHeight() == 15);
    assertTrue(game.getBoard().getWidth() == 15);
    
    assertTrue(game.getBoard().getBoard()[6][8].getObj() != null);
    game.getBoard().setBoard(new Tile[15][15]);
    assertTrue(game.getBoard().getBoard()[6][8] == null);
  }
  
  @Test
  public void testEquals_1() {
    GameWorld game1 = new GameWorld();
    GameWorld game2 = new GameWorld();

    assertTrue(game1.equals(game2));
    
    game1.setPlayer(new Player());
    game1.setBoard(new Board());
    
    //the player doesn't have a 
    assertFalse(game1.equals(game2));
  }

  @Test
  public void testEquals_2() {
    GameWorld game1 = new GameWorld();
    GameWorld game2 = new GameWorld();
    
    game1.setPlayer(null);
    assertFalse(game1.equals(game2));

    assertTrue(game1.equals(game1));
    assertNotEquals(game1.hashCode(), game2.hashCode());
    
    assertFalse(game2.equals(game1.getBoard()));
    
    game1.setBoard(null);
    assertFalse(game1.equals(game2));
    
    Board b = new Board();
    b.getBoard()[6][8].setObj(null);
    game1.setBoard(b);
    assertFalse(game1.equals(game2));
    
    game1 = null;
    assertFalse(game2.equals(game1));
  }

  @Test
  public void testEquals_3() {
    Flask flask1 = new Flask();
    Flask flask2 = new Flask();

    flask1.setStrat(null);
    assertFalse(flask1.equals(flask2));

    assertNotEquals(flask1.hashCode(), flask2.hashCode());
    assertTrue(flask1.equals(flask1));

    flask1.setWeight(5);
    assertTrue(flask1.getStrat() == null);
    assertFalse(flask1.equals(flask2));

    Item tool = new Tool();
    assertFalse(flask1.equals(tool));

    assertTrue(tool.equals(tool));
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testEquals_4() {
    Barrier bar1 = new Barrier();

    // make sure barriers start with no strategy
    assertTrue(bar1.getStrat() == null);

    bar1.setStrat(new WoodenPlanksStrategy());
    Barrier bar2 = new Barrier();
    assertFalse(bar2.equals(bar1));
    assertTrue(bar1.hashCode() != bar2.hashCode());
    bar2.setStrat(new WoodenPlanksStrategy());

    assertTrue(bar1.hashCode() != bar2.hashCode());
    assertTrue(bar1.equals(bar1));

    Player pl = new Player();
    assertFalse(bar1.equals(pl));

    Tool tool = new Tool();
    assertFalse(bar1.equals(tool));
  }

  @Test
  public void testEquals_5() {
    Tool t = new Tool();
    assertFalse(t.equals(null));
  }

  @Test
  public void testEquals_6() {
    Tool t = new Tool();
    t.setMaterial("wood");
    Tool other = new Tool();
    other.setMaterial("somethingElse");
    assertFalse(t.equals(other));
  }

  @Test
  public void testEquals_7() {
    Tool t = new Tool();
    t.setMaterial(null);
    Tool other = new Tool();
    other.setMaterial("something");
    assertFalse(t.equals(other));
  }

  @Test
  public void testEquals_8() {
    Tool t = new Tool();
    t.setLocation(null);
    Tool other = new Tool();
    other.setLocation(new Point(0, 0));
    assertFalse(t.equals(other));
  }

  @Test
  public void testEquals_9() {
    Tool t = new Tool();
    t.setLocation(null);
    Tool other = new Tool();
    other.setLocation(null);
    assertTrue(t.equals(other));
  }

  @Test
  public void testEquals_10() {
    Tool t = new Tool();
    t.setName(null);
    Tool other = new Tool();
    other.setName("something");
    assertFalse(t.equals(other));
  }

  @Test
  public void testEquals_11() {
    Tool t = new Tool();
    t.setName(null);
    Tool other = new Tool();
    other.setName(null);
    assertTrue(t.equals(other));
  }

  @Test
  public void testEquals_12() {
    Tool t = new Tool();
    t.setName("something");
    Tool other = new Tool();
    other.setName("somethingElse");
    assertFalse(t.equals(other));
  }
  
  @Test
  public void testEquals_13() {
    Weapon w = new Weapon();
    w.setDamage(5);
    Weapon other = new Weapon();
    other.setDamage(2);
    assertFalse(w.equals(other));
  }
  
  @Test
  public void testEquals_14() {
    Flask f = new Flask();
    f.setStrat(null);
    Flask other = new Flask();
    other.setStrat(new EmptyFlaskStrategy());
    assertFalse(f.equals(other));
  }
  
  @Test
  public void testEquals_15() {
    Flask f = new Flask();
    f.setStrat(null);
    Flask other = new Flask();
    other.setStrat(null);
    assertTrue(f.equals(other));
  }
  
  @Test
  public void testEquals_16() {
    
  }

  @Test
  public void testHashcode_1() {
    Tool t = new Tool();
    t.setMaterial(null);
    Tool other = new Tool();
    other.setMaterial(null);
    assertEquals(t.hashCode(), other.hashCode());
  }

  @Test
  public void testHashcode_2() {
    Tool t = new Tool();
    t.setLocation(null);
    Tool other = new Tool();
    other.setLocation(new Point(1, 2));
    assertNotEquals(t.hashCode(), other.hashCode());
  }
  
  @Test
  public void testHashcode_3() {
    Tool t = new Tool();
    t.setName(null);
    Tool other = new Tool();
    other.setName("jeff");
    assertNotEquals(t.hashCode(), other.hashCode());
  }
}
