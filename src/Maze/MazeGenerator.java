package Maze;
/*
 * Class that generates mazes
 * @author Gooomba
 */

public class MazeGenerator
{
    //instance variables
    int[][] generatedMaze;
    int numberOfRows = 0;
    int numberOfColumns = 0;

    /*
     * Constructor
     * @param int x, int y
     */
    public MazeGenerator(int x, int y)
    {
        numberOfRows = x;
        numberOfColumns = y;
        generatedMaze = new int[numberOfRows][numberOfColumns];

        mazeFiller();
    }

    /*
     * method that fills the maze randomly with 0's and 1's
     */
    public void mazeFiller()
    {
        double chance;

        for (int row = 0; row < numberOfRows; row++)
        {
            for (int column = 0; column < numberOfColumns; column++)
            {
                chance = Math.random();

                if (chance > 0.75)
                {
                    generatedMaze[row][column] = 0;
                }
                else
                {
                    generatedMaze[row][column] = 1;
                }
            }
        }

    }

    public int[][] getGeneratedMaze()
    {
        return generatedMaze;
    }

    /*
     * method that prints the int maze
     */
    public void printMaze()
    {
        for (int row = 0; row < generatedMaze.length; row++)
        {
            System.out.println();
            for (int column = 0; column < generatedMaze[row].length; column++)
            {
                System.out.print(generatedMaze[row][column] + "  ");
            }
        }
    }
}
