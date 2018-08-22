import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class K2  extends JFrame {
	JPanel contentPane = new JPanel();
	JLabel la = new JLabel();
	
	public K2() {
		setTitle("¿¹Á¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		contentPane.setFocusable(true);
		
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				la.setText(e.getKeyText(e.getKeyCode()));
				if(e.getKeyChar() == '%')
					contentPane.setBackground(Color.YELLOW);
				else if(e.getKeyCode() == KeyEvent.VK_F1)
					contentPane.setBackground(Color.GREEN);
			}
		});
		
		contentPane.add(la);
		
		setSize(300, 150);
		setVisible(true);
		
		contentPane.requestFocus();
	}
	
	public static void main(String[] args) {
		new K2();
	}
}
