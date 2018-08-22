import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import javafx.scene.layout.Border;

public class S6 extends JFrame {
	Container contentPane;
	JRadioButton[] rBtn = new JRadioButton[3];
	String[] names = {"사과", "배", "체리"};
	ImageIcon[] img = {
			new ImageIcon("apple.jpg"),
			new ImageIcon("pear.jpg"),
			new ImageIcon("cherry.jpg")
	};
	JLabel imageLabel = new JLabel();
	
	
	
	public S6() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel title = new JPanel();
		title.setBackground(Color.GRAY);

		ButtonGroup g = new ButtonGroup();
		for(int i=0;i<rBtn.length;i++) {
			rBtn[i] = new JRadioButton(names[i]);
			g.add(rBtn[i]);
			title.add(rBtn[i]);
			rBtn[i].addItemListener(new RadioListener());
		}
		
		
		
		
		rBtn[2].setSelected(true);
		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(imageLabel, BorderLayout.CENTER);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(250, 200);
		setVisible(true);
	}
	
	class RadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED) {
				return;
			}
			if(rBtn[0].isSelected()) {
				imageLabel.setIcon(img[0]);
			} else if(rBtn[1].isSelected()) {
				imageLabel.setIcon(img[1]);
			} else {
				imageLabel.setIcon(img[2]);
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new S6();
	}
}
