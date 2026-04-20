import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/** creates an interface (left panel) */
public class UI implements Constants, ChangeListener {
    /** The main frame to redraw */
    private static final JFrame frame = createFrame();

    /** Height of the main frame */
    private static int frameHeight;
    /** Width of the main frame */
    private static int frameWidth;

    /** JSpinners to read values from (sides and radius) */
    JSpinner[] spinnerValues = new JSpinner[2];

    /**
     * Starts the creation of all visual objects
     *
     * @param main An instance of the Main class for linking the paintComponent() method
     */
    public void init(Main main) {
        JPanel containerUI = createMainPanel();
        frame.add(createGraphPanel(main));
        frame.add(containerUI);
        frame.add(new RGBChooser(containerUI, 0));
        frame.add(new RGBChooser(containerUI, 1));
        frame.repaint();
        main.updateUI();
    }

    /** Creates a frame and configuring its settings */
    private static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        frame.setVisible(true);
        frame.setLayout(null);

        frameHeight = frame.getContentPane().getHeight();
        frameWidth = frame.getContentPane().getWidth();
        return frame;
    }

    /**
     * Creates a panel that contains the Main class's paintComponent() method
     *
     * @param main An instance of the Main class
     * @return the right panel (where the polygon will be drawn)
     */
    private JPanel createGraphPanel(Main main) {
        int offsetX = LEFT_PANEL_SIZE + BORDER_MARGIN;
        JPanel drawingPanel = new JPanel();
        drawingPanel.setLocation(offsetX, 0);
        drawingPanel.setSize(new Dimension(frameWidth - offsetX, frameHeight));
        drawingPanel.add(main);

        main.setNewCenterCoordinates(drawingPanel.getWidth(), drawingPanel.getHeight());
        return drawingPanel;
    }

    /**
     * Creates the left panel containing sliders
     *
     * @return created panel
     */
    private JPanel createMainPanel() {
        JPanel containerUI = new JPanel();
        //containerUI.setBackground(Color.lightGray);
        containerUI.setBounds(BORDER_MARGIN, BORDER_MARGIN, LEFT_PANEL_SIZE, frameHeight - BORDER_MARGIN * 2);

        containerUI.add(createJSpinner("Sides: ", 0,
                new SpinnerNumberModel(SIDES_INNITIAL, 1, 10000, 1)));

        containerUI.add(createJSpinner("Length: ", 1,
                new SpinnerNumberModel(RADIUS_OUTCIRCLE, 10, 450, 10)));
        return containerUI;
    }

    /**
     * Creates a slider for the outcircle radius (Main.radiusOutcircle) / the number of sides (Main.sides)
     *
     * @param text               the text to display
     * @param i                  the index of the JSlider in spinnerValues
     * @param spinnerNumberModel SpinnerNumberModel with determined min, max, step
     * @return the panel container that contains this slider and respectful labels.
     */
    private JPanel createJSpinner(String text, int i, SpinnerNumberModel spinnerNumberModel) {
        JPanel p = new JPanel();
        JLabel l = new JLabel(text);
        JSpinner model = new JSpinner();
        model.setModel(spinnerNumberModel);
        model.addChangeListener(this);
        p.add(l);
        p.add(model);
        spinnerValues[i] = model;
        return p;
    }

    /**
     * Updates the relevant variables depending on which element has changed
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(spinnerValues[0])) MathManager.sides = (int) spinnerValues[0].getValue();
        else MathManager.radiusOutcircle = (int) spinnerValues[1].getValue();
        frame.repaint();
    }

    /** Repaints frame if any RGB slider is changed */
    public static void colorChanged() {
        frame.repaint();
    }

}
