package net.client;

// 네트워크 기능 구현을 위한 패키지
import java.net.*;
import java.util.StringTokenizer;
// 원격지의 어플리케이션과 데이터를 주고받기 위한 패키지
import java.io.*;
// GUI 구성을 위한 클래스 임포트
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//객체 입출력에 사용할 패키지 임포트
import net.data.*;

public class Client_08 extends JFrame implements Runnable {
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
	
	private Socket client;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client_08() {
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
							client = 
								new Socket(
									ipField.getText().trim(), port);
							
							startBtn.setEnabled(false);
							
							receiveMsgArea.append("Client Activate.\n");
							receiveMsgArea.append("Server Connected.\n");
							
							out = 
								new ObjectOutputStream(
									new BufferedOutputStream(										
										client.getOutputStream()));
							out.flush();
							in = 
								new ObjectInputStream(
									new BufferedInputStream(
										client.getInputStream()));							
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
		
		sendMsgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				
				if( msg.length() == 0 )
					return;
				
				receiveMsgArea.append("S : " + msg + "\n");
				Data_08 data = new Data_08();
				data.setMessage(msg);
				
				try {
					out.writeObject(data);
					out.flush();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}			
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
					out.writeObject(data);
					out.flush();
					
					receiveMsgArea.append("S : " + data.getFileName() + " sended.\n");					
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
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
		
		Data_08 data;
		while(true) {
			 try {
				data = (Data_08)in.readObject();
				
				if( data == null || 
					(data.getDataType() == Data_08.TYPE_MESSAGE && data.getMessage().equals("bye")) )
					break;
				
				if( data.getDataType() == Data_08.TYPE_MESSAGE )
					this.receiveMsgArea.append(
							"R : " + data.getMessage() + "\n");
				else if( data.getDataType() == Data_08.TYPE_FILE ) {
					File dir = new File("download_c");
					if( !dir.exists() )	dir.mkdirs();
					File file = new File(dir, data.getFileName());
					
					BufferedOutputStream bos = 
						new BufferedOutputStream(
							new FileOutputStream(file));
					
					bos.write(data.getFileContents());
					bos.flush();
					bos.close();
					
					this.receiveMsgArea.append(
							"R : " + data.getFileName() + " saved\n");
				}
				
			} catch (Exception e) {				
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
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		this.startBtn.setEnabled(true);
		this.stopBtn.setEnabled(false);
		this.sendMsgBtn.setEnabled(false);
		this.sendFileBtn.setEnabled(false);
		
		this.in = null;
		this.out = null;		
		this.client = null;
				
		new Thread(this).start();		
	}

	public static void main(String[] args) {
		new Client_08();
	}
}













