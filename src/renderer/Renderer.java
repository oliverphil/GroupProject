package renderer;

import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import gameWorld.ViewDescriptor;

public class Renderer extends Canvas {
	private static final double DRAWING_HEIGHT = 500;
	private static final double DRAWING_WIDTH = 500;
	
	public void redraw(ViewDescriptor view) {
		GraphicsContext gr = getGraphicsContext2D();
		double height = heightProperty().get();
		double width = widthProperty().get();
		double scale = height / DRAWING_HEIGHT < width / DRAWING_WIDTH ? height / DRAWING_HEIGHT : width / DRAWING_WIDTH;
	}
}
