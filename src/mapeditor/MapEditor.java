package mapeditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MapEditor extends Application {

  private final int GRID_WIDTH = 21;
  private final int GRID_HEIGHT = 21;
  private GridPane gridPane;
  private Button floorBtn, itemBtn, northBtn, southBtn, eastBtn, westBtn, save, load, remove;
  private String[][] grid;
  private static String selectedIcon = "0";
  private static String selectedBtn = "floorBtn";
  int row;
  int col;
  String[] args;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // initialize array for the grid
    grid = new String[GRID_WIDTH][GRID_HEIGHT];

    for (int y = 0; y < GRID_HEIGHT; y++) {
      for (int x = 0; x < GRID_WIDTH; x++) {
        grid[x][y] = "0";
      }
    }
    grid[10][10] = "empty";
    grid[9][9] = "NW";
    grid[10][9] = "N";
    grid[11][9] = "NE";

    primaryStage.setTitle("Map");
    BorderPane border = new BorderPane();
    gridPane = new GridPane();

    HBox topHBox = drawTop();
    HBox bottomHBox = drawBottom();
    border.setTop(topHBox);
    border.setCenter(drawGrid());
    border.setBottom(bottomHBox);

    Scene scene = new Scene(border, 480, 590);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {
      if (e.getSource() == floorBtn) {
        selectedBtn = "floorBtn";
        new FloorTileMenu();
      }
      if (e.getSource() == remove) {
        selectedBtn = "remove";
      }
    }
  };

  EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent e) {
      int x = (int) e.getSceneX();
      int y = (int) e.getSceneY();
      row = getRow(y);
      col = getCol(x);
      
      if (selectedBtn == "floorBtn") {
        if (row != -1 && col != -1) {
          grid[col][row] = selectedIcon;
          drawGrid();
        }
      }
      if (selectedBtn == "remove") {
        remove(x, y);
      }
    }
  };

  private int getCol(int x) {
    return (int) Math.floor((x - 10) / 22);
  }

  private int getRow(int y) {
    return (int) Math.floor((y - 65) / 22);
  }

  private void remove(int x, int y) {
    // TODO Auto-generated method stub
    grid[col][row] = "0";
    drawGrid();

  }

  private HBox drawTop() {
    // TODO Auto-generated method stub
    HBox hBox = new HBox();
    hBox.setPadding(new Insets(15, 15, 15, 15));
    hBox.setSpacing(10);
    hBox.setStyle("-fx-background-color: #9b9781;");

    floorBtn = new Button("Add Floor Tile");
    floorBtn.setPrefSize(90, 20);
    floorBtn.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    itemBtn = new Button("Add Floor Object");
    itemBtn.setPrefSize(110, 20);
    itemBtn.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    remove = new Button("Remove");
    remove.setPrefSize(60, 20);
    remove.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    save = new Button("Save");
    save.setPrefSize(60, 20);
    save.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    load = new Button("Load");
    load.setPrefSize(60, 20);
    load.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    hBox.getChildren().addAll(floorBtn, itemBtn, remove, save, load);

    return hBox;
  }

  private HBox drawBottom() {
    // TODO Auto-generated method stub
    HBox hBox = new HBox();
    hBox.setPadding(new Insets(15, 15, 15, 15));
    hBox.setSpacing(10);
    hBox.setStyle("-fx-background-color: #9b9781;");

    northBtn = new Button("North");
    northBtn.setPrefSize(60, 20);
    // northBtn.setOnAction(this);

    eastBtn = new Button("East");
    eastBtn.setPrefSize(60, 20);
    // eastBtn.setOnAction(this);

    southBtn = new Button("South");
    southBtn.setPrefSize(60, 20);
    // southBtn.setOnAction(this);

    westBtn = new Button("West");
    westBtn.setPrefSize(60, 20);
    // westBtn.setOnAction(this);

    hBox.getChildren().addAll(northBtn, eastBtn, southBtn, westBtn);

    return hBox;
  }

  private Node drawGrid() {
    // TODO Auto-generated method stub
    gridPane.getChildren().clear();
    gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
    gridPane.setPadding(new Insets(10, 10, 10, 10));
    gridPane.setHgap(2);
    gridPane.setVgap(2);

    for (int y = 0; y < GRID_HEIGHT; y++) {
      for (int x = 0; x < GRID_WIDTH; x++) {
        Rectangle rec = new Rectangle(x, y, 20, 20);
        if (grid[x][y] == "0") {
          rec.setFill(Color.LIGHTGREY);
        } else if (grid[x][y] == "N") {
          Image img = new Image(getClass().getResourceAsStream("N.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "NE") {
          Image img = new Image(getClass().getResourceAsStream("NE.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "E") {
          Image img = new Image(getClass().getResourceAsStream("E.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "ES") {
          Image img = new Image(getClass().getResourceAsStream("ES.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "S") {
          Image img = new Image(getClass().getResourceAsStream("S.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "SW") {
          Image img = new Image(getClass().getResourceAsStream("SW.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "W") {
          Image img = new Image(getClass().getResourceAsStream("W.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "NW") {
          Image img = new Image(getClass().getResourceAsStream("NW.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "empty") {
          Image img = new Image(getClass().getResourceAsStream("empty.png"));
          rec.setFill(new ImagePattern(img));
        }
        gridPane.add(rec, x, y);

      }
    }
    return gridPane;
  }

  public static void setSelectedIcon(String icon) {
    selectedIcon = icon;
  }

  public static String getSelectedIcon() {
    return selectedIcon;
  }

}