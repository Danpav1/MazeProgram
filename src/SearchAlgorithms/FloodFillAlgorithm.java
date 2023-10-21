package SearchAlgorithms;
import java.util.EmptyStackException;

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
        this(m, 0, 0, 0, 0);
    }

    /*
     * "overloaded" constructor for custom start and end points
     * 
     * @param int[][] m, int sX, int sY, int eX, int eY
     */
    public FloodFillAlgorithm(int[][] m, int sX, int sY, int eX, int eY) {
      super();
      this.maze = m;

      //checks if the start / end coords are within bounds, throws exception if they arent
      startEndPointValidation(sX, sY, eX, eY);

      //saves the start & end points
      this.startPosition[0] = 0;
      this.startPosition[1] = 0;
      this.endPosition[0] = maze.length - 1;
      this.endPosition[1] = maze[0].length - 1;

      //creates a 2d string array of the same size as the int 2d array
      this.solvedMaze = new String[maze.length][maze[0].length];

      solveMaze();
  }

    /*
     * method that initiates the solving of the maze
     * @param int x, int y (starting coords)
     */
    public void solveMaze() {
        System.out.println("\n\t" + "Unsolved Maze: ");
        printMaze();

        int x = startPosition[0];
        int y = startPosition[1];

        traverse(x, y);
    } 

    /*
     * the method that checks surrounding nodes and moves to
     *  surrounding nodes if they meet certain criteria
     * @param int x, int y
     * @return boolean solved
     */
    private boolean traverse(int x, int y) {
        //method variables
        boolean solved = false;

        //sets the inputted coords to TRIED (2)
        setToTried(x, y);

        //checks if the inputted coords are the end coords
        if (isEnd(x, y))
        {
            solved = true;
            createPath(x, y);
        }

        /*
         * if these coords are not the end coords
         */
        if (!solved)
        {
            //try up
            if (validPosition(x - 1, y))
            {
                directions.push("down");
                traverse(x - 1, y);
            }

            //try left
            else if (validPosition(x, y - 1))
            {
                directions.push("right");
                traverse(x, y - 1);
            }

            //try right
            else if (validPosition(x, y + 1))
            {
                directions.push("left");
                traverse(x, y + 1);
            }

            //try down
            else if (validPosition(x + 1, y))
            {
                directions.push("up");
                traverse(x + 1, y);
            }

            /*
             * since all four directions are not valid (deadend)
             *  we iniatiate the backtrack algorithm
             */
            else
            {
                backTrack(x, y);
            }
        }
        return solved;
    }

    /*
     * backtracking algorithm
     * @param int x, int y
     */
    private void backTrack(int x, int y) {
        //method variable(s)
        String directionToTake = "";

        /*
         * checks surrounding nodes (checks for deadend)
         */
        //check above
         if (validPosition(x - 1, y))
         {
             directions.push("down");
             traverse(x - 1, y);
         }
        //check left
         else if (validPosition(x, y - 1))
         {
             directions.push("right");
             traverse(x, y - 1);
         }
        //check right
         else if (validPosition(x, y + 1))
         {
             directions.push("left");
             traverse(x, y + 1);
         }
        //check down
         else if (validPosition(x + 1, y))
         {
             directions.push("up");
             traverse(x + 1, y);
         }
         /*
          * if none of the positions are valid (we are at a deadend) 
          *   we pop the directions stack and move via that command.
          */
         else
        {
            //exception handling for when the maze has no path
            try
            {
                directionToTake = directions.pop();
            }
            catch(EmptyStackException e)
            {
                System.out.println("\n" + ANSI_RED + "EmptyStackException - The maze has no path." + ANSI_RESET);
                System.exit(0);
            }  

            //takes stack directions
            if (directionToTake == "up")
            {
                backTrack(x - 1, y);

            }
            else if (directionToTake == "left")
            {
                backTrack(x, y - 1);

            }
            else if (directionToTake == "right")
            {
                backTrack(x, y + 1);
                            
            }
            else if (directionToTake == "down")
            {
                backTrack(x + 1, y);
            }
        }
    }
}
