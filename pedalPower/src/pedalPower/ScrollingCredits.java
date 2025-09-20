package pedalPower;

import javax.swing.*;
import java.awt.*;

public class ScrollingCredits extends JFrame {
    private CreditPanel creditPanel;
    private Timer timer;
    private int yPosition;

    public ScrollingCredits() {
        setTitle("Thanks for Playing!");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        creditPanel = new CreditPanel();
        add(creditPanel);

        yPosition = getHeight(); // Start text from bottom
        timer = new Timer(30, e -> {
            yPosition -= 2; // Move text upwards
            if (yPosition + creditPanel.getTextHeight() < 0) {
                yPosition = getHeight(); // Reset when text scrolls out
            }
            creditPanel.setYPosition(yPosition);
            creditPanel.repaint();
        });
        setVisible(true);
        timer.start();
    }

    private class CreditPanel extends JPanel {
        private final String[] credits = {
            "Congratulations and very well done!",
            "The Pedal Power project is a great",
            "success, not only for your team, but",
            "for everyone in Maker’s Valley!",
            "",
            "",
            "Recent headlines say…",
            "",
            "Pedal Power puts the smile back on",
            "people’s faces! The air is cleaner,",
            "people are fitter and the roads are safer.",
            "",
            "",
            "Pedal Power to the rescue!",
            "Despite last night’s blackout traffic,",
            "lights stayed on and lives may have",
            "been saved, all thanks to the Pedal",
            "Power stations.",
            "",
            "",
            "",
            "Thanks for playing!",
            "",
            "",
            "Made by Euphrates <3",
            ""
        };

        private int yPosition;

        public void setYPosition(int yPosition) {
            this.yPosition = yPosition;
        }

        public int getTextHeight() {
            return credits.length * 30; // Approximate total height
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));

            int y = yPosition;
            for (String line : credits) {
                g.drawString(line, 50, y);
                y += 30; // Line spacing
            }
        }
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScrollingCredits().setVisible(true));
    }
    */
}
