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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapeditor.MapEditor;
import renderer.Renderer;

public class UserInterface extends Application {

  // TODO: 
  // Fix the size of the GUI
  // Remove Restart Area and all but Enable Sound on Options
  // Fix Help Printing 
  // Backpack and move items
  // Health bar
  // Write Tests [Renderer Tests, add 'Before All' to tests]
  
  public static final String HELP_MESSAGE = " ";
  private Stage window;
  private BorderPane layout = new BorderPane();

  // load arrow images and resize them to 60x60px
  private Image forwardArrowImage = new Image(getClass().getResourceAsStream("icons/forward.png"),
      60, 60, false, false);
  private Image backArrowImage = new Image(getClass().getResourceAsStream("icons/back.png"), 60, 60,
      false, false);
  private Image leftArrowImage = new Image(getClass().getResourceAsStream("icons/left.png"), 60, 60,
      false, false);
  private Image rightArrowImage = new Image(getClass().getResourceAsStream("icons/right.png"), 60,
      60, false, false);

  private GameWorld game;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    game = new GameWorld();
    window = primaryStage;
    window.setTitle("An Adventure Game!");
    // window.setFullScreen(true);

    /* MENU START */
    // Game Menu
    Menu gameMenu = new Menu("");
    Label t = new Label("Game");
    t.setStyle("-fx-text-fill: #D39365; ");
    gameMenu.setGraphic(t);

    MenuItem gameRestartArea = new MenuItem("Restart Area");
    gameRestartArea.setOnAction(e -> System.out.println("Restart Area"));
    MenuItem gameRestart = new MenuItem("Restart Game");
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
    MenuItem exit = new MenuItem("Exit");
    exit.setOnAction(e -> System.exit(0));
    gameMenu.getItems().add(exit);

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

    // Options Menu
    Menu optionsMenu = new Menu("");
    Label s = new Label("Options");
    s.setStyle("-fx-text-fill: #D39365; ");
    optionsMenu.setGraphic(s);
    optionsMenu.getItems().addAll(difficultyMenu, autoSave, toggleMusic);

