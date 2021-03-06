package graph;

import javafx.scene.image.*;

/**
 * BitmapToMatrix is a tool to convert images to integer matrixes based on the image grayscale values.
 * @author danijompero
 *
 */
public class BitmapToMatrix {

	/**
	 * Convert an image into a matrix that can then be used in a graph. The method
	 * will check the greyscale value of each pixel and assign it to the coordinate.
	 * Note that black will be 0 that will be considered out of bounds.
	 * 
	 * @param map	Image to be converted
	 * @return		Integer matrix where the value represents the grayscale value of the coordinate
	 */
	public static int[][] convert(Image map) {
		int x = (int) map.getHeight();
		int y = (int) map.getWidth();
		int[][] matrix = new int[x][y];

		PixelReader pr = map.getPixelReader();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = pr.getArgb(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);
				matrix[i][j] = (r + g + b) / 3;
			}
		}

		return matrix;
	}
}
