package thread;
import java.awt.*;
import javax.swing.*;
public class RunnableTimerEx extends JFrame implements Runnable {
	private JLabel timerLabel = new JLabel();
	public void run() {
		int num = 0;
		while(true) {
			timerLabel.setText(Integer.toString(num));
			num++;
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public RunnableTimerEx() {
		setTitle("RunnableTimerEx ¿¹Á¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));		
		c.add(timerLabel); 
		
		setSize(300,150);
		setVisible(true);	
		
		new Thread(this).start();
	}

	public static void main(String[] args) {
		new RunnableTimerEx();
	}	
}



