/** This class calculates variables associated with mathematical formulas */
public class MathFormulas implements Constants {
    /**
     * Finds the angle created by drawing lines from two adjacent vertices to the center of the polygon
     *
     * @param sides the number of sides of a polygon
     * @return the angle created by drawing lines from two adjacent vertices to the center
     */
    public static double getInnerAngle(int sides) {
        return 360.0 / sides;
    }

    /**
     * Find the new x-coordinate when the figure is rotated
     *
     * @param x     The old x-coordinate
     * @param y     The old y-coordinate
     * @param angle the angle of rotation
     * @return new x-coordinate
     */
    public static int getNewX(int x, int y, double angle) {
        angle = toDegrees(angle);
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    /**
     * Find the new y-coordinate when the figure is rotated
     *
     * @param x     The old x-coordinate
     * @param y     The old y-coordinate
     * @param angle the angle of rotation
     * @return new y-coordinate
     */
    public static int getNewY(int x, int y, double angle) {
        angle = toDegrees(angle);
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }

    /**
     * Converts degrees to rad
     *
     * @param angle the angle to convert
     * @return the angle in radians
     */
    public static double toDegrees(double angle) {
        return angle * Math.PI / 180;
    }
}
