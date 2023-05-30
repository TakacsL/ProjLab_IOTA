package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class show a pipe by drawing a line between two other game objects
 */
public class PipePanel extends JLabel {
    /**
     * Coordinates for showing the pipe
     */
    int x1, x2, y1, y2, myX, myY;
    /**
     * Text for the pipe
     */
    String label;

    /**
     * Constructs the pipe's visual object
     * @param x1 start X coordinate
     * @param y1 start Y coordinate
     * @param x2 end X coordinate
     * @param y2 end Y coordinate
     * @param myX label X coordinate
     * @param myY label Y coordinate
     * @param label label text
     */
    public PipePanel(int x1, int y1, int x2, int y2, int myX, int myY, String label) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.myX = myX;
        this.myY = myY;
        this.label = label;

        setBounds(0, 0, 1000, 1000);
    }

    /**
     * Draws the component based on the coordinates
     * @param g Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.drawString(label, myX, myY + 30);
        g2.setStroke(new BasicStroke(6.0F));
        g2.setColor(Color.BLACK);
        g2.drawLine(myX, myY, x1, y1);
        g2.drawLine(myX, myY, x2, y2);

        setVisible(true);

        g2.dispose();
        g.dispose();
    }
}
