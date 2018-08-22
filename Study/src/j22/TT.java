import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TT extends JFrame {
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JButton[] btns = new JButton[25];
	
	public TT() {
	
		setTitle("ºù°í°ÔÀÓ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		jp1.setLayout(new GridLayout(5, 5));
		jp2.setLayout(new FlowLayout());
		
		
		for(int i=0;i<btns.length;i++) {
			btns[i] = new JButton(Integer.toString(i+1));
			btns[i].addActionListener(new Push());
			jp1.add(btns[i]);
		}
		add(jp1, BorderLayout.CENTER);
		
		
		
		JButton random = new JButton("random");
		random.addActionListener(new Clear());
		random.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				HashSet<Integer> set = new HashSet<>();
				boolean flag;
				int number;
				
				for(int i=0;i<btns.length;i++) {
					number = (int) (Math.random()*btns.length) + 1;
					flag = set.add(number);
					if(!flag) {
						i--;
						continue;
					}
					
					btns[i].setText(Integer.toString(number));
				}
				
				
				
			}
		});
		jp2.add(random);
		
		add(jp2, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(600, 600);
	}
	
	
	
	public static void main(String[] args) {
		new TT();
	}
	
	private class Push implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if(b.getBackground().equals(Color.GREEN)) {
				b.setBackground(null);
			} else {
				
				b.setBackground(Color.GREEN);
			}
		}
		
	}
	
	private class Clear implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<btns.length;i++) {
				btns[i].setBackground(null);
			}
		}
		
	}
}
