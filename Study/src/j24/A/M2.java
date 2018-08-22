package j24.A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class M2 extends JFrame {
	JLabel lb = new JLabel("Hello");
	public M2() {
		super("Á¦¸ñ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lb, BorderLayout.CENTER);
		
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}
	
	void createMenu() {
		String[] menuNames = {"File", "Edit", "Source", "Project"};
		JMenu[] menus = new JMenu[menuNames.length];
		String[] menu0ItemNames = {"Color", "Font", "Top", "Bottom", "Original"};
		JMenuItem[] menu0Items = new JMenuItem[menu0ItemNames.length];
		String[] menu2ItemNames = {"First", "Second"};
		JMenuItem[] menu2Items = new JMenuItem[menu2ItemNames.length];
		
		JMenuBar menubar = new JMenuBar();
		
		for(int i=0;i<menuNames.length;i++) {
			menus[i] = new JMenu(menuNames[i]);
			menubar.add(menus[i]);
		}
		for(int i=0;i<menu0ItemNames.length;i++) {
			menu0Items[i] = new JMenuItem(menu0ItemNames[i]);
			menu0Items[i].addActionListener(new menu0ActionListener());
			menus[0].add(menu0Items[i]);
		}
//		for(int i=0;i<menu2ItemNames.length;i++) {
//			menu2Items[i] = new JMenuItem(menu2ItemNames[i]);
//			menus[2].add(menu2Items[i]);
//		}
		
		menu2Items[1] = new JMenuItem(menu2ItemNames[1]);
		JMenu jm3 = new JMenu("First");
		jm3.add(new JMenuItem("111"));
		jm3.add(new JMenuItem("222"));
		jm3.add(new JMenuItem("333"));
		menus[2].add(jm3);
		menus[2].add(menu2Items[1]);
		this.setJMenuBar(menubar);
	}
	
	class menu0ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Color")) {
				lb.setForeground(Color.BLUE);
			} else if(cmd.equals("Font")) {
				lb.setFont(new Font("Ravie", Font.ITALIC, 30));
			} else if(cmd.equals("Top")) {
				lb.setVerticalAlignment(SwingConstants.TOP);
			} else if(cmd.equals("Bottom")) {
				lb.setVerticalAlignment(SwingConstants.BOTTOM);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new M2();
	}
}
