package j22;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FlyingTextEx extends JFrame {
	JPanel contentPane = new JPanel();
	JLabel la = new JLabel("HELLO");
	final int FLYING_UNIT = 10;

	FlyingTextEx() {
		setTitle("상,하,좌,우 키를 이용하여 텍스트 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setFocusable(true);
		contentPane.addKeyListener(new MyKeyListener());
		la.setLocation(50, 50);
		la.setSize(100, 20);
		contentPane.add(la);
		setSize(300, 300);
		setVisible(true);
		contentPane.requestFocus();
	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			int gWidth = getSize().width;
			int gHeight = getSize().height;
			int lWidth = la.getSize().width;
			int lHeight = la.getSize().height;
			
			int x, y;
			switch (keyCode) {
			case KeyEvent.VK_UP:
				y = la.getY() - FLYING_UNIT;
				if( (y + lHeight) <= 0 )					
					y = gHeight;
				la.setLocation(la.getX(), y);
				break;
			case KeyEvent.VK_DOWN:
				y = la.getY() + FLYING_UNIT;
				if( y >= gHeight )					
					y = 0;
				la.setLocation(la.getX(), y);
				break;
			case KeyEvent.VK_LEFT:
				x = la.getX() - FLYING_UNIT;
				if( (x + lWidth) <= 0 )					
					x = gWidth;
				la.setLocation(x, la.getY());
				break;
			case KeyEvent.VK_RIGHT:
				x = la.getX() + FLYING_UNIT;
				if( x >= gWidth )					
					x = 0 - lWidth;
				la.setLocation(x, la.getY());
				break;
			}
		}
	}

	public static void main(String[] args) {
		new FlyingTextEx();
	}
}
