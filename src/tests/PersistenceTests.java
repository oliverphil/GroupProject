package tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gameworld.GameWorld;

import org.junit.jupiter.api.Test;

import persistence.Persistence;
import persistence.PersistenceException;

public class PersistenceTests {

  @Test
  public void testSaving() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "saveTest.xml");
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("Should save the game without errors.");
    }
  }

  @Test
  public void testLoading() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "loadTest.xml");
      GameWorld loadedGame = Persistence.loadGame("loadTest.xml");
      assertTrue(testWorld.equals(loadedGame), "loaded object should be equal to saved object");
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("Should save and load the game without errors");
    }
  }
  
  
}
