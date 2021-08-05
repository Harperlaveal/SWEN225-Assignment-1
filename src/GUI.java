import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public static JMenu menu;
	public static JMenuBar bar;
	public static JMenuItem start, exit;
	public boolean set = true;
	
	  public void menuScreen(){
		  frame = new JFrame("Menu Screen");
		  menu = new JMenu("Menu");
		  bar = new JMenuBar();
		  start = new JMenuItem("Start Game");
	      exit = new JMenuItem("Exit Game");
	      start.addActionListener(this);
	      exit.addActionListener(this);
	      menu.add(start);
	      menu.add(exit);
	      bar.add(menu);
	      frame.setJMenuBar(bar);
		  frame.setSize(500, 500);
	      frame.setVisible(true);
	      frame.setResizable(false);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Start Game")) {
			frame.setVisible(false);
			set = false;
		} else if (action.equals("Exit Game")) {
			frame.setVisible(false);
			System.exit(0);
		}
		
	}
	
	  
}
