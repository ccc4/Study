package d0821;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class s1 extends JFrame implements Runnable {
	private JLabel portLabel = new JLabel("PORT");
	private JTextField portTextField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));
	
	private JTextArea contentTextArea = new JTextArea();
	private JScrollPane contentPanel = new JScrollPane(contentTextArea);
	
	private JTextArea sendTextArea = new JTextArea();
	private JButton sendBtn = new JButton("SEND");
	private JPanel sendPanel = new JPanel(new BorderLayout());
	
	
	private ServerSocket server;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	private Thread inputMsgThread;
	
	public s1() {
		super("제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		settingPanel.add(portLabel);
		settingPanel.add(portTextField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);
		
		sendPanel.add(sendTextArea, BorderLayout.CENTER);
		sendPanel.add(sendBtn, BorderLayout.EAST);
		
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(sendPanel, BorderLayout.SOUTH);

		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopBtn.setEnabled(false);
		contentTextArea.setEditable(false);
		sendBtn.setEnabled(false);
		
		generateEvents();
		setSize(350, 500);
		setVisible(true);
		
		inputMsgThread = new Thread(this);
		inputMsgThread.start();
	}
	
	public void generateEvents() {
		
		this.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(portTextField.getText());
				
				new Thread() {
					@Override
					public void run() {
						try {
							startBtn.setEnabled(false);
							server = new ServerSocket(port);
							contentTextArea.append("서버 연결중...\n");
							client = server.accept();
							contentTextArea.append("서버 연결성공!\n");
							
							in = new BufferedReader(new InputStreamReader(client.getInputStream()));
							out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
						} catch (IOException e) {
							e.printStackTrace();
						}
						stopBtn.setEnabled(true);
						sendBtn.setEnabled(true);
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
		
		this.sendBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = sendTextArea.getText().trim();
				sendTextArea.setText("");
				if(msg.length() == 0) {
					return;
				} else {
					contentTextArea.append("S: " + msg + "\n");
					out.println(msg);
				}
			}
		});
		
	}
	
	@Override
	public void run() {
		// 1초마다 접속 확인
		while(true) {
			if(in != null) {
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// 연결시
		String msg;
		while(true) {
			try {
				msg = in.readLine();
				
				if(msg == null || msg.equals("bye")) {
					break;
				}
				this.contentTextArea.append("R: " + msg + "\n");
			} catch (Exception e) {
				break;
			}
		}
		
		stopBtn.doClick();
		// stop();
	}
	
	public void stop() {
		try {
			this.client.close();
			this.out.close();
			this.in.close();
			this.server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendBtn.setEnabled(false);
		
		this.contentTextArea.append("클라이언트 종료\n");
		
		this.client = null;
		this.out = null;
		this.in = null;
		this.server = null;
		
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		new s1();
	}

}
