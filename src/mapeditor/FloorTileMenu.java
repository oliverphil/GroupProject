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

public class FloorTileMenu extends Application implements EventHandler<ActionEvent> {
  private Button north;
  private Button northEast;
  private Button east;
  private Button eastSouth;
  private Button south;
  private Button southWest;
  private Button west;
  private Button northWest;
  private Button none;
  Stage primaryStage;

  public FloorTileMenu() {
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
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.setHgap(20);
    gridPane.setVgap(20);

    // add north west walls
    Image northWestImage = new Image(getClass().getResourceAsStream("icons/empty_NW.png"));
    northWest = new Button();
    northWest.setGraphic(new ImageView(northWestImage));
    northWest.setPrefSize(40, 40);
    northWest.setOnAction(this);
    gridPane.add(northWest, 0, 0);

    // add north wall
    Image northImage = new Image(getClass().getResourceAsStream("icons/empty_N.png"));
    north = new Button();
    north.setGraphic(new ImageView(northImage));
    north.setPrefSize(40, 40);
    north.setOnAction(this);
    gridPane.add(north, 1, 0);

    // add north east walls
    Image northEastImage = new Image(getClass().getResourceAsStream("icons/empty_NE.png"));
    northEast = new Button();
    northEast.setGraphic(new ImageView(northEastImage));
    northEast.setPrefSize(40, 40);
    northEast.setOnAction(this);
    gridPane.add(northEast, 2, 0);

    // add west wall
    Image westImage = new Image(getClass().getResourceAsStream("icons/empty_W.png"));
    west = new Button();
    west.setGraphic(new ImageView(westImage));
    west.setPrefSize(40, 40);
    west.setOnAction(this);
    gridPane.add(west, 0, 1);

    // add empty tile
    Image emptyImage = new Image(getClass().getResourceAsStream("icons/empty_none.png"));
    none = new Button();
    none.setGraphic(new ImageView(emptyImage));
    none.setPrefSize(40, 40);
    none.setOnAction(this);
    gridPane.add(none, 1, 1);

    // add east wall
    Image eastImage = new Image(getClass().getResourceAsStream("icons/empty_E.png"));
    east = new Button();
    east.setGraphic(new ImageView(eastImage));
    east.setPrefSize(40, 40);
    east.setOnAction(this);
    gridPane.add(east, 2, 1);

    // add south west walls
    Image southWestImage = new Image(getClass().getResourceAsStream("icons/empty_SW.png"));
    southWest = new Button();
    southWest.setGraphic(new ImageView(southWestImage));
    southWest.setPrefSize(40, 40);
    southWest.setOnAction(this);
    gridPane.add(southWest, 0, 2);

    // add south wall
    Image southImage = new Image(getClass().getResourceAsStream("icons/empty_S.png"));
    south = new Button();
    south.setGraphic(new ImageView(southImage));
    south.setPrefSize(40, 40);
    south.setOnAction(this);
    gridPane.add(south, 1, 2);

    // add east south walls
    Image eastSouthImage = new Image(getClass().getResourceAsStream("icons/empty_ES.png"));
    eastSouth = new Button();
    eastSouth.setGraphic(new ImageView(eastSouthImage));
    eastSouth.setPrefSize(40, 40);
    eastSouth.setOnAction(this);
    gridPane.add(eastSouth, 2, 2);
    return gridPane;
  }

  @Override
  public void handle(ActionEvent event) {
    if (event.getSource() == northWest)
      MapEditor.setDirection("NW");
    else if (event.getSource() == north)
      MapEditor.setDirection("N");
    else if (event.getSource() == northEast)
      MapEditor.setDirection("NE");
    else if (event.getSource() == east)
      MapEditor.setDirection("E");
    else if (event.getSource() == eastSouth)
      MapEditor.setDirection("ES");
    else if (event.getSource() == south)
      MapEditor.setDirection("S");
    else if (event.getSource() == southWest)
      MapEditor.setDirection("SW");
    else if (event.getSource() == west)
      MapEditor.setDirection("W");
    else if (event.getSource() == none)
      MapEditor.setDirection("none");
    primaryStage.close();

  }
}