package d0819;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class P4 extends JFrame {
	Container contentPane;
	JLabel lb;
	
	
	P4() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JLabel cherryLabel = new JLabel(new ImageIcon("images/aaa.jpg"));
		cherryLabel.setToolTipText("체리이미지 어때요");
		JLabel appleLabel = new JLabel(new ImageIcon("images/bbb.jpg"));
		appleLabel.setToolTipText("사과이미지 어때요");
		contentPane.add(cherryLabel);
		contentPane.add(appleLabel);
		
		
		
		
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(0);
		m.setDismissDelay(10000);
		
		
		
		
		
		
		
		setSize(400, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new P4();
	}
}
