import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerButton extends JButton {
    private Color bgColor = Color.BLACK;
    private Color hoverColor = Color.GRAY;
    private Color pressedColor = Color.GRAY;
    private int arcWidth = 10;
    private int arcHeight = 10;

    public RoundedCornerButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setPreferredSize(new Dimension(150, 40)); // Adjust the size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(bgColor);
        }

        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        super.paintComponent(g2);
        g2.dispose();
    }
}
