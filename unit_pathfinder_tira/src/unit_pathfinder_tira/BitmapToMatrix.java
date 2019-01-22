package unit_pathfinder_tira;

import java.awt.image.BufferedImage;

/**
 * @author danijompero
 *
 */
public class BitmapToMatrix {
	
	
	/**
	 * Convert an image into a matrix that can then be used in a graph. 
	 * The method will check the greyscale value of each pixel and assign it to the coordinate.
	 * Note that black will be 0 that will be considered out of bounds.
	 * @param img
	 * @return
	 */
	public static int[][] convert(BufferedImage map) {
		int x = map.getHeight();
		int y = map.getWidth();
		int[][] matrix = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = map.getRGB(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);
				matrix[j][i] = (r + g + b) / 3;
			}
		}
		
		return matrix;
	}
}
