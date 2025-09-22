package ta8;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class panelgabung2 extends JPanel {
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private Color colorStart = Color.BLUE;
    private Color colorEnd = Color.GREEN;

    public panelgabung2() {
        setOpaque(false);
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradient = new GradientPaint(0, 0, colorStart, getWidth(), getHeight(), colorEnd);
        g2.setPaint(gradient);

        Shape shape = createSmoothRoundedShape(getWidth(), getHeight());
        g2.fill(shape);

        g2.dispose();
    }

    private Shape createSmoothRoundedShape(int width, int height) {
        int rTL = Math.min(roundTopLeft, Math.min(width, height));
        int rTR = Math.min(roundTopRight, Math.min(width, height));
        int rBR = Math.min(roundBottomRight, Math.min(width, height));
        int rBL = Math.min(roundBottomLeft, Math.min(width, height));

        rTL = Math.min(rTL, Math.min(width / 2, height / 2));
        rTR = Math.min(rTR, Math.min(width / 2, height / 2));
        rBR = Math.min(rBR, Math.min(width / 2, height / 2));
        rBL = Math.min(rBL, Math.min(width / 2, height / 2));

        Path2D path = new Path2D.Double();

        path.moveTo(rTL, 0);

        // Top
        path.lineTo(width - rTR, 0);
        if (rTR > 0) path.quadTo(width, 0, width, rTR);

        // Right
        path.lineTo(width, height - rBR);
        if (rBR > 0) path.quadTo(width, height, width - rBR, height);

        // Bottom
        path.lineTo(rBL, height);
        if (rBL > 0) path.quadTo(0, height, 0, height - rBL);

        // Left
        path.lineTo(0, rTL);
        if (rTL > 0) path.quadTo(0, 0, rTL, 0);

        path.closePath();
        return path;
    }
}
