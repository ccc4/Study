import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class M3 extends JFrame {
	Container contentPane;
	
	
	public M3() {
		super("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JLabel lb1 = new JLabel(new ImageIcon("aaa.jpg"));
		lb1.setToolTipText("체리 이미지 비교");
		JLabel lb2 = new JLabel(new ImageIcon("bbb.jpg"));
		lb2.setToolTipText("어떄요 우리 사과");
		contentPane.add(lb1);
		contentPane.add(lb2);
		
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(0);
		m.setDismissDelay(3000);
		
		setSize(300, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new M3();
	}
}
