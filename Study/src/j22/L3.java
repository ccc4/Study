import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class L3 extends JFrame {
	public L3() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel text = new JLabel("hello");
		text.setSize(50, 20);
		text.setLocation(30, 30);
		c.add(text);
		
		c.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				text.setLocation(e.getX(), e.getY());
			}
		});
		
		
		
		
		setVisible(true);
		setSize(200, 200);
	}
	
	public static void main(String[] args) {
		new L3();
	}
}
