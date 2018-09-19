package persistence;

import java.io.File;

import javax.xml.bind.*;

import com.sun.org.apache.regexp.internal.recompile;

import gameWorld.GameWorld;

public class Persistence {

	public static void saveGame(GameWorld world) {
		try {
			
			File gameSave = new File("gameSave.xml");
			JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
			Marshaller gameMarshaller = gameContext.createMarshaller();
			gameMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			gameMarshaller.marshal(world, gameSave);
			
		} catch (JAXBException e) {
			System.out.println("Failed to save game file");
			e.printStackTrace();
		}
	}
	
	public static GameWorld loadGame() {
		try {
			
			File gameSave = new File("gameSave.xml");
			JAXBContext gameContext = JAXBContext.newInstance(GameWorld.class);
			Unmarshaller gameUnmarshaller = gameContext.createUnmarshaller();
			
			GameWorld newWorld = (GameWorld) gameUnmarshaller.unmarshal(gameSave);
			return newWorld;
			
		} catch (JAXBException e) {
			System.out.println("Failed to load game file");
			e.printStackTrace();
		}
		
		return null;
	}

}
