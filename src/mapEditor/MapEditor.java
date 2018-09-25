package mapEditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapEditor extends Application implements EventHandler<ActionEvent> {

	private final int GRID_WIDTH = 20;
	private final int GRID_HEIGHT = 20;
	private Button floorBtn, itemBtn, northBtn, southBtn, eastBtn, westBtn, save, load, remove;
	private String[][] grid;

	public static void Map(String[] args) {
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

		itemBtn = new Button("Add FloorObject");
		itemBtn.setPrefSize(90, 20);
		itemBtn.setOnAction(this);
		
		remove = new Button("Remove FloorObject");
		remove.setPrefSize(90, 20);
		remove.setOnAction(this);

//		final Pane spacer = new Pane();
//		spacer.setMinSize(70, 1);

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
			System.out.println("floor");
		} else if (event.getSource() == itemBtn) {
			System.out.println("item");
		}

	}
}