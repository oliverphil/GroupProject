package persistence;

import java.io.File;

import javax.xml.bind.*;

import gameWorld.GameWorld;

/**
 * Provides static methods for saving and loading the game state.
 * @author Wanja
 *
 */
public class Persistence {

	/**
	 * Saves the current state of the gameWorld to a file in XML format.
	 * @param world the GameWorld to be saved
	 * @param file the file to save the world to
	 */
	public static void saveGame(GameWorld world, String fileName) {
		try {
			
			File saveFile = new File(fileName);
			JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
			Marshaller gameMarshaller = gameContext.createMarshaller();
			gameMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			gameMarshaller.marshal(world, saveFile);
			
		} catch (JAXBException e) {
			System.out.println("Failed to save game file");
			e.printStackTrace();
		}
	}
	
	public static GameWorld loadGame(String fileName) {
		try {
			
			File gameSave = new File(fileName);
			JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
			Unmarshaller gameUnmarshaller = gameContext.createUnmarshaller();
			gameUnmarshaller.setSchema(null);	//TODO: generate a schema first and set it here
			
			GameWorld newWorld = (GameWorld) gameUnmarshaller.unmarshal(gameSave);
			return newWorld;
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("Failed to load the file.");
	}
	
	/**
	 * test main to check output.
	 * @param args needed for main
	 */
	public static void main(String[] args) {
		GameWorld testWorld = new GameWorld();
		saveGame(testWorld, "testSave.xml");
		GameWorld loadedGame = loadGame("testSave.xml");
		System.out.println(testWorld.equals(loadedGame));
	}
	
	//TODO: add map save and load methods

}









