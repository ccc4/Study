import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class M1 extends JFrame {
	
	public M1() {
		super("Á¦¸ñ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}
	
	void createMenu() {
		String[] menuNames = {"File", "Edit", "Source", "Project", "Run"};
		JMenu[] menus = new JMenu[menuNames.length];
		JMenuBar menubar = new JMenuBar();
		for(int i=0;i<menuNames.length;i++) {
			menus[i] = new JMenu(menuNames[i]);
			menubar.add(menus[i]);
		}
		JMenuItem i1 = new JMenuItem("New");
		JMenuItem i2 = new JMenuItem("Open");
		JMenuItem i3 = new JMenuItem("Save");
		JMenuItem i4 = new JMenuItem("SaveAs");
		menus[0].add(i1);
		menus[0].add(i2);
		menus[0].addSeparator();
		menus[0].add(i3);
		menus[0].add(i4);
		this.setJMenuBar(menubar);
	}
	
	
	public static void main(String[] args) {
		new M1();
	}
}
