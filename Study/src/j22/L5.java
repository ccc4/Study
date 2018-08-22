import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class L5 extends JFrame {
	public L5() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(2, 1));
		
		
		JButton btn1 = new JButton("blahblah");
		btn1.setBackground(Color.YELLOW);
		JTextField tf = new JTextField();

		
		btn1.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				tf.setText("버튼이 클릭됨");
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				tf.setText("마우스 벗어남");
			}
		});
		
		c.add(btn1);
		c.add(tf);
		
		add(c);
		
		setVisible(true);
		setSize(400, 300);
	}
	
	public static void main(String[] args) {
		new L5();
	}
	
	
}
