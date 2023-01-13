/*
 * child class of maze that houses the Depth-First search algorithm
 * @author Gooomba
 */

 /*
  * search vertically before horizontal
  */

public class DepthFirstSearchAlgorithm extends Maze
{
    //instance variable(s)

    /*
     * default constructor for default start and end points 
     *  (top left, bottom right)
     * @param int[][] m
     */
    public DepthFirstSearchAlgorithm(int[][] m)
    {
        super(m);
    }

    /*
     * overloaded constructor for custom start and end points
     * @param int[][] m, int sX, int sY, int eX, int eY
     */
    public DepthFirstSearchAlgorithm(int[][] m, int sX, int sY, int eX, int eY)
    {
        super(m, sX, sY, eX, eY);
    }

    /*
     * method that initiates the solving of the maze
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
     * traverse method
     * @param int x, int y
     */
    public boolean traverse(int x, int y)
    {
        boolean solved = false;
        System.out.println("\n" + "DepthFirstSearchAlgorithm traverse method called");
        solved = true;
        backTrack(x, y);
        return solved;
    }

    /*
     * backtrack method
     * @param int x, int y
     */
    public void backTrack(int x, int y)
    {
        System.out.println("DepthFirstSearchAlgorithm backtrack method called");
    }
}
