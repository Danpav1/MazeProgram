package SearchAlgorithms;
import java.util.Stack;

/*
 * Program that solves a maze
 * @author Gooomba
 */

public abstract class Maze
{
    //instance variables
    protected int[][] maze;
    protected String[][] solvedMaze;

    protected Stack<String> directions = new Stack<String>();

    protected static final int OPEN = 0;
    protected static final int WALL = 1;
    protected static final int TRIED = 2;
    protected static final int PATH = 3;

    //start position is top left of maze
    protected int[] startPosition = new int[2];
    //end position is bottem right of maze
    protected int[] endPosition = new int[2];

    //colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    /*
     * default constructor where the start points are in the top left
     *  and the end points are in the bottom right
     * @param int[][] m
     */
    public Maze(int[][] m)
    {
        maze = m;

        //saves the start & end points
        startPosition[0] = 0;
        startPosition[1] = 0;
        endPosition[0] = maze.length - 1;
        endPosition[1] = maze[0].length - 1;

        //creates a 2d string array of the same size as the int 2d array
        solvedMaze = new String[maze.length][maze[0].length];
    }

    /*
     * overloaded constructor to accept custom start and end points
     * @param @param int[][] m, int sX, int sY, int eX, int eY
     */
    public Maze(int[][] m, int sX, int sY, int eX, int eY)
    {
        maze = m;

        //handling for bad start and end coords
        boolean error = false;
        if (sX < 0 || sX > maze.length - 1)
        {
            System.out.println("Inputted start x coordinate out of array bounds");
            error = true;
        }
        if (sY < 0 || sY > maze[0].length - 1)
        {
            System.out.println("Inputted start y coordinate out of array bounds");
            error = true;
        }
        if (eX < 0 || eX > maze.length - 1)
        {
            System.out.println("Inputted end x coordinate out of array bounds");
            error = true;
        }
        if (eY < 0 || eY > maze[0].length - 1)
        {
            System.out.println("Inputted end y coordinate out of array bounds");
            error = true;
        }
        if (error)
        {
            System.exit(0);
        }

        //saves the start & end points
        startPosition[0] = sX;
        startPosition[1] = sY;
        endPosition[0] = eX;
        endPosition[1] = eY;

        //creates a 2d string array of the same size as the int 2d array
        solvedMaze = new String[maze.length][maze[0].length];
    }
    
    /*
     * method that creates a path within the maze array when 
     *  the maze is solved
     * @param int x, int y
     */
    protected void createPath(int x, int y)
    {
        //method variable(s)
        String directionToTake = "";

        //mark current as part of the path
        maze[x][y] = PATH;

        //takes stacks' directions while it has directions within it
        while (!directions.empty())
        {
            directionToTake = directions.pop();

            //takes stack directions
            if (directionToTake == "up")
            {
                createPath(x - 1, y);
            }
            else if (directionToTake == "left")
            {
                createPath(x, y - 1);
            }
            else if (directionToTake == "right")
            {
                createPath(x, y + 1);
            }
            else if (directionToTake == "down")
            {
                createPath(x + 1, y);
            }
        }
    }

    /*
     * checks if the node is the end node
     * @param int x, int y
     * @return boolean solved
     */
    protected boolean isEnd(int x, int y)
    {
        //method variables
        boolean solved = false;

        //logic
        if (x == endPosition[0] && y == endPosition[1])
        {
            solved = true;
        }

        return solved;
    }

    /*
     * sets the node at the coorindates to 3 or PATH
     * @param int x, int y
     */
    protected void setToPath(int x, int y)
    {
        maze[x][y] = PATH;
    }

    /*
     * sets the node at the coorindates to 2 or TRIED
     * @param int x, int y
     */
    protected void setToTried(int x, int y)
    {
        maze[x][y] = TRIED;
    }

    /*
     * checks the validity of the position the maze solver
     *  wants to go to next
     * @param int x, int y
     * @return boolean isValid
     */
    protected boolean validPosition(int x, int y)
    {
        //method variable(s)
        boolean isValid = false;

        //checks if coordinate is within array bounds (positive)
        if (y > maze[0].length - 1 || x > maze.length - 1)
        {
            isValid = false;
            return isValid;
        }

        //checks if coordinate is within array bounds (negative)
        if (x < 0 || y < 0)
            {
                isValid = false;
                return isValid;
            }

        //checks if coordinate is anything but OPEN
        if (maze[x][y] == OPEN)
        {
            isValid = true;
        }

        return isValid;
    }

    /*
     * method that prints the int maze
     */
    protected void printMaze()
    {
        for (int row = 0; row < maze.length; row++)
        {
            System.out.println();
            for (int column = 0; column < maze[row].length; column++)
            {
                System.out.print(maze[row][column] + "  ");
            }
        }
    }

    /*
     * method that duplicates the solved int maze and saves it as a string 2d
     *  array for easier manipulation when printing
     */
    public void copyMaze()
    {
        String ch;
        for (int row = 0; row < maze.length; row++)
        {
            for (int column = 0; column < maze[row].length; column++)
            {
                ch = String.valueOf(maze[row][column]);
                solvedMaze[row][column] = ch;
            }
        }
    }

    /*
     * method that prints the String maze
     */
    public void printStringMaze()
    {
        for (int row = 0; row < solvedMaze.length; row++)
        {
            System.out.println();
            for (int column = 0; column < solvedMaze[row].length; column++)
            {
                if (solvedMaze[row][column].equals("3"))
                {
                    System.out.print(ANSI_GREEN + "3" + ANSI_RESET + "  ");
                }
                else if (solvedMaze[row][column].equals("2"))
                {
                    System.out.print("0" + "  ");
                }
                else
                {
                    System.out.print(solvedMaze[row][column] + "  ");
                }
            }
        }
    }
}
