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
		  frame.setSize(1000, 1000);
	      frame.setVisible(true);
	      frame.setResizable(false);
	  }

	public void playerCount(){
		frame = new JFrame("Number of players");
		JTextField t1;
		t1=new JTextField("Number of players.");
		t1.setBounds(50,100, 200,30);
		frame.add(t1);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public void enterName(){
		frame = new JFrame("Enter name");
		JTextField t1;
		t1=new JTextField("Enter players name.");
		t1.setBounds(50,100, 200,30);
		frame.add(t1);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void pickCharacter(){
		frame = new JFrame("Choose character");
		JRadioButton r1=new JRadioButton("A) Male");
		JRadioButton r2=new JRadioButton("B) Female");
		r1.setBounds(75,50,100,30);
		r2.setBounds(75,100,100,30);
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);bg.add(r2);
		frame.add(r1);frame.add(r2);
		frame.setJMenuBar(bar);
		frame.setSize(1000, 1000);
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
		  d.setSize(500,500);
		  d.setVisible(true);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("Start Game")) {
			frame.setVisible(false);
			set = false;
			playerCount();
			enterName();
			pickCharacter();
		} else if (action.equals("Exit Game")) {
			exitDialog();
			frame.setVisible(false);
			System.exit(0);
		}
		
	}
	
	  
}
