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

  
  public IconsMenu() {
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

    Text text = new Text("Select an icon");
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
    Image northWestImage = new Image(getClass().getResourceAsStream("icons/emptyFlask.png"));
    emptyFlask = new Button();
    emptyFlask.setGraphic(new ImageView(northWestImage));
    emptyFlask.setPrefSize(40, 40);
    emptyFlask.setOnAction(this);
    gridPane.add(emptyFlask, 0, 0);

    // add north wall
    Image northImage = new Image(getClass().getResourceAsStream("icons/powerFlask.png"));
    powerFlask = new Button();
    powerFlask.setGraphic(new ImageView(northImage));
    powerFlask.setPrefSize(40, 40);
    powerFlask.setOnAction(this);
    gridPane.add(powerFlask, 1, 0);

    // add north east walls
    Image northEastImage = new Image(getClass().getResourceAsStream("icons/healthFlask.png"));
    healthFlask = new Button();
    healthFlask.setGraphic(new ImageView(northEastImage));
    healthFlask.setPrefSize(40, 40);
    healthFlask.setOnAction(this);
    gridPane.add(healthFlask, 2, 0);

    // add west wall
    Image westImage = new Image(getClass().getResourceAsStream("icons/torch.png"));
    torch = new Button();
    torch.setGraphic(new ImageView(westImage));
    torch.setPrefSize(40, 40);
    torch.setOnAction(this);
    gridPane.add(torch, 0, 1);

    // add empty tile
    Image emptyImage = new Image(getClass().getResourceAsStream("icons/boltCutters.png"));
    boltCutters = new Button();
    boltCutters.setGraphic(new ImageView(emptyImage));
    boltCutters.setPrefSize(40, 40);
    boltCutters.setOnAction(this);
    gridPane.add(boltCutters, 1, 1);

    // add east wall
    Image eastImage = new Image(getClass().getResourceAsStream("icons/crowbar.png"));
    crowbar = new Button();
    crowbar.setGraphic(new ImageView(eastImage));
    crowbar.setPrefSize(40, 40);
    crowbar.setOnAction(this);
    gridPane.add(crowbar, 2, 1);

    // add south west walls
    Image southWestImage = new Image(getClass().getResourceAsStream("icons/hammer.png"));
    hammer = new Button();
    hammer.setGraphic(new ImageView(southWestImage));
    hammer.setPrefSize(40, 40);
    hammer.setOnAction(this);
    gridPane.add(hammer, 0, 2);

    // add south wall
    Image southImage = new Image(getClass().getResourceAsStream("icons/khopesh.png"));
    khopesh = new Button();
    khopesh.setGraphic(new ImageView(southImage));
    khopesh.setPrefSize(40, 40);
    khopesh.setOnAction(this);
    gridPane.add(khopesh, 1, 2);

    // add east south walls
    Image southEastImage = new Image(getClass().getResourceAsStream("icons/pickAxe.png"));
    pickAxe = new Button();
    pickAxe.setGraphic(new ImageView(southEastImage));
    pickAxe.setPrefSize(40, 40);
    pickAxe.setOnAction(this);
    gridPane.add(pickAxe, 2, 2);

    return gridPane;
  }

  @Override
  public void handle(ActionEvent event) {
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
  }
}