package ta8;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class BlurWarna2 extends JPanel {
    private int roundness = 30;
    private Color panelColor = new Color(255, 153, 51, 100); // orange warna transparan

    public BlurWarna2() {
        setOpaque(false); // Penting untuk efek transparan
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint();
    }

    public void setPanelColor(Color color) {
        this.panelColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), roundness, roundness);
        g2.setColor(panelColor);
        g2.fill(shape);

        // Garis putih halus di pinggir panel
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(255, 255, 255, 50));
        g2.draw(shape);

        g2.dispose();
    }

    // Contoh penggunaan
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Glassmorphism Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 350);
            frame.setLayout(null);
            frame.getContentPane().setBackground(new Color(30, 30, 30)); // latar belakang gelap

            BlurWarna2 panel = new BlurWarna2();
            panel.setBounds(80, 80, 320, 150);
            panel.setPanelColor(new Color(255, 153, 51, 100)); // orange transparan
            panel.setRoundness(25);

            JLabel label = new JLabel("Panel Transparan Berwarna", JLabel.CENTER);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            panel.setLayout(new BorderLayout());
            panel.add(label, BorderLayout.CENTER);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
