package j25.client;

// 네트워크 기능 구현을 위한 패키지
import java.net.*;
import java.util.StringTokenizer;
// 원격지의 어플리케이션과 데이터를 주고받기 위한 패키지
import java.io.*;
// 입력 처리를 위한 쓰레드 클래스 임포트
// GUI 구성을 위한 클래스 임포트
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Client_05 extends JFrame implements Runnable {
	private JLabel ipLabel = new JLabel("IP");
	private JTextField ipField = new JTextField("localhost");
	private JLabel portNumberLabel = new JLabel("PORT");
	private JTextField portNumberField = new JTextField("5070");
	private JButton startBtn = new JButton("START");
	private JButton stopBtn = new JButton("STOP");
	private JPanel settingPanel = new JPanel(new GridLayout(1, 6));

	private JTextArea receiveMsgArea = new JTextArea();
	private JScrollPane receiveMsgAreaPanel = new JScrollPane(receiveMsgArea);

	private JTextArea sendMsgArea = new JTextArea();
	private JScrollPane sendMsgAreaPanel = new JScrollPane(sendMsgArea);
	private JButton sendMsgBtn = new JButton("Message");
	private JButton sendFileBtn = new JButton("FILE");
	private JPanel sendBtnPanel = new JPanel(new GridLayout(2, 1));
	private JPanel sendPanel = new JPanel(new BorderLayout());
	
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;
	
	public Client_05() {
		super("소켓 클라이언트 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		settingPanel.add(ipLabel);
		settingPanel.add(ipField);
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

		this.setSize(450, 500);
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
							server = new Socket(ipField.getText().trim(), port);
							
							startBtn.setEnabled(false);
							
							receiveMsgArea.append("Client Activate.\n");
							receiveMsgArea.append("Server Connected.\n");
							
							in = 
								new BufferedReader(
									new InputStreamReader(
											server.getInputStream()));
							out = 
								new PrintWriter(
									new BufferedWriter(
										new OutputStreamWriter(
											server.getOutputStream())), true);
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
				
				if( msg.indexOf("File:") == 0 ) {
					StringTokenizer st = 
							new StringTokenizer(msg, ":");
					
					st.nextToken();
					String fileName = st.nextToken();
					String fileSize = st.nextToken();
					
					File dir = new File("DownLoadFiles_Client");
					if( !dir.exists() )
						dir.mkdirs();
					File file = new File(dir, fileName);
					
					new Thread() {						
						public void run() {
							int port = 
								Integer.parseInt(
									portNumberField.getText().trim()) + 1;
							try {
								Socket fileSocket = 
									new Socket(
										server.getInetAddress().getHostAddress(), port);
								
								BufferedOutputStream bos = 
									new BufferedOutputStream(
										new FileOutputStream(file));
								BufferedInputStream bis = 
									new BufferedInputStream(
										fileSocket.getInputStream());
								
								byte [] data = new byte[1024];
								int size;
								while( (size = bis.read(data)) > 0 )
									bos.write(data, 0, size);
								
								bos.flush();
								bos.close();
								bis.close();
								fileSocket.close();
								
							} catch (IOException e) {								
								e.printStackTrace();
							}
						}
					}.start();;
					
				} else
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
			this.server.close();
			this.out.close();
			this.in.close();			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		
		this.in = null;
		this.out = null;		
		this.server = null;
				
		new Thread(this).start();		
	}

	public static void main(String[] args) {
		new Client_05();
	}
}













