package d0829;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import d0822.DataItem;

public class MainFrame extends JFrame {
	
	JButton selectServer = new JButton("서버");
	JButton selectClient = new JButton("클라");
	
	
	JLabel addressLabel;
	JTextField addressField;
	JLabel portLabel;
	JTextField portTextField;
	JPanel inputAddressPanel;
	
	JButton startBtn;
	JButton stopBtn;
	JPanel btnPanel;
	
	JPanel settingPanel;
	
	
	JTextArea contentArea;
	JScrollPane contentAreaPanel;
	
	JTextArea sendMsgArea;
	JScrollPane sendMsgPanel;
	JButton sendMsgBtn;
	JPanel sendPanel;
	
	// -------------------------------------------
	
	ServerSocket server;
	Socket client;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	Thread inputMsgThread;
	
	public MainFrame() {
		super("메인");
		
		
		this.setLayout(new GridLayout(1, 2));
		this.add(selectServer);
		this.add(selectClient);
		
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // 닫기 버튼 비활성화
		setSize(150, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		this.selectServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerFrame serverFrame = new ServerFrame("서버 애플리케이션");
				setVisible(false);
			}
		});

		this.selectClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClientFrame clientFrame = new ClientFrame("클라이언트 애플리케이션");
				setVisible(false);
			}
		});
	}
	
	
	class ServerFrame extends JFrame {
		
		public ServerFrame(String title) {
			super(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				addressField = new JTextField(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			addressLabel = new JLabel("My IP");
			portLabel = new JLabel("PORT");
			portTextField = new JTextField("5070");
			inputAddressPanel = new JPanel(new GridLayout(2, 2));
			inputAddressPanel.add(addressLabel);
			inputAddressPanel.add(addressField);
			inputAddressPanel.add(portLabel);
			inputAddressPanel.add(portTextField);
			
			startBtn = new JButton("START");
			stopBtn = new JButton("STOP");
			btnPanel = new JPanel(new GridLayout(1, 2));
			btnPanel.add(startBtn);
			btnPanel.add(stopBtn);
			
			settingPanel = new JPanel(new GridLayout(1, 2));
			settingPanel.add(inputAddressPanel);
			settingPanel.add(btnPanel);
			
			contentArea = new JTextArea();
			contentAreaPanel = new JScrollPane(contentArea);
			
			sendMsgArea = new JTextArea(3, 1);
			sendMsgPanel = new JScrollPane(sendMsgArea);
			sendMsgBtn = new JButton("SEND");
			sendPanel = new JPanel(new BorderLayout());
			sendPanel.add(sendMsgPanel, BorderLayout.CENTER);
			sendPanel.add(sendMsgBtn, BorderLayout.EAST);
			
			
			this.add(settingPanel, BorderLayout.NORTH);
			this.add(contentAreaPanel, BorderLayout.CENTER);
			this.add(sendPanel, BorderLayout.SOUTH);
			
			
			addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			portLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addressField.setEditable(false);
			contentArea.setEditable(false);
			stopBtn.setEnabled(false);
			sendMsgBtn.setEnabled(false);
			
			
			setSize(380, 500);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
			
			startBtn.addActionListener(new btnAction(1));
			stopBtn.addActionListener(new btnAction(0));
			sendMsgBtn.addActionListener(new btnAction(0));
			
			Thread inputMsgThread = new ThreadControl();
			inputMsgThread.start();
		}
	}

	class ClientFrame extends JFrame {
		
		public ClientFrame(String title) {
			super(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			addressLabel = new JLabel("ADDRESS");
			addressField = new JTextField("localhost");
			portLabel = new JLabel("PORT");
			portTextField = new JTextField("5070");
			inputAddressPanel = new JPanel(new GridLayout(2, 2));
			inputAddressPanel.add(addressLabel);
			inputAddressPanel.add(addressField);
			inputAddressPanel.add(portLabel);
			inputAddressPanel.add(portTextField);
			
			startBtn = new JButton("START");
			stopBtn = new JButton("STOP");
			btnPanel = new JPanel(new GridLayout(1, 2));
			btnPanel.add(startBtn);
			btnPanel.add(stopBtn);
			
			settingPanel = new JPanel(new GridLayout(1, 2));
			settingPanel.add(inputAddressPanel);
			settingPanel.add(btnPanel);
			
			contentArea = new JTextArea();
			contentAreaPanel = new JScrollPane(contentArea);
			
			sendMsgArea = new JTextArea(3, 1);
			sendMsgPanel = new JScrollPane(sendMsgArea);
			sendMsgBtn = new JButton("SEND");
			sendPanel = new JPanel(new BorderLayout());
			sendPanel.add(sendMsgPanel, BorderLayout.CENTER);
			sendPanel.add(sendMsgBtn, BorderLayout.EAST);
			
			
			this.add(settingPanel, BorderLayout.NORTH);
			this.add(contentAreaPanel, BorderLayout.CENTER);
			this.add(sendPanel, BorderLayout.SOUTH);
			
			
			addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			portLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentArea.setEditable(false);
			stopBtn.setEnabled(false);
			sendMsgBtn.setEnabled(false);
			
			
			setSize(380, 500);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
			
			
			startBtn.addActionListener(new btnAction(2));
			stopBtn.addActionListener(new btnAction(0));
			sendMsgBtn.addActionListener(new btnAction(0));
			
			Thread inputMsgThread = new ThreadControl();
			inputMsgThread.start();
		}
	}
	
	class btnAction implements ActionListener {
		int state;
		
		public btnAction(int state) {
			this.state = state;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String what = btn.getText().trim();
			
			if(what.equals("START")) {
				enableControl_startBtn();
				
				if(state == 1) {
					new Thread() {
						@Override
						public void run() {
							
							try {
								int port = Integer.parseInt(portTextField.getText());
								server = new ServerSocket(port);
								
								contentArea.append("Server Activated...\n");
								contentArea.append("Connection ...\n");
								client = server.accept();
								contentArea.append("Connection Success!\n");
								
								out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
								out.flush();
								in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
								
							} catch (IOException e) {
								e.printStackTrace();
								stopBtn.doClick();
							}
							
						}
					}.start();
					
				} else if(state == 2) {
					new Thread() {
						@Override
						public void run() {
							
							try {
								String ip = addressField.getText();
								int port = Integer.parseInt(portTextField.getText());
								client = new Socket(ip, port);
								contentArea.append("Connection Success!\n");
								
								in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
								out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
								out.flush();
								
							} catch (IOException e) {
								e.printStackTrace();
								stopBtn.doClick();
							}
							
						}
					}.start();
				}
				
				
			} else if(what.equals("STOP")) {
				stop();
			} else if(what.equals("SEND")) {
				String msg = sendMsgArea.getText().trim();
				if(msg.length() == 0) return;
				sendMsgArea.setText("");
				contentArea.append("S: " + msg + "\n");
				
				DataItem data = new DataItem();
				data.setMessage(msg);
				try {
					out.writeObject(data);
					out.flush();
					
				} catch (IOException e1) {
					System.out.println("sendMsgBtn Error");
				}
			}
			
		}
		
	}
	
	class ThreadControl extends Thread {
		@Override
		public void run() {
			while(true) {
				if(in != null) break;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("main thread run method-1 sleep Error");
				}
			}
			
			DataItem data;
			while(true) {
				try {
					data = (DataItem) in.readObject();
					int type =  data.getDataType();
					if(type == DataItem.TYPE_MESSAGE) {
						contentArea.append("R: " + data.getMessage() + "\n");
					}
					
					
				} catch (Exception e) {
					break;
				}
			}
			
			stopBtn.doClick();
		}
	}
	
	public void stop() {
		try {
			if(client != null) client.close();
			if(out != null) out.close();
			if(in != null) in.close();
			if(server != null) server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		enableControl_stopBtn();

		client = null;
		out = null;
		in = null;
		server = null;
		
		new ThreadControl().start();
	}
	
	public void enableControl_startBtn() {
		startBtn.setEnabled(false);
		stopBtn.setEnabled(true);
		sendMsgBtn.setEnabled(true);
	}
	public void enableControl_stopBtn() {
		contentArea.append("---종료---\n");
		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		sendMsgBtn.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}




