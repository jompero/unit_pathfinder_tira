package GUI;

import java.awt.Desktop;
import java.io.File;

import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.*;

public class ImageLoader {

	Desktop desktop = Desktop.getDesktop();

	public static Image trigger(Stage stage) {
		Image img = null;

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load map");
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			img = new Image(file.toURI().toString(), 512, 512, false, false);
		}

		return img;
	}

}
