import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class L6 extends JFrame {
	public L6() {
		setTitle("Action �̺�Ʈ ������ �ۼ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 150);
		setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
//		btn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(btn.getText().equals("Action")) {
//					btn.setText("�׼�");
//				} else {
//					btn.setText("Action");
//				}
//				
//			}
//		});

		

		
		
		btn.addActionListener(new MyActionListener());
		add(btn);
		
	}
	
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if(b.getText().equals("Action")) {
				b.setText("�׼�");
			} else {
				b.setText("Action");
			}
			
		}
		
	}
	public static void main(String[] args) {
		new L6();
	}
	
	
}
