package Maze;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import SearchAlgorithms.DepthFirstSearch;
import SearchAlgorithms.FloodFillAlgorithm;

/*
 * class that houses the UI for this program
 * @author Gooomba
 */

public class MazeTerminalUI
{
    //instance variables
    private static int[][] maze;
    private static int row, col = 0;

    private static int sRow = 0;
    private static int sCol = 0;
    private static int eRow = 0;
    private static int eCol = 0;

    private static boolean defaultConstructor = false;

    //colors used for contrast 
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BOLD = "\u001B[1m";

    /*
     * maze[col][row]
     * some hard coded test mazes
     * 0 = open
     * 1 = wall
     * 2 = tried
     * 3 = path
     */
    private static int[][] maze1 = {{0, 1, 1, 0, 0, 0}, // 6 by 6
                                    {0, 0, 0, 0, 1, 1},
                                    {0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 0},
                                    {0, 1, 1, 0, 1, 0},
                                    {0, 0, 0, 0, 1, 0}};

    private static int[][] maze2 = {{0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, // 11 by 11
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

    private static int[][] maze3 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // 23 by 23
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
    private static void readMaze(String filename) throws FileNotFoundException
    {
        Scanner myScanner = new Scanner(new File(filename));

        //reads and saves the first line(s) values which are the scale of the maze
        row = myScanner.nextInt();
        col = myScanner.nextInt();

        //initializes the maze based on the scale we read prior
        maze = new int[col][row];

        //reads the file and puts the values within the maze
        for (int x = 0; x < row; x++)
        {
            for (int y = 0; y < col; y++)
            {
                int temp = myScanner.nextInt();
                maze[x][y] = temp;
            }
        }
        myScanner.close();
    }

    /*
     * method that holds the UI for maze selection
     */
    private static void mazeSelectionUI()
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
            //if Yes is selected
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
                    //if maze1 is selected
                    case "1":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze1);
                        System.exit(0);
                        break;

                    //if maze2 is selected
                    case "2":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze2);
                        System.exit(0);
                        break;

                    //if maze3 is selected
                    case "3":
                        startEndSelectionUI();
                        algorithmSelectionUI(maze3);
                        System.exit(0);
                        break;

                    //if exit is selected
                    case "9":
                        System.out.println(ANSI_YELLOW + "Exiting" + ANSI_RESET);
                        System.exit(0);
                        break;
                    
                    //if the selection doesnt match any of the cases
                    default:
                        System.out.println("Invalid selection");
                }

            //if No is selected
            case "2":
                break;
        }
    }

    /*
     * method that holds the UI for filename selection
     */
    private static void fileNameSelectionUI() throws Exception
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
    private static void startEndSelectionUI()
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
                defaultConstructor = true;
                break;
            
            case "2":
                Scanner customCoord = new Scanner(System.in);

                System.out.println("Enter the custom start points seperated by a space (no parenthesis): " );
                
                sRow = customCoord.nextInt();
                sCol = customCoord.nextInt();

                System.out.println("Enter the custom end points seperated by a space (no parenthesis): " );

                eRow = customCoord.nextInt();
                eCol = customCoord.nextInt();

            //if the selection doesnt match any of the cases
            default:
                System.out.println("Invalid input");
                break;
        };
    }

    /*
     * method that holds the UI for algorithm selection
     * @param int[][] m
     */
    private static void algorithmSelectionUI(int[][] m)
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
            System.out.println("\t" + "2 : Depth-First Search");
            System.out.println("\t" + "9 : Exit this selection screen");

            String input = "";
            input = algorithmSelector.nextLine();

            switch (input)
            {
                //if FloodFill is selected
                case "1":
                    valid = true;
                    if (defaultConstructor)
                    {
                        long start = System.currentTimeMillis();

                        FloodFillAlgorithm ffa = new FloodFillAlgorithm(m);
                        System.out.println("\n\t" + ANSI_BOLD + "The solved maze:" + ANSI_RESET);
                        ffa.copyMaze();
                        ffa.printStringMaze();

                        long end = System.currentTimeMillis();
                        DecimalFormat formatter = new DecimalFormat("#0.00");
                        System.out.print("\n" + "Execution time is " + formatter.format((end - start)) + " milliseconds");
                    }
                    else
                    {
                        long start = System.currentTimeMillis();

                        FloodFillAlgorithm ffa = new FloodFillAlgorithm(m, sRow, sCol, eRow, eCol);
                        System.out.println("\n\t" + ANSI_BOLD +  "The solved maze:" + ANSI_RESET);
                        ffa.copyMaze();
                        ffa.printStringMaze();

                        long end = System.currentTimeMillis();
                        DecimalFormat formatter = new DecimalFormat("#0.00");
                        System.out.print("\n" + "Execution time is " + formatter.format((end - start)) + " milliseconds");
                    }
                break;

                //if DepthFirstSearch is selected
                case "2":
                    valid = true;
                    if (defaultConstructor)
                    {
                        long start = System.currentTimeMillis();

                        DepthFirstSearch dfsa = new DepthFirstSearch(m);
                        System.out.println("\n\t" + ANSI_BOLD + "The solved maze:" + ANSI_RESET);
                        dfsa.copyMaze();
                        dfsa.printStringMaze();

                        long end = System.currentTimeMillis();
                        DecimalFormat formatter = new DecimalFormat("#0.00");
                        System.out.print("\n" + "Execution time is " + formatter.format((end - start)) + " milliseconds");
                    }
                    else
                    {
                        long start = System.currentTimeMillis();

                        DepthFirstSearch dfsa = new DepthFirstSearch(m, sRow, sCol, eRow, eCol);
                        System.out.println("\n\t" + ANSI_BOLD + "The solved maze:" + ANSI_RESET);
                        dfsa.copyMaze();
                        dfsa.printStringMaze();

                        long end = System.currentTimeMillis();
                        DecimalFormat formatter = new DecimalFormat("#0.00");
                        System.out.print("\n" + "Execution time is " + formatter.format((end - start)) + " milliseconds");
                    }
                break;

                case "9":
                    System.out.println(ANSI_YELLOW + "Exiting" + ANSI_RESET);
                    algorithmSelector.close();
                    System.exit(0);

                //if the selection doesnt match any of the cases
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
