package ta8;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class PasswordField extends JPasswordField {
    
    private PasswordFieldUI textUI;

    public PasswordField() {
        textUI = new PasswordFieldUI(this);
        setUI(textUI);
    }

    private class PasswordFieldUI extends BasicPasswordFieldUI {
        
        private JPasswordField passwordField;
        private RoundedBorder border;
        private int round = 15;
        private List<String> items = new ArrayList<>();

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
            border.setRound(round);
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
        
        public PasswordFieldUI(JPasswordField passwordField) {
            this.passwordField = passwordField;
            border = new RoundedBorder(10);
            border.setRound(round);
            passwordField.setBorder(border);
            passwordField.setOpaque(false);
            passwordField.setSelectedTextColor(Color.BLACK);
            passwordField.setSelectionColor(Color.WHITE);
            passwordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    border.setColor(Color.WHITE);
                    passwordField.repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    border.setColor(Color.WHITE);
                    passwordField.repaint();
                }
            });
        }

        @Override
        protected void paintBackground(Graphics grphcs) {
            if (passwordField.isOpaque()) {
                Graphics2D g2 = (Graphics2D) grphcs.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(passwordField.getBackground());
                g2.fillRoundRect(0, 0, passwordField.getWidth(), passwordField.getHeight(), round, round);
                g2.dispose();
            }
        }
    }

    private class RoundedBorder extends EmptyBorder {
        
        private Color focusColor = (Color.WHITE);
        private Color color = (Color.WHITE);
        private int round;

        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public RoundedBorder(int border) {
            super(border, border, border, border);
        }

        @Override
        public void paintBorder(Component c, Graphics grphcs, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.isFocusOwner() ? focusColor : color);
            g2.drawRoundRect(x, y, width - 1, height - 1, round, round);
            g2.dispose();
        }
    }
}
