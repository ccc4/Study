import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class L4_2 extends JFrame {
	public L4_2() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		setLayout(new GridLayout(1, 4));
		
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(1, 4));
		
		
		JButton btn1 = new JButton("blahblah");
		btn1.setBackground(Color.YELLOW);
		
		btn1.addMouseListener(new BtnListener1());
		btn1.addMouseListener(new BtnListener2());
		btn1.addMouseListener(new BtnListener3());
		
		c.add(btn1);
		
		add(c);
		
		setVisible(true);
		setSize(400, 300);
	}
	
	public static void main(String[] args) {
		new L4_2();
	}
	
	class BtnListener1 implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			System.out.println("1");
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	class BtnListener2 implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("2");
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	class BtnListener3 implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("3");
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
}
