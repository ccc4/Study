import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class L2 extends JFrame {
	public L2() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 150);
		setLayout(new FlowLayout());
		
		JButton btn = new JButton("Mouse Event 테스트 버튼");
		btn.setBackground(Color.YELLOW);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(Color.YELLOW);
			}
		});
		
		add(btn);
	}
	
	public static void main(String[] args) {
		new L2();
	}
	
}
