package persistence;

import gameworld.GameWorld;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import mapeditor.MapEditor;

/**
 * Provides static methods for saving and loading the game and map editor state using JAXB (Java
 * Architecture for XML Binding).
 * 
 * @author Wanja Leuthold - 300424190
 *
 */
public class Persistence {

  /**
   * Saves the current state of the gameWorld to a file in XML format.
   *
   * @param world    the GameWorld to be saved
   * @param fileName the name of the file to save world to
   */
  public static void saveGame(GameWorld world, String fileName) throws PersistenceException {

    try {

      // setup: create a save file and a JAXB Marshaller for the class
      File saveFile = new File(fileName);
      JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
      Marshaller gameMarshaller = gameContext.createMarshaller();
      gameMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // convert the java object to XML
      gameMarshaller.marshal(world, saveFile);

    } catch (JAXBException e) {
      throw new PersistenceException("Failed to save the GameWorld file. \n", e);
    }
  }

  /**
   * Loads and returns a 'GameWorld' object from an XML file.
   *
   * @param fileName the name of the file to load from
   * @return the loaded 'GameWorld'
   */
  public static GameWorld loadGame(String fileName) throws PersistenceException {
    try {

      // setup: create a save file and a JAXB Unmarshaller for the class
      File gameSave = new File(fileName);
      JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
      Unmarshaller gameUnmarshaller = gameContext.createUnmarshaller();

      // load a java object from the XML file
      return (GameWorld) gameUnmarshaller.unmarshal(gameSave);

    } catch (JAXBException e) {
      throw new PersistenceException("Failed to load the GameWorld file. \n", e);
    }
  }

  /**
   * Saves the state of the 'MapEditor' to an XML file.
   *
   * @param editor   the 'MapEditor' to be saved
   * @param fileName the name of the file to be saved to
   */
  public static void saveMapEditor(MapEditor editor, String fileName) throws PersistenceException {
    try {

      // setup: create a save file and a JAXB Marshaller for the class
      File saveFile = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Marshaller editorMarshaller = editorContext.createMarshaller();
      editorMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // convert the java object to XML
      editorMarshaller.marshal(editor, saveFile);

    } catch (JAXBException e) {
      throw new PersistenceException("Failed to save the MapEditor file. \n", e);
    }
  }

  /**
   * Loads and returns a MapEditor object from an XML file.
   *
   * @param fileName the name of the file to load from
   * @return the loaded MapEditor file
   */
  public static MapEditor loadMapEditor(String fileName) throws PersistenceException {
    try {

      // setup: open a save file and create a JAXB Unmarshaller for the class
      File editorSave = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Unmarshaller editorUnmarshaller = editorContext.createUnmarshaller();

      // load a java object from the XML file
      return (MapEditor) editorUnmarshaller.unmarshal(editorSave);

    } catch (JAXBException e) {
      throw new PersistenceException("Failed to load the MapEditor file. \n", e);
    }

  }
}
