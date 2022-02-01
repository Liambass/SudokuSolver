// Brute force recursion based sudoku solver

package com.liambass.sudokusolver;

public class SudokuSolver {

	private int[][] grid = {{7, 0, 2, 0, 5, 0, 6, 0, 0},
	        				{0, 0, 0, 0, 0, 3, 0, 0, 0},
	        				{1, 0, 0, 0, 0, 9, 5, 0, 0},
	        				{8, 0, 0, 0, 0, 0, 0, 9, 0},
	        				{0, 4, 3, 0, 0, 0, 7, 5, 0},
	        				{0, 9, 0, 0, 0, 0, 0, 0, 8},
	        				{0, 0, 9, 7, 0, 0, 0, 0, 5},
	        				{0, 0, 0, 2, 0, 0, 0, 0, 0},
	        				{0, 0, 7, 0, 4, 0, 2, 0, 3}}; 

	private int gridSize = 9;
	private int regionRows = 3;
	private int regionCols = 3;

	public SudokuSolver() { 
	}
	
	public SudokuSolver(int[][] grid) throws Exception {
		this.grid = grid;
		gridSize = grid.length;
		regionRows = (int) Math.sqrt(gridSize);
		regionCols = (int) Math.sqrt(gridSize);
		if (regionRows * regionCols != gridSize) 
			throw new Exception("grid size is not a square number! "
					+ "You must manually define region size");
	}
	
	public SudokuSolver(int[][] grid, int regionRows, int regionCols) throws Exception {
		this.grid = grid;
		gridSize = grid.length;
		if (regionRows * regionCols != gridSize) 
			throw new Exception("Region size does not equal grid size!");
		this.regionRows = regionRows;
		this.regionCols = regionCols;
	}

	public boolean validInRow(int row, int num) {
		for (int i = 0; i < gridSize; i++) {
			if (grid[row][i] == num)
				return false;
		}
		return true;
	}
	
	public boolean validInCol(int col, int num) {
		for (int i = 0; i < gridSize; i++) {
			if (grid[i][col] == num)
				return false;
		}
		return true;
	}
	
	public boolean validInRegion(int row, int col, int num) {
		int regionRow = row - (row % regionRows);
		int regionCol = col - (col % regionCols);
		
		for (int i = regionRow; i < regionRow + regionRows; i++) {
			for (int j = regionCol; j < regionCol + regionCols; j++) {
				if (grid[i][j] == num) return false;
			}
		}
		return true;
	}
	
	public boolean validInCell(int row, int col, int num) {
		return (validInRow(row, num) && validInCol(col, num) 
				&& validInRegion(row, col, num));
		}
	
	public void printGrid() {
		for (int i = 0; i < gridSize; i++) {
			if (i % regionRows == 0 && i != 0) printHorizontalSeparator();
			for (int j = 0; j < gridSize; j++) {
				if (j % regionCols == 0 && j != 0) System.out.print("|");
				if (grid[i][j] == 0) System.out.print(" ");
				else if (grid[i][j] > 9) 
					System.out.print((char)(grid[i][j] + 55));
				else System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printHorizontalSeparator() {
		for (int i = 0; i < gridSize; i++) {
			if (i % regionCols == 0 && i != 0) System.out.print("+");
			System.out.print("-");
		}
		System.out.println();		
	}
	
	public boolean solve() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (grid[i][j] == 0) {
					for (int num = 1; num <= gridSize; num++) {
						if (validInCell(i, j, num)) {
							grid[i][j] = num;
							if (solve()) return true;
							else grid[i][j] = 0;
						}
					}
					return false;
				}				
			}
		}
		return true;
	}
	
}
