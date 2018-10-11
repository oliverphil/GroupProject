package mapeditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import persistence.Persistence;

/**
 * The MapEditor class creates a window where a map can be created by the user.
 *
 * @author Charlotte Gimblett
 */
@XmlRootElement
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
  private int row;
  private int col;
  private Application openWindow;

  private static Map<String, Image> images;

  @Override
  public void start(Stage primaryStage) throws Exception {
    // initializes 2D array that holds the map values
    grid = new String[GRID_WIDTH][GRID_HEIGHT];
    for (int y = 0; y < GRID_HEIGHT; y++) {
      for (int x = 0; x < GRID_WIDTH; x++) {
        grid[x][y] = "0_none";
      }
    }

    // creates a map of images so
    images = new HashMap<String, Image>();
    File iconFolder = new File("src" + File.separator + "mapeditor" + File.separator + "icons");
    File[] icons = iconFolder.listFiles();

    for (int i = 0; i < icons.length; i++) {
      String s = icons[i].getName().replaceAll(".png", "");
      String toString = icons[i].toString()
          .replace("src" + File.separator + "mapeditor" + File.separator + "", "");
      Image img = new Image(getClass().getResource(toString).toString());
      images.put(s, img);
    }

    // adds the first room to the map
    grid[9][9] = "empty_NW";
    grid[10][9] = "empty_N";
    grid[11][9] = "emptyFlask_NE";
    grid[9][10] = "empty_W";
    grid[10][10] = "empty_none";
    grid[11][10] = "empty_E";
    grid[9][11] = "empty_SW";
    grid[10][11] = "empty_S";
    grid[11][11] = "empty_SE";

    // initializes the stage, border pane, and scene
    primaryStage.setTitle("Map Editor");
    BorderPane border = new BorderPane();
    gridPane = new GridPane();
    HBox topHBox = drawTop();
    HBox bottomHBox = drawBottom();
    border.setTop(topHBox);
    border.setCenter(drawGrid());
    border.setBottom(bottomHBox);
    Scene scene = new Scene(border, 480, 581);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {
      // makes sure only one extra window is open at once
      if (openWindow != null) {
        try {
          if (openWindow instanceof FloorTileMenu) {
            ((FloorTileMenu) openWindow).primaryStage.close();
          } else if (openWindow instanceof IconsMenu) {
            ((IconsMenu) openWindow).primaryStage.close();
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      // do the appropriate action depending on what buttons are pushed
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
      if (e.getSource() == load) {
        Persistence.loadMapEditor("mapEditorLoad");
      }
      if (e.getSource() == save) {
        Persistence.saveMapEditor(MapEditor.this, "mapEditorSave");
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

      // adds appropriate floor tile to map
      if (selectedBtn == "floorBtn") {
        if (row != -1 && col != -1 && row <= 20 && col <= 20) {
          selectedIcon = "empty";
          grid[col][row] = selectedIcon + "_" + direction;
          drawGrid();
        }
      }
      // adds the appropriate icon to map
      if (selectedBtn == "itemBtn") {
        if (row != -1 && col != -1) {
          if (grid[col][row].endsWith("N")) {
            direction = "N";
          }
          if (grid[col][row].endsWith("_NE")) {
            direction = "NE";
          }
          if (grid[col][row].endsWith("_E")) {
            direction = "E";
          }
          if (grid[col][row].endsWith("_SE")) {
            direction = "SE";
          }
          if (grid[col][row].endsWith("_S")) {
            direction = "S";
          }
          if (grid[col][row].endsWith("_SW")) {
            direction = "SW";
          }
          if (grid[col][row].endsWith("_W")) {
            direction = "W";
          }
          if (grid[col][row].endsWith("_NW")) {
            direction = "NW";
          }
          if (grid[col][row].endsWith("_none")) {
            direction = "none";
          }
          grid[col][row] = selectedIcon + "_" + direction;
          drawGrid();
        }
      }
      // removes the appropriate tile/icon from map
      if (selectedBtn == "remove") {
        remove(x, y);
      }
    }
  };

  private int getCol(int x) {
    // calculates which column was clicked on
    return (int) ((x - 10) / 22);
  }

  private int getRow(int y) {
    // calculates which row was clicked on
    return (int) ((y - 51) / 22);
  }

  private void remove(int x, int y) {
    // removes the tile that was at the row and column which was clicked on
    grid[col][row] = "0_none";
    drawGrid();

  }

  private HBox drawTop() {
    // initializes the top section of the scene
    HBox box = new HBox();
    box.setPadding(new Insets(8, 10, 8, 10)); // top, right, bottom, left
    box.setSpacing(10);
    box.setStyle("-fx-background-color: #9b9781;");

    save = new Button("Save");
    save.setPrefSize(60, 20);
    save.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    load = new Button("Load");
    load.setPrefSize(60, 20);
    load.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    box.getChildren().addAll(save, load);
    return box;
  }

  private Node drawGrid() {
    // clears and redraws the grid in the center section
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
        Image img = images.get(currentIcon + "_" + currentDir);
        Rectangle rec = new Rectangle(x, y, 20, 20);
        rec.setFill(new ImagePattern(img));
        gridPane.add(rec, x, y);
      }
    }
    return gridPane;
  }

  private HBox drawBottom() {
    // initializes the bottom section of the scene
    HBox box = new HBox();
    box.setPadding(new Insets(15, 15, 15, 15));
    box.setSpacing(10);
    box.setStyle("-fx-background-color: #9b9781;");

    floorBtn = new Button("Add Floor Tile");
    floorBtn.setPrefSize(110, 20);
    floorBtn.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    itemBtn = new Button("Add Floor Object");
    itemBtn.setPrefSize(130, 20);
    itemBtn.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    remove = new Button("Remove");
    remove.setPrefSize(90, 20);
    remove.addEventHandler(ActionEvent.ACTION, actionEventHandler);

    box.getChildren().addAll(floorBtn, itemBtn, remove);
    return box;
  }

  public static void setSelectedIcon(String icon) {
    selectedIcon = icon;
  }

  public static void setDirection(String dir) {
    direction = dir;
  }

  public String[][] getGrid() {
    return grid;
  }

  /*
   * @XmlElementWrapper(name = "grid")
   * @XmlElements({ @XmlElement(name = "row", type = String[].class),
   * @XmlElement(name = "gridItem", type = String.class) })
   */
  @XmlElement
  public void setGrid(String[][] grid) {
    this.grid = grid;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof MapEditor)) {
      return false;
    }
    MapEditor o = (MapEditor) other;
    if (grid == null) {
      return o.getGrid() == null;
    }
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] != null && !grid[i][j].equals(o.getGrid()[i][j])
            || (grid[i][j] == null && o.getGrid()[i][j] != null)) {
          return false;
        }
      }
    }
    return true;
  }

}