package ta8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class BlurHover extends JPanel {
    private int roundness = 20;
    private Color glassColor = new Color(255, 255, 255, 90); // Warna default
    private float blurOpacity = 0.4f;
    
    private Color defaultColor = glassColor;
    private Color hoverColor = new Color(255,153,51); // Warna saat hover

    private boolean hovered = false;

    public BlurHover() {
        setOpaque(false);

        // Listener untuk hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        repaint();
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
        this.glassColor = defaultColor;
        repaint();
    }

    public int getRoundness() {
        return roundness;
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint();
    }

    public float getBlurOpacity() {
        return blurOpacity;
    }

    public void setBlurOpacity(float blurOpacity) {
        this.blurOpacity = blurOpacity;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, blurOpacity));

        Shape glassPanel = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), roundness, roundness);

        // Pakai warna hover kalau sedang di-hover
        g2.setColor(hovered ? hoverColor : defaultColor);
        g2.fill(glassPanel);

        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(255, 255, 255, 90));
        g2.draw(glassPanel);

        g2.dispose();
    }

    // Contoh penggunaan
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Glassmorphism Hover");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        BlurHover menu = new BlurHover();
        menu.setBounds(50, 50, 300, 150);
        menu.setDefaultColor(new Color(255, 255, 255, 90));
        menu.setHoverColor(new Color(0, 204, 102, 120)); // hijau pas hover
        menu.setRoundness(30);
        menu.setBlurOpacity(0.4f);

        JLabel label = new JLabel("Menu 1", JLabel.CENTER);
        label.setForeground(Color.BLACK);
        menu.setLayout(new BorderLayout());
        menu.add(label);

        frame.add(menu);
        frame.setVisible(true);
    }
}
