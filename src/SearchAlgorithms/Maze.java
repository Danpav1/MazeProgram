package SearchAlgorithms;
import java.util.Stack;

import Exceptions.BadStartEndCordsException;

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

    //constructor
    public Maze() {}

    /**
     * helper method that checks if the start and end coords given are out of bounds for
     *  our maze array.
     * @param sRow
     * @param sCol
     * @param eRow
     * @param eCol
     * @return
     */
    protected boolean startEndPointValidation(int sRow, int sCol, int eRow, int eCol) {
        boolean badStartCords = true;
        if (sRow < 0 || sRow > maze.length - 1) {
          throw new BadStartEndCordsException("Invalid start row");
        } else if (sCol < 0 || sCol > maze[0].length - 1) {
          throw new BadStartEndCordsException("Invalid start col");
        } else if (eRow < 0 || eRow > maze.length - 1) {
          throw new BadStartEndCordsException("Invalid end row");
        } else if (eCol < 0 || eCol > maze[0].length - 1) {
          throw new BadStartEndCordsException("Invalid end col");
        }
        return badStartCords;
    }
    
    /*
     * method that creates a path within the maze array when 
     *  the maze is solved
     * @param int row, int col
     */
    protected void createPath(int row, int col)
    {
        //method variable(s)
        String directionToTake = "";

        //mark current as part of the path
        maze[row][col] = PATH;

        //takes stacks' directions while it has directions within it
        while (!directions.empty())
        {
            directionToTake = directions.pop();

            //takes stack directions
            if (directionToTake == "up")
            {
                createPath(row - 1, col);
            }
            else if (directionToTake == "left")
            {
                createPath(row, col - 1);
            }
            else if (directionToTake == "right")
            {
                createPath(row, col + 1);
            }
            else if (directionToTake == "down")
            {
                createPath(row + 1, col);
            }
        }
    }

    /*
     * checks if the node is the end node
     * @param int row, int col
     * @return boolean solved
     */
    protected boolean isEnd(int row, int col)
    {
        //method variables
        boolean solved = false;

        //logic
        if (row == endPosition[0] && col == endPosition[1])
        {
            solved = true;
        }

        return solved;
    }

    /*
     * sets the node at the coorindates to 3 or PATH
     * @param int row, int col
     */
    protected void setToPath(int row, int col)
    {
        maze[row][col] = PATH;
    }

    /*
     * sets the node at the coorindates to 2 or TRIED
     * @param int row, int col
     */
    protected void setToTried(int row, int col)
    {
        maze[row][col] = TRIED;
    }

    /*
     * checks the validity of the position the maze solver
     *  wants to go to next
     * @param int row, int col
     * @return boolean isValid
     */
    protected boolean validPosition(int row, int col)
    {
        //method variable(s)
        boolean isValid = false;

        //checks if coordinate is within array bounds (positive)
        if (col > maze[0].length - 1 || row > maze.length - 1)
        {
            isValid = false;
            return isValid;
        }

        //checks if coordinate is within array bounds (negative)
        if (row < 0 || col < 0)
            {
                isValid = false;
                return isValid;
            }

        //checks if coordinate is anything but OPEN
        if (maze[row][col] == OPEN)
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
