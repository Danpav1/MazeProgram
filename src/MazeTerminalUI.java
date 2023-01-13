import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * class that houses the UI for this program
 * @author Gooomba
 */

public class MazeTerminalUI
{
    //instance variables
    static int[][] maze;
    static int x, y = 0;

    static int sX = 0;
    static int sY = 0;
    static int eX = 0;
    static int eY = 0;

    static boolean def = false;

    //colors used for contrast 
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BOLD = "\u001B[1m";

    /*
     * maze[y][x]
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

    
    /*
     * method that runs the UI sequence
     */
    public void runUI() throws Exception
    {
        mazeSelectionUI();

        fileNameSelectionUI();

        startEndSelectionUI();

        algorithmSelectionUI(maze);
    }

    /*
     * method that reads the specific maze file
     * @param String filename
     */
    public static void readMaze(String filename) throws FileNotFoundException
    {
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
    }

    /*
     * method that holds the UI for maze selection
     */
    public static void mazeSelectionUI()
    {
        //empty new line printed for spacing
        System.out.println("");

        Scanner hardCodedMazeSelection = new Scanner(System.in);

        System.out.println("Would you like to use one of the included mazes?");
        System.out.println("\t" + "1 : Yes");
        System.out.println("\t" + "2 : No");
        
        String mazeSelectionInput = hardCodedMazeSelection.nextLine();

        //empty new line printed for spacing
        System.out.println("");

        switch (mazeSelectionInput)
        {
            case "1":
                Scanner mazeSelector = new Scanner(System.in);

                System.out.println("Choose a maze: ");
                System.out.println("\t" + "1 : maze1 (6 by 6)");
                System.out.println("\t" + "2 : maze2 (11 by 11)");
                System.out.println("\t" + "3 : maze3 (23 by 23)");
                System.out.println("\t" + "9 : Exit");

                String mazeSelection = mazeSelector.nextLine();

                switch (mazeSelection)
                {
                    case "1":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze1);
                        System.exit(0);
                        break;

                    case "2":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze2);
                        System.exit(0);
                        break;

                    case "3":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze3);
                        System.exit(0);
                        break;

                    case "9":
                        System.out.println(ANSI_YELLOW + "Exiting" + ANSI_RESET);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid selection");
                }

            case "2":
                break;
        }
    }

    /*
     * method that holds the UI for filename selection
     */
    public static void fileNameSelectionUI() throws Exception
    {
        //empty new line printed for spacing
        System.out.println("");

        /*
         * filename selection screen in console
         */
        Scanner fileNameSaver = new Scanner(new File("previousinput.txt"));
        String fileNameSaverinput = "";

        if (fileNameSaver.hasNext())
        {
            fileNameSaverinput = fileNameSaver.nextLine();

            Scanner previousSelector = new Scanner(System.in);
        
            System.out.println("Would you like to use the last filename used?");
            System.out.println("\t" + "1 : Yes");
            System.out.println("\t" + "2 : No");

            String selection = previousSelector.nextLine();

            switch (selection)
            {
                case "1":
                    readMaze(fileNameSaverinput);
                    break;

                case "2":
                    String filename = "";
                    System.out.println("Enter the file name");
                    Scanner scan = new Scanner(System.in);
                    filename = scan.nextLine();

                    FileWriter myFileWriter = new FileWriter("previousinput.txt");
            
                    //exception handling
                    try
                    {
                        readMaze(filename);
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println(ANSI_RED + "ERROR: file not found" + ANSI_RESET);
                        System.exit(0);
                    }
                    finally
                    {
                        myFileWriter.write(filename);
                        myFileWriter.close();
                    }
                    break;

                default:
                    System.out.println("Invalid input");
            }
        }
        else
        {
            String filename = "";
            System.out.println("Enter the file name");
            Scanner scan = new Scanner(System.in);
            filename = scan.nextLine();

            FileWriter myFileWriter = new FileWriter("previousinput.txt");

            try
            {
                readMaze(filename);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(ANSI_RED + "ERROR: file not found" + ANSI_RESET);
                System.exit(0);
            }
            finally
            {
                myFileWriter.write(filename);
                myFileWriter.close();
            }
        }
    }

    /*
     * method that holds the UI for start & end point selection
     */
    public static void startEndSelectionUI()
    {
        //empty new line printed for spacing
        System.out.println("");

        Scanner myScanner = new Scanner(System.in);

        System.out.println("What start / end points would you like to use?");
        System.out.println("\t" + "1 : start (top left), end (bottom right)");
        System.out.println("\t" + "2 : custom points");

        String input = myScanner.nextLine();

        switch (input)
        {
            case "1":
                def = true;
                break;
            
            case "2":
                Scanner customCoord = new Scanner(System.in);

                System.out.println("Enter the custom start points seperated by a space (no parenthesis): " );
                
                sX = customCoord.nextInt();
                sY = customCoord.nextInt();

                System.out.println("Enter the custom end points seperated by a space (no parenthesis): " );

                eX = customCoord.nextInt();
                eY = customCoord.nextInt();

            default: 
        };
    }

    /*
     * method that holds the UI for algorithm selection
     * @param int[][] m
     */
    public static void algorithmSelectionUI(int[][] m)
    {
        //empty new line printed for spacing
        System.out.println("");

        /*
         * algorithm picking screen in console
         * is expandable to add different algorithms
         */
        Scanner algorithmSelector = new Scanner(System.in);
        boolean valid = false;

        while (!valid)
            {
            System.out.println("Which algorithm would you like to choose? Pick the number corresponding to your choice.");
            System.out.println("\t" + "1 : FloodFill");
            System.out.println("\t" + "9 : Exit this selection screen");

            String input = "";
            input = algorithmSelector.nextLine();

            switch (input)
            {
                case "1":
                    valid = true;
                    if (def)
                    {
                        FloodFillAlgorithm dsa = new FloodFillAlgorithm(m);
                        dsa.solveMaze();
                        System.out.println("\n\t" + ANSI_BOLD + "The solved maze:" + ANSI_RESET);
                        dsa.copyMaze();
                        dsa.printStringMaze();
                    }
                    else
                    {
                        FloodFillAlgorithm dsa = new FloodFillAlgorithm(m, sX, sY, eX, eY);
                        dsa.solveMaze();
                        System.out.println("\n\t" + ANSI_BOLD +  "The solved maze:" + ANSI_RESET);
                        dsa.copyMaze();
                        dsa.printStringMaze();
                    }
                break;

                case "9":
                    System.out.println(ANSI_YELLOW + "Exiting" + ANSI_RESET);
                    algorithmSelector.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
