package persistence;

import gameworld.GameWorld;
import gameworld.Monster;
import gameworld.barriers.BarrierStrategy;
import gameworld.holdables.EmptyFlaskStrategy;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import mapeditor.MapEditor;

/**
 * Provides static methods for saving and loading the game state.
 *
 * @author Wanja
 *
 */
public class Persistence {
  /**
   * Saves the current state of the gameWorld to a file in XML format.
   *
   * @param world    the GameWorld to be saved
   * @param fileName the name of the file to save the world to
   */
  public static void saveGame(GameWorld world, String fileName) throws PersistenceException {

    try {
      File saveFile = new File(fileName);
      JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
      Marshaller gameMarshaller = gameContext.createMarshaller();

      //final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
      //    .newSchema(GameWorld.class.getResource("/META-INF/wsdl/schema.xsd"));
      //gameMarshaller.setSchema(schema);

      gameMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      gameMarshaller.marshal(world, saveFile);
    } catch (JAXBException e) {
      e.printStackTrace();
      throw new PersistenceException("Failed to save the GameWorld file. \n");
    } /*
       * catch (SAXException e) { e.printStackTrace(); }
       */
  }

  /**
   * Loads and returns a 'GameWorld' object from an XML file.
   *
   * @param fileName the name of the file to load from
   * @return the loaded 'GameWorld'
   */
  public static GameWorld loadGame(String fileName) throws PersistenceException {
    try {

      File gameSave = new File(fileName);
      JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
      Unmarshaller gameUnmarshaller = gameContext.createUnmarshaller();
      gameUnmarshaller.setSchema(null); // TODO: generate a schema first and set it here

      GameWorld newWorld = (GameWorld) gameUnmarshaller.unmarshal(gameSave);
      return newWorld;

    } catch (JAXBException e) {
      e.printStackTrace();
      throw new PersistenceException("Failed to load the GameWorld file. \n");
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

      File saveFile = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Marshaller editorMarshaller = editorContext.createMarshaller();
      editorMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      editorMarshaller.marshal(editor, saveFile);

    } catch (JAXBException e) {
      e.printStackTrace();
      throw new PersistenceException("Failed to save the MapEditor file. \n");
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

      File editorSave = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Unmarshaller editorUnmarshaller = editorContext.createUnmarshaller();
      // gameUnmarshaller.setSchema(null); //TODO: add verification

      return (MapEditor) editorUnmarshaller.unmarshal(editorSave);

    } catch (JAXBException e) {
      e.printStackTrace();
      throw new PersistenceException("Failed to load the MapEditor file. \n");
    }

  }
}
