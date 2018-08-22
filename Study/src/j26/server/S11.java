package net.server;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.data.D8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class S11 extends JFrame {
	
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
	private ArrayList<Client> clients = new ArrayList<>();
	private HashMap<String, Client> clientsMap = new HashMap<>();
	
	class Client implements Runnable {
		private String address;
		private Socket client;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Client)) {
				return false;
			}
			Client target = (Client) obj;
			boolean flag = this.address.equals(target.address);
			
			return flag;
		}

		public Client(Socket client, String address) {
			this.client = client;
			this.address = address;
			try {
				out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
				out.flush();
				in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			new Thread(this).start();
		}
		
		public void closing() {
			try {
				this.client.close();
				this.out.close();
				this.in.close();
			} catch (Exception e) {
				;
			}
			
			clients.remove(this.address);
			clients.remove(this);
			
			receiveMsgArea.append(this.address + " 님이 퇴장하셨씁니다.\n");
		}
		
		public void send(D8 data) {
			try {
				this.out.writeObject(data);
				this.out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// 클라이언트 접속 확인중..
//			while(true) {
//				if(in != null) break;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			// 이미 만들어져 들어왔기 때문에 확인 할 필요가 없음

			// 연결시
			D8 data;
			while (true) {
				try {
					data = (D8) in.readObject();
					int type = data.getType(); // 클라이언트 연결이 끊겼을 시 해당 부분에 Exception 발생

					if (data == null || type == D8.TYPE_MESSAGE && data.getMessage().equals("bye")) {
						break;
					}

					if (type == D8.TYPE_MESSAGE) {
						receiveMsgArea.append(this.address + ": " + data.getMessage() + "\n");
						
						broadcasting(data);
					} 
//					else if (type == D8.TYPE_FILE) {
//						receiveMsgArea.append("R: 상대방이 파일을 보냄.\n");
//						
//						int check = JOptionPane.showConfirmDialog(null, "상대방이 파일을 보냈습니다.\n받으시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
//						if(check != JOptionPane.YES_OPTION) {
//							receiveMsgArea.append("파일수신을 거부하였습니다.\n");
//							return;
//						} else {
//							File dir = new File("downLoadFiles_C");
//							if (!dir.exists())
//								dir.mkdirs();
//							File file = new File(dir, Data.getFileName());
//							
//							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//							
//							bos.write(Data.getFileContents());
//							bos.flush();
//							bos.close();
//							receiveMsgArea.append("R: " + Data.getFileName() + "  - SAVE SUCCESS!\n");
//							
//						}
//					}
				} catch (Exception e) {
					break;
				}

			}

			// 위 while 문을 나갔다는건 통신이 끝났다는 뜻
//			stopBtn.doClick();
			closing();
		}
	}
	
	
	public S11() {
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
		
//		receiveMsgArea.setAutoscrolls(true);
		
		
		generateEvents();
		
		setSize(350, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void generateEvents() {
		this.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Thread() {
					@Override
					public void run() {
						startBtn.setEnabled(false);
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
						sendFileBtn.setEnabled(true);
						int port = Integer.parseInt(portNumberField.getText());
						try {
							server = new ServerSocket(port);

							receiveMsgArea.append("Server Activate...\n");
							receiveMsgArea.append("Server Wating...\n");
							
							while(true) {
								Socket c = server.accept();
								
								String hostAddress = c.getInetAddress().getHostAddress();
								
								boolean flag = clientsMap.containsKey(hostAddress);
								if(!flag) {
									Client client = new Client(c, hostAddress);
									clients.add(client);
									clientsMap.put(hostAddress, client);
									receiveMsgArea.append("Client Connected.(" + clients.size() + " / " + c.getInetAddress().getHostName() + " / " + hostAddress + ")\n");
								} else {
									try {
										c.close();
									} catch (Exception e2) {
										;
									}
								}
								
								
							}

						} catch (IOException e1) {
							receiveMsgArea.append("Server Closed\n");
						}
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
				
				D8 data = new D8();
				data.setMessage(msg);
				
				for(int i=0;i<clients.size();i++) {
					clients.get(i).send(data);
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
				
				File file = new File(chooser.getSelectedFile().getPath()); // 파일 경로 및 이름
				D8 data = new D8();
				byte[] contents = new byte[(int) file.length()];
				
				try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
					
					bis.read(contents);
					
					data.setFileName(chooser.getSelectedFile().getName());
					data.setFileContents(contents);

					receiveMsgArea.append("S: SEND LOADING...\n");
					for(int i=0;i<clients.size();i++) {
						clients.get(i).send(data);
					}
					receiveMsgArea.append("S: " + data.getFileName() + " - SEND SUCCESS!\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public synchronized void broadcasting(D8 data) {
		for(int i=0;i<clients.size();i++) {
			clients.get(i).send(data);
		}
	}
	
	public synchronized void stop() {

		try {
			this.server.close();
			
			for(int i=0;i<clients.size();i++) {
				clients.get(i).closing();
			}
			clients.clear();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		this.receiveMsgArea.append("exit\n");
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		this.sendFileBtn.setEnabled(true);
		
		this.receiveMsgArea.append("클라이언트 종료\n");

		this.server = null;
	}
	
	
	public static void main(String[] args) {
		new S11();
		
	}

}
