package botTrust;
/**
 * Class to run the GUI program that simulates "Bot Trust"
 * from Google Code Jam
 * @author Chad
 * 
 */
public class Driver {

	// Main method to run thread
	public static void main(String[] args) {
		GUI gui = new GUI();
		BotTrustGCJ gcj = new BotTrustGCJ(gui);
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				gcj.beginSim("A-small-practice.in");
				// gcj.beginSim("A-large-practice.in");
			}
		});
		thread.start();	
	}
}
