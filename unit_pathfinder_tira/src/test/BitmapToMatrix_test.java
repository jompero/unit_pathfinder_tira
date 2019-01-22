package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import unit_pathfinder_tira.BitmapToMatrix;

class BitmapToMatrix_test {

	@Test
	void test_black() {
		int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("debugblack.bmp"));
		} catch (IOException e) {
			fail("Image 'debugblack.bmp' not found");
			e.printStackTrace();
		}

		int[][]result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j]) fail("Image 'debugblack.bmp' was not converted correctly.");
			}
		}
	}

	@Test
	void test_direction() {
		int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 255, 0, 255, 0, 255, 0, 255, 0, 0},
						{0, 0, 255, 0, 255, 0, 255, 0, 255, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("debugdirection.bmp"));
		} catch (IOException e) {
			fail("Image 'debugdirection.bmp' not found");
			e.printStackTrace();
		}

		int[][]result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j]) fail("Image 'debugdirection.bmp' was not converted correctly.");
			}
		}
	}
	
	@Test
	void test_square() {
		int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 9, 9, 9, 9, 9, 9, 9, 9, 0},
						{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
						{0, 9, 8, 7, 7, 7, 7, 8, 9, 0},
						{0, 9, 8, 7, 7, 7, 7, 8, 9, 0},
						{0, 9, 8, 7, 7, 7, 7, 8, 9, 0},
						{0, 9, 8, 7, 7, 7, 7, 8, 9, 0},
						{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
						{0, 9, 9, 9, 9, 9, 9, 9, 9, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("debugsquare.bmp"));
		} catch (IOException e) {
			fail("Image 'debugsquare.bmp' not found");
			e.printStackTrace();
		}

		int[][]result = BitmapToMatrix.convert(img);
		if (result.length != matrix.length && result[0].length != matrix[0].length) {
			fail("Matrix is wrong length");
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != result[i][j]) fail("Image 'debugsquare.bmp' was not converted correctly.");
			}
		}
	}
}
