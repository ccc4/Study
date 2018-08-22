package j24.A;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


public class P3 extends JFrame {
	private JPanel titlePanel = new JPanel();
	private JScrollPane contentPanel;
	
	private JButton openBtn = new JButton("Open");
	private JButton SaveBtn = new JButton("Save");
	private JTextArea textArea = new JTextArea();
	
	
	public P3() {
		super("제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titlePanel.setLayout(new GridLayout(1, 2));
		titlePanel.add(openBtn);
		titlePanel.add(SaveBtn);
		contentPanel = new JScrollPane(textArea);
		
		
		openBtn.addActionListener(new Opener());
		
		
		
		
		
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class Opener implements ActionListener {
		JFileChooser chooser;

		public Opener() {
			chooser = new JFileChooser(".");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA & TXT Files", "java", "txt");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File file = new File(chooser.getSelectedFile().getPath());
			if (!file.exists())
				return;

			int result = JOptionPane.showConfirmDialog(null, "작성중인 내용이 모두 지워집니다.\n진행하시겠습니까?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION)
				return;

			textArea.setText("");

			try (BufferedReader input = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = input.readLine()) != null) {
					textArea.append(line + "\n");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}
	
	private class Saver implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	
	public static void main(String[] args) {
		new P3();
	}

}
