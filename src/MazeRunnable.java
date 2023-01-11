import java.io.File;
import java.util.Scanner;

/*
 * Runner class for maze
 * @author Gooomba
 */

public class MazeRunnable
{
    //instance variables
    //maze[y][x]

    /*
     * some hard coded test mazes
     * 0 = open
     * 1 = wall
     * 2 = tried
     * 3 = path
     */
    static int[][] maze1 = {{0, 1, 1, 0, 0, 0},
                            {0, 0, 0, 0, 1, 1},
                            {0, 1, 1, 0, 0, 0},
                            {0, 0, 0, 1, 1, 0},
                            {0, 1, 1, 0, 1, 0},
                            {0, 0, 0, 0, 1, 0}};

    static int[][] maze2 = {{0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
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

    static int[][] maze3 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                            {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                            {1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                            {0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                            {0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0},
                            {0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
                            {0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                            {0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
                            {0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0},
                            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0},
                            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                            {1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                            {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                            {0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                            {1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0},
                            {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                            {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
                            {1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1},
                            {1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0}};


    public static void main(String[] args) throws Exception
    {
        /*
        int[][] maze;
        int x, y = 0;
        String filename = "";

        System.out.println("Enter the file name");
        Scanner scan = new Scanner(System.in);
        filename = scan.nextLine();
    
        Scanner myScanner = new Scanner(new File(filename));

        //reads and saves the first line(s) values which are the scale of the maze
        y = myScanner.nextInt();
        x = myScanner.nextInt();

        //initializes the maze based on the scale we read prior
        maze = new int[y][x];

        //reads the file and puts the values within the maze
        for (int row = 0; row < y; row++)
        {
            for (int column = 0; column < x; column++)
            {
                int temp = myScanner.nextInt();
                maze[row][column] = temp;
            }
        }
        myScanner.close();
        */
        MazeGenerator mg = new MazeGenerator(50, 50);
        Maze m = new Maze(mg.getGeneratedMaze());

        m.solveMaze();

        System.out.println("\nThe solved maze:");

        m.copyMaze();
        m.printStringMaze();
    }
}
