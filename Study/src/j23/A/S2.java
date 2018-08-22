import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class S2 extends JFrame {
	Container contentPane;
	
	public S2() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JLabel textLabel = new JLabel("사랑합니다.");
		ImageIcon aaaa = new ImageIcon("1111.jpg");
		JLabel artLabel = new JLabel(aaaa);
		ImageIcon bbbb = new ImageIcon("2222.jpg");
		JLabel bottom = new JLabel("보고싶으면 연락주세요", bbbb, SwingConstants.CENTER);
		
		contentPane.add(textLabel);
		contentPane.add(artLabel);
		contentPane.add(bottom);
		
		
		
		
		setSize(400, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new S2();
	}
	
}
