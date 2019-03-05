package gui;

import javafx.stage.Stage;
import javafx.scene.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * MapTools is a GUI scene that provides benchmarking data and allows loading of maps.
 * @author danijompero
 *
 */
public class MapTools extends VBox {
	MapView mv;
	
	double stageWidth = 312;
	
	String infoText = "Clicking on the map will \nqueue the coordinates for the pathfinders.";
	String startText;
	String endText;
	String nullQueueText1 = "Coordinates: \n< Click somewhere to set the START point. >";
	String nullQueueText2 = "Coordinates: \n< Click somewhere to set the END point. >";
	String inputQueueText = "Coordinates: \n%s, %s";
	// This label will cycle the above queue texts
	Label inputQueue;
	int clicks = 0;
	
	String dijkstraTitle = 	"Dijkstra";
	String astarTitle = 	"A*";
	String jpsTitle = 		"Jump-Point Search";
	String[] titles = { dijkstraTitle, astarTitle, jpsTitle };
	
	String totalWeight = 	"Weight of the found path:";
	String nodesInPath = 	"Nodes in returned path:";
	String visitedNodes =	"Nodes visited during search:";
	String time = 			"Algorithm completed in (ms):";
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
	
	String copyDataIcon = "Copy"; // Couldn't find a proper Unicode icon :(
	String mapButtonText = "Load New Map";
	
	public MapTools(Node root, MapView map) {
		mv = map;
		stats = mv.getBenchmark();
		generateLayout();
		
		map.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			handleMapClick();
		});
	}
	
	/**
	 * When user clicks on Map, wait for results and draw it on the form.
	 */
	void handleMapClick() {
		clicks++;
		if (clicks == 1) {
			inputQueue.setText(nullQueueText2);
		} else {
			inputQueue.setText(String.format(inputQueueText, Arrays.toString(mv.getStart()),Arrays.toString(mv.getEnd())));
		}
		
		stats = mv.getBenchmark();
		for (int i = 0; i < statTexts.length; i++) {
			for (int j = 0; j < statTexts[0].length; j++) {
				statTexts[i][j].setText(stats[i][j]);
			}
		}
	}
	
	/**
	 * This method will generate the layout for the benchmarking data using labels.
	 * All children are laid out vertically starting with instructions, 
	 * then Benchmark data, then buttons.
	 */
	void generateLayout() {
		this.setPadding(new Insets(10));
		
		// Give instructions
		Label info = new Label(infoText);
		inputQueue = new Label(nullQueueText1);
		inputQueue.setPadding(new Insets(0, 0, 20, 0));
		this.getChildren().addAll(info, inputQueue);
		
		// Make forms
		int numForms = titles.length;
		for (int i = 0; i < numForms; i++) {
			// Make a new form for each pathfinder and stack containing nodes vertically
			VBox form = new VBox();
			form.setMinWidth(stageWidth);
			form.setMaxWidth(stageWidth);
			
			// Make a form title with a layer toggle check box
			CheckBox cb = makeTitle(i, titles[i]);
			form.getChildren().add(cb);
			
			// Make fields on which the benchmarking results are displayed
			form.getChildren().add(makeFields(i));
			
			this.getChildren().add(form);
		}
		
		// Button to copy benchmarking data to clipboard (and align it right with an HBox)
		HBox copyDataButton = new HBox();
		copyDataButton.setAlignment(Pos.BASELINE_RIGHT);
		copyDataButton.getChildren().add(copyDataButton());
		this.getChildren().add(copyDataButton);
		
		// Divider
		Region divider = new Region();
		VBox.setVgrow(divider, Priority.ALWAYS);
		this.getChildren().add(divider);
		
		// Button to load a new map including the event that triggers on action
		Button loadMapButton = loadMapButton();
		loadMapButton.setPrefWidth(stageWidth);
		this.getChildren().add(loadMapButton);
	}
	
	/**
	 * Creates a CheckBox which allows hiding of MapView's layers
	 * @param i 		Layer index to be hidden
	 * @param label		Button label
	 * @return
	 */
	CheckBox makeTitle(int i, String label) {
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
	
	/**
	 * Return a button that copies the benchmark data to clipboard.
	 * @return
	 */
	Button copyDataButton() {
		Button copyButton = new Button(copyDataIcon);
		copyButton.setOnAction(click -> {
			copyToClipboard();
		});
		return copyButton;
	}
	
	/**
	 * Returns a button that triggers 'copyToClipboard' on click.
	 * @return
	 */
	Button loadMapButton() {
		Button loadMapButton = new Button(mapButtonText);
		loadMapButton.setOnAction(click -> {
			Stage stage = (Stage) this.getScene().getWindow();
			Image img = ImageLoader.trigger(stage);
			mv.loadMap(img);
			inputQueue.setText(nullQueueText1);
			clicks = 0;
		});
		return loadMapButton;
	}
	
	/**
	 * Creates rows for each string in fieldTitles and provides a field for the benchmarking results.
	 * @param i		Index of formTitle
	 * @return
	 */
	VBox makeFields(int i) {
		int numFields = fieldTitles.length;
		VBox fields = new VBox();
		fields.setPadding(new Insets(0, 0, 10, 0));
		
		// Populate fields
		for (int j = 0; j < numFields; j++) {
			// Make an HBox
			HBox field = new HBox();
			field.setPadding(new Insets(1, 5, 1, 5));
			
			// Divide the HBox in left and right aligned labels divided by a region
			// Left Label
			Label left = new Label(fieldTitles[j]);
			// Divider
			Region center = new Region();
			HBox.setHgrow(center, Priority.ALWAYS);
			// Right label
			statTexts[i][j] = new Label(stats[i][j]);
			
			// Populate the form with the new fields and it in parent VBox fields
			field.getChildren().addAll(left, center, statTexts[i][j]);
			fields.getChildren().add(field);
		}
		
		return fields;
	}
	
	/**
	 * Copies the benchmarking data to clipboard in a format that can be pasted to a table.
	 */
	void copyToClipboard() {
		String data = "";
		data += String.format("Coordinates\t%s\t%s\n", Arrays.toString(mv.getStart()),Arrays.toString(mv.getEnd()));
		data += String.format("Pathfinder\t%s\t%s\t%s\n", dijkstraTitle, astarTitle, jpsTitle);
		for (int i = 0; i < 4; i++) {
			String row;
			switch (i) {
				case 0: row = "Weight";
					break;
				case 1: row = "Length";
					break;
				case 2: row = "Visited";
					break;
				default: row = "Time";
					break;
			}
			data += String.format("%s\t%s\t%s\t%s\n", row, statTexts[0][i].getText(), statTexts[1][i].getText(), statTexts[2][i].getText());
		}
		
		StringSelection stringSelection = new StringSelection(data);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
