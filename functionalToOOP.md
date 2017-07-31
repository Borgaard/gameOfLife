  Steps:
 *      Create a Board class, which includes:
 * -x Defining the data members (such as boolean[][] dataBoard which holds each individual tile in nestOfBools)
 * -x Define the constructor which sets up internal data (instance of Board class). This uses the data members and input given.
 * -x Move every method except main from Life.java into Board.java because those methods are concerned with the context of a Board, but are not yet part of the class.
 * -x Remove static attribute of all methods because we want them to be available on all instances. Static makes a method available to the class but not an instance. This worked for functional coding, but doesn't for OOP.
 * -x Remove parameters of functions that took boolean[][] and change return type to Board class because we already have the data as a data member and want to return another instance of the board rather than raw data which was the boolean[][]. The raw data has just data, while the Board class has data & functionality.
 * -x Fix the types in Life.java by using the Board class and its methods in main of Life.java
 * 
