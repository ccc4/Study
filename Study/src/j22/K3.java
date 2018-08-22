package j22;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class K3 extends JFrame {
	JPanel contentPane = new JPanel();
	JLabel la = new JLabel("HELLO");
	final int FLYING_UNIT = 10;
	
	
	public K3() {
		setTitle("¿¹Á¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setFocusable(true);
		
		
		
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				int jFrameWidth = getSize().width;
				int jFrameHeight = getSize().height;
				int laWidth = la.getSize().width;
				int laHeight = la.getSize().height;
				
				int x, y;
				
				switch (keyCode) {
				case KeyEvent.VK_UP:
					y = la.getY() - FLYING_UNIT;
					if(y + laHeight <= 0) {
						y = jFrameHeight;
					}
					la.setLocation(la.getX(), y);
					break;
				case KeyEvent.VK_DOWN:
					y = la.getY() + FLYING_UNIT;
					if(y >= jFrameHeight) {
						y = 0;
					}
					la.setLocation(la.getX(), y);
					break;
				case KeyEvent.VK_LEFT:
					x = la.getX() - FLYING_UNIT;
					if(x + laWidth <= 0) {
						x = jFrameWidth;
					}
					la.setLocation(x, la.getY());
					break;
				case KeyEvent.VK_RIGHT:
					x = la.getX() + FLYING_UNIT;
					if(x >= jFrameWidth) {
						x = 0;
					}
					la.setLocation(x, la.getY());
					break;
					
				}
			}
		});
		
		la.setLocation(50, 50);
		la.setSize(100, 20);
		contentPane.add(la);
		
		
		
		
		
		
		setVisible(true);
		setSize(300, 300);
		contentPane.requestFocus();
	}
	
	public static void main(String[] args) {
		new K3();
	}
}
