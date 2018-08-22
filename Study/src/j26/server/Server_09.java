package net.server;

// 네트워크 기능 구현을 위한 패키지
import java.net.*;
// 원격지의 어플리케이션과 데이터를 주고받기 위한 패키지
import java.io.*;
// GUI 구성을 위한 클래스 임포트
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
// 객체 입출력에 사용할 패키지 임포트
import net.data.*;
// 다중 클라이언트들을 저장하기 위한 클래스 패키지 임포트
import java.util.*;

public class Server_09 extends JFrame {
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 4));

	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);

	private JTextArea sendMsgArea = new JTextArea();
	private JScrollPane sendMsgAreaPanel = new JScrollPane(sendMsgArea);
	private JButton sendMsgBtn = new JButton("Message");
	private JButton sendFileBtn = new JButton("FILE");
	private JPanel sendBtnPanel = new JPanel(new GridLayout(2, 1));
	private JPanel sendPanel = new JPanel(new BorderLayout());

	private ServerSocket server;
	private ArrayList<Client> clients = new ArrayList<>();
	
	class Client implements Runnable {
		private Socket client;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		
		public Client(Socket client) {
			this.client = client;
			
			try {
				out = 
					new ObjectOutputStream(
						new BufferedOutputStream(										
							client.getOutputStream()));
				out.flush();							
				in = 
					new ObjectInputStream(
						new BufferedInputStream(
							client.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 각 클라이언들의 쓰레드 객체를 생성하여
			// 실행하는 코드
			new Thread(this).start();
		}		
		
		public void send(Data_08 data) {
			try {
				this.out.writeObject(data);
				this.out.flush();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void closing() {
			try {
				this.client.close();
				this.out.close();
				this.in.close();
			} catch (IOException e) {
				;
			}
		}

		public void run() {			
			Data_08 data;
			while(true) {
				 try {
					 data = (Data_08)in.readObject();
					
					 if( data == null || 
							(data.getDataType() == 
								Data_08.TYPE_MESSAGE && 
								data.getMessage().equals("bye")) )
						break;
					
					if( data.getDataType() == Data_08.TYPE_MESSAGE )				
						receiveMsgArea.append(
								"R : " + data.getMessage() + "\n");					
				} catch (Exception e) {				
					break;
				}
			}
		}
	}
	
	public Server_09() {
		super("서버 소켓 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		settingPanel.add(portNumberLabel);
		settingPanel.add(portNumberField);
		settingPanel.add(startBtn);
		settingPanel.add(stopBtn);

		sendBtnPanel.add(sendMsgBtn);
		sendBtnPanel.add(sendFileBtn);
		sendPanel.add(sendMsgAreaPanel, BorderLayout.CENTER);
		sendPanel.add(sendBtnPanel, BorderLayout.EAST);

		this.add(settingPanel, BorderLayout.NORTH);
		this.add(receiveMsgAreaPanel, BorderLayout.CENTER);
		this.add(sendPanel, BorderLayout.SOUTH);

		portNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopBtn.setEnabled(false);
		receiveMsgArea.setEditable(false);
		sendMsgBtn.setEnabled(false);
		sendFileBtn.setEnabled(false);

		generateEvents();

		this.setSize(350, 500);
		this.setVisible(true);
	}

	public void generateEvents() {
		this.startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				new Thread() {					
					public void run() {
						startBtn.setEnabled(false);
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
						sendFileBtn.setEnabled(true);
						
						int port = 
							Integer.parseInt(
								portNumberField.getText());				
						try {
							server = new ServerSocket(port);
														
							receiveMsgArea.append("Server Activate.\n");
							receiveMsgArea.append("Server Waiting.\n");
							
							while(true) {
								
								Socket c = server.accept();
								Client client = new Client(c);
								clients.add(client);
								
								receiveMsgArea.append(
								"Client Connected.(" + clients.size() + ")\n");
								
							}
							
						} catch (IOException e1) {					
							e1.printStackTrace();
						}				
						
						
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
				
				Data_08 data = new Data_08();
				data.setMessage(msg);
				
				for( int i = 0 ; i < clients.size() ; i++ )
					clients.get(i).send(data);
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		
		sendFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser;
				chooser = new JFileChooser("d:\\");
				
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, 
							"파일을 선택하지 않았습니다", 
							"경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				File file = 
					new File(chooser.getSelectedFile().getPath());
				
				Data_08 data = new Data_08();
				data.setFileName(file.getName());
				byte [] contents = new byte[(int)file.length()];
				data.setFileContents(contents);
				
				try (BufferedInputStream bis = 
						new BufferedInputStream(
							new FileInputStream(file))){
					
					bis.read(contents);
					
					for( int i = 0 ; i < clients.size() ; i++ )
						clients.get(i).send(data);
					
					receiveMsgArea.append(
						"S : " + data.getFileName() + " sended.\n");					
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void stop() {
		try {			
			this.server.close();
			
			for( int i = 0 ; i < clients.size() ; i++ ) {
				clients.get(i).closing();				
			}
			clients.clear();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		this.sendFileBtn.setEnabled(false);
				
		this.server = null;
	}

	public static void main(String[] args) {
		new Server_09();
	}
}













