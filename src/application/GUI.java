package application;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gameWorld.GameWorld;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import renderer.Renderer;



public class GUI extends Application {

	public String HELP_MESSAGE = " ";
    private Stage window;
    private BorderPane layout = new BorderPane();
    
    private GameWorld game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	game = new GameWorld();
        window = primaryStage;
        window.setTitle("An Adventure Game!");
        Scene scene = new Scene(layout, 800, 900);

        /*MENU START*/  
        //Game Menu
        Menu gameMenu = new Menu("Game");
        MenuItem gameRestartArea = new MenuItem("Restart Area");
        gameRestartArea.setOnAction(e -> System.out.println("Restart Area"));
        MenuItem gameRestart = new MenuItem("Restart Game");
        gameRestart.setOnAction(e -> System.out.println("Restart Game"));
        gameMenu.getItems().add(new MenuItem("Save..."));
        gameMenu.getItems().add(new MenuItem("Load..."));
        gameMenu.getItems().add(gameRestartArea);
        gameMenu.getItems().add(gameRestart);
        gameMenu.getItems().add(new SeparatorMenuItem());
        
        //Help Section
        gameMenu.getItems().add(new MenuItem("Help"));
        gameMenu.getItems().get(5).setOnAction(e -> { 
        	
            FileReader reader;
            int i;
			try {	
				reader = new FileReader("Help.txt");
				
				while ((i=reader.read()) != -1) {
					  System.out.print((char) i); 
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Help File Not Found");
			} catch (IOException e1) {
				System.out.println("Reading Error");
			} 
          
             
        	Notification helpNotification = new Notification("Instructions", HELP_MESSAGE, "Got it!");
        });
        gameMenu.getItems().add(new MenuItem("Exit"));
 
        //Difficulty Menu
        Menu difficultyMenu = new Menu("Difficulty");
        ToggleGroup difficultyToggle = new ToggleGroup();

        RadioMenuItem easy = new RadioMenuItem("Easy");
        easy.setToggleGroup(difficultyToggle);
        RadioMenuItem medium = new RadioMenuItem("Medium");
        medium.setToggleGroup(difficultyToggle);
        RadioMenuItem hard = new RadioMenuItem("Hard");
        hard.setToggleGroup(difficultyToggle);
        
        //start at medium difficulty
        medium.setSelected(true);

        difficultyMenu.getItems().addAll(easy, medium, hard);
        
        //Settings Menu
        Menu optionsMenu = new Menu("Options");
        
        CheckMenuItem toggleMusic = new CheckMenuItem("Enable Sound");
        toggleMusic.setSelected(true);
        toggleMusic.setOnAction(e -> {
            if(toggleMusic.isSelected())
                System.out.println("Audio is on");
            else
                System.out.println("Audio is off");
        });
        
        CheckMenuItem autoSave = new CheckMenuItem("Enable Autosave");
        autoSave.setOnAction(e -> {
            if(autoSave.isSelected())
                System.out.println("Autosave is enabled");
            else
                System.out.println("Autosave is disabled");
        });
        
        optionsMenu.getItems().addAll(difficultyMenu, autoSave, toggleMusic);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gameMenu, optionsMenu);
        /*MENU END*/
        
        
        /*CANVAS START*/
        VBox centerScreen = new VBox();
        Renderer gameScreen = new Renderer(500, 500);    //TODO: Update renderer width and height to be suitable in the window 
        centerScreen.getChildren().add(gameScreen);
        centerScreen.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        /*CANVAS END*/
        
        
        /*BOTTOM SCREEN START*/
        VBox bottomScreen = new VBox();
        bottomScreen.setMinHeight(200);
        bottomScreen.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        HBox backpack = new HBox();
        backpack.setMinHeight(50);
        backpack.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        
        HBox bottomMostScreen = new HBox();
        bottomMostScreen.setMinHeight(150);
        bottomMostScreen.setMinWidth(scene.getWidth());
        
        VBox bottomScreenLeft = new VBox();
        bottomScreenLeft.setMinHeight(bottomMostScreen.getHeight());
        bottomScreenLeft.setMinWidth((scene.getWidth()/2));
        bottomScreenLeft.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        VBox bottomScreenRight = new VBox();
        bottomScreenRight.setMinHeight(bottomMostScreen.getHeight());
        bottomScreenRight.setMinWidth((scene.getWidth()/2));
        bottomScreenRight.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        bottomMostScreen.getChildren().addAll(bottomScreenLeft, bottomScreenRight);
        
        bottomScreen.getChildren().addAll(backpack, bottomMostScreen);
        /*BOTTOM SCREEN END*/

         
        layout.setTop(menuBar);
        layout.setCenter(centerScreen);
        layout.setBottom(bottomScreen);
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        window.setScene(scene);
        window.show();
    }


}