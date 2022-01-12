import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class for the maze data
 * 
 * @author Suyu Chen
 */
public class Maze {
    private ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>();
    private int[] startPos = new int[] { -1, -1 };
    private int[] endPos = new int[] { -1, -1 };

    /**
     * Creates a new Maze from a file containing maze data.
     * 
     * @param mazeFileName the path to the maze file
     * @throws FileNotFoundException if the specified file does not exist
     * @throws InvalidMazeException  if the specified file is invalid
     */
    public Maze(String mazeFileName) throws FileNotFoundException, InvalidMazeException {
        Scanner fileInput = new Scanner(new File(mazeFileName));
        int row = 0;
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            data.add(new ArrayList<Character>());
            for (int i = 0; i < line.length(); i++) {
                data.get(row).add(line.charAt(i));
                if (line.charAt(i) == Consts.START) {
                    startPos[0] = row;
                    startPos[1] = i;
                } else if (line.charAt(i) == Consts.END) {
                    endPos[0] = row;
                    endPos[1] = i;
                }
            }
            row++;
        }
        if (this.isInvalid()) {
            throw new InvalidMazeException();
        }
        fileInput.close();
    }

    /**
     * Checks if this maze is a valid unsolved rectangular maze, with walls along
     * all outside edges, no invalid characters, and a start and end point
     * 
     * @return true if the maze is invalid, false otherwise
     */
    private boolean isInvalid() {
        for (ArrayList<Character> line : data) {
            if (line.size() <= 2 || line.get(0) != Consts.WALL || line.get(line.size() - 1) != Consts.WALL) {
                return true;
            }
            if (!data.isEmpty()) {
                if (line.size() != data.get(0).size()) {
                    return true;
                }
            }
            for (int i = 0; i < line.size(); i++) {
                if (i == 0 || i == line.size() - 1) {
                    if (line.get(i) != Consts.WALL) {
                        return true;
                    }
                } else {
                    if (Consts.VALID_MAZE_FILE_CHARS.indexOf(line.get(i)) == -1) {
                        return true;
                    }
                }
            }
        }
        if (startPos[0] == -1 || startPos[1] == -1 || endPos[0] == -1 || endPos[1] == -1) {
            return true;
        }
        return false;
    }

    /**
     * Gets the character at a position in the maze
     * 
     * @param i the row
     * @param j the column
     * @return the character at the specified location
     */
    public char get(int i, int j) {
        return data.get(i).get(j);
    }

    /**
     * Gets the character at a position in the maze
     * 
     * @param pos a length 2 integer array specifying a row and column
     * @return the character at the specified location
     */
    public char get(int[] pos) {
        return data.get(pos[0]).get(pos[1]);
    }

    /**
     * Puts a marker character in coordinate in the maze
     * 
     * @param pos    a length 2 integer array specifying the row and column to mark
     * @param marker the character to replace the current character at the specified
     *               location with
     */
    public void set(int[] pos, char marker) {
        data.get(pos[0]).set(pos[1], marker);
    }

    /**
     * Puts a marker character in coordinate in the maze
     * 
     * @param i      the row to mark
     * @param j      the column to mark
     * @param marker the character to replace the current character at the specified
     *               location with
     */
    public void set(int i, int j, char marker) {
        data.get(i).set(j, marker);
    }

    /**
     * Gets the number of rows in this maze
     * 
     * @return the number of rows in this maze
     */
    public int getRows() {
        return data.size();
    }

    /**
     * Gets the number of columns in this maze
     * 
     * @return the number of columns in this maze
     */
    public int getCols() {
        return data.get(0).size();
    }

    /**
     * Gets the position of the starting point on this maze
     * 
     * @return a length 2 int array containing the row and column of this maze's
     *         starting point
     */
    public int[] getStartPos() {
        return this.startPos;
    }

    /**
     * Gets the position of the ending point on this maze
     * 
     * @return a length 2 int array containing the row and column of this maze's
     *         ending point
     */
    public int[] getEndPos() {
        return this.endPos;
    }

    /**
     * Draws this maze with a Graphics object
     * 
     * @param g Graphics object to draw with
     * @param blockSize the size of each block in the maze, in pixels
     */
    public void draw(Graphics g, int blockSize) {
        char currentLocation;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(0).size(); j++) {
                currentLocation = data.get(i).get(j);
                if (currentLocation == Consts.WALL) {
                    g.setColor(Color.BLACK);
                } else if (currentLocation == Consts.PATH) {
                    g.setColor(Color.WHITE);
                } else if (startPos[0] == i && startPos[1] == j) {
                    g.setColor(Color.BLUE);
                } else if (currentLocation == Consts.VISITED) {
                    g.setColor(Color.GREEN);
                } else if (currentLocation == Consts.DEAD_END) {
                    g.setColor(Color.GRAY);
                } else if (currentLocation == Consts.END) {
                    g.setColor(Color.RED);
                }
                g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
            }
        }
    }

    /**
     * Returns a string with the contents of this maze
     * 
     * @return the string with the contents of this maze
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(0).size(); j++) {
                output += data.get(i).get(j);
            }
            output += "\n";
        }
        return output;
    }

}
