package d0822;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class Server extends JFrame implements Runnable {

	private JLabel portLabel = new JLabel("PORT");
	private JTextField portTextField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));
	
	private JTextArea contentArea = new JTextArea();
	private JScrollPane contentAreaPanel = new JScrollPane(contentArea);
	
	private JTextArea sendMsgArea = new JTextArea();
	private JButton sendFileBtn = new JButton("FILE");
	private JButton sendMsgBtn = new JButton("SEND");
	private JPanel sendBtnPanel = new JPanel(new GridLayout(2, 1)); 
	private JPanel sendPanel = new JPanel(new BorderLayout());
	
	// ----------------------------------------------------------------
	
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Thread inputMsgThread;
	
	
	public Server() {
		super("¼­¹ö");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		settingPanel.add(portLabel);
		settingPanel.add(portTextField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);
		
		sendBtnPanel.add(sendFileBtn);
		sendBtnPanel.add(sendMsgBtn);
		sendPanel.add(sendMsgArea, BorderLayout.CENTER);
		sendPanel.add(sendBtnPanel, BorderLayout.EAST);
		
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(contentAreaPanel, BorderLayout.CENTER);
		this.add(sendPanel, BorderLayout.SOUTH);
		
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentArea.setEditable(false);
		stopBtn.setEnabled(false);
		sendFileBtn.setEnabled(false);
		sendMsgBtn.setEnabled(false);
		
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
				startBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				sendFileBtn.setEnabled(true);
				sendMsgBtn.setEnabled(true);
				
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
							System.out.println("startBtn Error");
						}
						
					}
				}.start();
			}
		});
		
		this.sendMsgBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				if(msg.length() == 0) return;
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
		});
		
//		this.sendFileBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});

		this.stopBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
	}
	
	public void stop() {
			try {
				if(client != null) client.close();
				if(in != null) in.close();
				if(out != null) out.close();
				if(server != null) server.close();
			} catch (IOException e) {
				System.out.println("Stop Method Error");
			}
			
			this.contentArea.append("Client Disconnect\n");
			
			this.startBtn.setEnabled(true);
			this.stopBtn.setEnabled(false);
			this.sendFileBtn.setEnabled(false);
			this.sendMsgBtn.setEnabled(false);
			
			this.client = null;
			this.in = null;
			this.out = null;
			this.server = null;
			
			new Thread(this).start();
	}
	
	
	
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
					this.contentArea.append("R: " + data.getMessage() + "\n");
				}
				
				
			} catch (Exception e) {
				break;
			}
		}
		
		stopBtn.doClick();
	}
	
	
	
	
	
	public static void main(String[] args) {
		new Server();
	}

}
