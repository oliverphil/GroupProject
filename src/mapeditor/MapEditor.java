package mapeditor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MapEditor extends Application {

  private static final int GRID_WIDTH = 21;
  private static final int GRID_HEIGHT = 21;
  private GridPane gridPane;

  private Button floorBtn;
  private Button itemBtn;
  private Button save;
  private Button load;
  private Button remove;

  private String[][] grid;
  private static String selectedIcon = "0";
  private static String selectedBtn = "floorBtn";
  private static String direction = "none";
  private String currentIcon = "0";
  private String currentDir = "none";
  int row;
  int col;
  String[] args;

  private Application openWindow;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // initialize array for the grid
    grid = new String[GRID_WIDTH][GRID_HEIGHT];
    for (int y = 0; y < GRID_HEIGHT; y++) {
      for (int x = 0; x < GRID_WIDTH; x++) {
        grid[x][y] = "0_none";
      }
    }

    grid[9][9] = "empty_NW";
    grid[10][9] = "empty_N";
    grid[11][9] = "emptyFlask_NE";
    grid[9][10] = "empty_W";
    grid[10][10] = "empty_none";
    grid[11][10] = "empty_E";
    grid[9][11] = "empty_SW";
    grid[10][11] = "empty_S";
    grid[11][11] = "empty_SE";

    primaryStage.setTitle("Map Editor");
    BorderPane border = new BorderPane();
    gridPane = new GridPane();
    HBox topHBox = drawTop();
    border.setTop(topHBox);
    border.setCenter(drawGrid());
    Scene scene = new Scene(border, 480, 535);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {
      if (openWindow != null) {
        try {
          if (openWindow instanceof FloorTileMenu) {
            // ((FloorTileMenu) openWindow).primaryStage.close();
          } else if (openWindow instanceof IconsMenu) {
            // ((IconsMenu) openWindow).primaryStage.close();
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (e.getSource() == floorBtn) {
        selectedBtn = "floorBtn";
        openWindow = new FloorTileMenu();
      }
      if (e.getSource() == itemBtn) {
        selectedBtn = "itemBtn";
        openWindow = new IconsMenu();
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
          selectedIcon = "empty";
          grid[col][row] = selectedIcon + "_" + direction;
          drawGrid();
        }
      }
      if (selectedBtn == "itemBtn") {
        if (row != -1 && col != -1) {
          if (grid[col][row].endsWith("N")) {
            direction = "N";
          } else if (grid[col][row].endsWith("_NE")) {
            direction = "NE";
          } else if (grid[col][row].endsWith("_E")) {
            direction = "E";
          } else if (grid[col][row].endsWith("_SE")) {
            direction = "SE";
          } else if (grid[col][row].endsWith("_S")) {
            direction = "S";
          } else if (grid[col][row].endsWith("_SW")) {
            direction = "SW";
          } else if (grid[col][row].endsWith("_W")) {
            direction = "W";
          } else if (grid[col][row].endsWith("_NW")) {
            direction = "NW";
          } else if (grid[col][row].endsWith("_none")) {
            direction = "none";
          }
          grid[col][row] = selectedIcon + "_" + direction;
          drawGrid();
        }
      }
      if (selectedBtn == "remove") {
        remove(x, y);
      }
    }
  };

  private int getCol(int x) {
    return (int) ((x - 10) / 22);
  }

  private int getRow(int y) {
    return (int) ((y - 65) / 22);
  }

  private void remove(int x, int y) {
    grid[col][row] = "0_none";
    drawGrid();

  }

  private HBox drawTop() {
    HBox box = new HBox();
    box.setPadding(new Insets(15, 15, 15, 15));
    box.setSpacing(10);
    box.setStyle("-fx-background-color: #9b9781;");

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

    box.getChildren().addAll(floorBtn, itemBtn, remove, save, load);

    return box;
  }

  private Node drawGrid() {
    gridPane.getChildren().clear();
    gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
    gridPane.setPadding(new Insets(10, 10, 10, 10));
    gridPane.setHgap(2);
    gridPane.setVgap(2);

    for (int y = 0; y < GRID_HEIGHT; y++) {
      for (int x = 0; x < GRID_WIDTH; x++) {
        String[] gridSquare = (grid[x][y]).split("_");
        currentIcon = gridSquare[0];
        currentDir = gridSquare[1];
        Image img = new Image(
            getClass().getResource("icons/" + currentIcon + "_" + currentDir + ".png").toString());
        Rectangle rec = new Rectangle(x, y, 20, 20);

        rec.setFill(new ImagePattern(img));

        gridPane.add(rec, x, y);
      }
    }
    return gridPane;
  }

  public static void setSelectedIcon(String icon) {
    selectedIcon = icon;
  }

  public static void setDirection(String dir) {
    direction = dir;
  }
}