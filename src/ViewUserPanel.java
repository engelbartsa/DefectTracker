//Libby Lundgren

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ViewUserPanel extends JPanel{
	
	Container c = getRootPane();
	ListUsersDAO userDAO = new ListUsersDAO();
	JLabel title = new JLabel("VIEW USER TABLE");
	
	ArrayList<UserInfo> listUser = new ArrayList<UserInfo>(userDAO.arrayList);
	JList list = new JList(listUser.toArray());
	
	JScrollPane scroll = new JScrollPane(list);
	JButton back = new JButton("Back");
	JButton delete = new JButton("Delete User");
	JButton update = new JButton("Update User");
	
	public ViewUserPanel(){
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		
		setLayout(new BorderLayout());
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(350, 100));
		
		add(title, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		ButtonListener l = new ButtonListener();
		back.addActionListener(l);
		delete.addActionListener(l);
		update.addActionListener(l);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(back);
		buttonPanel.add(delete);
		buttonPanel.add(update);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back){
            removeAll();
            JPanel newPanel=new MainPanel();
            add(newPanel);
            revalidate();
            newPanel.repaint();
		}
		
		if(e.getSource()==delete){
			
			int user = list.getSelectedIndex();
			
			if(user != -1){
				//LL changed ListItem to UserInfo / and listModel to listUser
				UserInfo tempUser = listUser.get(user);
				userDAO.deleteUser(tempUser);
				
				listUser.remove(user);
				list.setListData(listUser.toArray());
				
				scroll.revalidate();
				scroll.repaint();
			}
			
			System.out.println("Remove selected object");
			}
		}
	}	
}
