package mapeditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The IconsMenu class creates a window where icons (items) can be selected.
 *
 * @author Charlotte Gimblett
 */

public class IconsMenu extends Application implements EventHandler<ActionEvent> {
  private Button emptyFlask;
  private Button powerFlask;
  private Button healthFlask;
  private Button torch;
  private Button boltCutters;
  private Button crowbar;
  private Button hammer;
  private Button khopesh;
  private Button pickAxe;
  Stage primaryStage;

  /**
   * The constructor calls the start method.
   */
  public IconsMenu() {
    // calls the start method
    try {
      start(new Stage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // initializes the stage, border pane, and scene
    this.primaryStage = primaryStage;
    BorderPane border = new BorderPane();
    HBox topHBox = drawTop();
    border.setTop(topHBox);
    border.setCenter(drawItems());
    Scene scene = new Scene(border, 200, 245);

    // sets position of icons menu
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX(primScreenBounds.getWidth() - (primScreenBounds.getWidth() / 4));
    primaryStage.setY(primScreenBounds.getHeight() - (primScreenBounds.getHeight() / 5) * 4);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private HBox drawTop() {
    // draws the top section of the scene
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
    // draws the center section of the scene with all the icons (items)
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.setHgap(20);
    gridPane.setVgap(20);

    // adds empty flask icon
    Image northWestImage = new Image(
        getClass().getResource("icons/emptyFlask_none.png").toString());
    emptyFlask = new Button();
    emptyFlask.setGraphic(new ImageView(northWestImage));
    emptyFlask.setPrefSize(40, 40);
    emptyFlask.setOnAction(this);
    gridPane.add(emptyFlask, 0, 0);

    // adds power flask icon
    Image northImage = new Image(getClass().getResource("icons/powerFlask_none.png").toString());
    powerFlask = new Button();
    powerFlask.setGraphic(new ImageView(northImage));
    powerFlask.setPrefSize(40, 40);
    powerFlask.setOnAction(this);
    gridPane.add(powerFlask, 1, 0);

    // adds health flask icon
    Image northEastImage = new Image(
        getClass().getResource("icons/healthFlask_none.png").toString());
    healthFlask = new Button();
    healthFlask.setGraphic(new ImageView(northEastImage));
    healthFlask.setPrefSize(40, 40);
    healthFlask.setOnAction(this);
    gridPane.add(healthFlask, 2, 0);

    // adds torch icon
    Image westImage = new Image(getClass().getResource("icons/torch_none.png").toString());
    torch = new Button();
    torch.setGraphic(new ImageView(westImage));
    torch.setPrefSize(40, 40);
    torch.setOnAction(this);
    gridPane.add(torch, 0, 1);

    // adds bolt cutter icon
    Image emptyImage = new Image(getClass().getResource("icons/boltCutters_none.png").toString());
    boltCutters = new Button();
    boltCutters.setGraphic(new ImageView(emptyImage));
    boltCutters.setPrefSize(40, 40);
    boltCutters.setOnAction(this);
    gridPane.add(boltCutters, 1, 1);

    // adds crow bar icon
    Image eastImage = new Image(getClass().getResource("icons/crowbar_none.png").toString());
    crowbar = new Button();
    crowbar.setGraphic(new ImageView(eastImage));
    crowbar.setPrefSize(40, 40);
    crowbar.setOnAction(this);
    gridPane.add(crowbar, 2, 1);

    // adds hammer icon
    Image southWestImage = new Image(getClass().getResource("icons/hammer_none.png").toString());
    hammer = new Button();
    hammer.setGraphic(new ImageView(southWestImage));
    hammer.setPrefSize(40, 40);
    hammer.setOnAction(this);
    gridPane.add(hammer, 0, 2);

    // adds khopesh icon
    Image southImage = new Image(getClass().getResource("icons/khopesh_none.png").toString());
    khopesh = new Button();
    khopesh.setGraphic(new ImageView(southImage));
    khopesh.setPrefSize(40, 40);
    khopesh.setOnAction(this);
    gridPane.add(khopesh, 1, 2);

    // adds pick axe
    Image southEastImage = new Image(getClass().getResource("icons/pickaxe_none.png").toString());
    pickAxe = new Button();
    pickAxe.setGraphic(new ImageView(southEastImage));
    pickAxe.setPrefSize(40, 40);
    pickAxe.setOnAction(this);
    gridPane.add(pickAxe, 2, 2);

    return gridPane;
  }

  @Override
  public void handle(ActionEvent event) {
    // changed the selected icon depending on which button is clicked.
    MapEditor.setButton("itemBtn");
    if (event.getSource() == emptyFlask) {
      MapEditor.setIcon("emptyFlask");
    } else if (event.getSource() == powerFlask) {
      MapEditor.setIcon("powerFlask");
    } else if (event.getSource() == healthFlask) {
      MapEditor.setIcon("healthFlask");
    } else if (event.getSource() == torch) {
      MapEditor.setIcon("torch");
    } else if (event.getSource() == boltCutters) {
      MapEditor.setIcon("boltCutters");
    } else if (event.getSource() == crowbar) {
      MapEditor.setIcon("crowbar");
    } else if (event.getSource() == hammer) {
      MapEditor.setIcon("hammer");
    } else if (event.getSource() == khopesh) {
      MapEditor.setIcon("khopesh");
    } else if (event.getSource() == pickAxe) {
      MapEditor.setIcon("pickaxe");
    }
  }
}