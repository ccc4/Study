import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class C1 extends JFrame {
	JLabel text;
	public C1() {
		super("Á¦¸ñ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text = new JLabel("Hello");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Ravie", Font.ITALIC, 30));
		getContentPane().add(text, BorderLayout.CENTER);
		
		
		createMenu();
		setSize(250, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	void createMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Text");
		JMenuItem item = new JMenuItem("Color");
		item.addActionListener(new menuAction());
		
		menu.add(item);
		menubar.add(menu);
		this.setJMenuBar(menubar);
	}
	
	class menuAction implements ActionListener {
		JColorChooser chooser = new JColorChooser();
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Color")) {
				Color selectedColor = chooser.showDialog(null, "Color1", Color.YELLOW);
				if(selectedColor != null) {
					text.setForeground(selectedColor);
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		new C1();
	}
}
