package d0820;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

public class P1 extends JFrame {
	JLabel imageLabel;
	
	public P1() {
		super("title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageLabel = new JLabel();
		getContentPane().add(imageLabel);
		
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}
	
	void createMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem item = new JMenuItem("Open");
		item.addActionListener(new itemAddImage());
		
		menu.add(item);
		menubar.add(menu);
		this.setJMenuBar(menubar);
	}
	
	class itemAddImage implements ActionListener{
		JFileChooser chooser = new JFileChooser();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고!", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			imageLabel.setIcon(new ImageIcon(filePath));
			pack();
		}
	}
	
	public static void main(String[] args) {
		new P1();
	}
}
