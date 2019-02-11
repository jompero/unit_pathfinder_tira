package gui;

import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class MapTools extends VBox {
	MapView mv;
	
	String dijkstraTitle = 	"Dijkstra";
	String astarTitle = 	"A*";
	String jpsTitle = 		"Jump-Point Search";
	String[] titles = { dijkstraTitle, astarTitle, jpsTitle };
	
	String totalWeight = 	"Weight of the found path:";
	String nodesInPath = 	"Nodes in path found:";
	String visitedNodes =	"Nodes visited during search:";
	String time = 			"Algorithm completed in:";
	String[] fieldTitles = { totalWeight, nodesInPath, visitedNodes, time };
	
	String[] dStats = new String[4];
	String[] aStats = new String[4];
	String[] jStats = new String[4];
	String[][] stats = { dStats, aStats, jStats };
	
	// As the values will change often, we need easy access to these text fields
	Label[] dStatsText = new Label[4];
	Label[] aStatsText = new Label[4];
	Label[] jStatsText = new Label[4];
	Label[][] statTexts = { dStatsText, aStatsText, jStatsText };
	
	public MapTools(Node root, MapView map) {
		mv = map;
		stats = mv.getBenchmark();
		generateLayout();

		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stats = mv.getBenchmark();
				for (int i = 0; i < statTexts.length; i++) {
					for (int j = 0; j < statTexts[0].length; j++) {
						statTexts[i][j].setText(stats[i][j]);
					}
				}
			}
		});
	}
	
	/**
	 * This method will generate the layout for the benchmarking data using labels.
	 * All children are laid out vertically starting with Benchmark data, then buttons.
	 */
	void generateLayout() {
		this.setPadding(new Insets(10));
		
		// Benchmark data
		int numForms = titles.length;
		int numFields = fieldTitles.length;
		VBox[] forms = new VBox[numForms];
		HBox[] fields = new HBox[numFields];
		
		for (int i = 0; i < numForms; i++) {
			// Make a new form for each pathfinder and stack containing nodes vertically
			forms[i] = new VBox();
			forms[i].setMinWidth(312);
			
			// Make a form title with a layer toggle checkbox
			//Label title = new Label(titles[i]);
			//title.setPadding(new Insets(2, 0, 2, 0));
			//title.setFont(Font.font("Verdana", 14));
			//forms[i].getChildren().add(title);
			// Also make a check box to allow hiding of the draw layer for this pathfinder
			CheckBox cb = layerToggle(i, titles[i]);
			forms[i].getChildren().add(cb);
			
			// Populate fields
			for (int j = 0; j < numFields; j++) {
				// Make an HBox
				fields[j] = new HBox();
				fields[j].setPadding(new Insets(1, 5, 1, 5));
				
				// Divide the HBox in left and right aligned labels divided by a region
				// Left Label
				Label left = new Label(fieldTitles[j]);
				// Divider
				Region center = new Region();
				HBox.setHgrow(center, Priority.ALWAYS);
				// Right label
				statTexts[i][j] = new Label(stats[i][j]);
				
				// Populate the form with the new fields
				fields[j].getChildren().addAll(left, center, statTexts[i][j]);
				forms[i].getChildren().add(fields[j]);
			}
			this.getChildren().add(forms[i]);
		}
		// Divider with button
		Region divider = new Region();
		VBox.setVgrow(divider, Priority.ALWAYS);
		this.getChildren().add(divider);
		
		// Button to load a new map including the event that triggers on action
		Button loadMapButton = new Button("Load New Map");
		loadMapButton.setOnAction(click -> {
			Stage stage = (Stage) this.getScene().getWindow();
			Image img = ImageLoader.trigger(stage);
			mv.loadMap(img);
		});
		this.getChildren().add(loadMapButton);
	}
	
	/**
	 * Creates a CheckBox which allows hiding of MapView's layers
	 * @param i 		Layer index to be hidden
	 * @param label		Button label
	 * @return
	 */
	CheckBox layerToggle(int i, String label) {
		CheckBox cb = new CheckBox(label);
		// Default checked
		cb.setIndeterminate(false);
		cb.setSelected(true);
		// Increase size
		cb.setPadding(new Insets(2, 0, 2, 0));
		cb.setFont(Font.font("Verdana", 14));
		
		cb.setOnAction(event -> {
			mv.toggleLayer(i, cb.selectedProperty().get());
		});
		return cb;
	}
}
