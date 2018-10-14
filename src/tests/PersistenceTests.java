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
  public void testValidGameSaving() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "saveTest.xml");
    } catch (PersistenceException e) {
      e.printStackTrace();
      fail("Should save the game without errors.");
    }
  }

  @Test
  public void testInvalidSaving() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "");
      fail("Should encounter an error while saving.");
    } catch (PersistenceException e) {
      return;
    }
  }

  @Test
  public void testValidGameLoading() {
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
  public void testInvalidGameLoading() {
    try {
      GameWorld testWorld = new GameWorld();
      Persistence.saveGame(testWorld, "loadTest.xml");
      Persistence.loadGame("thisFileDoesntExist.xml");
      fail("Shouldn't have been able to load that file.");
    } catch (PersistenceException e) {
      return;
    }
  }

  @Test
  public void testValidMapEditorSaving() {
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
  public void testInvalidMapEditorSaving() {
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
      Persistence.saveMapEditor(editor, "");
      fail("Should not be able to save to invalid file name.");
    } catch (PersistenceException e) {
      return;
    }

  }

  @Test
  public void testValidMapEditorLoading() {
    String[][] grid = new String[21][21];
    for (int i = 0; i < 21; i++) {
      for (int j = 0; j < 21; j++) {
        grid[i][j] = "empty_none";
      }
    }
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

  @Test
  public void testInvalidMapEditorLoading() {
    String[][] grid = new String[21][21];
    for (int i = 0; i < 21; i++) {
      for (int j = 0; j < 21; j++) {
        grid[i][j] = "empty_none";
      }
    }
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
      MapEditor editor2 = Persistence.loadMapEditor("thisFileDoesntExist.xml");
      assertTrue(editor.equals(editor2));
    } catch (PersistenceException e) {
      return;
    }
  }

}
