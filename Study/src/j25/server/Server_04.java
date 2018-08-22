package j25.server;

// ��Ʈ��ũ ��� ������ ���� ��Ű��
import java.net.*;
// �������� ���ø����̼ǰ� �����͸� �ְ�ޱ� ���� ��Ű��
import java.io.*;
// GUI ������ ���� Ŭ���� ����Ʈ
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Server_04 extends JFrame implements Runnable {
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));

	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);

	private JTextArea sendMsgArea = new JTextArea();
	private JScrollPane sendMsgAreaPanel = new JScrollPane(sendMsgArea);
	private JButton sendMsgBtn = new JButton("SEND");
	private JPanel sendMsgPanel = new JPanel(new BorderLayout());

	private ServerSocket server;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public Server_04() {
		super("���� ���� ���� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		settingPanel.add(portNumberLabel);
		settingPanel.add(portNumberField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);

		sendMsgPanel.add(sendMsgAreaPanel, BorderLayout.CENTER);
		sendMsgPanel.add(sendMsgBtn, BorderLayout.EAST);

		this.add(settingPanel, BorderLayout.NORTH);
		this.add(receiveMsgAreaPanel, BorderLayout.CENTER);
		this.add(sendMsgPanel, BorderLayout.SOUTH);

		portNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopBtn.setEnabled(false);
		receiveMsgArea.setEditable(false);
		sendMsgBtn.setEnabled(false);

		generateEvents();

		this.setSize(350, 500);
		this.setVisible(true);
		
		new Thread(this).start();		
	}

	public void generateEvents() {
		this.startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				new Thread() {					
					public void run() {
						int port = 
								Integer.parseInt(portNumberField.getText());				
						try {
							server = new ServerSocket(port);
							startBtn.setEnabled(false);
							
							receiveMsgArea.append("Server Activate.\n");
							receiveMsgArea.append("Server Wating.\n");
							
							client = server.accept();
							
							receiveMsgArea.append("Client Connected.\n");
							
							in = 
								new BufferedReader(
									new InputStreamReader(
										client.getInputStream()));
							out = 
								new PrintWriter(
									new BufferedWriter(
										new OutputStreamWriter(
											client.getOutputStream())), true);
						} catch (IOException e1) {					
							e1.printStackTrace();
						}				
						
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
					}
				}.start();				
			}
		});
		
		sendMsgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				
				if( msg.length() == 0 )
					return;
				
				receiveMsgArea.append("S : " + msg + "\n");
				out.println(msg);				
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
	}
		
	public void run() {
		while(true) {
			if( in != null )
				break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		String msg;
		while(true) {
			 try {
				msg = in.readLine();
				
				if( msg == null || msg.equals("bye") )
					break;
				
				this.receiveMsgArea.append("R : " + msg + "\n");
				
			} catch (IOException e) {				
				break;
			}
		}
		
		//stop();
		stopBtn.doClick();
	}
	
	public void stop() {
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
		
		this.in = null;
		this.out = null;
		this.client = null;
		this.server = null;
				
		new Thread(this).start();		
	}

	public static void main(String[] args) {
		new Server_04();
	}
}













