package d0820;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


public class P3 extends JFrame {
	JPanel btnPanel = new JPanel();
	JScrollPane contentPanel;
	
	JButton openBtn = new JButton("Open");
	JButton saveBtn = new JButton("Save");
	JTextArea textField = new JTextArea();
	
	
	
	public P3() {
		super("title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnPanel.setLayout(new GridLayout(1, 2));
		btnPanel.add(openBtn);
		btnPanel.add(saveBtn);
		
		contentPanel = new JScrollPane(textField);
		
		openBtn.addActionListener(new Opener());
		saveBtn.addActionListener(new Saver());
		
		add(btnPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		
		setSize(400, 400);
		setVisible(true);
	}
	
	class Opener implements ActionListener {
		JFileChooser chooser = new JFileChooser(".");
		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Java & 어쩌구", "java", "txt");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			File file = new File(chooser.getSelectedFile().getPath());
			if(!file.exists()) {
				return;
			}
			int result = JOptionPane.showConfirmDialog(null, "작성중인 내용이 모두 지워집니다.\n그래도 진행하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			
			if(result != JOptionPane.YES_OPTION) {
				return;
			}
			textField.setText("");
			
			try (BufferedReader in = new BufferedReader(new FileReader(file))){
				String line;
				while((line = in.readLine()) != null) {
					textField.append(line + "\n");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	class Saver implements ActionListener {
		JFileChooser chooser = new JFileChooser(".");
		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Java & 어쩌구", "java", "txt");
			chooser.setFileFilter(filter);
			int ret = chooser.showSaveDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			File file = new File(chooser.getSelectedFile().getPath());
			
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
				out.println(textField.getText());
				out.flush();
				
				String msg = "파일 저장완료.\n(" + file.getName() + " - " + file.length() + " Bytes)";
				JOptionPane.showMessageDialog(null, msg, "Success!", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new P3();
	}
}
