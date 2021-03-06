package d0829;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
	
	String nickname;
	
	// 시작
	JLabel nicknameLable = new JLabel("닉네임");
	JTextField nicknameField = new JTextField();
	JButton selectServer = new JButton("서버");
	JButton selectClient = new JButton("클라");
	JPanel mainPanel = new JPanel(new GridLayout(2, 2));
	
	// 각 서버, 클라이언트 UI
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
	
//	JButton sendImgBtn;
	JButton sendFileBtn;
	JPanel sendEtcPanel;
	JTextArea sendMsgArea;
	JScrollPane sendMsgPanel;
	JButton sendMsgBtn;
	JPanel sendPanel;
	
	DropTarget dt;
	
	// -------------------------------------------
	
	ServerSocket server;
	Socket client;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	Thread inputMsgThread;
	
	public MainFrame() {
		super("메인");
		
		mainPanel.add(nicknameLable);
		mainPanel.add(nicknameField);
		mainPanel.add(selectServer);
		mainPanel.add(selectClient);
		this.add(mainPanel);
		nicknameLable.setHorizontalAlignment(SwingConstants.CENTER);
		
//		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // 닫기 버튼 비활성화
		setSize(150, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		this.selectServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nickname = nicknameField.getText().trim();
				if(nickname.length() == 0) {
					JOptionPane.showMessageDialog(null, "닉네임을 입력해주십시오.", "닉네임 미입력", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ServerFrame serverFrame = new ServerFrame("서버 애플리케이션");
				setVisible(false);
			}
		});

		this.selectClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nickname = nicknameField.getText().trim();
				if(nickname.length() == 0) {
					JOptionPane.showMessageDialog(null, "닉네임을 입력해주십시오.", "닉네임 미입력", JOptionPane.WARNING_MESSAGE);
					return;
				}
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
			inputAddressPanel = new JPanel(new GridLayout(3, 2));
			inputAddressPanel.add(nicknameLable);
			inputAddressPanel.add(nicknameField);
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
			
//			sendImgBtn = new JButton("IMG");
			sendFileBtn = new JButton("FILE");
			sendEtcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			sendEtcPanel.add(sendImgBtn);
			sendEtcPanel.add(sendFileBtn);
			sendMsgArea = new JTextArea(3, 1);
			sendMsgPanel = new JScrollPane(sendMsgArea);
			sendMsgBtn = new JButton("SEND");
			sendPanel = new JPanel(new BorderLayout());
			sendPanel.add(sendEtcPanel, BorderLayout.NORTH);
			sendPanel.add(sendMsgPanel, BorderLayout.CENTER);
			sendPanel.add(sendMsgBtn, BorderLayout.EAST);
			
			
			this.add(settingPanel, BorderLayout.NORTH);
			this.add(contentAreaPanel, BorderLayout.CENTER);
			this.add(sendPanel, BorderLayout.SOUTH);
			
			
			addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			portLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nicknameField.setEditable(false);
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
			sendFileBtn.addActionListener(new btnAction(0));
			
			dt = new DropTarget(sendMsgArea, DnDConstants.ACTION_COPY_OR_MOVE, new DtClass(), true, null);
			
			inputMsgThread = new ThreadControl();
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
			inputAddressPanel = new JPanel(new GridLayout(3, 2));
			inputAddressPanel.add(nicknameLable);
			inputAddressPanel.add(nicknameField);
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
			
//			sendImgBtn = new JButton("IMG");
			sendFileBtn = new JButton("FILE");
			sendEtcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			sendEtcPanel.add(sendImgBtn);
			sendEtcPanel.add(sendFileBtn);
			sendMsgArea = new JTextArea(3, 1);
			sendMsgPanel = new JScrollPane(sendMsgArea);
			sendMsgBtn = new JButton("SEND");
			sendPanel = new JPanel(new BorderLayout());
			sendPanel.add(sendEtcPanel, BorderLayout.NORTH);
			sendPanel.add(sendMsgPanel, BorderLayout.CENTER);
			sendPanel.add(sendMsgBtn, BorderLayout.EAST);
			
			
			this.add(settingPanel, BorderLayout.NORTH);
			this.add(contentAreaPanel, BorderLayout.CENTER);
			this.add(sendPanel, BorderLayout.SOUTH);
			
			
			addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			portLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nicknameField.setEditable(false);
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
			sendFileBtn.addActionListener(new btnAction(0));
			
			dt = new DropTarget(sendMsgArea, DnDConstants.ACTION_COPY_OR_MOVE, new DtClass(), true, null);
			
			inputMsgThread = new ThreadControl();
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
								
								contentArea.append("===============Server Activated...\n");
								contentArea.append("===============Connection ...\n");
								client = server.accept();
								contentArea.append("===============Connection Success!\n");
								
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
								contentArea.append("===============Connection ...\n");
								client = new Socket(ip, port);
								contentArea.append("===============Connection Success!\n");
								
								in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
								out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
								DataItem data = new DataItem();
								data.setNickname(nickname);
								out.writeObject(data);
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
				contentArea.append(nickname + ": " + msg + "\n");
				
				DataItem data = new DataItem();
				data.setNickname(nickname);
				data.setMessage(msg);
				try {
					out.writeObject(data);
					out.flush();
					
				} catch (IOException e1) {
					System.out.println("sendMsgBtn Error");
				}
			} else if(what.equals("FILE")) {
				JFileChooser chooser = new JFileChooser();
				int ret = chooser.showOpenDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, 
							"파일을 선택하지 않았습니다", 
							"경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				File file = new File(chooser.getSelectedFile().getPath());
				DataItem data = new DataItem();
				byte[] contents = new byte[(int) file.length()];
				
				try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
					bis.read(contents);
					
					data.setNickname(nickname);
					data.setFileName(file.getName());
					data.setFileContents(contents);
					out.writeObject(data);
					out.flush();
					
					contentArea.append(data.getFileName() + " - 파일보냄.\n");
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	class DtClass extends DropTargetAdapter {

		@Override
		public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(dtde.getDropAction());
				Transferable tf = dtde.getTransferable();
				try {
				List list = (List) tf.getTransferData(DataFlavor.javaFileListFlavor);
				File targetFIle = (File) list.get(0);
				int ret = JOptionPane.showConfirmDialog(null, "파일을 보내시겠습니까?.\n", "파일 송신", JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					return;
				} else {
					String targetFilePath = targetFIle.getAbsolutePath();
					File file = new File(targetFilePath);
					DataItem data = new DataItem();
					byte[] contents = new byte[(int) file.length()];
					
					try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
						bis.read(contents);
						
						data.setNickname(nickname);
						data.setFileName(file.getName());
						data.setFileContents(contents);
						out.writeObject(data);
						out.flush();
						
						contentArea.append(data.getFileName() + " - 파일보냄.\n");
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
			String sender = "";
			while(true) {
				try {
					data = (DataItem) in.readObject();
					sender = data.getNickname();
					int type =  data.getDataType();
					if(type == DataItem.TYPE_JOIN) {
						contentArea.append(sender + " 님이 입장하셨습니다.\n");
					} else if(type == DataItem.TYPE_MESSAGE) {
						contentArea.append(sender + ": " + data.getMessage() + "\n");
					} else if(type == DataItem.TYPE_FILE) {
						int ret = JOptionPane.showConfirmDialog(null, sender + " 님이 파일을 보냈습니다.\n받으시겠습니까?", "파일 수신", JOptionPane.YES_NO_OPTION);
						if(ret != JOptionPane.YES_OPTION) {
							contentArea.append("파일수신을 거부하였습니다.\n");
						} else {
							
							File dir = new File(".\\Downloads");
							if(!dir.exists()) dir.mkdirs();
							File file = new File(dir, data.getFileName());
							
							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
							bos.write(data.getFileContents());
							bos.flush();
							bos.close();
							contentArea.append(data.getFileName() + " - 수신완료!\n");
						}
					}
					
					
				} catch (Exception e) {
					contentArea.append(sender + " 님이 퇴장하셨습니다.\n");
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
		contentArea.append("===============Disconnect\n");
		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		sendMsgBtn.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}




