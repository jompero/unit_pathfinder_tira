package unit_pathfinder_tira;

import GUI.MapTools;
import GUI.MapView;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("unit_pathfinder_tira");

		// Initialise root Node onto which map and tools are displayed
		BorderPane root = new BorderPane();
		
		// Setup a scene for the map with a default image
		Image img = new Image(getClass().getClassLoader().getResourceAsStream("maps/wc3maps512-png/battleground.png"), 512, 512, false, false);
		MapView map = new MapView(img);
		
		// Generate tools scene for the map
		MapTools tools = new MapTools(root, map);
		
		root.setCenter(map);
		root.setRight(tools);
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
