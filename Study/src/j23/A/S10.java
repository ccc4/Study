import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class S10 extends JFrame {
	Container contentPane;
	String[] fruits = {"apple", "banana", "kiwi", "mango"};
	ImageIcon[] images = {
			new ImageIcon("images/apple.jpg"), 
			new ImageIcon("images/banana.jpg"), 
			new ImageIcon("images/kiwi.jpg"), 
			new ImageIcon("images/mango.jpg"), 
			};
	JLabel imgLabel = new JLabel(images[0]);
	
	public S10() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JComboBox<String> strCombo = new JComboBox<>(fruits);
		strCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					return;
				}
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);
				
			}
		});
		contentPane.add(strCombo);
		contentPane.add(imgLabel);
		
		
		
		setSize(300, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new S10();
	}
}
