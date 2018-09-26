package mapEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MapEditor extends Application implements EventHandler<ActionEvent>, MouseListener {

	private final int GRID_WIDTH = 20;
	private final int GRID_HEIGHT = 20;
	private Button floorBtn, itemBtn, northBtn, southBtn, eastBtn, westBtn, save, load, remove;
	private String[][] grid;
	private static String selectedIcon = "0";
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

		primaryStage.setTitle("Map");

		BorderPane border = new BorderPane();
		HBox topHBox = drawTop();
		HBox bottomHBox = drawBottom();
		border.setTop(topHBox);
		border.setCenter(drawGrid());
		border.setBottom(bottomHBox);

//		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent e) {
//				System.out.println("Hello World");
//			}
//		};

		Scene scene = new Scene(border, 460, 570);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HBox drawTop() {
		// TODO Auto-generated method stub
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(15, 12, 15, 12));
		hBox.setSpacing(10);
		hBox.setStyle("-fx-background-color: #9b9781;");

		floorBtn = new Button("Add Floor Tile");
		floorBtn.setPrefSize(90, 20);
		floorBtn.setOnAction(this);

		itemBtn = new Button("Add Floor Object");
		itemBtn.setPrefSize(110, 20);
		itemBtn.setOnAction(this);

		remove = new Button("Remove");
		remove.setPrefSize(60, 20);
		remove.setOnAction(this);

		save = new Button("Save");
		save.setPrefSize(60, 20);
		save.setOnAction(this);

		load = new Button("Load");
		load.setPrefSize(60, 20);
		load.setOnAction(this);

		hBox.getChildren().addAll(floorBtn, itemBtn, remove, save, load);

		return hBox;
	}

	private HBox drawBottom() {
		// TODO Auto-generated method stub
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(15, 12, 15, 12));
		hBox.setSpacing(10);
		hBox.setStyle("-fx-background-color: #9b9781;");

		northBtn = new Button("North");
		northBtn.setPrefSize(60, 20);
		northBtn.setOnAction(this);

		eastBtn = new Button("East");
		eastBtn.setPrefSize(60, 20);
		eastBtn.setOnAction(this);

		southBtn = new Button("South");
		southBtn.setPrefSize(60, 20);
		southBtn.setOnAction(this);

		westBtn = new Button("West");
		westBtn.setPrefSize(60, 20);
		westBtn.setOnAction(this);

		hBox.getChildren().addAll(northBtn, eastBtn, southBtn, westBtn);

		return hBox;
	}

	private Node drawGrid() {
		// TODO Auto-generated method stub
		GridPane gridPane = new GridPane();
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

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == floorBtn) {
			FloorTileMenu ftm = new FloorTileMenu();
		}
	}

	public static void setSelectedIcon(String icon) {
		selectedIcon = icon;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse clicked");
		Node source = (Node) e.getSource();
		Integer colIndex = GridPane.getColumnIndex(source);
		Integer rowIndex = GridPane.getRowIndex(source);
		if (colIndex != null && rowIndex != null) {
			grid[colIndex][rowIndex] = selectedIcon;
		}
		drawGrid();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse exited");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse released");

	}
}