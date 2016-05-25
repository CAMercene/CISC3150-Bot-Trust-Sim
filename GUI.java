package botTrust;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class that displays the GUI program.
 * The GUI consists of the JPanel that paints the simulation of "Bot Trust"
 * and the JTextArea that displays the sequence and the resulting minimum time.
 * @author Chad
 *
 */
public class GUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int case_num = 0;
	
	JTextArea area;
	DrawPanel panel;
	
	public GUI() {
		JFrame frame = new JFrame("Number Line");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    
	    panel = new DrawPanel();
	    frame.getContentPane().add(panel, BorderLayout.PAGE_START);
	     
	    area = new JTextArea();
	    area.setEditable(false);
	    area.setLineWrap(true);
	    area.setWrapStyleWord(true);
	    
	    JScrollPane scroll = new JScrollPane();
	    scroll.getViewport().add(area);
	    scroll.setPreferredSize(new Dimension(300, 200));
	    
	    frame.add(scroll, BorderLayout.SOUTH);

	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	/**
	 * Sets the current case number to be displayed
	 * @param num
	 * 		Case number
	 */
	public void setCaseNumber(int num) {
		case_num = num;
		panel.case_num = num;
	}
	
	/**
	 * Sets the robots' positions
	 * @param orange
	 * 		Position for orange robot
	 * @param blue
	 * 		Position for blue robot
	 */
	public void setPositions(int orange, int blue) {
		panel.orangePos = orange;
		panel.bluePos = blue;
	}
	
	/**
	 * Sets the current time
	 * @param t
	 * 		Time to be displayed
	 */
	public void currentTime(int t) {
		panel.time = t;
	}
	
	/**
	 * Resets the robots' positions after each case
	 */
	public void resetBots() {
		panel.orangePos = 1;
		panel.bluePos = 1;
	}
	
	/**
	 * Displays sequence for each case onto the JTextArea
	 * @param sequence
	 * 		The order sequence to be displayed on the JTextArea
	 */
	public void appendSequence(String sequence) {
		area.append(sequence + "\n");
	}
	
	/**
	 * Displays resulting minimum time it takes to run the sequence
	 * onto the JTextArea
	 * @param time
	 * 		Minimum time to be displayed on the JTextArea
	 */
	public void appendMinimumTime(int time) {
		area.append(String.format("Case #%d: %d%n", case_num, time));
	}
	
	/**
	 * Resets time after each case
	 */
	public void resetTime() {
		panel.time = 0;
	}
}
