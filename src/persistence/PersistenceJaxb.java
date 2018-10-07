package persistence;

import gameworld.GameWorld;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import mapeditor.MapEditor;

import org.xml.sax.SAXException;

/**
 * Provides static methods for saving and loading the game state.
 * 
 * @author Wanja
 *
 */
public class PersistenceJaxb {
  /**
   * Saves the current state of the gameWorld to a file in XML format.
   * 
   * @param world    the GameWorld to be saved
   * @param fileName the name of the file to save the world to
   */
  public static void saveGame(GameWorld world, String fileName) {

//    try {
//      File saveFile = new File(fileName);
//      JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
//      Marshaller gameMarshaller = gameContext.createMarshaller();
//      
//      final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
//          .newSchema(GameWorld.class.getResource("/META-INF/wsdl/schema.xsd"));
//      gameMarshaller.setSchema(schema);
//
//      gameMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//      gameMarshaller.marshal(world, saveFile);
//    } catch (JAXBException e) {
//      System.out.println("Failed to save game file");
//      e.printStackTrace();
//    } catch (SAXException e) {
//      e.printStackTrace();
//    }
    XMLEncoder encoder = null;
    try {
      encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));

    } catch (FileNotFoundException e) {
      throw new PersistenceException("Error creating the XML Encoder\n" + e.getMessage());
    }

    encoder.writeObject(world);
    encoder.close();
  }

  /**
   * Loads and returns a 'GameWorld' object from an XML file.
   * 
   * @param fileName the name of the file to load from
   * @return the loaded 'GameWorld'
   */
  public static GameWorld loadGame(String fileName) {
    // try {
    //
    // File gameSave = new File(fileName);
    // JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
    // Unmarshaller gameUnmarshaller = gameContext.createUnmarshaller();
    // gameUnmarshaller.setSchema(null); // TODO: generate a schema first and set it here
    //
    // GameWorld newWorld = (GameWorld) gameUnmarshaller.unmarshal(gameSave);
    // return newWorld;
    //
    // } catch (JAXBException e) {
    // e.printStackTrace();
    // throw new RuntimeException("Failed to load the file.");
    // }

    XMLDecoder decoder = null;
    try {
      decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
    } catch (FileNotFoundException e) {
      throw new PersistenceException("Error creating the XML Decoder\n" + e.getMessage());
    }
    
    GameWorld loadedWorld = (GameWorld) decoder.readObject();
    decoder.close();
    return loadedWorld;

  }

  /**
   * Saves the state of the 'MapEditor' to an XML file.
   * 
   * @param editor   the 'MapEditor' to be saved
   * @param fileName the name of the file to be saved to
   */
  public static void saveMapEditor(MapEditor editor, String fileName) {
    try {

      File saveFile = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Marshaller editorMarshaller = editorContext.createMarshaller();
      editorMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      editorMarshaller.marshal(editor, saveFile);

    } catch (JAXBException e) {
      System.out.println("Failed to save game file");
      e.printStackTrace();
    }
  }

  /**
   * Loads and returns a MapEditor object from an XML file.
   * 
   * @param fileName the name of the file to load from
   * @return the loaded MapEditor file
   */
  public static MapEditor loadMapEditor(String fileName) {
    try {

      File editorSave = new File(fileName);
      JAXBContext editorContext = JAXBContext.newInstance(MapEditor.class);
      Unmarshaller editorUnmarshaller = editorContext.createUnmarshaller();
      // gameUnmarshaller.setSchema(null); //TODO: add verification

      return (MapEditor) editorUnmarshaller.unmarshal(editorSave);

    } catch (JAXBException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to load the file.");
    }

  }

  /**
   * test main to check output.
   * 
   * @param args needed for main
   */
  public static void main(String[] args) {
    GameWorld testWorld = new GameWorld();
    saveGame(testWorld, "testSave.xml");
    GameWorld loadedGame = loadGame("testSave.xml");
    System.out.println(testWorld.equals(loadedGame));
  }

  // TODO: add map save and load methods

}
