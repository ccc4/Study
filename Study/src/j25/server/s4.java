package j25.server;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
public class s4 extends JFrame implements Runnable {
	
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));
	
	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);
	
	private JButton textSaveBtn = new JButton("SAVE");
	private JButton textLoadBtn = new JButton("LOAD");
	private JButton ImageBtn = new JButton("IMAGE");
	private JButton FileBtn = new JButton("FILE");
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
	
	public s4() {
		super("서버 소켓 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		settingPanel.add(portNumberLabel);
		settingPanel.add(portNumberField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);
		
		additionalPanel.add(textSaveBtn);
		additionalPanel.add(textLoadBtn);
		additionalPanel.add(ImageBtn);
		additionalPanel.add(FileBtn);
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
		
//		this.sendMsgArea.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int value = e.getKeyCode();
//			}
//		});
		
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
		
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		
		this.receiveMsgArea.append("클라이언트 종료\n");

		// 쓰레드는 끝났지만 다음 쓰레드를 위한 준비
		this.in = null;
		this.out = null;
		this.client = null;
		this.server = null;

		new Thread(this).start();
	}
	
	
	public static void main(String[] args) {
		new s4();
		
	}

}
