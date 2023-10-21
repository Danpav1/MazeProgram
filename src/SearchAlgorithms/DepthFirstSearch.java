package SearchAlgorithms;
import java.util.EmptyStackException;

/*
 * child class of maze that houses the Depth-First search algorithm
 * @author Gooomba
 */

public class DepthFirstSearch extends Maze
{
    // instance variable(s)
    private String currentDirection = "";
    private boolean solved = false;

    /*
     * default constructor for default start and end points
     * (top left, bottom right)
     * 
     * @param int[][] m
     */
    public DepthFirstSearch(int[][] m)
    {
      this(m, 0, 0, 0, 0);
    }

    /*
     * "overloaded" constructor for custom start and end points
     * 
     * @param int[][] m, int sX, int sY, int eX, int eY
     */
    public DepthFirstSearch(int[][] m, int sX, int sY, int eX, int eY) {
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
    }

    /*
     * method that initiates the solving of the maze
     * 
     * @param int x, int y (starting coords)
     */
    public void solveMaze()
    {
        System.out.println("\n\t" + "Unsolved Maze: ");
        printMaze();

        int x = startPosition[0];
        int y = startPosition[1];

        traverse(x, y);
    }

    /*
     * helper method specific for DepthFirstSearch
     */
    private void followDirection(int x, int y)
    {
        // reads our current direction, then continues to move that direction
        setToTried(x, y);

        if (currentDirection == "up")
        {
            if (validPosition(x - 1, y))
            {
                directions.push("down");
                followDirection(x - 1, y);
            }
            else
            {
                currentDirection = "";
                traverse(x, y);
            }
        }

        else if (currentDirection == "left")
        {
            if (validPosition(x, y - 1))
            {
                directions.push("right");
                followDirection(x, y - 1);
            }
            else
            {
                currentDirection = "";
                traverse(x, y);
            }
        }

        else if (currentDirection == "right")
        {
            if (validPosition(x, y + 1))
            {
                directions.push("left");
                followDirection(x, y + 1);
            }
            else
            {
                currentDirection = "";
                traverse(x, y);
            }
        }

        else if (currentDirection == "down")
        {
            if (validPosition(x + 1, y))
            {
                directions.push("up");
                followDirection(x + 1, y);
            }
            else
            {
                currentDirection = "";
                traverse(x, y);
            }
        }
    }

    /*
     * traverse method
     * @param int x, int y
     */
    private boolean traverse(int x, int y)
    {
        //method variables

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
            if (currentDirection == "up" || currentDirection == "down" || currentDirection == "left" || currentDirection == "right" )
            {
                followDirection(x, y);
            }

            //try up
            if (validPosition(x - 1, y))
            {
                directions.push("down");
                currentDirection = "up";
                traverse(x - 1, y);
            }

            //try left
            else if (validPosition(x, y - 1))
            {
                directions.push("right");
                currentDirection = "left";
                traverse(x, y - 1);
            }

            //try right
            else if (validPosition(x, y + 1))
            {
                directions.push("left");
                currentDirection = "right";
                traverse(x, y + 1);
            }

            //try down
            else if (validPosition(x + 1, y))
            {
                directions.push("up");
                currentDirection = "down";
                traverse(x + 1, y);
            }

            /*
             * since all four directions are not valid (deadend)
             *  we iniatiate the backtrack algorithm
             */
            else if (!solved)
            {
                backTrack(x, y);
            }
        }

        return solved;
    }


    /*
     * backtrack method
     * 
     * @param int x, int y
     */
    private void backTrack(int x, int y)
    {
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
