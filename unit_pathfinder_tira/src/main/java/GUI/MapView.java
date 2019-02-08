package GUI;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import graph.BitmapToMatrix;
import graph.Graph;

import pathfinder.*;

public class MapView extends StackPane {
	Graph g;
	
	int[] start = new int[2];
	int[] end = { -1, -1 };
	
	Dijkstra d = new Dijkstra();
	AStar a = new AStar();
	JPS j = new JPS();
	Pathfinder[] pathfinders = { d, a, j };
	
	// GUI
	ImageView map = new ImageView();
	Canvas drawDijkstra = new Canvas(512, 512);
	Canvas drawAStar = new Canvas(512, 512);
	Canvas drawJPS = new Canvas(512, 512);
	GraphicsContext gcd = drawDijkstra.getGraphicsContext2D();
	GraphicsContext gca = drawAStar.getGraphicsContext2D();
	GraphicsContext gcj = drawJPS.getGraphicsContext2D();

	public MapView(Image img) {
		loadMap(img);
		
		// On click, queue the coordinate and run Dijkstra if 2 valid coordinates have
		// been clicked in a row
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (inputQueue(start, end, (int) event.getX(), (int) event.getY())) {
					System.out.println(String.format("Searching path between (%o, %o) and (%o, %o). Click elsewhere to set the new end point.", start[0], start[1], end[0], end[1]));
					ArrayList<int[]> dResult = d.search(g, start, end);
					ArrayList<int[]> aResult = a.search(g, start, end);
					ArrayList<int[]> jResult = j.search(g, start, end);
					
					// Draw paths
					clearDrawings();
					if (dResult != null) {
						highlightPixels(gcd, dResult, Color.YELLOW);
					}
					if (aResult != null) {
						highlightPixels(gca, aResult, Color.RED);
					}
					if (jResult != null) {
						highlightPixels(gcj, jResult, Color.MAGENTA);
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

	public String[][] getBenchmark() {
		int numPathfinders = pathfinders.length;
		int numStats = 4;
		String[][] stats = new String[numPathfinders][numStats];
		for (int i = 0; i < numPathfinders; i++) {
			Double weight = pathfinders[i].getTotalWeight();
			String totalWeight = String.format("%.2f", weight);
			String nodesInPath = Integer.toString(pathfinders[i].getNodesInPath());
			String visitedNodes = Integer.toString(pathfinders[i].getVisitedNodes());
			Long time = pathfinders[i].getTime();
			String timeInMilliseconds = String.format("%o ms", time);
			
			stats[i][0] = totalWeight;
			stats[i][1] = nodesInPath;
			stats[i][2] = visitedNodes;
			stats[i][3] = timeInMilliseconds;
			
		}
		return stats;
	}

	public void loadMap(Image img) {
		// Set the map image as background
		map.setImage(img);
		// Populate children if empty
		if (this.getChildren().isEmpty()) {
			this.getChildren().addAll(map, drawDijkstra, drawAStar, drawJPS);
		}
		// And clear the drawing boards and input queue
		clearDrawings();
		start = new int[2];
		end[0] = -1;
		end[1] = -1;
				
		// Convert selected image to a Graph
		g = new Graph(BitmapToMatrix.convert(img));
	}
	
	private void clearDrawings() {
		gca.clearRect(0, 0, 512, 512);
		gcd.clearRect(0, 0, 512, 512);
		gcj.clearRect(0, 0, 512, 512);
	}
}
