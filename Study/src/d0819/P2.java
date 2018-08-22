package d0819;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javafx.scene.control.MenuBar;

public class P2 extends JFrame {
	Container contentPane;
	
	public P2() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu1 = new JMenu("File");
		fileMenu1.add(new JMenuItem("New"));
		fileMenu1.add(new JMenuItem("Open"));
		fileMenu1.addSeparator();
		fileMenu1.add(new JMenuItem("Save"));
		fileMenu1.add(new JMenuItem("SaveAs"));
		JMenu fileMenu2 = new JMenu("Edit");
		JMenu fileMenu3 = new JMenu("Source");
		JMenu fileMenu4 = new JMenu("Project");
		JMenu fileMenu5 = new JMenu("Run");
		
		mb.add(fileMenu1);
		mb.add(fileMenu2);
		mb.add(fileMenu3);
		mb.add(fileMenu4);
		mb.add(fileMenu5);
		setJMenuBar(mb);
		
		setSize(300, 300);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new P2();
	}
}
