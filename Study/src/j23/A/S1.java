package j23.A;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class S1 extends JFrame {
	Container contentPane;
	JLabel la;
	JButton b1, b2, b3, b4;
	
	S1() {
		setTitle("Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		
		b1 = new JButton("��ġ�� ũ�� ����");
		b1.addActionListener(new MyButtonListener());
		
		b2 = new JButton("��� ����");
		b2.setOpaque(true);
		b2.setForeground(Color.MAGENTA);
		b2.setBackground(Color.YELLOW);
		b2.setFont(new Font("���ü", Font.ITALIC, 20));
		b2.addActionListener(new MyButtonListener());
		
		
		b3 = new JButton("�۵����� �ʴ� ��ư");
		b3.setEnabled(false);
		b3.addActionListener(new MyButtonListener());
		
		b4 = new JButton("�����/���̱�");
		b4.addActionListener(new MyButtonListener());
		
		contentPane.add(b1);
		contentPane.add(b2);
		contentPane.add(b3);
		contentPane.add(b4);
		
		
		setSize(250, 200);
		setVisible(true);
	}
	
	class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == b1) {
				System.out.println("��ư�� ��ġ�� ũ��");
				System.out.println("��ġ: " + b1.getX() + ", " + b1.getY() + "/ ũ��: " + b1.getWidth() + ", " + b1.getHeight());
				JPanel c = (JPanel) b1.getParent();
				System.out.println("����Ʈ���� ��ġ�� ũ��");
				System.out.println("��ġ: " + c.getX() + ", " + c.getY() + "/ ũ��: " + c.getWidth() + ", " + c.getHeight());
				
			} else if(source == b2) {
				System.out.println("��� ����");
				System.out.println("��Ʈ: " + b2.getFont());
				System.out.println("����: " + b2.getBackground());
				System.out.println("���ڻ�: " + b2.getForeground());
			} else {
				if(b1.isVisible()) {
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
				} else {
					b1.setVisible(true);
					b2.setVisible(true);
					b3.setVisible(true);
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		new S1();
	}
}
