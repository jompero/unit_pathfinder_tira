package unit_pathfinder_tira;

import java.util.ArrayList;

import graph.BitmapToMatrix;
import graph.Graph;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pathfinder.AStar;
import pathfinder.Dijkstra;
import pathfinder.JPS;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("unit_pathfinder_tira");

		// Setup a layer for the map, on start a pop-up dialog prompts to open an image
		ImageView mapImage = new ImageView();
		Image img = ImageLoader.trigger(primaryStage);
		mapImage.setImage(img);

		// Setup a layer for drawing the paths
		Canvas drawLayer = new Canvas(512, 512);
		GraphicsContext gc = drawLayer.getGraphicsContext2D();

		// Initialise stackpane and add above layers to it, make it root and display the
		// scene
		StackPane root = new StackPane();
		root.getChildren().add(mapImage);
		root.getChildren().add(drawLayer);
		primaryStage.setScene(new Scene(root, 512, 512));
		primaryStage.setResizable(false);
		primaryStage.show();

		// Instantiate Graph with the selected image converted to weighted matrix
		Graph g = new Graph(BitmapToMatrix.convert(img));
		Dijkstra d = new Dijkstra();
		AStar a = new AStar();
		JPS j = new JPS();

		int[] start = new int[2];
		int[] end = { -1, -1 };

		// On click, queue the coordinate and run Dijkstra if 2 valid coordinates have
		// been clicked in a row
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (inputQueue(start, end, (int) event.getX(), (int) event.getY())) {
					System.out.println(String.format("Searching path between (%o, %o) and (%o, %o). Click elsewhere to set the new end point.", start[0], start[1], end[0], end[1]));
					ArrayList<int[]> dResult = d.search(g, start, end);
					ArrayList<int[]> aResult = a.search(g, start, end);
					ArrayList<int[]> jResult = j.search(g, start, end);
					gc.clearRect(0, 0, 512, 512);
					if (dResult != null) {
						highlightPixels(gc, dResult, Color.YELLOW);
					}
					if (aResult != null) {
						highlightPixels(gc, aResult, Color.RED);
					}
					if (jResult != null) {
						highlightPixels(gc, jResult, Color.MAGENTA);
					}
				} else {
					System.out.println(String.format("Starting point set at (%o, %o), click elsewhere to set the end point.", end[0], end[1]));
				}
			}
		});
	}

	// Make previous click the starting point and new click the ending point
	boolean inputQueue(int[] start, int[] end, int x, int y) {
		start[0] = end[0];
		start[1] = end[1];
		end[0] = x;
		end[1] = y;
		if (start[0] < 0)
			return false;
		return true;
	}

	// Take the result from Dijkstra and draw a yellow line to indicate the path
	void highlightPixels(GraphicsContext gc, ArrayList<int[]> pixels, Color color) {
		gc.setStroke(color);
		gc.setLineWidth(2);
		gc.beginPath();
		for (int[] i : pixels) {
			gc.lineTo(i[0], i[1]);
		}
		gc.stroke();
		gc.closePath();
	}
}
