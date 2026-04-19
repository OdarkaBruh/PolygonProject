import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class UI implements UIConstants, ChangeListener {
    private static final JFrame frame = createFrame();
    private static final JPanel panelUI = new JPanel();

    private static int frameHeight;
    private static int frameWidth;

    JSpinner[] spinnerValues = new JSpinner[2];

    public void init(Main main) {
        frame.add(createGraphPanel(main));
        createMainPanel();
        frame.add(panelUI);
        frame.add(new RGBChooser(panelUI, 0));
        frame.add(new RGBChooser(panelUI, 1));
        frame.repaint();
        main.updateUI();
    }

    private static JFrame createFrame(){
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

    private JPanel createGraphPanel(Main main){
        JPanel p = new JPanel();
        int offsetX = LEFT_PANEL_SIZE+BORDER_MARGIN;
        p.setLocation(offsetX, 0);
        p.setSize(new Dimension(frameWidth-offsetX, frameHeight));

        main.setPreferredSize(new Dimension(p.getWidth(), p.getHeight()));
        main.setLocation(0, 0);

        p.add(main);
        Main.centralX = p.getWidth()/2;
        Main.centralY = p.getHeight()/2;
        //p.setBackground(Color.green);
       // main.setBackground(Color.red);

        return p;
    }

    private void createMainPanel(){
        panelUI.setBackground(Color.green);
        panelUI.setBounds(BORDER_MARGIN, BORDER_MARGIN, LEFT_PANEL_SIZE, frameHeight-BORDER_MARGIN*2);

        panelUI.add(createJSpinner("Sides: ", 0,
                new SpinnerNumberModel(SIDES_INNITIAL, 1, 10000, 1)));

        panelUI.add(createJSpinner("Length: ", 1,
                new SpinnerNumberModel(RADIUS_OUTCIRCLE, 10, 450, 10)));
    }

    private JPanel createJSpinner(String text, int i, SpinnerNumberModel spinnerNumberModel){
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource().equals(spinnerValues[0])) Main.sides = (int) spinnerValues[0].getValue();
        else Main.lengthR = (int) spinnerValues[1].getValue();
        frame.repaint();
    }

    public static void colorChanged() {
        frame.repaint();
    }

}
