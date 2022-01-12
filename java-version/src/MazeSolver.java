/**
 * Class for solving mazes
 * 
 * @author Suyu Chen
 */
public class MazeSolver {

    /**
     * Finds a path from the start point to the end point of a maze. The maze is
     * mutated by changing characters along the solution path and different characters
     * along other attempted paths, as defined by the Consts class.
     * 
     * @param maze the maze to be solved
     */
    public static void findExit(Maze maze) {
        findExit(maze, maze.getStartPos()[0], maze.getStartPos()[1]);
    }

    /**
     * Finds a path from a specified location to the end of the maze. The maze is
     * mutated by changing characters along the solution path and different characters
     * along other attempted paths, as defined by the Consts class.
     * 
     * @param maze the maze to be solved
     * @param location the starting location
     */
    public static void findExit(Maze maze, int[] location) {
        findExit(maze, location[0], location[1]);
    }

    private static boolean findExit(Maze maze, int row, int col) {
        if (Consts.DELAY)
            delay(Consts.MS_PER_FRAME);
        char currentChar = maze.get(row, col);
        if (currentChar == Consts.END) {
            return true;
        } else if (currentChar == Consts.WALL || currentChar == Consts.VISITED || currentChar == Consts.DEAD_END) {
            return false;
        } else {
            maze.set(row, col, Consts.VISITED);
            boolean validPath = findExit(maze, row + 1, col) || findExit(maze, row - 1, col)
                    || findExit(maze, row, col + 1) || findExit(maze, row, col - 1);
            if (!validPath) {
                maze.set(row, col, Consts.DEAD_END);
            }
            return validPath;
        }
    }

    /**
     * Pauses thread for speciified time
     * 
     * @param ms time in ms
     */
    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("Thread error");
        }
    }
}
