import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class K4 extends JFrame {
	JPanel contentPane = new JPanel(); 
			
	
	public K4() {
		setTitle("·£´ý»ö»ó");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int r = (int) (Math.random()*256);
					int g = (int) (Math.random()*256);
					int b = (int) (Math.random()*256);
					
					contentPane.setBackground(new Color(r, g, b));
				}
			}
		});

		
		
		setVisible(true);
		setSize(300, 300);
	}
	
	public static void main(String[] args) {
		new K4();
	}
}
