package test_package;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.image.*;

import org.junit.jupiter.api.Test;

import graph.BitmapToMatrix;

class BitmapToMatrix_test {

	@Test
	void test_black() {
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		Image img = new Image(getClass().getResourceAsStream("debugblack.bmp"));

		int[][] result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j])
					fail("Image 'debugblack.bmp' was not converted correctly.");
			}
		}
	}

	@Test
	void test_direction() {
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 255, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 255, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 255, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 255, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 255, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 255, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 255, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 255, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		Image img = new Image(getClass().getResourceAsStream("debugdirection.bmp"));

		int[][] result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j])
					fail("Image 'debugdirection.bmp' was not converted correctly.");
			}
		}
	}

	@Test
	void test_square() {
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
							{ 0, 9, 9, 9, 9, 9, 9, 9, 9, 0 },
							{ 0, 9, 8, 8, 8, 8, 8, 8, 9, 0 }, 
							{ 0, 9, 8, 7, 7, 7, 7, 8, 9, 0 }, 
							{ 0, 9, 8, 7, 7, 7, 7, 8, 9, 0 },
							{ 0, 9, 8, 7, 7, 7, 7, 8, 9, 0 }, 
							{ 0, 9, 8, 7, 7, 7, 7, 8, 9, 0 }, 
							{ 0, 9, 8, 8, 8, 8, 8, 8, 9, 0 },
							{ 0, 9, 9, 9, 9, 9, 9, 9, 9, 0 }, 
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		Image img = new Image(getClass().getResourceAsStream("debugsquare.bmp"));

		int[][] result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j])
					fail("Image 'debugsquare.bmp' was not converted correctly.");
			}
		}
	}
}
