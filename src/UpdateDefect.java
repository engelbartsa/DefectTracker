//Heather Bowling
//this is a work in progress. right now it's just a copy of the add defect.  I will be improving this before Friday.
//making a change to this panel to save it.

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateDefect extends JPanel {

//	making a change to this panel to save it.
	ListDefectsDAO defectTracker = new ListDefectsDAO();
	ListUsersDAO userTracker = new ListUsersDAO();
	
	String[] statuses = {"Open", "In Progress", "Closed"};
	String[] priorities = {"Low", "Medium", "High"};
	
	JLabel title = new JLabel("Update the defect information here: ");
	
	JLabel defectLabel = new JLabel("Defect Id: ");
	JLabel defectId = new JLabel();
	
	JLabel openDateLabel = new JLabel("Open Date: ");
	JLabel openDate = new JLabel();
	
	JLabel closeDateLabel = new JLabel("Close Date: ");
	JTextField closeDate = new JTextField(50);
	
	JLabel summaryLabel = new JLabel("Defect Summary: ");
	JTextField summary = new JTextField(50);
	JLabel descriptionLabel = new JLabel("Description: ");
	JTextField description = new JTextField(50);
	JLabel priorityLabel = new JLabel("Priority: ");
	JComboBox priority = new JComboBox(priorities);
	JLabel statusLabel = new JLabel("Status: ");
	JComboBox status = new JComboBox(statuses);
	JLabel error = new JLabel();
	
	ArrayList<UserInfo> assigneeList = new ArrayList<UserInfo>(userTracker.arrayList);
	
	JLabel assigneeIDLabel = new JLabel("Assignee ID: ");
	JComboBox assigneeID = new JComboBox(assigneeList.toArray());
	
	JLabel reporterIDLabel = new JLabel("Reporter ID: ");
	JComboBox reporterID = new JComboBox(assigneeList.toArray());
	
	JLabel commentsLabel = new JLabel("Comments: ");
	JTextField comments = new JTextField(50);
	
	JButton submit = new JButton("Submit");
	JButton back = new JButton("Back to Main");
	JButton viewDefects = new JButton("View Defects");

	public UpdateDefect(DefectInfo tempDefect) {
		
		ButtonListener b = new ButtonListener();
		submit.addActionListener(b);
		viewDefects.addActionListener(b);
		back.addActionListener(b);
		
		setLayout(new BorderLayout());
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		error.setFont(new Font("Serif", Font.BOLD, 20));
		add(title, BorderLayout.NORTH);
		
		JPanel buttonLabels = new JPanel(new GridLayout(11,0));
		JPanel textBoxes = new JPanel(new GridLayout(11,0));
		
		buttonLabels.add(defectLabel);
		defectId.setText(Integer.toString(tempDefect.getDefectID()));
		textBoxes.add(defectId);
		
		buttonLabels.add(openDateLabel);
		openDate.setText(tempDefect.getOpenDate());
		textBoxes.add(openDate);
		
		buttonLabels.add(closeDateLabel);
		closeDate.setText(tempDefect.getCloseDate());
		textBoxes.add(closeDate);
		
		buttonLabels.add(summaryLabel);
		summary.setText(tempDefect.getSummary());
		textBoxes.add(summary);
		
		buttonLabels.add(descriptionLabel);
		description.setText(tempDefect.getDescription());
		textBoxes.add(description);
		
		buttonLabels.add(statusLabel);
		status.setToolTipText(tempDefect.getStatus());
		textBoxes.add(status);
		
		buttonLabels.add(priorityLabel);
		priority.setToolTipText(tempDefect.getPriority());
		textBoxes.add(priority);
		
		buttonLabels.add(assigneeIDLabel);
		assigneeID.setToolTipText(tempDefect.getAssigneeID());
		textBoxes.add(assigneeID);
		
		buttonLabels.add(reporterIDLabel);
		reporterID.setToolTipText(tempDefect.getReporterID());
		textBoxes.add(reporterID);
		
		buttonLabels.add(commentsLabel);
		comments.setToolTipText(tempDefect.getComments());
		textBoxes.add(comments);
		buttonLabels.add(error);
		
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

//commented the submit out until I can get this panel working.
/*			if (e.getSource() == submit) {
				String tempSummary = summary.getText();
				String tempDescription = description.getText();
				String tempAssigneeID = assigneeID.getSelectedItem().toString();
				tempAssigneeID = tempAssigneeID.substring(0,tempAssigneeID.indexOf(';'));
				String tempReporterID = reporterID.getSelectedItem().toString();
				tempReporterID = tempReporterID.substring(0,tempReporterID.indexOf(';'));
				String tempStatus = status.getSelectedItem().toString();
				String tempPriority = priority.getSelectedItem().toString();
				String tempComments = comments.getText();
//Adding error messages for required fields
				if (tempSummary.equals("")){
						error.setText("Enter a summary.");
				} else if (tempDescription.equals("")){
					error.setText("Enter a description.");
				} else if (tempAssigneeID.equals("")){
					error.setText("Enter an Assignee ID.");
				} else if (tempReporterID.equals("")) {
					error.setText("Enter a Reporter ID.");
				} else if (tempStatus.equals("")){
						error.setText("Select a status.");
				} else if (tempPriority.equals("")){
					error.setText("Select a priority.");
				} else {
				
				DefectInfo d = new DefectInfo(tempReporterID, tempSummary, tempDescription, tempAssigneeID, tempStatus, tempPriority, tempComments);
 				defectTracker.insertNewDefect(d);
   				
   				int defect_id = defectTracker.getDefectId();
   				String chgLog = defectTracker.selectDefectbyID(defect_id);
   				
   				DefectInfo h = new DefectInfo(defect_id, tempReporterID, chgLog);
   				defectTracker.insertHistory(h);
					
				summary.setText("");
				description.setText("");
				comments.setText("");
					
				System.out.println("Add new item to defect_tracker database; history table");
				}
			}
*/
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
