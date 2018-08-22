import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class L4 extends JFrame {
	public L4() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		setLayout(new GridLayout(1, 4));
		
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(1, 4));
		
		
		JButton btn1 = new JButton("blahblah");
		btn1.setBackground(Color.YELLOW);
		JButton btn2 = new JButton("blahblah");
		btn2.setBackground(Color.YELLOW);
		JButton btn3 = new JButton("blahblah");
		btn3.setBackground(Color.YELLOW);
		
		JTextField tField = new JTextField("abcd");
		
		BtnListener bl = new BtnListener();
		btn1.addMouseListener(bl);
		btn2.addMouseListener(bl);
		btn3.addMouseListener(bl);
		
		TextListener tl = new TextListener();
		tField.addMouseListener(tl);
		
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		c.add(tField);
		
		add(c);
		
		setVisible(true);
		setSize(400, 300);
	}
	
	public static void main(String[] args) {
		new L4();
	}
	
	class BtnListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBackground(Color.RED);
		}
		public void mouseExited(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBackground(Color.YELLOW);
			
		}
		
	}
	class TextListener implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {
			JTextField t = (JTextField) e.getSource();
			t.setText("HiHi");
		}
		public void mouseExited(MouseEvent e) {
			JTextField t = (JTextField) e.getSource();
			t.setText("abcd");
			
		}
		
	}
}
