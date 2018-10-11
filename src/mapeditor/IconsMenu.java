package mapeditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

<<<<<<< HEAD
=======
/**
 * The IconsMenu class creates a window where icons (items) can be selected.
 *
 * @author Charlotte Gimblett
 */
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
public class IconsMenu extends Application implements EventHandler<ActionEvent> {
  private Button emptyFlask;
  private Button powerFlask;
  private Button healthFlask;
  private Button torch;
  private Button boltCutters;
  private Button crowbar;
  private Button hammer;
  private Button khopesh;
<<<<<<< HEAD
  private Button pickAxe;
  Stage primaryStage;

  
  public IconsMenu() {
=======
  private Button pickaxe;
  Stage primaryStage;

  /**
   * The constructor calls the start method.
   */
  public IconsMenu() {
    // calls the start method
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    try {
      start(new Stage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

<<<<<<< HEAD
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
=======
  @Override
  public void start(Stage primaryStage) throws Exception {
    // initializes the stage, border pane, and scene
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    this.primaryStage = primaryStage;
    BorderPane border = new BorderPane();
    HBox topHBox = drawTop();
    border.setTop(topHBox);
    border.setCenter(drawItems());

    Scene scene = new Scene(border, 200, 245);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private HBox drawTop() {
<<<<<<< HEAD
=======
    // draws the top section of the scene
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    HBox box = new HBox();
    box.setPadding(new Insets(15, 15, 15, 15));
    box.setSpacing(10);
    box.setStyle("-fx-background-color: #9b9781;");

    Text text = new Text("Select an icon");
    text.setFont(Font.font("Verdana", 15));

    box.getChildren().addAll(text);

    return box;
  }

  private Node drawItems() {
<<<<<<< HEAD
=======
    // draws the center section of the scene with all the icons (items)
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.setHgap(20);
    gridPane.setVgap(20);

<<<<<<< HEAD
    // add north west walls
    Image northWestImage = new Image(getClass().getResourceAsStream("icons/emptyFlask.png"));
=======
    // adds empty flask icon
    Image northWestImage = new Image(
        getClass().getResource("icons/emptyFlask_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    emptyFlask = new Button();
    emptyFlask.setGraphic(new ImageView(northWestImage));
    emptyFlask.setPrefSize(40, 40);
    emptyFlask.setOnAction(this);
    gridPane.add(emptyFlask, 0, 0);

<<<<<<< HEAD
    // add north wall
    Image northImage = new Image(getClass().getResourceAsStream("icons/powerFlask.png"));
=======
    // adds power flask icon
    Image northImage = new Image(getClass().getResource("icons/powerFlask_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    powerFlask = new Button();
    powerFlask.setGraphic(new ImageView(northImage));
    powerFlask.setPrefSize(40, 40);
    powerFlask.setOnAction(this);
    gridPane.add(powerFlask, 1, 0);

<<<<<<< HEAD
    // add north east walls
    Image northEastImage = new Image(getClass().getResourceAsStream("icons/healthFlask.png"));
=======
    // adds health flask icon
    Image northEastImage = new Image(
        getClass().getResource("icons/healthFlask_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    healthFlask = new Button();
    healthFlask.setGraphic(new ImageView(northEastImage));
    healthFlask.setPrefSize(40, 40);
    healthFlask.setOnAction(this);
    gridPane.add(healthFlask, 2, 0);

<<<<<<< HEAD
    // add west wall
    Image westImage = new Image(getClass().getResourceAsStream("icons/torch.png"));
=======
    // adds torch icon
    Image westImage = new Image(getClass().getResource("icons/torch_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    torch = new Button();
    torch.setGraphic(new ImageView(westImage));
    torch.setPrefSize(40, 40);
    torch.setOnAction(this);
    gridPane.add(torch, 0, 1);

<<<<<<< HEAD
    // add empty tile
    Image emptyImage = new Image(getClass().getResourceAsStream("icons/boltCutters.png"));
=======
    // adds bolt cutter icon
    Image emptyImage = new Image(getClass().getResource("icons/boltCutters_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    boltCutters = new Button();
    boltCutters.setGraphic(new ImageView(emptyImage));
    boltCutters.setPrefSize(40, 40);
    boltCutters.setOnAction(this);
    gridPane.add(boltCutters, 1, 1);

<<<<<<< HEAD
    // add east wall
    Image eastImage = new Image(getClass().getResourceAsStream("icons/crowbar.png"));
=======
    // adds crow bar icon
    Image eastImage = new Image(getClass().getResource("icons/crowbar_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    crowbar = new Button();
    crowbar.setGraphic(new ImageView(eastImage));
    crowbar.setPrefSize(40, 40);
    crowbar.setOnAction(this);
    gridPane.add(crowbar, 2, 1);

<<<<<<< HEAD
    // add south west walls
    Image southWestImage = new Image(getClass().getResourceAsStream("icons/hammer.png"));
=======
    // adds hammer icon
    Image southWestImage = new Image(getClass().getResource("icons/hammer_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    hammer = new Button();
    hammer.setGraphic(new ImageView(southWestImage));
    hammer.setPrefSize(40, 40);
    hammer.setOnAction(this);
    gridPane.add(hammer, 0, 2);

<<<<<<< HEAD
    // add south wall
    Image southImage = new Image(getClass().getResourceAsStream("icons/khopesh.png"));
=======
    // adds khopesh icon
    Image southImage = new Image(getClass().getResource("icons/khopesh_none.png").toString());
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
    khopesh = new Button();
    khopesh.setGraphic(new ImageView(southImage));
    khopesh.setPrefSize(40, 40);
    khopesh.setOnAction(this);
    gridPane.add(khopesh, 1, 2);

<<<<<<< HEAD
    // add east south walls
    Image southEastImage = new Image(getClass().getResourceAsStream("icons/pickAxe.png"));
    pickAxe = new Button();
    pickAxe.setGraphic(new ImageView(southEastImage));
    pickAxe.setPrefSize(40, 40);
    pickAxe.setOnAction(this);
    gridPane.add(pickAxe, 2, 2);
=======
    // adds pick axe
    Image southEastImage = new Image(getClass().getResource("icons/pickaxe_none.png").toString());
    pickaxe = new Button();
    pickaxe.setGraphic(new ImageView(southEastImage));
    pickaxe.setPrefSize(40, 40);
    pickaxe.setOnAction(this);
    gridPane.add(pickaxe, 2, 2);
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce

    return gridPane;
  }

  @Override
  public void handle(ActionEvent event) {
<<<<<<< HEAD
    if (event.getSource() == emptyFlask) {
      MapEditor.setSelectedIcon("emptyFlask");
      primaryStage.close();
    } else if (event.getSource() == powerFlask) {
      MapEditor.setSelectedIcon("powerFlask");
      primaryStage.close();
    } else if (event.getSource() == healthFlask) {
      MapEditor.setSelectedIcon("healthFlask");
      primaryStage.close();
    } else if (event.getSource() == torch) {
      MapEditor.setSelectedIcon("torch");
      primaryStage.close();
    } else if (event.getSource() == boltCutters) {
      MapEditor.setSelectedIcon("boltCutters");
      primaryStage.close();
    } else if (event.getSource() == crowbar) {
      MapEditor.setSelectedIcon("crowbar");
      primaryStage.close();
    } else if (event.getSource() == hammer) {
      MapEditor.setSelectedIcon("hammer");
      primaryStage.close();
    } else if (event.getSource() == khopesh) {
      MapEditor.setSelectedIcon("khopesh");
      primaryStage.close();
    } else if (event.getSource() == pickAxe) {
      MapEditor.setSelectedIcon("pickAxe");
      primaryStage.close();
    }
=======
    // changed the selected icon depending on which button is clicked.
    if (event.getSource() == emptyFlask) {
      MapEditor.setSelectedIcon("emptyFlask");
    }
    if (event.getSource() == powerFlask) {
      MapEditor.setSelectedIcon("powerFlask");
    }
    if (event.getSource() == healthFlask) {
      MapEditor.setSelectedIcon("healthFlask");
    }
    if (event.getSource() == torch) {
      MapEditor.setSelectedIcon("torch");
    }
    if (event.getSource() == boltCutters) {
      MapEditor.setSelectedIcon("boltCutters");
    }
    if (event.getSource() == crowbar) {
      MapEditor.setSelectedIcon("crowbar");
    }
    if (event.getSource() == hammer) {
      MapEditor.setSelectedIcon("hammer");
    }
    if (event.getSource() == khopesh) {
      MapEditor.setSelectedIcon("khopesh");
    }
    if (event.getSource() == pickaxe) {
      MapEditor.setSelectedIcon("pickaxe");
    }
    primaryStage.close();
>>>>>>> 5c6ab03426213bb3f1328be37816925b874fc4ce
  }
}