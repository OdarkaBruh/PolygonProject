import java.awt.*;

/** This method performs all the mathematical calculations (calculates vertices) */
public class MathManager implements Constants {
    /** The x-coordinate of the center of the polygon */
    public static int centralX = 0;
    /** The y-coordinate of the center of the polygon */
    public static int centralY = 0;

    /** Number of sides of a polygon */
    public static int sides = SIDES_INNITIAL;
    /** The distance from the center to the vertex of a polygon */
    public static int radiusOutcircle = RADIUS_OUTCIRCLE;

    /**
     * Changes centralX and centralY to the center of the panel
     *
     * @param panelWidth  Width of the parent panel
     * @param panelHeight Height of the parent panel
     */
    public static void setNewCenterCoordinates(int panelWidth, int panelHeight) {
        centralX = panelWidth / 2;
        centralY = panelHeight / 2;
    }

    /**
     * Calculates all the vertices
     *
     * @param x The x-coordinate of the first point
     * @param y The y-coordinate of the first point
     * @return An array containing the coordinates of all vertices
     */
    public static int[][] calculateVertices(int x, int y) {
        int[][] vertices = calculateVerticesFromFirstPoint(createFirstDot(x, y));
        return normalizeCoordinates(vertices);
    }

    /**
     * Adds first point to the array
     *
     * @param x The x-coordinate of the first point
     * @param y The y-coordinate of the first point
     * @return the vertices array
     */
    public static int[][] createFirstDot(int x, int y) {
        int[][] vertices = new int[sides][2];
        vertices[0][0] = x;
        vertices[0][1] = y;
        return vertices;
    }

    /**
     * Calculates all other vertices from the first one;
     *
     * @param vertices the array of vertices containing only one vertex
     * @return the filled vertex array
     */
    public static int[][] calculateVerticesFromFirstPoint(int[][] vertices) {
        double angle = MathFormulas.getInnerAngle(sides);
        for (int i = 1; i < sides; i++) {
            vertices[i][0] = MathFormulas.getNewX(vertices[0][0], vertices[0][1], i * angle);
            vertices[i][1] = MathFormulas.getNewY(vertices[0][0], vertices[0][1], i * angle);
        }
        return vertices;
    }

    /**
     * Convert the vertex coordinates to the Java Swing coordinate system.
     * <p/>
     * All the vertices were calculated as if the center of the polygon == the center of the coordinate system
     * Now these coordinates need to be mapped to the Java Swing coordinate system.
     *
     * @param vertices the vertex array
     * @return the vertex array containing coordinates ready to be plotted
     */
    public static int[][] normalizeCoordinates(int[][] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i][0] += centralX;
            vertices[i][1] += centralY;
        }
        return vertices;
    }

}
