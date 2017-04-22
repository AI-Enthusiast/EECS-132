import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * A JButton that has a Tsuro game tile painted on it.
 * The game tile contains 4 paths connecting 4 pairs of endpoints on the icon.
 * Each endpoint is represented by a number
 * <pre>
 *         0   1
 *       +-------+
 *     6 |       | 2
 *       |       |
 *     7 |       | 3
 *       +-------+
 *         4   5
 * </pre>
 *
 * The four paths on the tile are represented by an array of int where
 * <tt>array[0] = 5</tt> means we have a path connecting endpoint 0 and endpoint 5.
 *
 * @author Harold Connamacher
 */
public class TsuroButton extends JButton {

    /** The code to draw a 4-path icon across the front of a button */
    private class GameTile implements Icon {

        /**
         * Returns the height of the icon.
         * @return the icon height
         */
        @Override
        public int getIconHeight() {
            return TsuroButton.this.getHeight();
        }

        /**
         * Returns the width of the icon.
         * @return the icon width
         */
        @Override
        public int getIconWidth() {
            return TsuroButton.this.getWidth();
        }

        /**
         * Convert an getConnections() position to a coordinate.
         * @param value the getConnections() position
         * @return a coordinate on the icon
         */
        private Dimension endPoint2Dimension(int value) {
            int x_value = 0, y_value = 0;
            switch (value) {
                case 0:
                    x_value = getWidth() / 3;
                    y_value = 0;
                    break;
                case 1:
                    x_value = 2 * getWidth() / 3;
                    y_value = 0;
                    break;
                case 2:
                    x_value = getWidth() - 1;
                    y_value = getHeight() / 3;
                    break;
                case 3:
                    x_value = getWidth() - 1;
                    y_value = 2 * getHeight() / 3;
                    break;
                case 5:
                    x_value = 2 * getWidth() / 3;
                    y_value = getHeight() - 1;
                    break;
                case 4:
                    x_value = getWidth() / 3;
                    y_value = getHeight() - 1;
                    break;
                case 7:
                    x_value = 0;
                    y_value = 2 * getHeight() / 3;
                    break;
                default:
                    x_value = 0;
                    y_value = getHeight() / 3;
            }
            return new Dimension(x_value, y_value);
        }

        /**
         * Draw a path on the icon between two getConnections() locations.
         * @param g  the graphics context used for the drawing
         * @param start  one of the getConnections()s of the path
         * @param end    the other getConnections() of the path
         */
        private void drawPath(Graphics2D g, int start, int end) {
            Dimension start_dim = endPoint2Dimension(start);
            Dimension end_dim = endPoint2Dimension(end);

            if (start_dim.width == 0 && end_dim.width == 0)
                g.drawArc(-getWidth()/4, getHeight() / 3, getWidth() / 2, 2*getHeight()/3 - getHeight() / 3, 270, 180);
            else if (start_dim.width == getWidth() - 1 && end_dim.width == getWidth() - 1)
                g.drawArc(getWidth() - 1 - getWidth() / 4, getHeight() / 3, getWidth() / 2, 2*getHeight()/3 - getHeight() / 3, 90, 180);
            else if (start_dim.height == 0 && end_dim.height == 0)
                g.drawArc(getWidth() / 3, - getHeight() / 4, 2*getWidth() / 3 - getWidth() / 3, getHeight() / 2, 180, 180);
            else if (start_dim.height == getHeight() - 1 && end_dim.height == getHeight() - 1)
                g.drawArc(getWidth() / 3, getHeight() - 1 - getHeight() / 4, 2 * getWidth() / 3 - getWidth() / 3, getHeight() / 2, 0, 180);
            else
                g.drawLine(start_dim.width, start_dim.height, end_dim.width, end_dim.height);
        }

