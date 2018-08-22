package d0820;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

public class P2 extends JFrame {
	JLabel textLabel;
	
	public P2() {
		super("title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textLabel = new JLabel("Hello");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textLabel, BorderLayout.CENTER);
		
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}
	
	void createMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem item = new JMenuItem("Color");
		item.addActionListener(new changeColor());
		
		menu.add(item);
		menubar.add(menu);
		this.setJMenuBar(menubar);
	}
	
	class changeColor implements ActionListener{
		JColorChooser chooser = new JColorChooser();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Color")) {
				Color selectedColor = chooser.showDialog(null, "Color", Color.RED);
				if(selectedColor != null) {
					textLabel.setForeground(selectedColor);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new P2();
	}
}
