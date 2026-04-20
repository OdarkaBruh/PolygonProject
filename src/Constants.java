import java.awt.*;

/**
 * Interface for all constants.
 * It isn't perfect solution, but I really wanted to try this out.
 */
public interface Constants {
    /** The width of the frame */
    static final int APPLICATION_WIDTH = 1300;
    /** The height of the frame */
    static final int APPLICATION_HEIGHT = 700;

    /** The initial value of the radius of outcircle */
    static final int RADIUS_OUTCIRCLE = 250;
    /** The initial value of the number of sides */
    static final int SIDES_INNITIAL = 10;

    /** The offset from the edge of the frame */
    static final int BORDER_MARGIN = 20;
    /** The size of the left panel (with UI elements) */
    static final int LEFT_PANEL_SIZE = APPLICATION_WIDTH / 4;

    /** The width of panel which shows color (it is located to the left of RGB sliders) */
    static final int COLOR_PANEL_WIDTH = 18;
    /** The text above each of the four RGB sliders */
    static final String[] RGB_LABELS = new String[]{"Red: ", "Green: ", "Blue: ", "Alpha: "};

    /** The minimum value of the slider that determines the line thickness */
    static final int SLIDER_THICKNESS_MIN = 1;
    /** The maximum value of the slider that determines the line thickness */
    static final int SLIDER_THICKNESS_MAX = 20;
}
