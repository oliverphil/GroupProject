package renderer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import gameWorld.GameWorld;
import gameWorld.ViewDescriptor;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Renderer extends Canvas implements Observer {

	public Renderer(double width, double height) {
		super(width, height);
	}

	public void redraw(ViewDescriptor view) {
		System.out.println("Drawing");
		if (view == null)
			return;
		GraphicsContext gc = getGraphicsContext2D();
		int i = 0;
		List<String> visibleTiles = view.getView();
		for (double x = 0; x < getWidth(); x += getWidth() / 3) {
			switch (visibleTiles.get(i)) {
			case "wall":
				gc.setFill(Color.LIGHTSLATEGRAY);
				gc.fillRect(x, 0, getWidth() / 3, getHeight() * 2 / 3);
				break;
			case "door":
				gc.setFill(Color.BLACK);
				gc.fillRect(x, 0, getWidth() / 3, getHeight() * 2 / 3);
				break;
			case "clear":
				gc.setFill(Color.WHITE);
				gc.fillRect(x, 0, getWidth() / 3, getHeight() * 2 / 3);
				break;
			}
			i++;
		}
		for (double x = 0; x < getWidth(); x += getWidth() / 3) {
			switch (visibleTiles.get(i)) {
			case "clear":
				gc.setFill(Color.BLANCHEDALMOND);
				gc.fillRect(x, getHeight() * 2 / 3, getWidth() / 3, getHeight() / 3);
				break;
			}
			i++;
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0.getClass().equals(GameWorld.class) && arg1 instanceof ViewDescriptor) {
			redraw((ViewDescriptor) arg1);
		}
	}
}
