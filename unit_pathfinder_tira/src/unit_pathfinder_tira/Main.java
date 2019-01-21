package unit_pathfinder_tira;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// Basic input output
		Dijkstra d = new Dijkstra();
		int[][] matrix = {	{1, 2, 3, 1, 1, 1}, 
							{5, 1, 3, 1, 1, 1}, 
							{3, 3, 3, 1, 1, 1}, 
							{3, 3, 3, 1, 1, 1}, 
							{3, 3, 3, 1, 1, 1}, 
							{3, 3, 3, 1, 1, 1}};
		Graph g = new Graph(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter X coordinate of starting pos:");
		int x1 = s.nextInt();
		System.out.println("Enter Y coordinate of starting pos:");
		int y1 = s.nextInt();
		System.out.println("Enter X coordinate of ending pos:");
		int x2 = s.nextInt();
		System.out.println("Enter Y coordinate of ending pos:");
		int y2 = s.nextInt();
		
		s.close();
		
		int[] start = {x1, y1};
		int[] end = {x2, y2};
		ArrayList<int[]> result = d.search(g, start, end);
		for (int[] i : result) {
			System.out.println(i[0] + " " + i[1]);
		}
	}

}
