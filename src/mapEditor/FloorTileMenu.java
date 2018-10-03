package mapEditor;

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
	private Button N, NE, E, ES, S, SW, W, NW, empty;
	Stage primaryStage;

	public FloorTileMenu() {
		try {
			start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.setSpacing(10);
		hBox.setStyle("-fx-background-color: #9b9781;");

		Text text = new Text("Select a floor tile");
		text.setFont(Font.font("Verdana", 15));

		hBox.getChildren().addAll(text);

		return hBox;
	}

	private Node drawItems() {
		// TODO Auto-generated method stub
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(20);
		gridPane.setVgap(20);

		// add north west walls
		Image NWimage = new Image(getClass().getResourceAsStream("NW.png"));
		NW = new Button();
		NW.setGraphic(new ImageView(NWimage));
		NW.setPrefSize(40, 40);
		NW.setOnAction(this);
		gridPane.add(NW, 0, 0);

		// add north wall
		Image Nimage = new Image(getClass().getResourceAsStream("N.png"));
		N = new Button();
		N.setGraphic(new ImageView(Nimage));
		N.setPrefSize(40, 40);
		N.setOnAction(this);
		gridPane.add(N, 1, 0);

		// add north east walls
		Image NEimage = new Image(getClass().getResourceAsStream("NE.png"));
		NE = new Button();
		NE.setGraphic(new ImageView(NEimage));
		NE.setPrefSize(40, 40);
		NE.setOnAction(this);
		gridPane.add(NE, 2, 0);

		// add west wall
		Image Wimage = new Image(getClass().getResourceAsStream("W.png"));
		W = new Button();
		W.setGraphic(new ImageView(Wimage));
		W.setPrefSize(40, 40);
		W.setOnAction(this);
		gridPane.add(W, 0, 1);

		// add empty tile
		Image emptImage = new Image(getClass().getResourceAsStream("empty.png"));
		empty = new Button();
		empty.setGraphic(new ImageView(emptImage));
		empty.setPrefSize(40, 40);
		empty.setOnAction(this);
		gridPane.add(empty, 1, 1);

		// add east wall
		Image Eimage = new Image(getClass().getResourceAsStream("E.png"));
		E = new Button();
		E.setGraphic(new ImageView(Eimage));
		E.setPrefSize(40, 40);
		E.setOnAction(this);
		gridPane.add(E, 2, 1);

		// add south west walls
		Image SWimage = new Image(getClass().getResourceAsStream("SW.png"));
		SW = new Button();
		SW.setGraphic(new ImageView(SWimage));
		SW.setPrefSize(40, 40);
		SW.setOnAction(this);
		gridPane.add(SW, 0, 2);

		// add south wall
		Image Simage = new Image(getClass().getResourceAsStream("S.png"));
		S = new Button();
		S.setGraphic(new ImageView(Simage));
		S.setPrefSize(40, 40);
		S.setOnAction(this);
		gridPane.add(S, 1, 2);

		// add east south walls
		Image ESimage = new Image(getClass().getResourceAsStream("ES.png"));
		ES = new Button();
		ES.setGraphic(new ImageView(ESimage));
		ES.setPrefSize(40, 40);
		ES.setOnAction(this);
		gridPane.add(ES, 2, 2);

		return gridPane;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == NW) {
			MapEditor.setSelectedIcon("NW");
			primaryStage.close();
		} else if (event.getSource() == N) {
			MapEditor.setSelectedIcon("N");
			primaryStage.close();
		} else if (event.getSource() == NE) {
			MapEditor.setSelectedIcon("NE");
			primaryStage.close();
		} else if (event.getSource() == E) {
			MapEditor.setSelectedIcon("E");
			primaryStage.close();
		} else if (event.getSource() == ES) {
			MapEditor.setSelectedIcon("ES");
			primaryStage.close();
		} else if (event.getSource() == S) {
			MapEditor.setSelectedIcon("S");
			primaryStage.close();
		} else if (event.getSource() == SW) {
			MapEditor.setSelectedIcon("SW");
			primaryStage.close();
		} else if (event.getSource() == W) {
			MapEditor.setSelectedIcon("W");
			primaryStage.close();
		} else if (event.getSource() == empty) {
			MapEditor.setSelectedIcon("empty");
			primaryStage.close();
		}
		System.out.println(MapEditor.getSelectedIcon());
	}
}