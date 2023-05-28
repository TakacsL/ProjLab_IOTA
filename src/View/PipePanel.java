package View;

import javax.swing.*;
import java.awt.*;

public class PipePanel extends JLabel {

    int x1, x2, y1, y2, myX, myY;
    String label;

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
