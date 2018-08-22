package d0822;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.StringTokenizer;

public class C8_1 extends JFrame implements Runnable {
	
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
	
	private Socket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private DataItem data;

	private Thread inputMsgThread;
	
	public C8_1() {
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
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopBtn.setEnabled(false);
		receiveMsgArea.setEditable(false);
		sendMsgBtn.setEnabled(false);
		sendImageBtn.setEnabled(false);
		sendFileBtn.setEnabled(false);
		
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
				
				new Thread() {
					@Override
					public void run() {
						startBtn.setEnabled(false);
						stopBtn.setEnabled(true);
						sendMsgBtn.setEnabled(true);
						sendFileBtn.setEnabled(true);
						int port = Integer.parseInt(portNumberField.getText());
						String url = addressField.getText().trim();
						try {
							server = new Socket(url, port);
							receiveMsgArea.append("Client Connected\n");

							in = new ObjectInputStream(new BufferedInputStream(server.getInputStream()));
							out = new ObjectOutputStream(new BufferedOutputStream(server.getOutputStream()));
							out.flush();
							
						} catch (NullPointerException | IOException e1) {
							System.out.println("서버 연결 확인을 해주십시오.");
							return;
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
				DataItem data = new DataItem();

				String msg = sendMsgArea.getText().trim();
				sendMsgArea.setText("");
				if (msg.length() == 0) {
					return;
				}
//				receiveMsgArea.append("S: " + msg + "\n");
				data.setMessage(msg);
				try {
					out.writeObject(data);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	@Override
	public void run() {
		// 클라이언트 접속 확인중..
		while(true) {
			if(in != null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 연결시
		DataItem data;
		while (true) {
			try {
				data = (DataItem) in.readObject();
				int type = data.getDataType(); // 클라이언트 연결이 끊겼을 시 해당 부분에 Exception 발생

				if (type == DataItem.TYPE_MESSAGE && data.getMessage().equals("bye")) {
					break;
				}

				if (type == DataItem.TYPE_MESSAGE) {
					this.receiveMsgArea.append("R: " + data.getMessage() + "\n");
				} else if (type == DataItem.TYPE_FILE) {
					this.receiveMsgArea.append("R: 상대방이 파일을 보냄.\n");
					
					int check = JOptionPane.showConfirmDialog(null, "상대방이 파일을 보냈습니다.\n받으시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
					if(check != JOptionPane.YES_OPTION) {
						this.receiveMsgArea.append("파일수신을 거부하였습니다.\n");
						return;
					} else {
						File dir = new File("downLoadFiles_C");
						if (!dir.exists())
							dir.mkdirs();
						File file = new File(dir, data.getFileName());
						
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						
						bos.write(data.getFileContents());
						bos.flush();
						bos.close();
						this.receiveMsgArea.append("R: " + data.getFileName() + "  - SAVE SUCCESS!\n");
						
					}
				}
			} catch (Exception e) {
				break;
			}

		}

		// 위 while 문을 나갔다는건 통신이 끝났다는 뜻
		stopBtn.doClick();
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
		new C8_1();
		
	}

}
