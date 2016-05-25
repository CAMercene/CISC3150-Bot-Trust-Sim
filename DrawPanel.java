package botTrust;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Class that draws the orange and blue robots with their number lines to the JPanel,
 * as well as the current case and current time is displayed. The positions of the robots,
 * case number, and time is consistently updated.
 * @author Chad
 *
 */
public class DrawPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int PREF_W = 1030; // Preferred width of display
	private static final int PREF_H = 300; // Preferred height of display
	private static final int GAP = 10; // Distance between each number on number line
	private static final int START = 0; // Start of number line
	private static final int END = 100; // End of number line
	private static final int VERT_LINE_HEIGHT = 20; // Length of peg on number line
	private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 14); // Font for each number displayed
	private static final int TEXT_GAP = 2; // Distance between number and line
	
	int orangePos = 1; // Position of orange robot
	int bluePos = 1; // Position of blue robot
	int case_num = 0; // Case number
	int time = 0; // Current time
	/**
	 * Paints the JPanel with number line, robot positions, case number, and time.
	 * It will always paint every time it needs to update.
	 */
	protected void paintComponent(Graphics g) {
	    // call super method
		super.paintComponent(g);

		// Return width and height of display
	    int width = getWidth();
	    int height = getHeight();

	    // Draw the current case number and the time the moment a robot presses the button
	    g.setFont(new Font("default", Font.BOLD, 36));
	    g.drawString("Case #" + case_num, GAP, 50);
	    g.drawString("Time: " + time, 500, 50);
	    
	    // Drawing the first number line
	    int x1 = GAP;
	    int y1 = 2*(height / 5);
	    int x2 = width - 2 * GAP;
	    int y2 = y1; 
	    g.drawLine(x1, y1, x2, y2);

	    for (int i = START; i <= END; i++) {
	    	int x = (i * (x2 - x1)) / (END - START) + GAP;
		    drawNumberLine(g, i, x, y1, VERT_LINE_HEIGHT);
		}
	    
	    // Drawing the orange robot in its current position
	    g.setColor(Color.ORANGE);
	    g.fillOval(5 + 10*orangePos, y1-35, 10, 10);
	    g.setColor(Color.BLACK);
	      
	    // Drawing the second number line
	    x1 = GAP;
	    y1 = 4*(height / 5);
	    x2 = width - 2 * GAP;
	    y2 = y1;
	    g.drawLine(x1, y1, x2, y2);

	    for (int i = START; i <= END; i++) {
	    	int x = (i * (x2 - x1)) / (END - START) + GAP;
		    drawNumberLine(g, i, x, y1, VERT_LINE_HEIGHT);
		}
	    
	    // Drawing the blue robot in its current position
	    g.setColor(Color.BLUE);
	    g.fillOval(5 + 10*bluePos, y1-35, 10, 10);
	      
	    repaint();    
	}
	/**
	 * Draws the number line
	 * @param g
	 * 		variable of Graphics class
	 * @param number
	 * 		Number to be shown on the number line
	 * @param x
	 * 		x-coordinate of the number
	 * @param y
	 * 		y-coordinate of the number
	 * @param vertLineHeight
	 * 		height of vertical line on number line
	 */
	private void drawNumberLine(Graphics g, int number, int x, int y, int vertLineHeight) {
		// Drawing the vertical line
		int x1 = x;
	    int y1 = y;
	    int x2 = x;
	    int y2;
	    
	    // Numbers divisible by 5 will have longer vertical lines than any other number
	    if((number % 5) == 0)
	    	y2 = y - vertLineHeight;
	    else
	    	y2 = y - vertLineHeight/2;
	    g.drawLine(x1, y1, x2, y2);
	    
	    // Only display the number on the number line if number is divisible by 5
	    if((number % 5) == 0) {
	    	String text = String.valueOf(number);
	      	g.setFont(FONT);
	      	FontMetrics fontMetrics = g.getFontMetrics();
	      	int textX = x - fontMetrics.stringWidth(text) / 2;
	      	int textY = y + fontMetrics.getHeight() + TEXT_GAP;
	      	g.drawString(text, textX, textY);
	    }
	}
	/**
	 * Returns the preferred size of the display
	 */
	public Dimension getPreferredSize() {
	   if (isPreferredSizeSet()) {
	      return super.getPreferredSize();
	   }
	   return new Dimension(PREF_W, PREF_H);
	}
}
