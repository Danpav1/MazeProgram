/*
 * Runner class for maze
 * @author Gooomba
 */

public class MazeRunnable
{
    public static void main(String[] args) throws Exception
    {
        /*
         * --- maze legend ---
         * 
         * 0 = OPEN
         * 1 = WALL
         * 2 = TRIED
         * 3 = PATH
         * 
         * maze[row][column] - (<row>, <column>)
         * maze[1][0] = 3
         */
        int[][] maze = {{0, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1},
                        {0, 1, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 1, 0},
                        {0, 0, 0, 0, 1, 0}};

        int[][] maze2 = {{0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                         {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0},
                         {0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
                         {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                         {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                         {1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                         {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                         {0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
                         {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                         {0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                         {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}};

        Maze m = new Maze(maze);

        System.out.println("The unsolved maze:");

        m.printMaze();

        System.out.print("\n\n" + "--------------------------------------" + "\n");

        m.solveMaze();

        System.out.println("The solved maze:");

        m.printMaze();
    }
}
