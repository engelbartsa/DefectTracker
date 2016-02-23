
//Paula Bintner

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	JButton view = new JButton("View Defects");
	JButton add = new JButton("Add Defects");
	JButton sort = new JButton("Sort Defects");
	JButton addUser = new JButton("Add User");

	public MainPanel() {

		removeAll();
		JPanel mainMenu = new JPanel();

		mainMenu.add(view);
		mainMenu.add(add);
		mainMenu.add(sort);
		mainMenu.add(addUser);
		add(mainMenu);

		ButtonHandler handler = new ButtonHandler();
		view.addActionListener(handler);
		add.addActionListener(handler);
		sort.addActionListener(handler);
		addUser.addActionListener(handler);

	}

	class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == view) {

				removeAll();
				JPanel newPanel = new ViewDefectsPanel();
				add(newPanel);
				revalidate();
				newPanel.repaint();
			}
			if (e.getSource() == add) {
				removeAll();
				JPanel newPanel = new AddDefect();
				add(newPanel);
				revalidate();
				newPanel.repaint();
			}
			if (e.getSource() == sort) {
				removeAll();
				JPanel newPanel = new SortDefects();
				add(newPanel);
				revalidate();
				newPanel.repaint();
			}
			if (e.getSource() == addUser) {
				removeAll();
				JPanel newPanel = new AddUser();
				add(newPanel);
				revalidate();
				newPanel.repaint();
			}
		}
	}
}
