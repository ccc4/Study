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
		super("Ŭ���̾�Ʈ ���� ���� ������");
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
							System.out.println("���� ���� Ȯ���� ���ֽʽÿ�.");
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
		// Ŭ���̾�Ʈ ���� Ȯ����..
		while(true) {
			if(in != null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// �����
		DataItem data;
		while (true) {
			try {
				data = (DataItem) in.readObject();
				int type = data.getDataType(); // Ŭ���̾�Ʈ ������ ������ �� �ش� �κп� Exception �߻�

				if (type == DataItem.TYPE_MESSAGE && data.getMessage().equals("bye")) {
					break;
				}

				if (type == DataItem.TYPE_MESSAGE) {
					this.receiveMsgArea.append("R: " + data.getMessage() + "\n");
				} else if (type == DataItem.TYPE_FILE) {
					this.receiveMsgArea.append("R: ������ ������ ����.\n");
					
					int check = JOptionPane.showConfirmDialog(null, "������ ������ ���½��ϴ�.\n�����ðڽ��ϱ�?", "confirm", JOptionPane.YES_NO_OPTION);
					if(check != JOptionPane.YES_OPTION) {
						this.receiveMsgArea.append("���ϼ����� �ź��Ͽ����ϴ�.\n");
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

		// �� while ���� �����ٴ°� ����� �����ٴ� ��
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
		
		this.receiveMsgArea.append("Ŭ���̾�Ʈ ����\n");

		// ������� �������� ���� �����带 ���� �غ�
		this.in = null;
		this.out = null;
		this.server = null;

		new Thread(this).start();
	}
	
	
	public static void main(String[] args) {
		new C8_1();
		
	}

}
