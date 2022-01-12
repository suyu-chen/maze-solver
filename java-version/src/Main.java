import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main program for the maze solver
 * @author Suyu Chen
 */
public class Main {

    /**
     * The main maze solver program
     * @param args String array of command line arguments
     */
    public static void main(String[] args) {
        Maze maze = null;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter name of maze file, include extension");
        while(maze == null){
            try{
                maze = new Maze(userInput.nextLine());
            }catch(FileNotFoundException e){
                System.out.println("File not found, try again");                
            }catch(InvalidMazeException e){
                System.out.println("Invalid file, try again");
            }
        }
        userInput.close();

        new MazeVisualizer(maze);
        MazeSolver.findExit(maze);
        System.out.println(maze);
    }
}
