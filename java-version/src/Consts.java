/**
 * Constants for the maze
 * 
 * @author Suyu Chen
 */
public final class Consts {
    public static final char START = 'S';
    public static final char END = 'E';
    public static final char WALL = '#';
    public static final char PATH = ' ';
    public static final char VISITED = '█';
    public static final char DEAD_END = '□';
    public static final String VALID_MAZE_FILE_CHARS = "" + START + END + WALL + PATH;
    public static final int MS_PER_FRAME = 5;
    public static final boolean DELAY = true;

    private Consts() {
    }
}
