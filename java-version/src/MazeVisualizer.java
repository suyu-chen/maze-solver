import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Visualizer for maze solver
 * 
 * @author Suyu Chen
 */
public class MazeVisualizer extends JFrame {
    private Maze maze;
    private int blockSize;

    /**
     * Creates and initializes a new MazeVisualizer JFrame
     * @param maze the maze to visualize
     */
    MazeVisualizer(Maze maze) {
        this.maze = maze;
        this.getContentPane().add(BorderLayout.CENTER, new MazePanel());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        blockSize = (int) Math.min(screenSize.getWidth()/maze.getCols(), screenSize.getHeight()/maze.getRows());
        this.getContentPane().setPreferredSize(
                new Dimension(maze.getCols() * blockSize, maze.getRows() * blockSize));
        this.pack();
        this.setResizable(true);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * JPanel for maze solver
     */
    private class MazePanel extends JPanel {

        /**
         * Draws the maze to the screen
         * @param g the Graphics object for drawing
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            maze.draw(g, blockSize);
            this.repaint();
        }
    }
}
