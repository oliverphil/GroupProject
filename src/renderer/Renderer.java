package renderer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import gameWorld.ViewDescriptor;

public class Renderer extends Canvas{
	
	public Renderer(double width, double height) {
		super(width, height);
	}
	
	public void redraw(ViewDescriptor view) {
		System.out.println("Drawing");
		GraphicsContext gc = getGraphicsContext2D();

	}
}
