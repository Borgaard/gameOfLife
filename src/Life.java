/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * HOW TO MAKE THIS OOPIER:
 * Overview:
 *  Current implementation is not OOP because it does not encapsulate,
 * does not use classes nor methods, and doesn't use instance objects.
 *  Steps:
 *      Create a Board class, which includes:
 *          - Defining the data members (such as boolean[][] dataBoard which holds each individual tile in nestOfBools)
 *          - Define the constructor which sets up internal data (instance of Board class). This uses the data members and input given.
 *          - Move every method except main from Life.java into Board.java because those methods are concerned with the context of a Board,              but are not yet part of the class.
 *          - Remove static attribute of all methods because we want them to be available on all instances. Static makes a method available              to the class but not an instance. This worked for functional coding, but doesn't for OOP.
 *          - Remove parameters of functions that took boolean[][] and change return type to Board class because we already have the data                as a data member and want to return another instance of the board rather than raw data which was the boolean[][]. The raw                  data has just data, while the Board class has data & functionality.
 *          - Fix the types in Life.java by using the Board class and its methods in main of Life.java
 * 
 *  Advantage of OOP for this program:
 *      Will give us a more concrete implementation of the Life board and is
 *      more specialized (will revisit later).
 * Disadvantages of OOP:
 *      Because you have a specific data structure (nestOfBools or Board?) you
 *      sacrifice the ability to use that data in other contexts (example: some
 *      other method that takes boolean[][] can't use it because Board abstracts
 *      away how to use it in private methods and data.).
 * Advantages of functional:
 *      Flexibility of data, such as anything that allows input of boolean[][]
 *      could be used in a future program because it is public.
 * Disadvantages of functional, and why we transitioned this program to OOP:
 *      1: Little less organized because it's a bunch of functions, and it's
 *         easier to add unneeded things; there isn't a necessarily an
 *         obvious place to put them. These functions might have nothing to do with
 *         our boolean[][].
 *      2: Because we're using a generic data structure (boolean[][]), we can't bundle (functions with methods & data attached) specific
 *         behavior together nicely.
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
    Board initialInstance = new Board(nestOfBools);
//        Life.PrintBoard(nestOfBools);
//        boolean[][] nextGenOfBools = Life.NewBunnies(nestOfBools);
//        System.out.println(".   .   .   .   .");
//        Life.PrintBoard(nextGenOfBools);
    for (int i = 0; i < 10; i++) { //change back to 20 at some point D: 
      initialInstance.Print();
      initialInstance = initialInstance.NewTorusWrappingGeneration(); //overwrites previous board
      System.out.println(".   .   .   .   . current gen: " + i);

    }
  }
}
