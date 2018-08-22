package j25.client;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.StringTokenizer;

public class c5 extends JFrame implements Runnable {
	
	private JLabel addressLabel = new JLabel("ADDRESS");
	private JTextField addressField = new JTextField("localhost");
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JPanel inputAddressPanel = new JPanel(new GridLayout(2, 2));
	
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel btnPanel = new JPanel(new GridLayout(1, 2));
	
	private JPanel settingPanel = new JPanel(new GridLayout(1, 2));
	
	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);
	
	private JTextArea sendMsgArea = new JTextArea();
	private JScrollPane sendMsgAreaPanel = new JScrollPane(sendMsgArea);
	private JButton sendMsgBtn = new JButton("SEND");
	private JPanel sendMsgPanel = new JPanel(new BorderLayout()); 
	
	// ----------------------------------------------------------------
	
//	private ServerSocket server;
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;

	private Thread inputMsgThread;
	
	public c5() {
		super("클라이언트 소켓 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		inputAddressPanel.add(addressLabel);
		inputAddressPanel.add(addressField);
		inputAddressPanel.add(portNumberLabel);
		inputAddressPanel.add(portNumberField);
		btnPanel.add(startBtn);
		btnPanel.add(stopBtn);
		settingPanel.add(inputAddressPanel);
		settingPanel.add(btnPanel);
		
		sendMsgPanel.add(sendMsgAreaPanel, BorderLayout.CENTER);
		sendMsgPanel.add(sendMsgBtn, BorderLayout.EAST);
		
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(receiveMsgAreaPanel, BorderLayout.CENTER);
		this.add(sendMsgPanel, BorderLayout.SOUTH);
		
		
		portNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopBtn.setEnabled(false);
		receiveMsgArea.setEditable(false);
		sendMsgBtn.setEnabled(false);
		
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
				String url = addressField.getText().trim();
				new Thread() {
					@Override
					public void run() {
						try {
							server = new Socket(url, port);
							receiveMsgArea.append("Client Connected\n");
							startBtn.setEnabled(false);
							
							in = new BufferedReader(new InputStreamReader(server.getInputStream()));
							out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())), true);
							
							
						} catch (IOException e1) {
							System.out.println("서버 연결 확인을 해주십시오.");
							return;
//							e1.printStackTrace();
						}
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
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
				
				if(msg.indexOf("File:") == 0) {
					StringTokenizer st = new StringTokenizer(msg, ":");
					st.nextToken();
					String fileName = st.nextToken();
					String fileSize = st.nextToken();
					
					File dir = new File("DownLoadFIles_Client");
					if(!dir.exists()) {
						dir.mkdirs();
					}
					File file = new File(dir, fileName);
					
					new Thread() {
						@Override
						public void run() {
							int port = Integer.parseInt(portNumberField.getText()) + 1;
							try {
								Socket fileSocket = new Socket(server.getInetAddress().getHostAddress(), port);
								
								BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
								BufferedInputStream bis = new BufferedInputStream(fileSocket.getInputStream());
								
								byte[] data = new byte[1024];
								int size;
								while((size = bis.read(data)) > 0) {
									bos.write(data, 0, size);
								}
								bos.flush();
								bos.close();
								bis.close();
								fileSocket.close();
								
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}.start();
				} else {
					this.receiveMsgArea.append("R: " + msg + "\n");
				}
				
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
			this.server.close();
			this.out.close();
			this.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		this.receiveMsgArea.append("exit\n");
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		
		this.receiveMsgArea.append("클라이언트 종료\n");

		// 쓰레드는 끝났지만 다음 쓰레드를 위한 준비
		this.in = null;
		this.out = null;
		this.server = null;

		new Thread(this).start();
	}
	
	
	public static void main(String[] args) {
		new c5();
		
	}

}
