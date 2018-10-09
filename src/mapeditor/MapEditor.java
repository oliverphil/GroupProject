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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MapEditor extends Application {

  private static final int GRID_WIDTH = 21;
  private static final int GRID_HEIGHT = 21;
  private GridPane gridPane;
  
  private Button floorBtn;
  private Button itemBtn;
  private Button northBtn;
  private Button southBtn;
  private Button eastBtn;
  private Button westBtn;
  private Button save;
  private Button load;
  private Button remove;
  
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

    border.setTop(topHBox);
    border.setCenter(drawGrid());

    Scene scene = new Scene(border, 480, 535);
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
      if (e.getSource() == itemBtn) {
        selectedBtn = "itemBtn";
        new IconsMenu();
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
      if (selectedBtn == "itemBtn") {
        if (row != -1 && col != -1) {
          if(grid[col][row] == "0") {
           // add shit here 
            grid[col][row] = "empty, " + selectedIcon;
          }
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
    grid[col][row] = "0";
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
        Rectangle rec = new Rectangle(x, y, 20, 20);
        if (grid[x][y] == "0") {
          rec.setFill(Color.LIGHTGREY);
        } else if (grid[x][y] == "N") {
          Image img = new Image(getClass().getResourceAsStream("icons/N.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "NE") {
          Image img = new Image(getClass().getResourceAsStream("icons/NE.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "E") {
          Image img = new Image(getClass().getResourceAsStream("icons/E.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "ES") {
          Image img = new Image(getClass().getResourceAsStream("icons/SE.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "S") {
          Image img = new Image(getClass().getResourceAsStream("icons/S.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "SW") {
          Image img = new Image(getClass().getResourceAsStream("icons/SW.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "W") {
          Image img = new Image(getClass().getResourceAsStream("icons/W.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "NW") {
          Image img = new Image(getClass().getResourceAsStream("icons/NW.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "empty") {
          Image img = new Image(getClass().getResourceAsStream("icons/empty.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "emptyFlask") {
          Image img = new Image(getClass().getResourceAsStream("icons/emptyFlask.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "healthFlask") {
          Image img = new Image(getClass().getResourceAsStream("icons/healthFlask.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "powerFlask") {
          Image img = new Image(getClass().getResourceAsStream("icons/powerFlask.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "boltCutters") {
          Image img = new Image(getClass().getResourceAsStream("icons/boltCutters.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "crowbar") {
          Image img = new Image(getClass().getResourceAsStream("icons/crowbar.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "hammer") {
          Image img = new Image(getClass().getResourceAsStream("icons/hammer.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "khopesh") {
          Image img = new Image(getClass().getResourceAsStream("icons/khopesh.png"));
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "pickaxe") {
          Image img = new Image(getClass().getResourceAsStream("icons/pickaxe.png"));
          System.out.println("pickaxe");
          rec.setFill(new ImagePattern(img));
        } else if (grid[x][y] == "torch") {
          Image img = new Image(getClass().getResourceAsStream("icons/torch.png"));
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