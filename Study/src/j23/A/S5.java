import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javafx.scene.layout.Border;

public class S5 extends JFrame {
	Container contentPane;
	JCheckBox[] fruits = new JCheckBox[3];
	String[] names = { "사과", "배", "체리" };
	JLabel sumLabel;
	int sum = 0;

	public S5() {
		setTitle("sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(new JLabel("사과 100원, 배 500원, 체리 20000원"), BorderLayout.NORTH);
		JPanel article = new JPanel();
		article.setLayout(new GridLayout(1, fruits.length));
		for (int i = 0; i < fruits.length; i++) {
			fruits[i] = new JCheckBox(names[i]);
			fruits[i].setBorderPainted(true);
			article.add(fruits[i]);
			fruits[i].addItemListener(new MyItemListener());
		}
		contentPane.add(article, BorderLayout.CENTER);
		sumLabel = new JLabel("현재 0원 입니다.");
		sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(sumLabel, BorderLayout.SOUTH);

		setSize(250, 200);
		setVisible(true);
	}

	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			int selected = 1;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				selected = 1;
			} else {
				selected = -1;
			}
			if (e.getItem() == fruits[0]) {
				sum += selected * 100;
			} else if (e.getItem() == fruits[1]) {
				sum += selected * 500;
			} else {
				sum += selected * 20000;
			}

			sumLabel.setText("현재 " + sum + "원 입니다.");

		}

	}

	public static void main(String[] args) {
		new S5();
	}
}
