package application;

import gameworld.GameWorld;
import gameworld.holdables.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.JFileChooser;

import mapeditor.MapEditor;
import persistence.Persistence;
import renderer.Renderer;

public class UserInterface extends Application {

  // TODO:
  // Fix Help Printing
  // Backpack and move buttons
  // Fix Help Printing
  // Health bar *Note: Power Flask duration = 10sec
  // Write Tests [Renderer Tests, add 'Before All' to tests]

  // Have a variable in GUI that is filled when an item is selected for either use/drop
  // Make sure that GUI updates backpack and healthbar each time (Need a redraw for backpack and
  // healthbar)
  // Is player alive (checked on each event) -> Fade to black if dead (call to renderer)
  // Make healthbar a canvas (player.gethealth())

  // CRC Card for GUI
  // Read Me for Game (WITH CHARLOTTE)
  // Howard Lukefah = Clippy for Help Page

  public static final String HELP_MESSAGE = " ";
  private Stage window;
  private BorderPane layout = new BorderPane();
  private int selectedItem;
  private List<Item> items = new ArrayList<Item>();
  private double HEALTHBAR_SCALE = 4.35;
  private double CARRYBAR_SCALE = 29;

  private Rectangle healthBar;
  private BorderPane healthBarLayout = new BorderPane();
  
  private Rectangle carryingCapacityBar;
  private BorderPane carryingCapacityLayout = new BorderPane();

  private static Item itemInPack;
  private static BorderPane statusScreen = new BorderPane();
  static Label itemLabel = new Label("");

  private GameWorld game;
  private GridPane backpackGrid;

