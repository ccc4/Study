package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class SimpleTextEditor extends JFrame {
	private JPanel btnPanel = new JPanel();
	private JScrollPane editorPanel;

	private JButton openBtn = new JButton("Open");
	private JButton saveBtn = new JButton("Save");
	private JTextArea contentArea = new JTextArea();

	public SimpleTextEditor() {
		super("파일 열기 및 저장 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 화면 구성
		btnPanel.setLayout(new GridLayout(1, 2));
		btnPanel.add(openBtn);
		btnPanel.add(saveBtn);

		editorPanel = new JScrollPane(contentArea);

		add(btnPanel, BorderLayout.NORTH);
		add(editorPanel, BorderLayout.CENTER);

		// 상세 모습과 이벤트 처리
		saveBtn.setEnabled(false);
		openBtn.addActionListener(new OpenFileListener());
		saveBtn.addActionListener(new SaveFileListener());
		
		contentArea.addKeyListener(new KeyAdapter() {			
			public void keyReleased(KeyEvent e) {
				if( contentArea.getText().length() > 0 )
					saveBtn.setEnabled(true);
				else
					saveBtn.setEnabled(false);
			}
		});

		this.setSize(500, 500);
		
		/*
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
			(int)((dim.getWidth()/2)-(this.getWidth()/2)), 
			(int)((dim.getHeight()/2)-(this.getHeight()/2)));
		*/
		this.setLocationRelativeTo(null);
		
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setVisible(true);
	}

	private class OpenFileListener implements ActionListener {
		JFileChooser chooser;

		OpenFileListener() {
			chooser = new JFileChooser("d:\\");
		}

		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("JAVA & TXT Files", 
						"java", "txt");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, 
						"파일을 선택하지 않았습니다", 
						"경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File file = 
				new File(chooser.getSelectedFile().getPath());
			if (!file.exists())
				return;

			int result = JOptionPane.showConfirmDialog(null, 
					"작성 중인 내용이 모두 지워집니다.\n진행하시겠습니까?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION)
				return;

			contentArea.setText("");

			try (
				BufferedReader input = 
					new BufferedReader(
						new FileReader(file))) {

				String line;
				while ((line = input.readLine()) != null)
					contentArea.append(line + "\n");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	private class SaveFileListener implements ActionListener {
		JFileChooser chooser;

		SaveFileListener() {
			chooser = new JFileChooser("d:\\");
		}

		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = 
					new FileNameExtensionFilter(
						"JAVA & TXT Files", "java", "txt");			
			chooser.setFileFilter(filter);
			
			int ret = chooser.showSaveDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, 
						"파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			File file = new File(chooser.getSelectedFile().getPath());		
			
			try (
				PrintWriter output = 
					new PrintWriter(
						new BufferedWriter(
							new FileWriter(file)))) {

				output.println(contentArea.getText());
				output.flush();

				String msg = "파일 저장이 완료되었습니다.\n";
				msg += "(" + file.getName() + " - " + file.length() + " Bytes )"; 
				JOptionPane.showMessageDialog(null, 
						msg, "Save Complete", 
						JOptionPane.INFORMATION_MESSAGE); 

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SimpleTextEditor();
	}

}
