import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class K1 extends JFrame {
	JPanel contentPane = new JPanel();
	JLabel[] keyMessage;
	
	public K1() {
		setTitle("¿¹Á¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		contentPane.addKeyListener(new MyKeyListener());
		
		keyMessage = new JLabel[3];
		keyMessage[0] = new JLabel("  getKeyCode()  ");
		keyMessage[1] = new JLabel("  getKeyChar()  ");
		keyMessage[2] = new JLabel("  getKeyText()  ");
		
		for(int i=0;i<keyMessage.length;i++) {
			keyMessage[i].setOpaque(true);
			keyMessage[i].setBackground(Color.CYAN);
			contentPane.add(keyMessage[i]);
			
		}
		
		contentPane.setFocusable(true);
		
		setVisible(true);
		setSize(300, 150);
		
		contentPane.requestFocus();
		
		
	}
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			char keyChar = e.getKeyChar();
			keyMessage[0].setText(Integer.toString(keyCode));
			keyMessage[1].setText(Character.toString(keyChar));
			keyMessage[2].setText(e.getKeyText(keyCode));
		}
	}
	
	public static void main(String[] args) {
		new K1();
	}
}

