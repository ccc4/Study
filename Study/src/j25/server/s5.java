package j25.server;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
public class s5 extends JFrame implements Runnable {
	
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));
	
	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);
	
	private JButton textSaveBtn = new JButton("SAVE");
	private JButton textLoadBtn = new JButton("LOAD");
	private JButton sendImageBtn = new JButton("IMAGE");
	private JButton sendFileBtn = new JButton("FILE");
	private JPanel additionalPanel = new JPanel(new GridLayout(1, 4));
	
	private JTextArea sendMsgArea = new JTextArea();
	private JScrollPane sendMsgAreaPanel = new JScrollPane(sendMsgArea);
	private JButton sendMsgBtn = new JButton("SEND");
	private JPanel sendMsgPanel = new JPanel(new BorderLayout()); 
	
	private JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
	
	// ----------------------------------------------------------------
	
	private ServerSocket server;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	private Thread inputMsgThread;
	
	public s5() {
		super("서버 소켓 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		settingPanel.add(portNumberLabel);
		settingPanel.add(portNumberField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);
		
		additionalPanel.add(textSaveBtn);
		additionalPanel.add(textLoadBtn);
		additionalPanel.add(sendImageBtn);
		additionalPanel.add(sendFileBtn);
		sendMsgPanel.add(sendMsgAreaPanel, BorderLayout.CENTER);
		sendMsgPanel.add(sendMsgBtn, BorderLayout.EAST);
		bottomPanel.add(additionalPanel);
		bottomPanel.add(sendMsgPanel);
		
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(receiveMsgAreaPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		
		portNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		
		stopBtn.setEnabled(false);
		receiveMsgArea.setEditable(false);
		sendMsgBtn.setEnabled(false);
		
		sendImageBtn.setEnabled(false);
		sendFileBtn.setEnabled(false);
		
		generateEvents();
		
		setSize(350, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		
		inputMsgThread = new Thread(this);
		inputMsgThread.start();
	}
	
	public void generateEvents() {
		this.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(portNumberField.getText());
				
				new Thread() {
					@Override
					public void run() {
						try {
							server = new ServerSocket(port);
							startBtn.setEnabled(false);
							
							receiveMsgArea.append("Server Activate...\n");
							receiveMsgArea.append("Server Wating...\n");
							client = server.accept();
							receiveMsgArea.append("Client Connected\n");
							
							in = new BufferedReader(new InputStreamReader(client.getInputStream()));
							out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
							
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
						sendImageBtn.setEnabled(true);
						sendFileBtn.setEnabled(true);
					}
				}.start();
			}
		});
		
		
		this.stopBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		
		
		this.sendMsgBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				if(msg.length() == 0) {
					return;
				}
				receiveMsgArea.append("S: " + msg + "\n");
				out.println(msg);
//				out.flush();
			}
		});
		
		// textArea 에서 엔터로 send 버튼 누르는 기능 구현하기
//		this.sendMsgArea.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int value = e.getKeyCode();
//			}
//		});
		
		
		this.textSaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				int ret = chooser.showSaveDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				File file = new File(chooser.getSelectedFile().getPath());
				new Thread() {
					@Override
					public void run() {
						try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
//							out.println(receiveMsgArea.getText());
							
							StringReader r = new StringReader(receiveMsgArea.getText());
							BufferedReader br = new BufferedReader(r);
							String s;
							while( (s = br.readLine()) != null )
								out.println(s);
							out.flush();
							
							String msg = "파일 저장이 완료되었습니다.\n(" + file.getName() + " - " + file.length() + ")";
							JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}.start();
			}
		});
		
		this.textLoadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA & TXT Files", "JAVA", "TXT");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				File file = new File(chooser.getSelectedFile().getPath());
				if (!file.exists()) {
					return;
				}

				int result = JOptionPane.showConfirmDialog(null, "작성 중인 내용이 모두 지워집니다.\n진행하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result != JOptionPane.YES_OPTION) {
					return;
				}
				receiveMsgArea.setText("");

				new Thread() {
					@Override
					public void run() {
						try {
							BufferedReader input = new BufferedReader(new FileReader(file));
							String line;
							while ((line = input.readLine()) != null) {
								receiveMsgArea.append(line);
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}.start();
			}
		});
		
		
		
		this.sendFileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				File file = 
					new File(chooser.getSelectedFile().getPath());
				if (!file.exists()) return;
				
				String msg = "File:" + file.getName() + ":" + file.length();
				out.println(msg);
				
				new Thread() { // **
					@Override
					public void run() {
						int port = Integer.parseInt(portNumberField.getText()) + 1;
						try {
							ServerSocket fileServerSocket = new ServerSocket(port);
							Socket fileSocket = fileServerSocket.accept();
							BufferedOutputStream bos = new BufferedOutputStream(fileSocket.getOutputStream());
							BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
							
							byte[] data = new byte[1024];
							
							while(bis.read(data) > 0) {
								bos.write(data);
							}
							bis.close();
							bos.flush();
							bos.close();
							
							fileSocket.close();
							fileServerSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
	}
	
	
	
	
	@Override
	public void run() {
		
		// 클라이언트 접속 확인중..
		while(true) {
			if(in != null) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 연결시
		String msg;
		while(true) {
			try {
				msg = in.readLine(); // 클라이언트 연결이 끊겼을 시 해당 부분에 Exception 발생
				
				if(msg == null || msg.equals("bye")) {
//					throw new IOException();
					break;
				}
				
				this.receiveMsgArea.append("R: " + msg + "\n");
			} catch (IOException e) {
				break;
			}
		}
		
		// 위 while 문을 나갔다는건 통신이 끝났다는 뜻
		stopBtn.doClick();
		//stop();
		
	}
	
	
	public synchronized void stop() {

		try {
			this.client.close();
			this.out.close();
			this.in.close();
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		this.receiveMsgArea.append("exit\n");
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		this.sendImageBtn.setEnabled(false);
		this.sendFileBtn.setEnabled(false);
		
		this.receiveMsgArea.append("클라이언트 종료\n");

		// 쓰레드는 끝났지만 다음 쓰레드를 위한 준비
		this.in = null;
		this.out = null;
		this.client = null;
		this.server = null;

		new Thread(this).start();
	}
	
	
	public static void main(String[] args) {
		new s5();
		
	}

}
