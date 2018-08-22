import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Cal_Test extends JFrame {
	Container contentPane;
	JButton[] btns = new JButton[20];
	
	public Cal_Test() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel viewer = new JPanel();
		JTextArea view1 = new JTextArea();
		JTextArea view2 = new JTextArea();
		viewer.setLayout(new GridLayout(2, 1));
		viewer.setOpaque(true);
		viewer.setBackground(Color.CYAN);
		view2.setText("결과나오는곳");
		viewer.add(view1);
		viewer.add(view2);
		
		JPanel article = new JPanel();
		article.setLayout(new BoxLayout(article, BoxLayout.Y_AXIS));
		JPanel set1 = new JPanel();
		JPanel set2 = new JPanel();
		article.add(set1);
		article.add(set2);
		
		
		
		set1.setLayout(new GridLayout(2, 4));
		
		
		JButton divide = new JButton("/");
		divide.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view1.append("/");
			}
		});
		set1.add(divide);
		
		JButton multi = new JButton("*");
		multi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view1.append("*");
			}
		});
		set1.add(multi);
		
		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view1.append("+");
			}
		});
		set1.add(plus);
		
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view1.append("-");
			}
		});
		set1.add(minus);
		
		JButton clear = new JButton("C");
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view1.setText("");
				view2.setText("");
			}
		});
		set1.add(clear);
		
		JButton back = new JButton("<-");
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String aa = view1.getText();
				if(aa.equals("")) {
					view2.setText("값이 있어야 취소 가능");
				} else {
					view1.setText(aa.substring(0, aa.length()-1));
				}
			}
		});
		set1.add(back);
		
		JButton result = new JButton("=");
		result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String getText = view1.getText();
				
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("JavaScript");
				
				int value = 0;
				try {
					value = (int) engine.eval(getText);
				} catch (ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(value);
				view2.setText(String.valueOf(value));
			}
		});
		set1.add(result);
		
		
		
		
		
		set2.setLayout(new GridLayout(3, 3));
		
		for(int i=0;i<9;i++) {
			final int j = i+1;
			btns[i] = new JButton(Integer.toString(j));
			btns[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					view1.append(Integer.toString(j));
				}
			});
			set2.add(btns[i]);
		}
		
		
		
		contentPane.add(viewer);
		contentPane.add(article);
		
		setSize(300, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Cal_Test();
	}
}
