import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public JFrame menuFrame, playerFrame, nameFrame, characterFrame;
	public static JMenu menu;
	public static JMenuBar bar;
	public static JMenuItem start, exit;
	public boolean set = true;
	public static JDialog d;
	public int totalPlayerCount = 0;
	public int currentPlayerCount = 0;
	public Map<String, Player> players = new HashMap<>();
	public JTextField nameTextField;
	public String playerChoice = "";
	public int move;
	public JFrame boardFrame;
	public JLabel showHand;
	public JLabel showRoll;

	public void menuScreen(){
		menuFrame = new JFrame("Menu Screen");
		menu = new JMenu("Menu");
		bar = new JMenuBar();
		start = new JMenuItem("Start Game");
		exit = new JMenuItem("Exit Game");
		start.addActionListener(this);
		exit.addActionListener(this);
		menu.add(start);
		menu.add(exit);
		bar.add(menu);
		menuFrame.setJMenuBar(bar);
		menuFrame.setSize(400, 300);
		menuFrame.setVisible(true);
		menuFrame.setResizable(false);
	}

	/**
	 * Frame to choose player count
	 */
	public void playerCount(){
		playerFrame = new JFrame("Number of players");
		playerFrame.setSize(400, 300);
		JLabel label = new JLabel("Enter number of players:");
		// radio buttons for choosing players
		JRadioButton onePlayer = new JRadioButton("1");
		JRadioButton twoPlayers = new JRadioButton("2");
		JRadioButton threePlayers = new JRadioButton("3");
		JRadioButton fourPlayers = new JRadioButton("4");
		JButton playerCountConfirm = new JButton("Confirm players");
		onePlayer.addActionListener(this);
		twoPlayers.addActionListener(this);
		threePlayers.addActionListener(this);
		fourPlayers.addActionListener(this);
		playerCountConfirm.addActionListener(this);
		ButtonGroup playersGroup = new ButtonGroup(); // group to link radio buttons together
		playersGroup.add(onePlayer);
		playersGroup.add(twoPlayers);
		playersGroup.add(threePlayers);
		playersGroup.add(fourPlayers);
		playerFrame.setLayout(new FlowLayout());
		playerFrame.add(label);
		playerFrame.add(onePlayer);
		playerFrame.add(twoPlayers);
		playerFrame.add(threePlayers);
		playerFrame.add(fourPlayers);
		playerFrame.add(playerCountConfirm);
		playerFrame.setVisible(true);
		playerFrame.setResizable(false);
	}

	/**
	 * Frame to enter player names and choose a character
	 */
	public void enterName(){
		playerFrame.setVisible(false);
		nameFrame = new JFrame("Enter name");
		nameFrame.setSize(400, 300);
		nameTextField = new JTextField("");
		nameTextField.setColumns(5);
		JLabel label = new JLabel("Enter next player name and choose character:");
		JRadioButton optionOne = new JRadioButton("Lucilla");
		JRadioButton optionTwo = new JRadioButton("Bert");
		JRadioButton optionThree = new JRadioButton("Maline");
		JRadioButton optionFour = new JRadioButton("Percy");
		optionOne.addActionListener(this);
		optionTwo.addActionListener(this);
		optionThree.addActionListener(this);
		optionFour.addActionListener(this);
		ButtonGroup playersGroup = new ButtonGroup();
		playersGroup.add(optionOne);
		playersGroup.add(optionTwo);
		playersGroup.add(optionThree);
		playersGroup.add(optionFour);
		JButton nameConfirm = new JButton("Confirm player");
		nameConfirm.addActionListener(this);
		nameFrame.setLayout(new FlowLayout());
		nameFrame.add(label);
		nameFrame.add(nameTextField);
		nameFrame.add(nameConfirm);
		nameFrame.add(optionOne);
		nameFrame.add(optionTwo);
		nameFrame.add(optionThree);
		nameFrame.add(optionFour);
		nameFrame.setVisible(true);
		nameFrame.setResizable(false);

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
	
	public void gameBoard() {
	  	move = Game.diceRoll();
	  	boardFrame = new JFrame("Board");
		JButton hand = new JButton("Hand");
		JButton resetGame = new JButton("Reset Game");
		JButton roll = new JButton("Roll");
		JButton quitGame = new JButton("Quit Game");
		resetGame.setBounds(10, 10, 150, 150);
		hand.setBounds(10, 170, 150, 150);
		roll.setBounds(10, 330, 150, 150);
		quitGame.setBounds(10, 490, 150, 150);
		hand.addActionListener(this);
		resetGame.addActionListener(this);
		roll.addActionListener(this);
		quitGame.addActionListener(this);
		boardFrame.add(resetGame);
		boardFrame.add(hand);
		boardFrame.add(roll);
		boardFrame.add(quitGame);
		String currentPlayer = Game.players.get(Game.index).getName();
		JLabel current = new JLabel("Current Player");
		current.setBounds(175, 10, 500, 15);
		current.setText("It is " + currentPlayer +"'s turn");
		boardFrame.add(current);
	    boardFrame.setSize(800,700);
	    boardFrame.setLayout(null);  
	    boardFrame.setVisible(true);
	}
	
  public void showHand() {
	  Player player = Game.players.get(Game.index);
	  showHand = new JLabel("showHand");
	  showHand.setBounds(175, 30, 300, 15);
	  boardFrame.add(showHand);
	  showHand.setText(player.toString() + "'s Hand" + player.getHand());
  }
  
  public void showRoll() {
	  showHand.setText("");
	  showRoll = new JLabel("showRoll");
	  showRoll.setBounds(175, 50, 300, 15);
	  boardFrame.add(showRoll);
	  showRoll.setText("You Rolled a: " + Integer.toString(move));
  }

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Start Game")) {
			menuFrame.setVisible(false);
			set = false;
			playerCount();

		} else if (action.equals("Exit Game")) {
			exitDialog();
			menuFrame.setVisible(false);
			System.exit(0);

		} else if (action.equals("1")) { // sets player count to which button is selected
			totalPlayerCount = 1;

		} else if (action.equals("2")) {
			totalPlayerCount = 2;

		} else if (action.equals("3")) {
			totalPlayerCount = 3;

		} else if (action.equals("4")) {
			totalPlayerCount = 4;

		} else if (action.equals("Confirm players")) { // confirms player count and moves on to name choice
			System.out.println(totalPlayerCount);
			enterName();

		} else if (action.equals("Lucilla")) {
			playerChoice = "Lucilla";

		} else if (action.equals("Bert")) {
			playerChoice = "Bert";

		} else if (action.equals("Maline")) {
			playerChoice = "Maline";

		} else if (action.equals("Percy")) {
			playerChoice = "Percy";

		} else if (action.equals("Confirm player")) {
			currentPlayerCount++;
			String name = nameTextField.getText();
			Player newPlayer = new Player(name);
			newPlayer.character = playerChoice;
			players.put(name, newPlayer);
			if(currentPlayerCount < totalPlayerCount) {
				enterName();
			}else {
				for(Player player : players.values()) { // prints out for testing
					System.out.println(player.getName());
					System.out.println(player.character);
					System.out.println();
				}
				// add call to the main game frame method here
				nameFrame.setVisible(false);
				gameBoard();
			}
		} else if (action.equals("Hand")) {
			showHand();
		} else if (action.equals("Roll")) {
			showRoll();
		} else if (action.equals("Reset Game")) {
			System.exit(0);
		} else if (action.equals("Quit Game")) {
			System.exit(0);
		}
	}
}
