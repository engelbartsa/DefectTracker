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

public class ViewDefectsPanel extends JPanel {
	Container c = getRootPane();
	ListDefectsDAO dtDAO = new ListDefectsDAO();
	JLabel title = new JLabel("VIEW DEFECT TRACKER");
	
	ArrayList<DefectInfo> listDefect = new ArrayList<DefectInfo>(dtDAO.arrayList);
	JList list = new JList(listDefect.toArray());
	
	JScrollPane scroll = new JScrollPane(list);
	JButton back = new JButton("Back");
	JButton delete = new JButton("Delete defect");
	//JButton update = new JButton("Update defect");
	
	public ViewDefectsPanel(){
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		
		setLayout(new BorderLayout());
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(650, 300));
		
		add(title, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		ButtonListener l = new ButtonListener();
		back.addActionListener(l);
		delete.addActionListener(l);
		//update.addActionListener(l);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(back);
		buttonPanel.add(delete);
		//buttonPanel.add(update);
		
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
			
			int defect = list.getSelectedIndex();
			
			if(defect != -1){
				//LL changed ListItem to DefectInfo / and deleteItem to deleteDefect
				DefectInfo tempDefect = listDefect.get(defect);
				dtDAO.deleteHistory(tempDefect);
				dtDAO.deleteDefect(tempDefect);
				
				//LL changed listModel to listDefect
				listDefect.remove(defect);
				list.setListData(listDefect.toArray());
				
				scroll.revalidate();
				scroll.repaint();
			}
			
			System.out.println("Remove selected object");
		}
		}
	}
}