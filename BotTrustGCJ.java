package botTrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BotTrustGCJ {
	
	GUI gui;
	
	// Stores sequence of robots ("O" or "B")
	Queue<String> orderSequence = new LinkedList<String>();
	
	// Stores the number of buttons to be pressed for each case
	Queue<Integer> num_buttons = new LinkedList<Integer>();
	
	// Stores the numbers of the buttons to be pressed by the orange robot
    Deque<Integer> orangeSequence =  new ArrayDeque<Integer>();
    
    // Stores the numbers of the buttons to be pressed by the blue robot
    Deque<Integer> blueSequence = new ArrayDeque<Integer>();
    
    String robot;
    String sequence = "";
    int buttons;
    int num_cases;
    int orangePos;
    int bluePos;
    int min_time;
    
    int orangeSwitch;
	int orangeTime;
	int blueSwitch;
	int blueTime;
	
	public BotTrustGCJ(GUI gui) {
		this.gui = gui;
	}
	
	/**
	 * Begins the simulation for "Bot Trust"
	 * @param fileName
	 * 		Name of file to be opened
	 */
	public void beginSim(String fileName) {
		File file = new File(fileName);
		
		try {
			// Open file
			Scanner sc = new Scanner(file);
			num_cases = sc.nextInt(); // Retrieve number of cases to run
			
			// For each case, read in the number of buttons
			// and the order sequence
			for(int i = 0; i < num_cases; i ++) {
				int num_actions = sc.nextInt();
				num_buttons.add(num_actions);
				
				for(int j = 0; j < num_actions; j++) {
					robot = sc.next();
					orderSequence.add(robot);
					
					if(robot.equals("O")) {
						orangeSequence.add(sc.nextInt());
					}
					else {
						blueSequence.add(sc.nextInt());
					}
				}
			}
			sc.close();
	    } catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Start simulation for each case
		for(int k = 1; k <= num_cases; k++) {
			this.gui.setCaseNumber(k);
			buttons = num_buttons.remove();
			sequence += String.format("%d ", buttons);
			
			min_time = 0; // Reset time
			orangePos = 1; // Reset orange robot position
			bluePos = 1; // Reset blue robot position
			
			for(int m = 0; m < buttons; m++) {
				robot = orderSequence.remove();
				sequence += (robot + " ");
				
				if(robot.equals("O")) {
					this.calculateOrangeBot();
					sequence += (orangeSwitch + " ");
					this.setBlueBot();
				}
				else {
					this.calculateBlueBot();
					sequence += (blueSwitch + " ");
					this.setOrangeBot();
				}
				
				this.gui.setPositions(orangePos, bluePos);
				this.gui.currentTime(min_time);
				
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {

				}
			}
			
			this.gui.appendSequence(sequence);
			sequence = ""; // Reset the sequence
			this.gui.resetBots();
			this.gui.appendMinimumTime(min_time);
			this.gui.resetTime();
		}
	}
	
	/**
	 * Calculate the position for the orange robot if it is 
	 * the orange robot's turn to press its button in the sequence
	 */
	public void calculateOrangeBot() {
		orangeSwitch = orangeSequence.removeFirst();
		orangeTime = Math.abs(orangeSwitch - orangePos) + 1;
		min_time += orangeTime;
		orangePos = orangeSwitch;
	}
	
	/**
	 * Calculate the position for the blue robot if it is 
	 * the blue robot's turn to press its button in the sequence
	 */
	public void calculateBlueBot() {
		blueSwitch = blueSequence.removeFirst();
        blueTime = Math.abs(blueSwitch - bluePos) + 1;
        min_time += blueTime;
        bluePos = blueSwitch;
	}
	
	/**
	 * Set the orange robot's position when it is not the active robot
	 * to press its button. While the orange robot does not need to press
	 * its button, it needs to be prepared when it is its turn in the sequence.
	 */
	public void setOrangeBot() {
		if(!orangeSequence.isEmpty()) {
			orangeSwitch = orangeSequence.getFirst();
	        orangeTime = Math.abs(orangeSwitch - orangePos);
	        if(orangeTime >= blueTime) {
	        	orangePos = (orangeSwitch > orangePos) ? 
	        			(orangePos + blueTime) : (orangePos - blueTime);
	        } else { orangePos = orangeSwitch; }
		}
	}
	
	/**
	 * Set the blue robot's position when it is not the active robot
	 * to press its button. While the blue robot does not need to press
	 * its button, it needs to be prepared when it is its turn in the sequence.
	 */
	public void setBlueBot() {
		if(!blueSequence.isEmpty()) {
			blueSwitch = blueSequence.getFirst();
	        int blueTime = Math.abs(blueSwitch - bluePos);
	        if(blueTime >= orangeTime) {
	            bluePos = (blueSwitch > bluePos) ? 
	            		(bluePos + orangeTime) : (bluePos - orangeTime);
	          } else { bluePos = blueSwitch; }
	    }
	}
}
