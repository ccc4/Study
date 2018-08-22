import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class L3_1 extends JFrame {
	public L3_1() {
		setTitle("Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel c = new JPanel();
		setContentPane(c);
		setLayout(null);
		
		
		
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
		new L3_1();
	}
}
