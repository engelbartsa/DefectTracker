//Ashley Risius

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUser extends JPanel {

	String[] roles = {"Manager", "Developer", "Business Partner", "Help Desk"};
	JLabel title = new JLabel("Add New User");
	JLabel firstNameLabel = new JLabel("First Name: ");
	JTextField firstName = new JTextField(30);
	JLabel lastNameLabel = new JLabel("Last Name: ");
	JTextField lastName = new JTextField(30);
	JLabel roleLabel = new JLabel("Role: ");
	JComboBox role = new JComboBox(roles);
	JLabel userIdLabel = new JLabel("User Id: ");
	JTextField userId = new JTextField(30);
	JLabel emailLabel = new JLabel("Email: ");
	JTextField email = new JTextField(30);
	JButton back = new JButton("Back to Main");
	JButton addUser = new JButton("Add User");
	JLabel error = new JLabel();

	ListUsersDAO defectTracker = new ListUsersDAO();


	public AddUser() {

		ButtonListener b = new ButtonListener();
		addUser.addActionListener(b);
		back.addActionListener(b);

		setLayout(new BorderLayout());

		title.setFont(new Font("Serif", Font.BOLD, 20));
		error.setFont(new Font("Serif", Font.BOLD, 20));
		add(title, BorderLayout.NORTH);

		JPanel buttonLabels = new JPanel(new GridLayout(6, 0));
		JPanel textBoxes = new JPanel(new GridLayout(6, 0));

		buttonLabels.add(firstNameLabel);
		textBoxes.add(firstName);
		buttonLabels.add(lastNameLabel);
		textBoxes.add(lastName);
		buttonLabels.add(roleLabel);
		textBoxes.add(role);
		buttonLabels.add(userIdLabel);
		textBoxes.add(userId);
		buttonLabels.add(emailLabel);
		textBoxes.add(email);
		buttonLabels.add(error);

		add(buttonLabels, BorderLayout.WEST);
		add(textBoxes, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		buttonPanel.add(addUser);
		buttonPanel.add(back);

		add(buttonPanel, BorderLayout.SOUTH);

	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == addUser) {
				
				String tempUserId = userId.getText();
				String tempFirstName = firstName.getText();
				String tempLastName = lastName.getText();
				String tempEmail = email.getText();
				String tempRole = role.getSelectedItem().toString(); 
			 
				if (tempUserId.equals("")){
					error.setText("Enter a user id.");
					
				} else if (tempFirstName.equals("")){
					error.setText("Enter the first name.");
				} else if (tempLastName.equals("")){
					error.setText("Enter the last name.");
				} else if (tempEmail.equals("")) {
					error.setText("Enter an email address");
				} else if (tempRole.equals("")){
					error.setText("Enter the role.");
				} else {
				
				UserInfo u = new UserInfo(tempUserId, tempFirstName, tempLastName, tempRole, tempEmail );
				defectTracker.insertNewUser(u);
				
				userId.setText("");
				firstName.setText("");
				lastName.setText("");
				email.setText("");
				
				System.out.println("Add new user to database");
			}
			}

			if (e.getSource() == back) {
				removeAll();
				JPanel newPanel = new MainPanel();
				add(newPanel);
				revalidate();
				newPanel.repaint();
			}
		}

	}
}
