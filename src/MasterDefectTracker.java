//Ashley Risius

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MasterDefectTracker {

	public static void main(String[] args) {

		JFrame master = new JFrame();
		JPanel mainPanel = new Login();
		//JPanel mainPanel = new MainPanel();
		master.add(mainPanel);

		master.setTitle("Login to Defect Tracker System");
		master.setVisible(true);
		master.setSize(900, 900);
		master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
