package tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gameworld.GameWorld;
import mapeditor.MapEditor;

import org.junit.jupiter.api.Test;

import persistence.Persistence;
import persistence.PersistenceException;

public class PersistenceTests {

  @Test
  public void testErrorFreeGameSaving() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "saveTest.xml");
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("Should save the game without errors.");
    }
  }

  @Test
  public void testGameLoading() {
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

  @Test
  public void testErrorFreeMapEditorSaving() {
    String[][] grid = new String[21][21];
    grid[2][2] = "empty_NW";
    grid[3][2] = "empty_NW";
    grid[4][2] = "empty_NW";
    grid[5][2] = "empty_NW";
    grid[6][2] = "empty_NW";
    grid[7][2] = "empty_NW";
    grid[8][2] = "empty_NW";
    grid[9][2] = "empty_NW";
    grid[13][2] = "empty_NW";
    
    MapEditor editor = new MapEditor();
    editor.setGrid(grid);
    
    try {
      Persistence.saveMapEditor(editor, "testEditorSave.xml");
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("should save mapEditor without errors.");
    }

  }

  @Test
  public void testMapEditorLoading() {
    String[][] grid = new String[21][21];
    grid[2][2] = "empty_NW";
    grid[3][2] = "empty_NW";
    grid[4][2] = "empty_NW";
    grid[5][2] = "empty_NW";
    grid[6][2] = "empty_NW";
    grid[7][2] = "empty_NW";
    grid[8][2] = "empty_NW";
    grid[9][2] = "empty_NW";
    grid[13][2] = "empty_NW";
    
    MapEditor editor = new MapEditor();
    editor.setGrid(grid);
    
    try {
      Persistence.saveMapEditor(editor, "testEditorLoad.xml");
      MapEditor editor2 = Persistence.loadMapEditor("testEditorLoad.xml");
      assertTrue(editor.equals(editor2));
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("should save mapEditor without errors.");
    }
  }

}
