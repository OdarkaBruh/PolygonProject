import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * A class for creating an element that allows the user to change the color (RGBA value).
 * (Note: JColorChooser looks way too overcrowded and not user-friendly for this task)
 */
public class RGBChooser extends JComponent implements ChangeListener, Constants {
    /**
     * All slider controls are associated with elements in a specific array. It is the index of element in array.
     * <p/>
     * More specifically, the sliders control variable lineWidth in the Main.java file
     */
    public int colorIndex;

    /** The panel which displays color with these RGB values */
    private final JPanel colorDisplayPanel;
    /** Array of all sliders to read them all if they are changed */
    private final JSlider[] valuesRGBSlider = new JSlider[4];
    /** slider which tracks the thickness of line (Main.lineWidth) */
    private JSlider lineLengthSlider;

    /**
     * Creates an element consisting of four sliders—one for each RGB channel (red, green, blue, alpha),
     * a color panel, and a line thickness slider.
     */
    RGBChooser(JPanel parent, int colorIndex) {
        this.colorIndex = colorIndex;

        JPanel containerPanel = new JPanel();
        containerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel rgbPanel = createRGBPanel();

        colorDisplayPanel = createColorDisplayPanel();
        containerPanel.add(colorDisplayPanel);

        containerPanel.add(rgbPanel);
        containerPanel.add(createLineWidthSlider());
        parent.add(containerPanel);
    }

    /**
     * Creates a slider which manages line width
     *
     * @return created slider
     */
    private JSlider createLineWidthSlider() {
        lineLengthSlider = new JSlider(JSlider.VERTICAL, SLIDER_THICKNESS_MIN, SLIDER_THICKNESS_MAX,
                Main.lineWidth[colorIndex]);
        lineLengthSlider.addChangeListener(this);
        return lineLengthSlider;
    }

    /**
     * Creates a panel with 4 sliders for each RGB (RGBA) channel
     *
     * @return a parent (container) panel with sliders
     */
    private JPanel createRGBPanel() {
        Color color = Main.colors[colorIndex];

        JPanel parent = new JPanel();
        parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
        parent.setBorder(new EmptyBorder(10, 10, 10, 10));

        int[] c = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
        for (int i = 0; i < 4; i++) createJSliderRGB(i, RGB_LABELS[i], c[i], parent);
        return parent;
    }

    /**
     * Creates a label and a slider which will be responsible for one of the RGB channels
     *
     * @param i            index of the channel to store it in an array in the correct order:
     *                     (0 = red, 1 = green, 2 = blue, 3 = alpha)
     * @param text         label's text
     * @param defaultValue default value of the slider.
     * @param parent       the parent panel to which the created object should be added
     */
    private void createJSliderRGB(int i, String text, int defaultValue, JPanel parent) {
        valuesRGBSlider[i] = new JSlider(JSlider.HORIZONTAL,
                0, 255, defaultValue);

        valuesRGBSlider[i].addChangeListener(this);
        valuesRGBSlider[i].setMajorTickSpacing(50);
        valuesRGBSlider[i].setMinorTickSpacing(10);
        valuesRGBSlider[i].setPaintTicks(true);
        valuesRGBSlider[i].setPaintLabels(true);

        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);

        parent.add(label);
        parent.add(valuesRGBSlider[i]);
    }

    /**
     * Creates an empty panel whose color matches these RGB values
     *
     * @return created panel
     */
    private JPanel createColorDisplayPanel() {
        JPanel colorDisplayPanel = new JPanel();
        colorDisplayPanel.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH, 200));
        colorDisplayPanel.setBackground(Main.colors[colorIndex]);
        return colorDisplayPanel;
    }

    /**
     * The JSlider method for tracking changes. It updates either the variable string length or the color variable,
     * depending on the caller.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(lineLengthSlider)) Main.lineWidth[colorIndex] = lineLengthSlider.getValue();
        else {
            Color color = new Color(valuesRGBSlider[0].getValue(), valuesRGBSlider[1].getValue(), valuesRGBSlider[2].getValue(), valuesRGBSlider[3].getValue());
            Main.colors[colorIndex] = color;
            colorDisplayPanel.setBackground(color);
        }
        UI.colorChanged();
    }
}
