/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author borgaard
 */
public class Life {
    public static void main(String[] args) {
        boolean[][] nestOfBools = {
            {false, false, false, false, false},
            {false, false, false, true, true},
            {false, false, true, false, true},
            {false, false, false, false, true},
            {false, false, false, false, false}
        };
//        Life.PrintBoard(nestOfBools);
//        boolean[][] nextGenOfBools = Life.NewBunnies(nestOfBools);
//        System.out.println(".   .   .   .   .");
//        Life.PrintBoard(nextGenOfBools);
        for (int i = 0; i < 20; i++) {
            Life.PrintBoard(nestOfBools);
            nestOfBools = Life.NewBunnies(nestOfBools);
            System.out.println(".   .   .   .   . current gen: " +i);

        }
        int modExample = -1 % 12;
        System.out.println(modExample);
    }
    public static void PrintBoard(boolean[][] board) {
      for(int i = 0; i < board.length; i++) {
        String line = "";
        for (int j = 0; j < board[i].length; j++) {
          if(board[i][j]) {
            line += "#";
          } else {
            line += "_";
          }
        }
        System.out.println(line);
      }
    }
    public static boolean[][] NewBunnies(boolean[][] currentBoard) { // currentBoard enables us to look at the cell in question. boolean cell = currentBoard[row][column];
        int rows = currentBoard.length; // number of rows in the entire board.
        int columns = currentBoard[0].length; // number of columns in the entire board. is equiv to currentBoard[row].length.
        boolean[][] nextGen = new boolean[rows][columns]; // creates another array of arrays with the same dim as our 5x5 currentBoard. 
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) { // clockwise conditionals:
                int livingNeighbors = 0;
                //System.out.println("row is " +row+ "; column is " +column+ ". Neighbors: " +livingNeighbors);
                if(row > 0 && column > 0 && currentBoard[row-1][column-1] == true){ // top left, 1 above and 1 left on row axis.
                    livingNeighbors += 1;
                }
                if(row > 0 && currentBoard[row-1][column]){ //top center; 1 above selected cell on row axis.
                    livingNeighbors += 1;
                }
                if(row > 0 && column < columns -1 && currentBoard[row-1][column+1] == true){ // top right.
                    livingNeighbors += 1;
                }
                if(column < columns-1 && currentBoard[row][column+1] == true){ // middle right. if in 4th column, we don't want to look at the 5th on the end.
                    livingNeighbors += 1;
                }
                // testing down below...
                if(row < rows-1 && column < columns-1 && currentBoard[row+1][column+1] == true){ // bottom right. 
                    livingNeighbors += 1;
                } 
                if(row < rows-1 && currentBoard[row+1][column] == true){ // bottom center.
                    livingNeighbors += 1;
                }
                if(row < rows -1 && column > 0 && currentBoard[row+1][column-1] == true){ // bottom left
                    livingNeighbors += 1;
                }
                if(column > 0 && currentBoard[row][column-1] == true){ //middle left.
                    livingNeighbors += 1;
                }
                // if livingNeighbors is 2 || 3, in the nextGen at the current [row][column], set to true.
                if(livingNeighbors == 2 || livingNeighbors == 3){
                    nextGen[row][column] = true;
                }
                /*we need to check the following coordinates in addition
                    x[row-1][column+1] top right row > 0
                    x[row][column+1], middle right
                    x[row+1][column+1] bottom right 
                    x[row+1][column] bottom center
                    x[row][column-1] bottom left
                    x[row][column-1] middle left
                    and/or make sure they are not off the edge of the board
                after all that if livingNeighbors is 2 or 3 then set nextGen[row][column] to true else false
                                    row -1 should not be above the board. 
                                    row > 0
                {      column = 0  column = 1  column = 2  column = 3  column = 4; each cell.
     each row;  row = 0 {false, false, false, false, false},
                row = 1 {false, false, false, true, true},     column + 1 should not be off the board
    column > 0  row = 2 {false, false, true, false, true},     column < currentBoard[row].length
                row = 3 {false, false, false, false, true},
                row = 4 {false, false, false, false, false}
                };
                                    row +1 should not be off the board
                                    row < currentBoard.length
                
                */
            }
        }
        return nextGen;
    }
    
    /*      TODO rows:
        wrap around board to allow left neighbor to become right neighbor
            if row-1 == -1 then replace row-1 with rows-1/currentBoard.length-1
        wrap around from right neighbor to become left
            if row+1 == rows/currentBoard.length then replace row+1 with 0 
            
            TODO columns:
        if column-1 == -1 then replace column-1 with columns-1/currentBoard[0].length
        if column+1 == columns/currentBoard[0].length then replace column+1 with 0
            
    */
    public static boolean[][] NewTorusDonutBunnies(boolean[][] currentBoard) { // currentBoard enables us to look at the cell in question. boolean cell = currentBoard[row][column];
        int rows = currentBoard.length; // number of rows in the entire board.
        int columns = currentBoard[0].length; // number of columns in the entire board. is equiv to currentBoard[row].length.
        boolean[][] nextGen = new boolean[rows][columns]; // creates another array of arrays with the same dim as our 5x5 currentBoard. 
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
                if(row > 0 && column > 0 && currentBoard[row-1][column-1] == true){ // top left, 1 above and 1 left on row axis.
                    livingNeighbors += 1;
                }
                if(row > 0 && currentBoard[row-1][column]){ //top center; 1 above selected cell on row axis.
                    livingNeighbors += 1;
                }
                if(row > 0 && column < columns -1 && currentBoard[row-1][column+1] == true){ // top right.
                    livingNeighbors += 1;
                }
                if(column < columns-1 && currentBoard[row][column+1] == true){ // middle right. if in 4th column, we don't want to look at the 5th on the end.
                    livingNeighbors += 1;
                }
                // testing down below...
                if(row < rows-1 && column < columns-1 && currentBoard[row+1][column+1] == true){ // bottom right. 
                    livingNeighbors += 1;
                } 
                if(row < rows-1 && currentBoard[row+1][column] == true){ // bottom center.
                    livingNeighbors += 1;
                }
                if(row < rows -1 && column > 0 && currentBoard[row+1][column-1] == true){ // bottom left
                    livingNeighbors += 1;
                }
                if(column > 0 && currentBoard[row][column-1] == true){ //middle left.
                    livingNeighbors += 1;
                }
                // if livingNeighbors is 2 || 3, in the nextGen at the current [row][column], set to true.
                if(livingNeighbors == 2 || livingNeighbors == 3){
                    nextGen[row][column] = true;
                }
  
            }
        }
        return nextGen;
    }
    
}
