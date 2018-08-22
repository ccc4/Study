package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.util.*;

public class ListenerEx_07_Bingo extends JFrame {
	private JPanel mainPanel = new JPanel();
	private JPanel bingoPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	private JButton [] bingoNumbers = new JButton[25];
	private JButton btnRefresh = new JButton("REFRESH");
	
	ListenerEx_07_Bingo() {
		setTitle("���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// �г� ���̾ƿ� ����
		mainPanel.setLayout(new BorderLayout());
		bingoPanel.setLayout(new GridLayout(5, 5, 7, 7));
		btnPanel.setLayout(new FlowLayout());
		
		// ���� ��ȣ ����� ���� ��ư ��ü ����
		for( int i = 0 ; i < bingoNumbers.length ; i++ ) {
			bingoNumbers[i] = new JButton(Integer.toString(i+1));
			// �гο� ��ư �߰�
			bingoPanel.add(bingoNumbers[i]);
		}
		
		btnPanel.add(btnRefresh);
		
		mainPanel.add(bingoPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
			
		this.setContentPane(mainPanel);
		
		btnRefresh.addActionListener(new RefreshNumbers());
		
		setSize(700, 700);
		setVisible(true);
	}	
	
	private class RefreshNumbers implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			HashSet<Integer> set = new HashSet<>();
			boolean flag;
			int number;
			Random random = new Random();
			for( int i = 0 ; i < bingoNumbers.length ; i++ ) {
				number = random.nextInt(bingoNumbers.length) + 1;
				flag = set.add(number);
				if( !flag ) {
					i--;
					continue;
				}
				
				bingoNumbers[i].setText(Integer.toString(number));
			}
		}		
	}
	
	public static void main(String[] args) {
		new ListenerEx_07_Bingo();
	}
}













