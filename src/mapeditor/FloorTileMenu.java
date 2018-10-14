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
 * The FloorTileMenu class creates a window where floor tiles can be selected.
 *
 * @author Charlotte Gimblett
 */
public class FloorTileMenu extends Application implements EventHandler<ActionEvent> {
  private Button north;
  private Button northEast;
  private Button east;
  private Button southEast;
  private Button south;
  private Button southWest;
  private Button west;
  private Button northWest;
  private Button none;
  Stage primaryStage;

  /**
   * The constructor calls the start method.
   */
  public FloorTileMenu() {
    try {
      start(new Stage());
    } catch (Exception e) {
      e.printStackTrace();
    }
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

    // sets position of floor tile menu
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX(primScreenBounds.getWidth() - (primScreenBounds.getWidth() / 5));
    primaryStage.setY(primScreenBounds.getHeight() - (primScreenBounds.getHeight() / 15)*7);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private HBox drawTop() {
    // draws the top section of the scene
    HBox box = new HBox();
    box.setPadding(new Insets(15, 15, 15, 15));
    box.setSpacing(10);
    box.setStyle("-fx-background-color: #9b9781;");

    Text text = new Text("Select a floor tile");
    text.setFont(Font.font("Verdana", 15));

    box.getChildren().addAll(text);
    return box;
  }

  private Node drawItems() {
    // draws the center section of the scene with all the floor tile items
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.setHgap(20);
    gridPane.setVgap(20);

    // add north west wall tile
    Image northWestImage = new Image(getClass().getResource("icons/empty_NW.png").toString());
    northWest = new Button();
    northWest.setGraphic(new ImageView(northWestImage));
    northWest.setPrefSize(40, 40);
    northWest.setOnAction(this);
    gridPane.add(northWest, 0, 0);

    // add north wall tile
    Image northImage = new Image(getClass().getResource("icons/empty_N.png").toString());
    north = new Button();
    north.setGraphic(new ImageView(northImage));
    north.setPrefSize(40, 40);
    north.setOnAction(this);
    gridPane.add(north, 1, 0);

    // add north east wall tile
    Image northEastImage = new Image(getClass().getResource("icons/empty_NE.png").toString());
    northEast = new Button();
    northEast.setGraphic(new ImageView(northEastImage));
    northEast.setPrefSize(40, 40);
    northEast.setOnAction(this);
    gridPane.add(northEast, 2, 0);

    // add west wall tile
    Image westImage = new Image(getClass().getResource("icons/empty_W.png").toString());
    west = new Button();
    west.setGraphic(new ImageView(westImage));
    west.setPrefSize(40, 40);
    west.setOnAction(this);
    gridPane.add(west, 0, 1);

    // add the no wall tile
    Image emptyImage = new Image(getClass().getResource("icons/empty_none.png").toString());
    none = new Button();
    none.setGraphic(new ImageView(emptyImage));
    none.setPrefSize(40, 40);
    none.setOnAction(this);
    gridPane.add(none, 1, 1);

    // add east wall tile
    Image eastImage = new Image(getClass().getResource("icons/empty_E.png").toString());
    east = new Button();
    east.setGraphic(new ImageView(eastImage));
    east.setPrefSize(40, 40);
    east.setOnAction(this);
    gridPane.add(east, 2, 1);

    // add south west wall tile
    Image southWestImage = new Image(getClass().getResource("icons/empty_SW.png").toString());
    southWest = new Button();
    southWest.setGraphic(new ImageView(southWestImage));
    southWest.setPrefSize(40, 40);
    southWest.setOnAction(this);
    gridPane.add(southWest, 0, 2);

    // add south wall tile
    Image southImage = new Image(getClass().getResource("icons/empty_S.png").toString());
    south = new Button();
    south.setGraphic(new ImageView(southImage));
    south.setPrefSize(40, 40);
    south.setOnAction(this);
    gridPane.add(south, 1, 2);

    // add south east wall tile
    Image eastSouthImage = new Image(getClass().getResource("icons/empty_SE.png").toString());
    southEast = new Button();
    southEast.setGraphic(new ImageView(eastSouthImage));
    southEast.setPrefSize(40, 40);
    southEast.setOnAction(this);
    gridPane.add(southEast, 2, 2);
    return gridPane;
  }

  @Override
  public void handle(ActionEvent event) {
    // changed the selected direction depending on which button is clicked.
    MapEditor.setButton("floorBtn");
    if (event.getSource() == northWest) {
      MapEditor.setDirection("NW");
    } else if (event.getSource() == north) {
      MapEditor.setDirection("N");
    } else if (event.getSource() == northEast) {
      MapEditor.setDirection("NE");
    } else if (event.getSource() == east) {
      MapEditor.setDirection("E");
    } else if (event.getSource() == southEast) {
      MapEditor.setDirection("SE");
    } else if (event.getSource() == south) {
      MapEditor.setDirection("S");
    } else if (event.getSource() == southWest) {
      MapEditor.setDirection("SW");
    } else if (event.getSource() == west) {
      MapEditor.setDirection("W");
    } else if (event.getSource() == none) {
      MapEditor.setDirection("none");
    }
  }
}