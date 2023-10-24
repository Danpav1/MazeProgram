package SearchAlgorithms;

/*
* child class of maze, houses the Flood Fill search algorithm
* @author Gooomba
*/

public class FloodFillAlgorithm extends Maze
{
  /*
  * default constructor for default start and end points 
  *  (top left, bottom right)
  * @param int[][] m
  */
  public FloodFillAlgorithm(int[][] m)
  {
    this(m, 0, 0, m.length - 1, m[0].length - 1);
  }
  
  /*
  * "overloaded" constructor for custom start and end points
  * 
  * @param int[][] m, int sRow, int sCol, int eRow, int eCol
  */
  public FloodFillAlgorithm(int[][] m, int sRow, int sCol, int eRow, int eCol) {
    super();
    this.maze = m;
    
    //checks if the start / end coords are within bounds, throws exception if they arent
    startEndPointValidation(sRow, sCol, eRow, eCol);
    
    //saves the start & end points
    this.startPosition[0] = 0;
    this.startPosition[1] = 0;
    this.endPosition[0] = maze.length - 1;
    this.endPosition[1] = maze[0].length - 1;
    
    //creates a 2d string array of the same size as the int 2d array
    this.solvedMaze = new String[maze.length][maze[0].length];
    
    //solves the maze
    solveMaze();
  }
  
  /*
  * method that initiates the solving of the maze
  * @param int row, int col (starting coords)
  */
  public void solveMaze() {
    System.out.println("\n\t" + "Unsolved Maze: ");
    printMaze();
    
    int row = startPosition[0];
    int col = startPosition[1];
    
    traverse(row, col);
  } 
  
  /*
  * the method that checks surrounding nodes and moves to
  *  surrounding nodes if they meet certain criteria
  * @param int row, int y
  * @return boolean solved
  */
  private boolean traverse(int row, int col) {
    
    //sets the inputted coords to TRIED (2)
    setToTried(row, col);
    
    //checks if the inputted coords are the end coords
    if (isEnd(row, col))
    {
      this.solved = true;
      createPath(row, col);
      return this.solved;
    }
    else {
      
      //try up
      if (validPosition(row - 1, col))
      {
        directions.push("down");
        traverse(row - 1, col);
      }
      
      //try left
      else if (validPosition(row, col - 1))
      {
        directions.push("right");
        traverse(row, col - 1);
      }
      
      //try right
      else if (validPosition(row, col + 1))
      {
        directions.push("left");
        traverse(row, col + 1);
      }
      
      //try down
      else if (validPosition(row + 1, col))
      {
        directions.push("up");
        traverse(row + 1, col);
      }
      
      /*
      * since all four directions are not valid (deadend)
      *  we iniatiate the backtrack algorithm
      */
      else
      {
        backTrack(row, col);
      }
    }
    return this.solved;
  }
  
  /*
  * backtracking algorithm
  * @param int row, int col
  */
  private void backTrack(int row, int col) {
    //method variable(s)
    String directionToTake = "";

    //checks the surrounding nodes to take a new path
    if (validPosition(row + 1, col)) { //down
      directions.push("up");
      traverse(row + 1, col);
    } else if (validPosition(row - 1, col)) { //up
      directions.push("down");
      traverse(row - 1, col);
    } else if (validPosition(row, col + 1)) { //right
      directions.push("left");
      traverse(row, col + 1);
    } else if (validPosition(row, col - 1)) { //left
      directions.push("right");
      traverse(row, col - 1);
    }
    /*
     * I have no idea why but if we dont have this if statement the
     *  createPath goes rogue and just breaks everything. Debugging
     *  also just spazzes out. 
     */
    if (this.solved) {
      return;
    }

    //makes sure the maze has a path
    if (directions.isEmpty() == true) {
      System.out.println("\nMaze has no path");
      System.exit(1);
    }
    else {
      directionToTake = directions.pop();
    }

    //takes stack directions
    if (directionToTake == "up")
    {
      backTrack(row - 1, col);
    }
    else if (directionToTake == "left")
    {
      backTrack(row, col - 1);
    }
    else if (directionToTake == "right")
    {
      backTrack(row, col + 1);
    }
    else if (directionToTake == "down")
    {
      backTrack(row + 1, col);
    }
  }
}
