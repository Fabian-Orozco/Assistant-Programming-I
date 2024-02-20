package Consultas.RepasoExamen;

import java.util.List;

public class Solution {
	public static void main(String[] args) {
		System.out.println("\nInicio\n");

		int[] edades = new int[3];
		
		edades[0] = 1;
		edades[1] = 2;
		edades[2] = 3;
		
		// System.out.println(edades[2]);
		// System.out.println(edades.length);
		
		int[][] matriz = new int[3][3];
		matriz[0][0] = 4;
		// System.out.println(matriz[0][0]);
		// System.out.println(matriz[1][1]);

		String[] nombres = new String[3];

		// nombres[0] = "Palita";
		// System.out.println(nombres[0]);

		// recorrer arreglo
		for (int index = 0; index < nombres.length; index++) {
			System.out.print(edades[index]);
		}

		// recorrer matriz
		int counter = 1;
		System.out.println("\nmatriz: ");
		for (int row = 0; row < matriz.length; row++) {
			System.out.println();
			for (int col = 0; col < matriz[row].length; col++) {
				matriz[row][col] = counter++;
				System.out.print(matriz[row][col]);
			}
		}
		
		int numA = 3;
		int numB = 5;
		// invocación de un metodo
		System.out.println("\nSuma: " + suma(numA, numB));
		System.out.println("\nFinal\n");
	}

	// declarar un metodo
	private static int suma(int numeroA, int numeroB) {
		// block code 
		return numeroA + numeroB;
	}
}
