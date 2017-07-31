/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaardn
 */
public class Board {
  private boolean[][] dataBoard;
  
  public Board(boolean[][] input) { // constructor creates instance of the class each generation.
    dataBoard = input;
  }
  
  public void Print() {
    for (int i = 0; i < dataBoard.length; i++) { //         for (boolean[] board1 : board) {
      String line = "";
      for (int j = 0; j < dataBoard[i].length; j++) {
        if (dataBoard[i][j]) {
          line += "#";
        } else {
          line += "_";
        }
      }
      System.out.println(line);
    }
  }
  // how: take traditional JavaMod and add the result to mod, and return.
  // use: every time attempt to access cell, if cell is off the edge, we need to map it to other edge.
  private int PythonMod(int baseInput, int mod) {
    int tempJavaMod = baseInput % mod;
    if (tempJavaMod < 0) {
      return tempJavaMod + mod;
    } else {
      return tempJavaMod;
    }
  }
  
  public Board NewEdgedGeneration() { // each cell is now dataBoard[row][column];
    int rows = dataBoard.length; // number of rows in the entire board.
    int columns = dataBoard[0].length; // number of columns in the entire board. is equiv to dataBoard[row].length.
    boolean[][] nextGen = new boolean[rows][columns]; // creates another array of arrays with the same dim as our 5x5 dataBoard. 
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) { // clockwise conditionals:
        int livingNeighbors = 0;
        //System.out.println("row is " +row+ "; column is " +column+ ". Neighbors: " +livingNeighbors);
        if (row > 0 && column > 0 && dataBoard[row - 1][column - 1] == true) { // top left, 1 above and 1 left on row axis.
          livingNeighbors += 1;
        }
        if (row > 0 && dataBoard[row - 1][column]) { //top center; 1 above selected cell on row axis.
          livingNeighbors += 1;
        }
        if (row > 0 && column < columns - 1 && dataBoard[row - 1][column + 1] == true) { // top right.
          livingNeighbors += 1;
        }
        if (column < columns - 1 && dataBoard[row][column + 1] == true) { // middle right. if in 4th column, we don't want to look at the 5th on the end.
          livingNeighbors += 1;
        }
        // testing down below...
        if (row < rows - 1 && column < columns - 1 && dataBoard[row + 1][column + 1] == true) { // bottom right. 
          livingNeighbors += 1;
        }
        if (row < rows - 1 && dataBoard[row + 1][column] == true) { // bottom center.
          livingNeighbors += 1;
        }
        if (row < rows - 1 && column > 0 && dataBoard[row + 1][column - 1] == true) { // bottom left
          livingNeighbors += 1;
        }
        if (column > 0 && dataBoard[row][column - 1] == true) { //middle left.
          livingNeighbors += 1;
        }
        // if livingNeighbors is 2 || 3, in the nextGen at the current [row][column], set to true.
        if (livingNeighbors == 2 || livingNeighbors == 3) {
          nextGen[row][column] = true;
        }
       
      }
    }
    return new Board(nextGen);
  }

  /*      TODO rows:
        wrap around board to allow left neighbor to become right neighbor
            if row-1 == -1 then replace row-1 with rows-1/dataBoard.length-1
        wrap around from right neighbor to become left
            if row+1 == rows/dataBoard.length then replace row+1 with 0 
            
            TODO columns:
        if column-1 == -1 then replace column-1 with columns-1/dataBoard[0].length
        if column+1 == columns/dataBoard[0].length then replace column+1 with 0
            
   */


  public Board NewTorusWrappingGeneration() { // dataBoard enables us to look at the cell in question. boolean cell = dataBoard[row][column];
    int rows = dataBoard.length; // number of rows in the entire board.
    int columns = dataBoard[0].length; // number of columns in the entire board. is equiv to dataBoard[row].length.
    boolean[][] nextGen = new boolean[rows][columns]; // creates another array of arrays with the same dim as our 5x5 dataBoard. 
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) { // clockwise conditionals:
        int livingNeighbors = 0;
        /* TODO:
                need to change the if statements so that we no longer bother checking these row-1/column-1 conditions 
                because we're giong to wrap around the edge of the board and not try to find something on the end. to do this,
                we'll make a function wrapArounder, which will take 2 integers and return and integer. the first int is test value,
                second int is length of the column or row. behavior will be: if test value is -1 return the length-1.
                if the test value = length then return 0, otherwise/else return test value given.
      
         */

        //System.out.println("row is " +row+ "; column is " +column+ ". Neighbors: " +livingNeighbors);
        if (dataBoard[PythonMod(row - 1, rows)][PythonMod(column - 1, columns)] == true) { // top left, 1 above and 1 left on row axis.
          livingNeighbors += 1;
        }
        if (dataBoard[PythonMod(row - 1, rows)][column]) { //top center; 1 above selected cell on row axis.
          livingNeighbors += 1;
        }
        if (dataBoard[PythonMod(row - 1, rows)][PythonMod(column + 1, columns)] == true) { // top right.
          livingNeighbors += 1;
        }
        if (dataBoard[row][PythonMod(column + 1, columns)] == true) { // middle right. if in 4th column, we don't want to look at the 5th on the end.
          livingNeighbors += 1;
        }
        // testing down below...
        if (dataBoard[PythonMod(row + 1, rows)][PythonMod(column + 1, columns)] == true) { // bottom right. 
          livingNeighbors += 1;
        }
        if (dataBoard[PythonMod(row + 1, rows)][column] == true) { // bottom center.
          livingNeighbors += 1;
        }
        if (dataBoard[PythonMod(row + 1, rows)][PythonMod(column - 1, columns)] == true) { // bottom left
          livingNeighbors += 1;
        }
        if (dataBoard[row][PythonMod(column - 1, columns)] == true) { //middle left.
          livingNeighbors += 1;
        }
        // if livingNeighbors is 2 || 3, in the nextGen at the current [row][column], set to true.
        if (livingNeighbors == 2 || livingNeighbors == 3) {
          nextGen[row][column] = true;
        }

      }
    }
    return new Board(nextGen);
  }
}
