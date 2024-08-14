import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class TitcTacToePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10,BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.setColor(Color.WHITE);
        g2d.setColor(Color.WHITE);
        

         int padding = 50; 

        // Draw the horizontal lines
        g2d.drawLine(padding, getHeight() / 3, getWidth() - padding, getHeight() / 3);
        g2d.drawLine(padding, 2 * getHeight() / 3, getWidth() - padding, 2 * getHeight() / 3);

        // Draw the vertical lines
        g2d.drawLine(getWidth() / 3, padding, getWidth() / 3, getHeight() - padding);
        g2d.drawLine(2 * getWidth() / 3, padding, 2 * getWidth() / 3, getHeight() - padding);
    }
}