    // Map Editor Menu
    CheckMenuItem launchMapEditor = new CheckMenuItem("Launch Map Editor");
    launchMapEditor.setOnAction(e -> {
      try {
        new MapEditor().start(new Stage());
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    Menu mapEditorMenu = new Menu("");
    Label q = new Label("Map Editor");
    q.setStyle("-fx-text-fill: #D39365; ");
    mapEditorMenu.setGraphic(q);
    mapEditorMenu.getItems().addAll(launchMapEditor);

    // Main menu bar
    MenuBar menuBar = new MenuBar();
    menuBar.setStyle("-fx-background-color: #1d1f23; ");
    menuBar.getMenus().addAll(gameMenu, optionsMenu, mapEditorMenu);
    /* MENU END */

    /* CANVAS START */
    VBox centerScreen = new VBox();
    centerScreen.scaleShapeProperty();
    Renderer gameScreen = new Renderer(700, 700); // TODO: Scale Shape Property for Renderer
    gameScreen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      game.interact(gameScreen.onClick(e));
    });
    centerScreen.getChildren().add(gameScreen);
    centerScreen.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    gameRestart.setOnAction(e -> {
      game = new GameWorld();
      game.addObserver(gameScreen);
      game.update();
    });
    /* CANVAS END */

    /* BOTTOM SCREEN START */
    // Button Format
    Button lookLeft = new Button();
    lookLeft.scaleShapeProperty();
    lookLeft.setGraphic(new ImageView(leftArrowImage));
    lookLeft.setStyle("-fx-background-color: #1d1f23; ");
    lookLeft.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    lookLeft.setOnAction(e -> game.rotateLeft());

    Button lookRight = new Button();
    lookRight.scaleShapeProperty();
    lookRight.setGraphic(new ImageView(rightArrowImage));
    lookRight.setStyle("-fx-background-color: #1d1f23; ");
    lookRight.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    lookRight.setOnAction(e -> game.rotateRight());

    Button moveForward = new Button();
    moveForward.scaleShapeProperty();
    moveForward.setGraphic(new ImageView(forwardArrowImage));
    moveForward.setStyle("-fx-background-color: #1d1f23; ");
    moveForward.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
    moveForward.setOnAction(e -> game.moveForward());

    Button moveBack = new Button();
    moveBack.scaleShapeProperty();
    moveBack.setGraphic(new ImageView(backArrowImage));
    moveBack.setStyle("-fx-background-color: #1d1f23; ");
    moveBack.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    moveBack.setOnAction(e -> game.moveBackwards());

    Button dropItem = new Button("Drop Item");
    dropItem.scaleShapeProperty();
    dropItem.setTextFill(Color.rgb(211, 147, 101));
    dropItem.setStyle("-fx-background-color: #1d1f23; ");
    dropItem.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    dropItem.setOnAction(e -> System.out.println("Dropped Item"));

    Button useItem = new Button("Use Item");
    useItem.scaleShapeProperty();
    useItem.setTextFill(Color.rgb(211, 147, 101));
    useItem.setStyle("-fx-background-color: #1d1f23; ");
    useItem.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    useItem.setOnAction(e -> System.out.println("Used Item"));

    moveBack.setOnAction(e -> game.moveBackwards());

    VBox bottomScreen = new VBox();
    bottomScreen.scaleShapeProperty();
    bottomScreen.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    HBox backpack = new HBox();
    backpack.scaleShapeProperty();
    backpack.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));

    // Build scene
    Scene scene = new Scene(layout);
    scene.setFill(Color.rgb(43, 42, 41));

    HBox bottomMostScreen = new HBox();
    bottomMostScreen.scaleShapeProperty();

    VBox bottomScreenLeft = new VBox();
    bottomScreenLeft.scaleShapeProperty();
    bottomScreenLeft.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    // Button Grid
    GridPane buttonGrid = new GridPane();
    buttonGrid.scaleShapeProperty();

    GridPane.setRowIndex(moveForward, 0);
    GridPane.setColumnIndex(moveForward, 1);
    GridPane.setRowIndex(lookRight, 1);
    GridPane.setColumnIndex(lookRight, 2);
    GridPane.setRowIndex(moveBack, 2);
    GridPane.setColumnIndex(moveBack, 1);
    GridPane.setRowIndex(lookLeft, 1);
    GridPane.setColumnIndex(lookLeft, 0);
    GridPane.setRowIndex(dropItem, 0);
    GridPane.setColumnIndex(dropItem, 0);
    GridPane.setRowIndex(useItem, 0);
    GridPane.setColumnIndex(useItem, 2);

    buttonGrid.getChildren().addAll(moveForward, lookRight, moveBack, lookLeft, dropItem, useItem);

    bottomScreenLeft.getChildren().addAll(buttonGrid);

    VBox bottomScreenRight = new VBox();
    bottomScreenRight.scaleShapeProperty();
    bottomScreenRight.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    bottomMostScreen.getChildren().addAll(bottomScreenLeft, bottomScreenRight);

    bottomScreen.getChildren().addAll(backpack, bottomMostScreen);
    /* BOTTOM SCREEN END */

    game.addObserver(gameScreen);
    game.update();

    window.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
      if (k.getCode() == KeyCode.W) {
        game.moveForward();
      } else if (k.getCode() == KeyCode.S) {
        game.moveBackwards();
      } else if (k.getCode() == KeyCode.A) {
        game.rotateLeft();
      } else if (k.getCode() == KeyCode.D) {
        game.rotateRight();
      }
    });

    // allows scene to be visible
    layout.setBackground(Background.EMPTY);
    layout.scaleShapeProperty();
    layout.setTop(menuBar);
    layout.setCenter(centerScreen);
    layout.setBottom(bottomScreen);
    layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    window.setScene(scene);
    window.sizeToScene();
    window.show();
  }
}