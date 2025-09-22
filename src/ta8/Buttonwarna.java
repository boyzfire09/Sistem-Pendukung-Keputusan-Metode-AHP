package ta8;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Buttonwarna extends JButton {

    private boolean over;

    // Warna default
    private Color colorStart = new Color(52, 152, 219);
    private Color colorEnd = new Color(0, 204, 153);

    // Warna hover (fill over)
    private Color hoverStart = new Color(0, 153, 255);
    private Color hoverEnd = new Color(0, 255, 204);

    // Warna klik
    private Color colorClick = new Color(30, 92, 133);

    private int strokeWidth = 2;
    private int roundedCorner = 30;

    // Constructor
    public Buttonwarna() {
        setOpaque(false);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);

        // Mouse event untuk hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                over = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                over = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);

            GradientPaint gp;

            if (getModel().isPressed()) {
                gp = new GradientPaint(0, 0, colorClick, getWidth(), getHeight(), colorClick.darker());
            } else if (over) {
                gp = new GradientPaint(0, 0, hoverStart, getWidth(), getHeight(), hoverEnd);
            } else {
                gp = new GradientPaint(0, 0, colorStart, getWidth(), getHeight(), colorEnd);
            }

            g2d.setPaint(gp);
            g2d.fillRoundRect(s, s, w, h, roundedCorner, roundedCorner);
        }

        super.paintComponent(g);
    }

    // Getter & Setter PROPERTI
    public Color getColorStart() {
        return colorStart;
    }

    public void setColorStart(Color colorStart) {
        this.colorStart = colorStart;
        repaint();
    }

    public Color getColorEnd() {
        return colorEnd;
    }

    public void setColorEnd(Color colorEnd) {
        this.colorEnd = colorEnd;
        repaint();
    }

    public Color getHoverStart() {
        return hoverStart;
    }

    public void setHoverStart(Color hoverStart) {
        this.hoverStart = hoverStart;
        repaint();
    }

    public Color getHoverEnd() {
        return hoverEnd;
    }

    public void setHoverEnd(Color hoverEnd) {
        this.hoverEnd = hoverEnd;
        repaint();
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        repaint();
    }

    public int getRoundedCorner() {
        return roundedCorner;
    }

    public void setRoundedCorner(int roundedCorner) {
        this.roundedCorner = roundedCorner;
        repaint();
    }
}