  // load arrow images and resize them to 60x60px
  private Image forwardArrowImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "forward.png"), 60, 60, false, false);
  private Image backArrowImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "back.png"), 60, 60, false, false);
  private Image leftArrowImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "left.png"), 60, 60, false, false);
  private Image rightArrowImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "right.png"), 60, 60, false, false);

  // load backpack icon images
  private Image boltCutterImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "boltCutters.png"), 60, 60, false,
      false);
  private Image crowbarImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "crowbar.png"), 60, 60, false,
      false);
  private Image hammerImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "hammer.png"), 60, 60, false,
      false);
  private Image khopeshImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "khopesh.png"), 60, 60, false,
      false);
  private Image pickaxeImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "pickaxe.png"), 60, 60, false,
      false);
  private Image torchImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "torch.png"), 60, 60, false, false);
  private Image emptyFlaskImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "emptyFlask.png"), 60, 60, false,
      false);
  private Image healthFlaskImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "healthFlask.png"), 60, 60, false,
      false);
  private Image powerFlaskImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "powerFlask.png"), 60, 60, false,
      false);
  private Image bombImage = new Image(
      getClass().getResourceAsStream("icons" + File.separator + "bomb.png"), 60, 60, false, false);

  public static void main(String[] args) {
    launch(args);
  }

  private void update() {

    double healthBarWidth = game.getPlayer().getHealth() * HEALTHBAR_SCALE;
    healthBar = new Rectangle(10, 3, healthBarWidth, 15);
    healthBar.setFill(Color.DARKSEAGREEN);
    healthBarLayout.setCenter(healthBar);   
    
    double carryBarWidth = ((game.getPlayer().getCarryingCapacity()) - (game.getPlayer().getCurrentWeight())) * CARRYBAR_SCALE;
    carryingCapacityBar = new Rectangle(10, 3, carryBarWidth, 15);
    carryingCapacityBar.setFill(Color.DARKORANGE);
    carryingCapacityLayout.setCenter(carryingCapacityBar);  

    backpackGrid.getChildren().clear();
    items.clear();
    items.addAll(game.getPlayer().getBag());
    if (selectedItem >= items.size()) {
      selectedItem = items.size() - 1 < 0 ? 0 : items.size() - 1;
    }
    ArrayList<Button> packItemsArray = new ArrayList<Button>();

    for (int i = 0; i < game.getPlayer().getBag().size(); i++) {
      itemInPack = game.getPlayer().getBag().get(i);
      Button itemButton = new Button();

      switch (itemInPack.toString()) {
        case "emptyFlask":
          itemButton.setGraphic(new ImageView(emptyFlaskImage));
          break;
        case "crowbar":
          itemButton.setGraphic(new ImageView(crowbarImage));
          break;
        case "powerFlask":
          itemButton.setGraphic(new ImageView(powerFlaskImage));
          break;
        case "healthFlask":
          itemButton.setGraphic(new ImageView(healthFlaskImage));
          break;
        case "pickaxe":
          itemButton.setGraphic(new ImageView(pickaxeImage));
          break;
        case "boltCutters":
          itemButton.setGraphic(new ImageView(boltCutterImage));
          break;
        case "khopesh":
          itemButton.setGraphic(new ImageView(khopeshImage));
          break;
        case "torch":
          itemButton.setGraphic(new ImageView(torchImage));
          break;
        case "hammer":
          itemButton.setGraphic(new ImageView(hammerImage));
          break;
        case "bomb":
          itemButton.setGraphic(new ImageView(bombImage));
          break;
        default:
          return;

      }

      itemButton.setAccessibleHelp(((Integer) i).toString());
      itemButton.setStyle("-fx-background-color: #1d1f23; ");
      itemButton.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
          BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
      itemButton.setOnAction(e -> {
        selectedItem = Integer.parseInt(itemButton.accessibleHelpProperty().get());
        drawBorder();
        animateLabel(itemInPack.toString());
      });
      packItemsArray.add(itemButton);
      backpackGrid.add(itemButton, i, 0);
    }
    drawBorder();
  }

  private void drawBorder() {
    if (!items.isEmpty()) {
      for (Node n : backpackGrid.getChildren()) {
        Button b = (Button) n;
        b.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
            new CornerRadii(3), BorderWidths.DEFAULT)));
      }
      ((Button) backpackGrid.getChildren().get(selectedItem))
          .setBorder(new Border(new BorderStroke(Color.rgb(255, 0, 0), BorderStrokeStyle.SOLID,
              new CornerRadii(3), BorderWidths.DEFAULT)));
    }
  }
  
  public static void animateLabel(String label) {
    ParallelTransition labelAnimation = new ParallelTransition();
    
    switch (label) {
      case "emptyFlask":
        itemLabel.setText("An empty flask");
        break;
      case "powerFlask":
        itemLabel.setText("A full flask. The liquid looks powerful");
        break;
      case "healthFlask":
        itemLabel.setText("A full flask. The liquid looks invigorating");
        break;
      case "crowbar":
        itemLabel.setText("A rusty crowbar");
        break;
      case "pickaxe":
        itemLabel.setText("A sharp pickaxe. Good for mining");
        break;
      case "boltCutters":
        itemLabel.setText("A pair of bolt cutters");
        break;
      case "khopesh":
        itemLabel.setText("A mysterious and powerful ancient sword...");
        break;
      case "torch":
        itemLabel.setText("A blazing torch");
        break;
      case "hammer":
        itemLabel.setText("A heavy hammer");
        break;
      case "bomb":
        itemLabel.setText("An odd bomb");
        break;
      case "david":
        itemLabel.setText("You attacked Pharoh Pierce!");
        break;
      case "marco":
        itemLabel.setText("You attacked Mummy Marco!");
        break;
      case "thomas":
        itemLabel.setText("You attacked Tombstone Thomas!");
        break;
      case "woodenBlockade":
        itemLabel.setText("Some wooden planks. Looks like they might pry away");
        break;
      case "stoneBlockade":
        itemLabel.setText("Crumbled stones block your path");
        break; 
      case "chainBlockade":
        itemLabel.setText("Rusted chains are covering the stone door");
        break; 
      case "healthFountain":
        itemLabel.setText("A spring bubbling with life!");
        break; 
      case "powerFountain":
        itemLabel.setText("A spring bubbling with power!");
        break;
      case "ladder":
        itemLabel.setText("The old ladder takes you to the surface...");
        break;
      default:
        return;
    }
    
    labelAnimation.setOnFinished(e -> {
      statusScreen.setCenter(itemLabel);
      itemLabel.setTextFill(Color.WHITE);
      itemLabel.setFont(new Font("Arial", 26));
      FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), itemLabel);
      fadeOut.setFromValue(1.0);
      fadeOut.setToValue(0.0);
      fadeOut.play();
    });
    
    labelAnimation.play();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    game = new GameWorld();
    window = primaryStage;
    window.setTitle("An Adventure Game!");
    window.setResizable(false);
    
    // sets position of the window
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX(primScreenBounds.getWidth() - (primScreenBounds.getWidth() / 10) *9);
    primaryStage.setY(primScreenBounds.getHeight() - (primScreenBounds.getHeight() / 25) *24);

    /* MENU START */
    // Game Menu
    Menu gameMenu = new Menu("");
    Label t = new Label("Game");
    t.setStyle("-fx-text-fill: #D39365; ");
    gameMenu.setGraphic(t);

    MenuItem save = new MenuItem("Save...");
    save.setOnAction(e -> {
      JFileChooser getFile = new JFileChooser();
      int returnVal = getFile.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        Persistence.saveGame(game, getFile.getSelectedFile().toString());
      }

    });
    gameMenu.getItems().add(save);
    MenuItem load = new MenuItem("Load...");

    gameMenu.getItems().add(load);
    MenuItem gameRestart = new MenuItem("Restart Game");
    gameRestart.setOnAction(e -> {
      game = new GameWorld();
      items.clear(); 
      selectedItem = 0;
      update();
    });
    
    gameMenu.getItems().add(gameRestart);
    gameMenu.getItems().add(new SeparatorMenuItem());

    // Help Section
    MenuItem help = new MenuItem("Help");
    help.setOnAction(e -> {
      try {
        Scanner sc = new Scanner(new File("src/application/help.txt"));

        while (sc.hasNext()) {
          System.out.println(sc.nextLine());
        }
        sc.close();
      } catch (FileNotFoundException e1) {
        System.out.println("Help File Not Found");
      }
      gameMenu.getItems().add(help);

      new Notification("Instructions", HELP_MESSAGE, "Got it!");
    });
    MenuItem exit = new MenuItem("Exit");
    exit.setOnAction(e -> System.exit(0));
    gameMenu.getItems().add(exit);

    // Settings Menu
    CheckMenuItem toggleMusic = new CheckMenuItem("Enable Sound");
    toggleMusic.setSelected(true);

    // Options Menu
    Menu optionsMenu = new Menu("");
    Label s = new Label("Options");
    s.setStyle("-fx-text-fill: #D39365; ");
    optionsMenu.setGraphic(s);
    optionsMenu.getItems().addAll(toggleMusic);

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

    statusScreen.setStyle("-fx-background-color: #1d1f23; ");
    statusScreen.setMinHeight(32);
    statusScreen.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));

    Renderer gameScreen = new Renderer(700, 700);
    gameScreen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      game.interact(gameScreen.onClick(e));
      update();
    });
    centerScreen.getChildren().addAll(gameScreen, statusScreen);
    centerScreen.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    gameRestart.setOnAction(e -> {
      gameScreen.restartGame();
      game = new GameWorld();
      game.addObserver(gameScreen);
      game.update();
    });
    load.setOnAction(e -> {
      JFileChooser getFile = new JFileChooser();
      int returnVal = getFile.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        game = Persistence.loadGame(getFile.getSelectedFile().toString());
        game.addObserver(gameScreen);
        game.update();
      }
    });
    toggleMusic.setOnAction(e -> {
      if (toggleMusic.isSelected()) {
        gameScreen.unmute();
      } else {
        gameScreen.mute();
      }
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
    lookLeft.setOnAction(e -> {
      game.rotateLeft();
      update();
    });

    Button lookRight = new Button();
    lookRight.scaleShapeProperty();
    lookRight.setGraphic(new ImageView(rightArrowImage));
    lookRight.setStyle("-fx-background-color: #1d1f23; ");
    lookRight.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    lookRight.setOnAction(e -> {
      game.rotateRight();
      update();
    });

    Button moveForward = new Button();
    moveForward.scaleShapeProperty();
    moveForward.setGraphic(new ImageView(forwardArrowImage));
    moveForward.setStyle("-fx-background-color: #1d1f23; ");
    moveForward.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
    moveForward.setOnAction(e -> {
      game.moveForward();
      update();
    });

    Button moveBack = new Button();
    moveBack.scaleShapeProperty();
    moveBack.setGraphic(new ImageView(backArrowImage));
    moveBack.setStyle("-fx-background-color: #1d1f23; ");
    moveBack.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    moveBack.setOnAction(e -> {
      game.moveBackwards();
      update();
    });

    Button dropItem = new Button("Drop Item");
    dropItem.scaleShapeProperty();
    dropItem.setTextFill(Color.rgb(211, 147, 101));
    dropItem.setStyle("-fx-background-color: #1d1f23; ");
    dropItem.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    dropItem.setMinHeight(35);
    dropItem.setMinWidth(231);
    dropItem.setOnAction(e -> {
      if (!items.isEmpty()) {
        game.dropItem(items.get(selectedItem));
        items.remove(selectedItem);
        selectedItem = selectedItem > 0 ? selectedItem - 1 : 0;
      }
      update();
    });

    Button useItem = new Button("Use Item");
    useItem.scaleShapeProperty();
    useItem.setTextFill(Color.rgb(211, 147, 101));
    useItem.setStyle("-fx-background-color: #1d1f23; ");
    useItem.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20), BorderStrokeStyle.SOLID,
        new CornerRadii(3), BorderWidths.DEFAULT)));
    useItem.setMinHeight(35);
    useItem.setMinWidth(231);
    useItem.setOnAction(e -> {
      if (!items.isEmpty()) {
        game.useItem(items.get(selectedItem));
      }
      update();
    });

    moveBack.setOnAction(e -> {
      game.moveBackwards();
    });

    VBox bottomScreen = new VBox();
    bottomScreen.scaleShapeProperty();
    bottomScreen.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    // Backpack
    backpackGrid = new GridPane();
    backpackGrid.setScaleShape(true);
    backpackGrid.scaleShapeProperty();
    backpackGrid.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));

    // Health Bar
    healthBarLayout.setStyle("-fx-background-color: #171916; ");
    healthBarLayout.setMinWidth(462);
    healthBarLayout.setMinHeight(20);
    
    // Carrying Capacity Bar
    carryingCapacityLayout.setStyle("-fx-background-color: #171916; ");
    carryingCapacityLayout.setMinWidth(462);
    carryingCapacityLayout.setMinHeight(20);

    // Use and Drop Format
    BorderPane useDropLayout = new BorderPane();
    useDropLayout.setMinWidth(462);
    useDropLayout.setMinHeight(35);
    HBox useDropBox = new HBox();
    useDropBox.getChildren().addAll(dropItem, useItem);
    useDropLayout.setCenter(useDropBox);

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

    buttonGrid.getChildren().addAll(moveForward, lookRight, moveBack, lookLeft);

    bottomScreenLeft.getChildren().addAll(buttonGrid);

    VBox bottomScreenRight = new VBox();
    bottomScreenRight.setScaleShape(true);
    bottomScreenRight.scaleShapeProperty();
    bottomScreenRight.setBorder(new Border(new BorderStroke(Color.rgb(25, 22, 20),
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    bottomScreenRight.getChildren().addAll(healthBarLayout, carryingCapacityLayout, useDropLayout, backpackGrid);

    bottomMostScreen.getChildren().addAll(bottomScreenLeft, bottomScreenRight);

    bottomScreen.getChildren().addAll(bottomMostScreen);
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
      update();
    });

    update();

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
