package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator extends JFrame {
	private JPanel mainPanel = new JPanel();
	private JPanel screenPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	private JLabel historyLabel = new JLabel("");
	private JLabel curLabel = new JLabel("0");
	
	private JButton [] buttons = new JButton[20];
	private String [] printMsg = 
		{"CE", "C", "¡ç", "¡À", "7", "8", "9", 
		"¡¿", "4", "5", "6", "£­", "1", "2", "3", 
		"£«", "¡¾", "0", ".", "£½"};
	
	public Calculator() {
		setTitle("°è»ê±â");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		historyLabel.setBorder(null);
		historyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		historyLabel.setFont(new Font("°íµñÃ¼", Font.ITALIC, 15));
		
		curLabel.setBorder(null);
		curLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		curLabel.setFont(new Font("°íµñÃ¼", Font.ITALIC, 25));
	
		screenPanel.setLayout(new GridLayout(3, 1, 5, 5));
		screenPanel.add(historyLabel);
		screenPanel.add(curLabel);
		
		buttonPanel.setLayout(new GridLayout(5, 4, 3, 3));
		for( int i = 0 ; i < printMsg.length ; i++ ) {
			buttons[i] = new JButton(printMsg[i]);
			buttons[i].setFont(new Font("°íµñÃ¼", Font.ITALIC, 15));
			
			try {
				Integer.parseInt(printMsg[i]);
				buttons[i].setBackground(Color.LIGHT_GRAY);
			} catch( Exception e ) {
				buttons[i].setBackground(Color.GRAY);
			}
			
			buttons[i].addActionListener(new CalActionListener());
			buttonPanel.add(buttons[i]);
		}	
		
		mainPanel.setLayout(new BorderLayout());		
		mainPanel.add(screenPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		setContentPane(mainPanel);
		this.setResizable(false);
		
		setSize(450, 550);
		setVisible(true);
	}
	
	private class CalActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			String btnMsg = btn.getText().trim();
			
			String historyMsg = historyLabel.getText().trim();	
			String curMsg = curLabel.getText().trim();
			if( curMsg.equals("0") )
				curMsg = "";
			
			boolean isNumber = 
				btnMsg.charAt(0)>='0' && btnMsg.charAt(0)<='9';
			isNumber = isNumber || btnMsg.charAt(0) == '.';
			
			if( isNumber ) {
				curLabel.setText(curMsg + btnMsg);
				return;
			} else if( btnMsg.equals("C") ) {
				historyLabel.setText("");
				curLabel.setText("0");
			} else if( btnMsg.equals("CE") ) {				
				curLabel.setText("0");
			} else if( btnMsg.equals("¡ç") && curMsg.length() > 0 ) {
				StringBuilder sb = new StringBuilder(curMsg);
				sb.deleteCharAt(curMsg.length() - 1);
				
				if( sb.length() > 0 )
					curLabel.setText(sb.toString());
				else
					curLabel.setText("0");
			} else if( btnMsg.equals("¡¾") ) {
				if( curMsg.length() <= 0 )
					return;
				
				StringBuilder sb = new StringBuilder(curMsg);
				if( sb.charAt(0) == '-' )
					sb.deleteCharAt(0);
				else
					sb.insert(0, '-');
				
				curLabel.setText(sb.toString());
			} else {	
				if( curMsg.equals("") && !historyMsg.equals("") && historyMsg.charAt(0) != '0' )
					return;
				else if( curMsg.length() == 0 && historyMsg.equals("") )
					historyLabel.setText("0 " + btnMsg);	
				else if( curMsg.length() == 0 && !historyMsg.equals("") && historyMsg.charAt(0) == '0' )
					historyLabel.setText("0 " + btnMsg);					
				else  {
					curLabel.setText("0");
					historyLabel.setText(
						historyMsg + " " + curMsg + " " + btnMsg);
				}
			}			
		}		
	}
	
	public static void main(String[] args) {
		new Calculator();
	}
}
