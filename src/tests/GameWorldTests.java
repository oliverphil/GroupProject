package tests;

import org.junit.jupiter.api.BeforeEach;

import gameworld.GameWorld;
import gameworld.Player;

public class GameWorldTests {

  private GameWorld game;
  private Player player;

  /**
   * A method to run before each test. Creates a new renderer.
   */
  @BeforeEach
  public void getNewGameWorld() {
    game = new GameWorld();
    player = new Player();
  }

}
