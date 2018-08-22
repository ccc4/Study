package j26.server;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import j26.data.D8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;

public class S8_1 extends JFrame implements Runnable {
	
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
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Thread inputMsgThread;
	
	public S8_1() {
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

//									in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//									out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
							out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
							out.flush();
							in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));

						} catch (IOException e1) {
							e1.printStackTrace();
						}
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
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
				D8 fileData = new D8();
				
				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				if(msg.length() == 0) {
					return;
				}
				receiveMsgArea.append("S: " + msg + "\n");
				fileData.setMessage(msg);
				try {
					out.writeObject(fileData);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
				D8 fileData = new D8();
				File file = new File(chooser.getSelectedFile().getPath());
				
				try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
					
					StringReader r = new StringReader(receiveMsgArea.getText());
					BufferedReader br = new BufferedReader(r);
					String s;
					while( (s = br.readLine()) != null )
						pw.println(s);
						pw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
//				new Thread() {
//					@Override
//					public void run() {
//						try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
////							out.println(receiveMsgArea.getText());
//							
//							StringReader r = new StringReader(receiveMsgArea.getText());
//							BufferedReader br = new BufferedReader(r);
//							String s;
//							while( (s = br.readLine()) != null )
//								out.println(s);
//							out.flush();
//							
//							String msg = "파일 저장이 완료되었습니다.\n(" + file.getName() + " - " + file.length() + ")";
//							JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.INFORMATION_MESSAGE);
//							
//						} catch (Exception e2) {
//							e2.printStackTrace();
//						}
//					}
//				}.start();
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
				try {
					BufferedReader input = new BufferedReader(new FileReader(file));
					String line;
					while ((line = input.readLine()) != null) {
						receiveMsgArea.append(line);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

//				new Thread() {
//					@Override
//					public void run() {
//						try {
//							String line;
//							while ((line = input.readLine()) != null) {
//								receiveMsgArea.append(line);
//							}
//						} catch (Exception e2) {
//							e2.printStackTrace();
//						}
//					}
//				}.start();
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
				
				D8 fileData = new D8();
				File file = new File(chooser.getSelectedFile().getPath()); // 파일 경로 및 이름
				byte[] contents = new byte[(int) file.length()];
				
				try {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					bis.read(contents);
					
					fileData.setFileName(chooser.getSelectedFile().getName());
					fileData.setFileContents(contents);

					receiveMsgArea.append("S: SEND LOADING...\n");
					out.writeObject(fileData);
					out.flush();
					receiveMsgArea.append("S: " + fileData.getFileName() + " - SEND SUCCESS!\n");
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	@Override
	public void run() {
		// 클라이언트 접속 확인중..
		while(true) {
			if(in != null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 연결시
		D8 fileData;
		while (true) {
			try {
				fileData = (D8) in.readObject();
				int type = fileData.getType(); // 클라이언트 연결이 끊겼을 시 해당 부분에 Exception 발생

				if (type == D8.TYPE_MESSAGE && fileData.getMessage().equals("bye")) {
					break;
				}

				if (type == D8.TYPE_MESSAGE) {
					this.receiveMsgArea.append("R: " + fileData.getMessage() + "\n");
				} else if (type == D8.TYPE_FILE) {
					this.receiveMsgArea.append("R: 상대방이 파일을 보냄.\n");
					
					int check = JOptionPane.showConfirmDialog(null, "상대방이 파일을 보냈습니다.\n받으시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
					if(check != JOptionPane.YES_OPTION) {
						this.receiveMsgArea.append("파일수신을 거부하였습니다.\n");
						return;
					} else {
						File dir = new File("downLoadFiles_C");
						if (!dir.exists())
							dir.mkdirs();
						File file = new File(dir, fileData.getFileName());
						
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						
						bos.write(fileData.getFileContents());
						bos.flush();
						bos.close();
						this.receiveMsgArea.append("R: " + fileData.getFileName() + "  - SAVE SUCCESS!\n");
						
					}
				}
			} catch (Exception e) {
				break;
			}

		}

		// 위 while 문을 나갔다는건 통신이 끝났다는 뜻
		stopBtn.doClick();
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
		this.sendFileBtn.setEnabled(true);
		
		this.receiveMsgArea.append("클라이언트 종료\n");

		// 쓰레드는 끝났지만 다음 쓰레드를 위한 준비
		this.client = null;
		this.in = null;
		this.out = null;
		this.server = null;

		new Thread(this).start();
	}
	
	
	public static void main(String[] args) {
		new S8_1();
		
	}

}
