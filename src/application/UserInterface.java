package application;

import gameworld.GameWorld;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import renderer.Renderer;

public class UserInterface extends Application {

  public static final String HELP_MESSAGE = " ";
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

    /* MENU START */
    // Game Menu
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

    // Help Section
    gameMenu.getItems().add(new MenuItem("Help"));
    gameMenu.getItems().get(5).setOnAction(e -> {

      try {
        Scanner sc = new Scanner(new File("src/application/help.txt"));

        while (sc.hasNext()) {
          System.out.println(sc.nextLine());
        }
        sc.close();
      } catch (FileNotFoundException e1) {
        System.out.println("Help File Not Found");
      }

      new Notification("Instructions", HELP_MESSAGE, "Got it!");
    });
    gameMenu.getItems().add(new MenuItem("Exit"));

    // Difficulty Menu
    ToggleGroup difficultyToggle = new ToggleGroup();

    RadioMenuItem easy = new RadioMenuItem("Easy");
    easy.setToggleGroup(difficultyToggle);
    RadioMenuItem medium = new RadioMenuItem("Medium");
    medium.setToggleGroup(difficultyToggle);
    RadioMenuItem hard = new RadioMenuItem("Hard");
    hard.setToggleGroup(difficultyToggle);

    // start at medium difficulty
    medium.setSelected(true);

    Menu difficultyMenu = new Menu("Difficulty");
    difficultyMenu.getItems().addAll(easy, medium, hard);

    // Settings Menu
    CheckMenuItem toggleMusic = new CheckMenuItem("Enable Sound");
    toggleMusic.setSelected(true);
    toggleMusic.setOnAction(e -> {
      if (toggleMusic.isSelected()) {
        System.out.println("Audio is on");
      } else {
        System.out.println("Audio is off");
      }
    });

    CheckMenuItem autoSave = new CheckMenuItem("Enable Autosave");
    autoSave.setOnAction(e -> {
      if (autoSave.isSelected()) {
        System.out.println("Autosave is enabled");
      } else {
        System.out.println("Autosave is disabled");
      }
    });

    Menu optionsMenu = new Menu("Options");
    optionsMenu.getItems().addAll(difficultyMenu, autoSave, toggleMusic);

    // Main menu bar
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().addAll(gameMenu, optionsMenu);
    /* MENU END */

    /* CANVAS START */
    VBox centerScreen = new VBox();
    Renderer gameScreen = new Renderer(800, 700);
    gameScreen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      game.interact(gameScreen.onClick(e));
    });
    centerScreen.getChildren().add(gameScreen);
    centerScreen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    /* CANVAS END */

    /* BOTTOM SCREEN START */
    Button lookLeft = new Button();
    lookLeft.setText("<-");
    lookLeft.setOnAction(e -> game.rotateLeft());

    Button lookRight = new Button();
    lookRight.setText("->");
    lookRight.setOnAction(e -> game.rotateRight());

    Button moveForward = new Button();
    moveForward.setText("FORWARD");
    moveForward.setOnAction(e -> game.moveForward());

    Button moveBack = new Button();
    moveBack.setText("BACK");
    moveBack.setOnAction(e -> game.moveBackwards());

    Button dropItem = new Button();
    dropItem.setText("Drop");
    dropItem.setOnAction(e -> System.out.println("Dropped Item"));

    Button useItem = new Button();
    useItem.setText("Use");
    useItem.setOnAction(e -> System.out.println("Used Item"));

    moveBack.setOnAction(e -> game.moveBackwards());

    VBox bottomScreen = new VBox();
    bottomScreen.setMinHeight(200);
    bottomScreen.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    HBox backpack = new HBox();
    backpack.setMinHeight(50);
    backpack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        new CornerRadii(10), BorderWidths.DEFAULT)));

    Scene scene = new Scene(layout, 800, 900);

    HBox bottomMostScreen = new HBox();
    bottomMostScreen.setMinHeight(150);
    bottomMostScreen.setMinWidth(scene.getWidth());

    VBox bottomScreenLeft = new VBox();
    bottomScreenLeft.setMinHeight(bottomMostScreen.getHeight());
    bottomScreenLeft.setMinWidth((scene.getWidth() / 2));
    bottomScreenLeft.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    HBox innerButtonPannel = new HBox();
    HBox innerButtonPannel2 = new HBox();
    // ADD ALL BUTTONS TO HBOX
    innerButtonPannel.getChildren().addAll(lookLeft, moveForward, moveBack, lookRight);

    innerButtonPannel2.getChildren().addAll(dropItem, useItem);
    bottomScreenLeft.getChildren().addAll(innerButtonPannel, innerButtonPannel2);

    VBox bottomScreenRight = new VBox();
    bottomScreenRight.setMinHeight(bottomMostScreen.getHeight());
    bottomScreenRight.setMinWidth((scene.getWidth() / 2));
    bottomScreenRight.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    bottomMostScreen.getChildren().addAll(bottomScreenLeft, bottomScreenRight);

    bottomScreen.getChildren().addAll(backpack, bottomMostScreen);
    /* BOTTOM SCREEN END */

    game.addObserver(gameScreen);
    game.update();

    layout.setTop(menuBar);
    layout.setCenter(centerScreen);
    layout.setBottom(bottomScreen);
    layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    window.setScene(scene);
    window.show();
  }
}