        /**
         * Draw the icon on the button
         * @param c  the component the icon is on
         * @param g  the graphics context used for the drawing the icon
         * @param x1 the top left coordinate for the icon - ignored by this routine
         * @param y1 the top left coordinate for the icon - ignored by this routine
         */
        public void paintIcon (Component c, Graphics g, int x1, int y1) {
            Graphics2D g2D = (Graphics2D)g.create();

            g2D.setColor(getBackground());
            g2D.fillRect(0, 0, TsuroButton.this.getWidth(), TsuroButton.this.getHeight());

            if (getConnections() != null) {
                g2D.setColor(getForeground());
                g2D.setStroke(new BasicStroke(getPathWidth()));
                for (int i = 0; i < getConnections().length; i++) {
                    drawPath(g2D, i, getConnections()[i]);
                }

                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i] != null) {
                        g2D.setColor(pieces[i]);
                        Dimension piece_dim = endPoint2Dimension(i);
                        int width, height, x, y;
                        if (piece_dim.width == 0) {
                            x = 0;
                            y = piece_dim.height - getHeight() / 8;
                            width = getWidth() / 8;
                            height = getHeight() / 4;
                        }
                        else if (piece_dim.width == getWidth() - 1) {
                            x = getWidth() - 1 - getWidth() / 8;
                            y = piece_dim.height - getHeight() / 8;
                            width = getWidth() / 8;
                            height = getHeight() / 4;
                        }
                        else if (piece_dim.height == 0) {
                            x = piece_dim.width - getWidth() / 8;
                            y = 0;
                            width = getWidth() / 4;
                            height = getHeight() / 8;
                        }
                        else {
                            x = piece_dim.width - getWidth() / 8;
                            y = getHeight() - 1 - getHeight() / 8;
                            width = getWidth() / 4;
                            height = getHeight() / 8;
                        }
                        g2D.fillOval(x, y, width, height);
                    }
                }
            }
            g2D.dispose();
        }
    }

    /** An array that stores the endpoint to endpoint connections of the 4 paths on the icon */
    private int[] paths = null;

    /** Stores whether there is a piece on one of the icon endpoints */
    private Color[] pieces = new Color[8];

    /** The background color for the icon */
    private Color background = Color.BLACK;

    /** The color used to draw the paths */
    private Color foreground = Color.GREEN;

    /** The width used to draw the paths on the icon */
    private float pathWidth = 3.0F;

    /** Add a pair to a random symmetric permutation.
     * @param connections  the array stores what each endpoint connects to
     * @param filled       the array indicates whether a an endpoint has a match
     * @param numLeft      the number of not-filled entries of the connections array
     */
    private static void makeRandomConnection(int[] connections, boolean[] filled, int numLeft) {
        int next = 0;
        while (filled[next])                                 // get the next spot not yet connected
            next++;

        int skip = (int)(Math.random() * (numLeft - 1));     // randomly choose from the remaining spots
        int connectTo = next + 1;
        while (skip > 0 || filled[connectTo]) {              // go to that randomly chosen spot
            if (!filled[connectTo])
                skip--;
            connectTo++;
        }
        connections[next] = connectTo;                       // make the connection
        connections[connectTo] = next;
        filled[next] = true;
        filled[connectTo] = true;
    }

    /**
     * Create a random symmetric permutation.  The permutation represents the connections between
     * two endpoints.  If index i stores value j, then the icon will have a path from endpoint i to
     * endpoint j.
     * @return a new array storing the random path connections
     */
    public static int[] makeRandomConnectArray() {
        int[] connections = new int[8];
        boolean[] filled = new boolean[8];
        makeRandomConnection(connections, filled, 8);
        makeRandomConnection(connections, filled, 6);
        makeRandomConnection(connections, filled, 4);
        makeRandomConnection(connections, filled, 2);
        return connections;
    }

    /**
     * Create a button that represents a tile of the Tsuro game.
     */
    public TsuroButton() {
        super();
        setIcon(new GameTile());
    }

    /**
     * Change the connections for the paths of the game tile.  The paths are drawn on the button icon.  A null input produces a blank tile.
     * @param connections  an array of 8 ints.  connections[i] = j means there is a path from endpoint i to endpoint j.
     */
    public void setConnections(int[] connections) {
        this.paths = connections;
        repaint();
    }

    /**
     * Return an array showing the connections for the game tile that are drawn on the icon
     * @return  the array giving the endpoint connections for the paths that are drawn on the icon
     */
    public int[] getConnections() {
        return paths;
    }

    /**
     * Remove a stone from a specific endpoint of a path on the game tile.
     * @param endPoint  the path endpoint that we are removing a game piece from
     */
    public void removeStone(int endPoint) {
        pieces[endPoint] = null;
        repaint();
    }

    /**
     * Add a game stone to a specific path endpoint on the game tile.
     * @param stoneColor  the color of the stone to be added
     * @param endPoint  the path endpoint for the piece
     */
    public void addStone(Color stoneColor, int endPoint) {
        pieces[endPoint] = stoneColor;
        repaint();
    }

    /**
     * Retrieve the color used for the background of the game tile button.
     * @return the background color of the button
     */
    @Override
    public Color getBackground() {
        return background;
    }

    /**
     * Change the color used for the background of the game tile button.
     * @param background  the background color of the button
     */
    @Override
    public void setBackground(Color background) {
        this.background = background;
        repaint();
    }

    /**
     * Retrieve the color used to draw the paths on the icon.
     * @return the color used to draw the paths
     */
    @Override
    public Color getForeground() {
        return foreground;
    }

    /**
     * The color used to draw the paths on the game tile.
     * @param foreground  the color used to draw the paths
     */
    @Override
    public void setForeground(Color foreground) {
        this.foreground = foreground;
        repaint();
    }

    /**
     * Return the pen width used to draw the paths on the game tile.
     * @return the pen width
     */
    public float getPathWidth() {
        return pathWidth;
    }

    /**
     * Change the pen width used to draw the paths on the game tile.
     * @param pathWidth  the width used to draw the paths
     */
    public void setPathWidth(float pathWidth) {
        this.pathWidth = pathWidth;
        repaint();
    }
}