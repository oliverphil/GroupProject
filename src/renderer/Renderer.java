package renderer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import gameWorld.ViewDescriptor;
import java.util.List;

public class Renderer extends Canvas {

	public Renderer(double width, double height) {
		super(width, height);
	}

	public void redraw(ViewDescriptor view) {
		System.out.println("Drawing");
		GraphicsContext gc = getGraphicsContext2D();
		int i = 0;
		List<String> visibleTiles = view.getView();
		for (double x = 0; x < getWidth(); x += getWidth() / 3) {
			switch(visibleTiles.get(i)) {
			case "wall":
				gc.setFill(Color.LIGHTSLATEGRAY);
				gc.fillRect(x, 0, getWidth() / 3, getHeight()*2/3);
				break;
			case "door":
				gc.setFill(Color.BLACK);
				gc.fillRect(x, 0, getWidth() / 3, getHeight()*2/3);
			case "clear":
				gc.setFill(Color.WHITE);
				gc.fillRect(x, 0, getWidth() / 3, getHeight()*2/3);
			}
			i++;
		}
	}
}
