package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuActionEventEx extends JFrame {
	Container contentPane;
	JLabel label = new JLabel("Hello");

	MenuActionEventEx() {
		setTitle("Menu 만들기  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.CENTER);
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}

	void createMenu() {
		JMenuBar mb = new JMenuBar();
				
		JMenu colorMenu = new JMenu("Color", true);	
		JMenuItem colorMenuItem_Blue = new JMenuItem("Blue");
		colorMenuItem_Blue.addActionListener(new MenuActionListener());
		JMenuItem colorMenuItem_Black = new JMenuItem("Black");
		colorMenuItem_Black.addActionListener(new MenuActionListener());
		colorMenu.add(colorMenuItem_Blue);
		colorMenu.add(colorMenuItem_Black);		
		mb.add(colorMenu);
		
		JMenu fontMenu = new JMenu("Font");
		JMenuItem fontMenu_Ravie = new JMenuItem("Ravie");
		fontMenu_Ravie.addActionListener(new MenuActionListener());		
		fontMenu.add(fontMenu_Ravie);	
		mb.add(fontMenu);
		
		JMenu alignMenu = new JMenu("Align");
		
		JMenu horizontalMenu = new JMenu("Horizontal");
		JMenuItem horizontalMenuItem_Left = new JMenuItem("Left");
		horizontalMenuItem_Left.addActionListener(new MenuActionListener());
		JMenuItem horizontalMenuItem_Rigth = new JMenuItem("Right");
		horizontalMenuItem_Rigth.addActionListener(new MenuActionListener());
		JMenuItem horizontalMenuItem_Center = new JMenuItem("Center_H");
		horizontalMenuItem_Center.addActionListener(new MenuActionListener());
		horizontalMenu.add(horizontalMenuItem_Left);
		horizontalMenu.add(horizontalMenuItem_Rigth);
		horizontalMenu.add(horizontalMenuItem_Center);
		
		JMenu verticalMenu = new JMenu("Vertical");
		JMenuItem verticalMenu_Top = new JMenuItem("Top");
		verticalMenu_Top.addActionListener(new MenuActionListener());
		JMenuItem verticalMenu_Center = new JMenuItem("Center_V");
		verticalMenu_Center.addActionListener(new MenuActionListener());
		JMenuItem verticalMenu_Bottom = new JMenuItem("Bottom");
		verticalMenu_Bottom.addActionListener(new MenuActionListener());
		verticalMenu.add(verticalMenu_Top);
		verticalMenu.add(verticalMenu_Center);
		verticalMenu.add(verticalMenu_Bottom);
		
		alignMenu.add(horizontalMenu);
		alignMenu.add(verticalMenu);
		
		mb.add(alignMenu);
		
		this.setJMenuBar(mb);
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (cmd.equals("Blue"))
				label.setForeground(Color.BLUE);
			else if (cmd.equals("Black"))
				label.setForeground(Color.BLACK);
			else if (cmd.equals("Ravie"))
				label.setFont(new Font("Ravie", Font.ITALIC, 30));
			else if (cmd.equals("Top"))
				label.setVerticalAlignment(SwingConstants.TOP);
			else if (cmd.equals("Center_V"))
				label.setVerticalAlignment(SwingConstants.CENTER);
			else if (cmd.equals("Bottom"))
				label.setVerticalAlignment(SwingConstants.BOTTOM);
			else if (cmd.equals("Left"))
				label.setHorizontalAlignment(SwingConstants.LEFT);
			else if (cmd.equals("Right"))
				label.setHorizontalAlignment(SwingConstants.RIGHT);
			else if (cmd.equals("Center_H"))
				label.setHorizontalAlignment(SwingConstants.CENTER);
			
		}
	}

	public static void main(String[] args) {
		new MenuActionEventEx();
	}
}
