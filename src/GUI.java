import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public static JMenu menu;
	public static JMenuBar bar;
	public static JMenuItem start, exit;
	public boolean set = true;
	public static JDialog d;
	
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

	  public void exitDialog(){
		  JFrame f= new JFrame();
		  d = new JDialog(f , "exitDialog", true);
		  d.setLayout( new FlowLayout() );
		  JButton b = new JButton ("Yes");
		  b.addActionListener ( new ActionListener()
		  {
			  public void actionPerformed( ActionEvent e )
			  {
				  d.setVisible(false);
			  }
		  });
		  d.add( new JLabel ("Are you sure you want to quit?"));
		  d.add(b);
		  d.setSize(300,300);
		  d.setVisible(true);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("Start Game")) {
			frame.setVisible(false);
			set = false;
		} else if (action.equals("Exit Game")) {
			exitDialog();
			frame.setVisible(false);
			System.exit(0);
		}
		
	}
	
	  
}
