package Consultas.RepasoExamen;

import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		run();
	}

	private void run() {
		int n = 3;
		int tamanoBloque = n*n;
		int[][] sudoku = new int[tamanoBloque][tamanoBloque];

		for (int row = 0; row < tamanoBloque; row++) {
			for (int col = 0; col < tamanoBloque; col++) {
				if (input.hasNextInt()) {
					sudoku[row][col] = input.nextInt();
				} else {
					sudoku[row][col] = 0;
					input.next();
				}
			}
		}
		revisarFila(sudoku, tamanoBloque);
	}

	private void revisarFila(int[][] sudoku, int tamanoBloque) {
		for (int row = 0; row < tamanoBloque; row++) {
			int[] digits = new int[tamanoBloque];
			for (int col = 0; col < tamanoBloque; col++) {
				if (sudoku[row][col] > 0) {
					
				}
			}
		}
	}
}
