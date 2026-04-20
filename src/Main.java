import javax.swing.*;
import java.awt.*;

/** Main class which initializes the creation of UI and later draws the graph */
public class Main extends JPanel implements Constants {
    /**
     * The colors of the elements are in the following order:
     * - the color of the outer sides, the color of the lines inside, and the color of the center point.
     */
    public static Color[] colors = new Color[]{Color.BLACK, new Color(190, 190, 190, 255), Color.red};
    /**
     * The line widths are the following order:
     * - the width of the outer sides and the width of the lines inside
     */
    public static int[] lineWidth = new int[]{3, 1};

    /** Creates UI */
    public static void main(String[] args) {
        new UI().init(new Main());
    }

    /**
     * Sets the center of the panel as the center of the polygon and redraws it.
     *
     * @param panelWidth  Width of the parent panel
     * @param panelHeight Height of the parent panel
     */
    public void setNewCenterCoordinates(int panelWidth, int panelHeight) {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        MathManager.setNewCenterCoordinates(panelWidth, panelHeight);
    }

    /**
     * JPanel's method to draw elements. Draws a polygon.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        int[][] vertices = MathManager.calculateVertices(0, -MathManager.radiusOutcircle);

        drawLinesFromCenter(g, vertices);
        drawVerticesAndSides(g, vertices);
        drawCenter(g);
    }

    /**
     * Draws lines from the center of the polygon to the vertices
     *
     * @param g        Graphics that draw elements
     * @param vertices The vertices from which to draw the line
     */
    private void drawLinesFromCenter(Graphics g, int[][] vertices) {
        g.setColor(colors[1]);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(lineWidth[1]));
        for (int[] vertex : vertices) g2.drawLine(MathManager.centralX, MathManager.centralY, vertex[0], vertex[1]);
    }

    /**
     * Draws vertices and the lines between them
     *
     * @param g        Graphics that draw elements
     * @param vertices The vertices that are drawn
     */
    private void drawVerticesAndSides(Graphics g, int[][] vertices) {
        g.setColor(colors[0]);
        for (int i = 0; i < vertices.length; i++) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(lineWidth[0]));

            if (i != 0) g2.drawLine(vertices[i][0], vertices[i][1], vertices[i - 1][0], vertices[i - 1][1]);
            else g2.drawLine(vertices[0][0], vertices[0][1],
                    vertices[vertices.length - 1][0], vertices[vertices.length - 1][1]);

            paintOval(g, vertices[i][0], vertices[i][1], lineWidth[0] + 8 / lineWidth[0]);
        }
    }

    /**
     * Draws the center of the polygon
     *
     * @param g Graphics that draw elements
     */
    private void drawCenter(Graphics g) {
        g.setColor(colors[2]);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        paintOval(g, MathManager.centralX, MathManager.centralY, 5);
    }

    /**
     * Draws a filled circle
     *
     * @param g      Graphics that draw elements
     * @param x      The x-coordinate of the center of the circle
     * @param y      The y-coordinate of the center of the circle
     * @param radius The radius of the center of the circle
     */
    private void paintOval(Graphics g, int x, int y, int radius) {
        g.drawOval(x - radius / 2, (y - radius / 2), radius, radius);
        g.fillOval((x - radius / 2), (y - radius / 2), radius, radius);
    }
}