package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameworld.Board;
import gameworld.GameWorld;
import gameworld.Player;
import renderer.Renderer;
import renderer.Renderer.ItemOnScreen;

public class GameWorldTests {

  @Test
  public void testStartPos_1() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 7);
  }

  @Test
  public void testValidMovement_1() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    //movement commands
    game.openDoor();
    game.moveForward();

    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 4);
  }

  @Test
  public void testValidMovement_2() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    //movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "east");

    //movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "south");

    //movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "west");

    //movement command
    game.rotateRight();
    assertEquals(player.getDirection(), "north");
  }

  @Test
  public void testValidMovement_3() {
    GameWorld game = new GameWorld();
    Player player = game.getPlayer();

    //movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "west");

    //movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "south");

    //movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "east");

    //movement command
    game.rotateLeft();
    assertEquals(player.getDirection(), "north");
  }

  @Test
  public void testValidMovement_4() {
    GameWorld game = new GameWorld();

    //movement command
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

    //ItemOnScreen item = new ItemOnScreen(0,0,0,0,0, "flask");

    Player player = game.getPlayer();
    assertEquals(player.getLocation().getX(), 7);
    assertEquals(player.getLocation().getY(), 4);
  }
}
