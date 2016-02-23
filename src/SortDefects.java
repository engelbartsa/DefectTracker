//Ashley Risius

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SortDefects extends JPanel{

	JLabel title = new JLabel("Sort Defects");
	JComboBox defectId = new JComboBox();
	JComboBox userId = new JComboBox();
	JLabel summmaryLabel = new JLabel("Defect Summary: ");
	JLabel descriptionLabel = new JLabel("Description: ");
	String[] priorities = {"Low", "Medium", "High"};
	JComboBox priority = new JComboBox(priorities);
	String[] statuses = {"Open", "In Progress", "Closed"};
	JComboBox status = new JComboBox(statuses);

	JLabel openDateLabel = new JLabel("Date Opened: ");
	JLabel closeDateLabel = new JLabel("Date Closed: ");
	JComboBox reporterID = new JComboBox(); //i need to get list from database
	JComboBox assigneeID = new JComboBox(); //i need to get list from database
	JLabel commnentsLabel = new JLabel("Defect Summary: ");
	
	JTextArea defectList = new JTextArea(20, 45);
	
	JButton back = new JButton("Back to Main");
	ListDefectsDAO defectTrackerDAO = new ListDefectsDAO();
	
	public SortDefects(){
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		setMinimumSize(new Dimension(400,250));
	//	defectList.append(defectTrackerDAO.getCurrentListFromDefect(statuses[0]));
		defectList.setLineWrap(true);
		defectList.setEditable(true);
		defectList.setVisible(true);

		JScrollPane scroll = new JScrollPane(defectList);
	   
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
        SortListener ss = new SortListener();
        defectId.addActionListener(ss);
        userId.addActionListener(ss);
        priority.addActionListener(ss);
        status.addActionListener(ss);
        assigneeID.addActionListener(ss);
        reporterID.addActionListener(ss);
        back.addActionListener(ss);
        
        setLayout(new BorderLayout());
        
		add(title, BorderLayout.NORTH);
		add(defectId, BorderLayout.WEST);
		add(userId, BorderLayout.WEST);
		add(status, BorderLayout.WEST);
		add(priority, BorderLayout.WEST);
		add(assigneeID, BorderLayout.WEST);
		add(scroll, BorderLayout.CENTER);
		add(back, BorderLayout.SOUTH);	
		
	}
	class SortListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//LL commented out to get Back button to work from Sort window.
			/*String d = defectId.getSelectedItem().toString();
			defectList.setText(defectTrackerDAO.getCurrentListFromDefect(d));
			System.out.println("noted change");
			*/
			
			if(e.getSource() == back){
				removeAll();
                JPanel newPanel= new MainPanel();
                add(newPanel);
                revalidate();
                newPanel.repaint();
			}
		}
		
	}
	
	
}
