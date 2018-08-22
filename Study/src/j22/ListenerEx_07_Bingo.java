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
		setTitle("빙고 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 패널 레이아웃 설정
		mainPanel.setLayout(new BorderLayout());
		bingoPanel.setLayout(new GridLayout(5, 5, 7, 7));
		btnPanel.setLayout(new FlowLayout());
		
		// 빙고 번호 출력을 위한 버튼 객체 생성
		for( int i = 0 ; i < bingoNumbers.length ; i++ ) {
			bingoNumbers[i] = new JButton(Integer.toString(i+1));
			// 패널에 버튼 추가
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













