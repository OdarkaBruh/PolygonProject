public class MathFormulas implements Constants{

    public static double getInnerAngle(int sides) {
        return 360.0 / sides;
    }

    public static int getNewX(int x, int y, double angle) {
        angle = toDegrees(angle);
        return (int) (x*Math.cos(angle)-y*Math.sin(angle));
    }

    public static int getNewY(int x, int y, double angle) {
        angle = toDegrees(angle);
        return (int) (x*Math.sin(angle)+y*Math.cos(angle));
    }

    public static double toDegrees(double angle){
        return angle*Math.PI/180;
    }
}
