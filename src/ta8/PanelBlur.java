package ta8;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class PanelBlur extends JPanel {
    private int roundness = 20;
    private Color glassColor = new Color(255, 255, 255, 90); // Warna putih transparan
    private float blurOpacity = 0.4f; // Opasitas blur

    public PanelBlur() {
        setOpaque(false); // Membuat panel transparan
    }

    public int getRoundness() {
        return roundness;
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint();
    }

    public Color getGlassColor() {
        return glassColor;
    }

    public void setGlassColor(Color glassColor) {
        this.glassColor = glassColor;
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

        // Membuat efek transparan dan blur
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, blurOpacity));

        // Membuat panel dengan sudut membulat
        Shape glassPanel = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), roundness, roundness);
        g2.setColor(glassColor);
        g2.fill(glassPanel);

        // Menambahkan border transparan halus
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(255, 255, 255, 90));
        g2.draw(glassPanel);

        g2.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Glassmorphism");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Contoh penggunaan panel glassmorphism
        PanelBlur glassPanel = new PanelBlur();
        glassPanel.setBounds(50, 50, 300, 200);
        glassPanel.setGlassColor(new Color(255, 255, 255, 100)); // Warna semi-transparan
        glassPanel.setRoundness(30);
        glassPanel.setBlurOpacity(0.4f);

        frame.add(glassPanel);
        frame.setVisible(true);
    }
}
