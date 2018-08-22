import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class T1 extends JFrame {
	Container contentPane;
	public T1() {
		super("제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		
		JTabbedPane pane = createTabbedPane();
		contentPane.add(pane, BorderLayout.CENTER);
		
		
		setSize(250, 200);
		setLocationRelativeTo(null);
//		setResizable(false);
		setVisible(true);
	}
	
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("tab1", new JLabel(new ImageIcon("aa.jpg")));
		pane.addTab("tab2", new JLabel(new ImageIcon("bb.jpg")));
		pane.addTab("tab3", new MyPanel());
		return pane;
	}
	
	class MyPanel extends JPanel {
		public MyPanel() {
			this.setBackground(Color.YELLOW);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(10, 10, 50, 50);
			g.setColor(Color.BLUE);
			g.fillOval(10, 70, 50, 50);
			g.setColor(Color.BLACK);
			g.drawString("tab 3에 들어가는 JPanel 입니다.", 30, 50);
		}
	}
	
	
	public static void main(String[] args) {
		new T1();
	}
}
