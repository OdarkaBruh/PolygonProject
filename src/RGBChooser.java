import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RGBChooser extends JComponent implements ChangeListener, UIConstants{
    private static final int DEFAULT_VALUE_COLOR = 50;

    private JPanel colorPanel;
    private final JSlider[] valuesRGBSlider = new JSlider[4];
    private JSlider lineLengthSlider;
    public int colorIndex;

    RGBChooser(JPanel parent, int colorIndex){
        this.colorIndex = colorIndex;

        JPanel containerPanel = new JPanel();
        containerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel rgbPanel = createRGBPanel();
        colorPanel = createColorDisplayPanel();
        rgbPanel.setVisible(true);
        containerPanel.add(colorPanel);
        containerPanel.add(rgbPanel);
        containerPanel.add(createLineWidthSlider());
        parent.add(containerPanel);

    }

    private JSlider createLineWidthSlider(){
        lineLengthSlider = new JSlider(JSlider.VERTICAL, SLIDER_THICKNESS_MIN, SLIDER_THICKNESS_MAX,
                Main.lineWidth[colorIndex]);
        lineLengthSlider.addChangeListener(this);
        return lineLengthSlider;
    }

    private JPanel createRGBPanel() {
        JPanel parent = new JPanel();
        parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
        parent.setBorder(new EmptyBorder(10, 10, 10, 10));
        createColors(parent);
        return parent;
    }

    private void createColors(JPanel parent){
        Color color = Main.colors[colorIndex];
        int[] c = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};

        for (int i = 0; i < 4; i++) createColor(i, RGB_LABELS[i], c[i],  parent);
    }
    private void createColor(int i, String text, int defaultValue, JPanel parent){
        valuesRGBSlider[i] = createJSliderRGB(defaultValue);
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        parent.add(label);
        parent.add(valuesRGBSlider[i]);
    }

    private JPanel createColorDisplayPanel(){
        JPanel colorDisplayPanel = new JPanel();
        colorDisplayPanel.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH, 200));
        colorDisplayPanel.setBackground(Main.colors[colorIndex]);
        return colorDisplayPanel;
    }

    private JSlider createJSliderRGB(int defaultValue){
        JSlider slider = new JSlider(JSlider.HORIZONTAL,
                0, 255, defaultValue);

        slider.addChangeListener(this);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(lineLengthSlider)) Main.lineWidth[colorIndex] = lineLengthSlider.getValue();
        else {
            Color color = new Color(valuesRGBSlider[0].getValue(), valuesRGBSlider[1].getValue(), valuesRGBSlider[2].getValue(), valuesRGBSlider[3].getValue());
            Main.colors[colorIndex] = color;
            colorPanel.setBackground(color);
        }
        UI.colorChanged();
    }
}
