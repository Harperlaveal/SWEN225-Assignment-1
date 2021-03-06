import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * GUI class which handles all of the frames and the transitions between them
 */
public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public JFrame menuFrame, playerFrame;
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
	public ArrayList<JFrame> nameFrames = new ArrayList<JFrame>();
	public JLabel boardLabel = new JLabel("");

	/**
	 * Setting up the initial menu screen frame
	 */
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
		JRadioButton threePlayers = new JRadioButton("3");
		JRadioButton fourPlayers = new JRadioButton("4");
		JButton playerCountConfirm = new JButton("Confirm players");
		threePlayers.addActionListener(this);
		fourPlayers.addActionListener(this);
		playerCountConfirm.addActionListener(this);
		ButtonGroup playersGroup = new ButtonGroup(); // group to link radio buttons together
		playersGroup.add(threePlayers);
		playersGroup.add(fourPlayers);
		playerFrame.setLayout(new FlowLayout());
		playerFrame.add(label);
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
		JFrame nameFrame = new JFrame("Enter name");
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
		nameFrames.add(nameFrame);

	}

	/**
	 * Confirmation process for exiting the program
	 */
	public void exitDialog(){
		JFrame exitFrame = new JFrame();
		d = new JDialog(exitFrame , "exitDialog", true);
		d.setLayout( new FlowLayout() );
		JButton yesButton = new JButton ("Yes");
		JButton noButton = new JButton ("No");
		yesButton.addActionListener ( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				d.setVisible(false);
				menuFrame.setVisible(false);
				System.exit(0);
			}
		});
		noButton.addActionListener ( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				d.setVisible(false);
				exitFrame.setVisible(false);
			}
		});
		d.add( new JLabel ("Are you sure you want to quit?"));
		d.add(yesButton);
		d.add(noButton);
		d.setSize(400,300);
		d.setVisible(true);
	}

	/**
	 * Setting up the main game Frame GUI
	 */
	public void gameBoard() {
		move = Game.diceRoll();
		boardFrame = new JFrame("Board");
		JButton hand = new JButton("Hand");
		JButton resetGame = new JButton("Reset Game");
		JButton roll = new JButton("Roll");
		JButton quitGame = new JButton("Exit Game");
		JButton upButton = new JButton("Up");
		JButton downButton = new JButton("Down");
		JButton leftButton = new JButton("Left");
		JButton rightButton = new JButton("Right");
		resetGame.setBounds(10, 10, 150, 150);
		hand.setBounds(10, 170, 150, 150);
		roll.setBounds(10, 330, 150, 150);
		quitGame.setBounds(10, 490, 150, 150);
		upButton.setBounds(250, 545, 50, 50);
		downButton.setBounds(250, 595, 50, 50);
		leftButton.setBounds(200, 570, 50, 50);
		rightButton.setBounds(300, 570, 50, 50);
		hand.addActionListener(this);
		resetGame.addActionListener(this);
		roll.addActionListener(this);
		quitGame.addActionListener(this);
		upButton.addActionListener(this);
		downButton.addActionListener(this);
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		boardFrame.add(resetGame);
		boardFrame.add(hand);
		boardFrame.add(roll);
		boardFrame.add(quitGame);
		boardFrame.add(upButton);
		boardFrame.add(downButton);
		boardFrame.add(leftButton);
		boardFrame.add(rightButton);
		String currentPlayer = Game.players.get(Game.index).getName();
		JLabel current = new JLabel("Current Player");
		current.setBounds(175, 10, 500, 15);
		if(players.get(currentPlayer) == null) {
			current.setText("It is " + currentPlayer +"'s (bot) turn");
		}else {
			current.setText("It is " + currentPlayer +"'s (" + players.get(currentPlayer).getName() + ") turn");
		}
		boardFrame.add(current);
		updateBoard();
		boardFrame.setSize(800,700);
		boardFrame.setLayout(null);
		boardFrame.setVisible(true);
	}

	/**
	 * Helper method to update the actual game board in the game board frame
	 */
	public void updateBoard() {
		ArrayList<String> board = Game.board.getBoard();
		String boardString = "";
		for(String string : board) {
			boardString += string;
		}
		boardLabel.setText(boardString);
		boardLabel.setBounds(175, 70, 500, 500);
		boardFrame.add(boardLabel);
	}

	/**
	 * Show which cards are in the current player's hand
	 */
	public void showHand() {
		Player player = Game.players.get(Game.index);
		showHand = new JLabel("showHand");
		showHand.setBounds(175, 30, 300, 15);
		boardFrame.add(showHand);
		showHand.setText(player.toString() + "'s Hand" + player.getHand());
	}

	/**
	 * Show which number was rolled
	 */
	public void showRoll() {
		showHand.setText("");
		showRoll = new JLabel("showRoll");
		showRoll.setBounds(175, 50, 300, 15);
		boardFrame.add(showRoll);
		showRoll.setText("You Rolled a: " + Integer.toString(move));
	}

	/**
	 * Hides all the name frames
	 */
	public void hideNameFrames() {
		for(JFrame frame : nameFrames) {
			frame.setVisible(false);
		}
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
			newPlayer.setName(name);
			players.put(playerChoice, newPlayer);
			if(currentPlayerCount < totalPlayerCount) {
				enterName();
			}else {
				for(Player player : players.values()) { // prints out for testing
					System.out.println(player.getName());
					System.out.println(player.character);
					System.out.println();
				}
				hideNameFrames();
				gameBoard();
			}
		} else if (action.equals("Hand")) {
			showHand();
		} else if (action.equals("Roll")) {
			showRoll();
		} else if (action.equals("Reset Game")) {
			totalPlayerCount = 0;
			currentPlayerCount = 0;
			nameFrames.clear();
			players.clear();
			boardFrame.setVisible(false);
			playerCount();
		} else if (action.equals("Up")) {
			Player currentPlayer = Game.players.get(Game.index);
			Game.board.movePlayer(currentPlayer, "up");
			Game.board.drawBoard();
			updateBoard();

		} else if (action.equals("Down")) {
			Player currentPlayer = Game.players.get(Game.index);
			Game.board.movePlayer(currentPlayer, "down");
			Game.board.drawBoard();
			updateBoard();

		} else if (action.equals("Left")) {
			Player currentPlayer = Game.players.get(Game.index);
			Game.board.movePlayer(currentPlayer, "left");
			Game.board.drawBoard();
			updateBoard();

		} else if (action.equals("Right")) {
			Player currentPlayer = Game.players.get(Game.index);
			Game.board.movePlayer(currentPlayer, "right");
			Game.board.drawBoard();
			updateBoard();

		}
	}
}