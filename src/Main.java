import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Main extends JPanel implements Constants{
    static int sides = SIDES_INNITIAL;
    static int lengthR = RADIUS_OUTCIRCLE;
    
    private static UI ui = new UI();
    public static int centralX = (APPLICATION_WIDTH/3) + SPINNER_WIDTH;
    public static int centralY = (APPLICATION_HEIGHT/3);

    public static Color[] colors = new Color[]{Color.BLACK, Color.lightGray, Color.red,};
    public static int[] lineWidth = new int[]{3,1};

    public static void main(String[] args) {
        ui.init(new Main());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int[][] vertices = createFirstDot(0, -lengthR);
        vertices = moveCoordinatesArr(calculateVerticesFromFirstPoint(vertices));

        drawLinesFromCenter(g, vertices);
        drawVerticesAndSides(g, vertices);
        drawCenter(g);
    }

    public int[][] createFirstDot(int x, int y){
        int[][] vertices = new int[sides][2];
        vertices[0][0] = x;
        vertices[0][1] = y;
        return vertices;
    }

    public int[][] calculateVerticesFromFirstPoint(int[][] vertices) {
        double angle = MathFormulas.getInnerAngle(sides);

        for (int i = 1; i < sides; i++){
            vertices[i][0] = MathFormulas.getNewX(vertices[0][0], vertices[0][1], i*angle);
            vertices[i][1] = MathFormulas.getNewY(vertices[0][0], vertices[0][1], i*angle);
        }
        return vertices;
    }

    public int[][] moveCoordinatesArr(int[][] vertices){
        for (int i = 0; i < vertices.length; i++){
            vertices[i][0] +=centralX;
            vertices[i][1] +=centralY;
        }
        return vertices;
    }

    public void drawVerticesAndSides(Graphics g, int[][] vertices) {
        g.setColor(colors[0]);
        for (int i = 0; i < vertices.length; i++){
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(lineWidth[0]));

            if (i != 0 ) g2.drawLine(vertices[i][0], vertices[i][1], vertices[i-1][0], vertices[i-1][1]);
            else g2.drawLine(vertices[0][0], vertices[0][1],
                    vertices[vertices.length-1][0], vertices[vertices.length-1][1]);

            paintOval(g, vertices[i][0], vertices[i][1], lineWidth[0]+8/lineWidth[0]);
        }
    }

    public void drawLinesFromCenter(Graphics g, int[][] vertices) {
        g.setColor(colors[1]);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(lineWidth[1]));
        for (int[] vertex : vertices) {
            g2.drawLine(centralX, centralY, vertex[0], vertex[1]);
        }
    }

    public void drawCenter(Graphics g) {
        g.setColor(colors[2]);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        paintOval(g, centralX, centralY, 5);
    }

    public void paintOval(Graphics g, int x1, int y1, int radius){
        g.drawOval(x1-radius/2, (y1-radius/2), radius, radius);
        g.fillOval((x1-radius/2), (y1-radius/2), radius, radius);
    }
}