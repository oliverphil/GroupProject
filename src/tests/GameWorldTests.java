package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameworld.Board;
import gameworld.FloorObject;
import gameworld.GameWorld;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Point;
import gameworld.holdables.Flask;
import renderer.Renderer;
import renderer.Renderer.ItemOnScreen;

public class GameWorldTests {

  @Test
  public void testStartPos() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 7);
  }

  @Test
  public void testValidMovement_1() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement commands
    game.openDoor();
    game.moveForward();

    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 4);
  }

  @Test
  public void testValidMovement_2() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "east");

    // movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "south");

    // movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "west");

    // movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "north");
  }

  @Test
  public void testValidMovement_3() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    // movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "west");

    // movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "south");

    // movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "east");

    // movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "north");
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
    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 4);
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
    
    assert(game.getBoard().getBoard()[7][6].hasDoor("west"));

    //rotate left then open the door
    game.rotateLeft();
    game.interact("door", 0);
    
    //make sure that tile no longer has a door
    assert(!game.getBoard().getBoard()[7][6].hasDoor("west"));
  }
  
  @Test
  public void testInteract_3() {
    GameWorld game = new GameWorld();
    
    //check player health
    assert(game.getPlayer().getHealth() == 100);
    
    Monster david = new Monster();
    david.setLocation(new Point(0, 13));
    david.setName("david");
    david.setDamage(25);
    david.setHealth(250);
    
    game.getBoard().getBoard()[6][7].setObj(david);
    
    game.interact("david", 2);
    
    //check player health
    assert(game.getPlayer().getHealth() == 75);
  }
  
  @Test
  public void testInteract_4() {
    GameWorld game = new GameWorld();
    
    //check player health
    assert(game.getPlayer().getHealth() == 100);
    
    Monster marco = new Monster();
    marco.setLocation(new Point(0, 13));
    marco.setName("marco");
    marco.setDamage(20);
    marco.setHealth(250);
    
    game.getBoard().getBoard()[6][7].setObj(marco);
    
    game.interact("marco", 2);
    
    //check player health
    assert(game.getPlayer().getHealth() == 80);
  }
  
  @Test
  public void testInteract_5() {
    GameWorld game = new GameWorld();
    
    //check player health
    assert(game.getPlayer().getHealth() == 100);
    
    Monster thomas = new Monster();
    thomas.setLocation(new Point(0, 13));
    thomas.setName("thomas");
    thomas.setDamage(15);
    thomas.setHealth(250);
    
    game.getBoard().getBoard()[6][7].setObj(thomas);
    
    game.interact("thomas", 2);
    
    //check player health
    assert(game.getPlayer().getHealth() == 85);
  }
  
  @Test
  public void testInteract_6() {
    GameWorld game = new GameWorld();
    
    game.getPlayer().setHealth(80);
    
    //check player health
    assert(game.getPlayer().getHealth() == 80);
    
    Flask flask = new Flask();
    flask.setName("healthFlask");
    flask.fill("health");
    flask.setWeight(1);
    game.getBoard().getBoard()[6][7].setObj(flask);
    
    game.interact("healthFlask", 2);
    game.useItem(flask);
    
    //check player health
    assert(game.getPlayer().getHealth() == 100);
    assert(flask.isEmpty());
  }
  
  @Test
  public void testInteract_7() {
    GameWorld game = new GameWorld();
    
    game.getPlayer().setHealth(82);
    
    //check player health
    assert(game.getPlayer().getHealth() == 82);
    
    Flask flask = new Flask();
    flask.setName("healthFlask");
    flask.fill("health");
    flask.setWeight(1);
    game.getBoard().getBoard()[6][7].setObj(flask);
    
    game.interact("healthFlask", 2);
    assert(flask.toString().equals("healthFlask"));
    game.useItem(flask);
    game.useItem(flask);
    
    //check player health
    assert(game.getPlayer().getHealth() == 100);
    assert(flask.isEmpty());
  }
  
  @Test
  public void testInteract_8() {
    GameWorld game = new GameWorld();
    
    Flask flask = new Flask();
    flask.setName("powerFlask");
    flask.setWeight(1);
    flask.fill("power");
    game.getBoard().getBoard()[6][7].setObj(flask);
    
    game.interact("powerFlask", 2);
    assert(flask.toString().equals("powerFlask"));
    game.useItem(flask);
    
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      assert(false);
      e.printStackTrace();
    }
    
    //check player health
    assert(game.getPlayer().isStrengthened());
    assert(flask.isEmpty());
  }
}
