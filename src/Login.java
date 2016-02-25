
//Paula Bintner

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {

	JLabel title = new JLabel("Login to Defect Tracker System");
	JLabel userIdLabel = new JLabel("User ID");
	JTextField userId = new JTextField(25);
	JButton submitBtn = new JButton("Submit");

	ListUsersDAO usersDAO = new ListUsersDAO();

	public Login(){
		
		title.setForeground(Color.blue);
		title.setFont(new Font("Serif",Font.BOLD,20));
		
		ButtonListener b = new ButtonListener();
		submitBtn.addActionListener(b);

		add(title);
		add(userIdLabel);
		add(userId);
		add(submitBtn);
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitBtn) {
				String str1 = userId.getText();
				try {
					String str = usersDAO.getCurrentListFromUser(str1);
					if (!str.equals(" ")){
						removeAll();
						JPanel newPanel = new MainPanel();
						add(newPanel);
						revalidate();
						repaint();
					} else {
						JOptionPane.showMessageDialog(null,"Incorrect user-Id ..Try Again with Valid User ID");
						userId.setText("");	
					}		
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
	}
}