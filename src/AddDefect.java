//Ashley Risius

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDefect extends JPanel {

	//not adding defectId as the table should add sequentially add this
	//not adding close date since this is the open slot.
	//not adding reportId as this should be whoever is logged in
	//JLabel openDate = new JLabel("Date Opened: "); //do i need this or will table populate current date?
	//JComboBox userId = new JComboBox(); //do i need this?  what's the different between this and reported/assignee?
	
	String[] statuses = {"Open", "In Progress", "Closed"};
	String[] priorities = {"Low", "Medium", "High"};
	
	JLabel title = new JLabel("Enter the defect information here: ");
	JLabel summaryLabel = new JLabel("Defect Summary: ");
	JTextField summary = new JTextField(10);
	JLabel descriptionLabel = new JLabel("Enter a description here: ");
	JTextField description = new JTextField(10);
	JLabel priorityLabel = new JLabel("Priority: ");
	JComboBox priority = new JComboBox(priorities);
	JLabel statusLabel = new JLabel("Status: ");
	JComboBox status = new JComboBox(statuses);
	JLabel assigneeIDLabel = new JLabel("Assignee ID: ");
	ListUsersDAO userTracker = new ListUsersDAO();
	ArrayList<UserInfo> assigneeList = new ArrayList<UserInfo>(userTracker.arrayList);
	JComboBox assigneeID = new JComboBox(assigneeList.toArray());
	JLabel reporterIDLabel = new JLabel("Reporter ID: ");
	JComboBox reporterID = new JComboBox(assigneeList.toArray());
	JLabel commentsLabel = new JLabel("Comments: ");
	JTextField comments = new JTextField(10);
	
	JButton submit = new JButton("Submit");
	JButton back = new JButton("Back to Main");
	JButton viewDefects = new JButton("View Defects");
	ListDefectsDAO defectTracker = new ListDefectsDAO();
	
	public AddDefect() {
		
		ButtonListener b = new ButtonListener();
		submit.addActionListener(b);
		viewDefects.addActionListener(b);
		back.addActionListener(b);
		
		setLayout(new BorderLayout());
		
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		add(title, BorderLayout.NORTH);
		
		JPanel buttonLabels = new JPanel(new GridLayout(4,0));
		JPanel textBoxes = new JPanel(new GridLayout(6,0));
		
		buttonLabels.add(summaryLabel);
		textBoxes.add(summary);
		buttonLabels.add(descriptionLabel);
		textBoxes.add(description);
		buttonLabels.add(statusLabel);
		textBoxes.add(status);
		buttonLabels.add(priorityLabel);
		textBoxes.add(priority);
		buttonLabels.add(assigneeIDLabel);
		textBoxes.add(assigneeID);
		buttonLabels.add(commentsLabel);
		textBoxes.add(comments);
		
		add(buttonLabels, BorderLayout.WEST);
		add(textBoxes, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		buttonPanel.add(submit);
		buttonPanel.add(viewDefects);
		buttonPanel.add(back);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {

				LocalDateTime tempOpenDate = LocalDateTime.now();
				String tempCloseDate = null;
				String tempReporterID = assigneeID.getSelectedItem().toString();
				String tempSummary = summary.getText();
				String tempDescription = description.getText();
				String tempAssigneeID = assigneeID.getSelectedItem().toString();
				String tempStatus = status.getSelectedItem().toString();
				String tempPriority = priority.getSelectedItem().toString();
				String tempComments = comments.getText();

				 //need to add some type of error handling if options are empty
				 
				DefectInfo d = new DefectInfo(tempOpenDate, tempCloseDate, tempReporterID, tempSummary, tempDescription, tempAssigneeID, tempStatus, tempPriority, tempComments);
   				ListDefectsDAO.insertNewDefect(d);
					
				summary.setText("");
				description.setText("");
				comments.setText("");
					
				System.out.println("Add new item to database");
			}

			if (e.getSource() == viewDefects) {
				removeAll();
				JPanel newPanel = new SortDefects();
				add(newPanel);
				revalidate();
				newPanel.repaint();
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
