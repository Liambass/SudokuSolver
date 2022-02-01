package com.liambass.sudokusolver;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		int[][] grid = {{0,0,3,0,1,0},
						{5,6,3,3,2,0},
						{0,5,4,2,0,3},
						{2,0,6,4,5,0},
						{0,1,2,0,4,5},
						{0,4,0,1,0,0}};
		
		
		SudokuSolver game = new SudokuSolver(grid, 2, 3);
		
		game.printGrid();
		game.solve();
		game.printGrid();

	}

}
