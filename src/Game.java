import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Game class controls all of the game logic in order to run through the entire game
 */
public class Game{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
	//List of collections containing cards and players
	public static List<Player> players = new ArrayList<Player>();
	public static List<Character> characters = new ArrayList<Character>();
	public static List<Weapon> weapons = new ArrayList<Weapon>();
	public static List<Estate> estates = new ArrayList<Estate>();

	public static List<Player> orderedPlayers = new ArrayList<Player>();
	public static List<Character> orderedCharacters = new ArrayList<Character>();
	public static List<Weapon> orderedWeapons = new ArrayList<Weapon>();
	public static List<Estate> orderedEstates = new ArrayList<Estate>();

	public static Guess finalGuess = new Guess(null, null, null);
	public static int move;
	public static int index = 0;
	Scanner sc = new Scanner(System.in);
	static Board board = new Board();
	GUI gui = new GUI();
	public int count = 0;
	public Player currentTurn;
	public Estate currentEstate;

  //------------------------
  // CONSTRUCTOR
  //------------------------
/**
 * Game constructor
 */
  public Game(){
	  setCharacters();
	  setWeapons();
	  setEstateWeapons();
	  setPlayers();
	  board.setEstates();
	  board.setPlayers();
	  chooseMurderer();
	  dealCards();
	  gui.menuScreen();
	  begin();
	  //startGame();
	  board.drawBoard();
	  //start();
  }

  /**
   * Begin method to begin the game
   */
  public void begin() {
	  while(true) {
		  System.out.println("\n");
		  if(gui.set == false) {
			  break;
		  }
	  }
  }
//  /**
//   *
//   * starts the game asking for the amount of players
//   *
//   */
//  public void startGame() {
//	  System.out.println("Each player choose a character out of Lucilla, Bert, Maline and Percy");
//	  int num;
//	  Collections.shuffle(players); // shuffles the collection
////	  do {
////	  	System.out.println("How many players 1-4:");
////	  	num = sc.nextInt();
////	  } while (!isValid(num));
//  }

  /**
   * Adds all players to a list
   *
   * @param player1
   * first player
   * @param player2
   * second player 
   * @param player3
   * third player
   * @param player4
   * fourth player
   */
  public void addPlayers(Player player1, Player player2, Player player3, Player player4) {
	players.add(player1);
	players.add(player2);
	players.add(player3);
	players.add(player4);
	orderedPlayers.add(player1);
	orderedPlayers.add(player2);
	orderedPlayers.add(player3);
	orderedPlayers.add(player4);
  }

//  /**
//   *
//   * Checks for valid number of players in game
//   *
//   * @param num
//   * number of players given from input
//   * @return
//   * True or false depending on if valid number is given
//   */
//  public boolean isValid(int num) {
//	  if (num > 4) {
//		  System.out.println("Too many players");
//		  return false;
//	  }
//	  else if (num < 1) {
//		  System.out.println("Too few players");
//		  return false;
//	  }
//	  return true;
//  }

//  /**
//   * Starts game by choosing a random player
//   *
//   */
//  public void start() {
//	  count = 0;
//	  System.out.println("\n");
//	  Player player = players.get(index); // gets player at start of list for first turn
//	  System.out.println("It is " + player.toString() + "'s turn");
//	  System.out.println("Type 'ready' to begin turn");
//	  String ready = sc.next(); // checks if player is ready
//	  if(ready.equals("ready")) {
//		  handOrDice(player);
//	  } else {
//		  System.out.println("Invalid Input");
//		  start();
//	  }
//  }

  /**
   * Checks for game over
   *
   */
  public void gameOver() {
	  if (players.isEmpty()) { // checks if no players are left in the game
		  System.out.println("Game Over no body wins");
		  sc.close();
		  System.exit(0);
	  }
  }

//  /**
//   * Choose between checking hand or dice
//   *
//   * @param player
//   * current player
//   */
//  public void handOrDice(Player player) {
//	  currentTurn = player;
//	  System.out.println("Type 'hand' to see cards in hand or 'dice' to roll the dice");
//	  String next = sc.next();
//	  if(next.equals("hand")) { // checks to see if player wants to check his hand or roll the dice
//		  System.out.println(player.toString() + "'s Hand" + player.getHand());
//		  handOrDice(player);
//	  } else if (next.equals("dice")) {
//		  move = diceRoll(); // rolls dice
//		  System.out.println("You rolled a " + move);
//		  while(count < getMove()) {
//			  System.out.println("\n");
//			  System.out.println("Press 'a' to move left press 's' to move down press 'd' to move right and press 'w' to move up, then press enter to submit it");
//			  keyPressed(player);
//		  }
//		  guessOrEnd(player);
//	  } else {
//		  System.out.println("Invalid Input");
//		  handOrDice(player);
//	  }
//  }

//  /**
//   * Checks if player want to make a guess or end turn
//   *
//   *
//   * @param player
//   * current player
//   */
//  public void guessOrEnd(Player player) {
//	  System.out.println("\n");
//	  boolean valid = true;
//	  while(valid) { // makes sure player gives valid input
//		  if (player.inEstate()) { // checks to see if player is currently inside an estate
//			  System.out.println("Type 'guess' to make a guess using the estate you are in, 'solve' to make a final solve attempt or 'end' to end turn");
//			  String string = sc.next();
//			  if(string.equals("guess")) {
//				  makeGuess(player);
//				  valid = false; // acknowledges valid input
//			  }
//			  else if(string.equals("solve")) {
//				  makeFinalGuess(player);
//				  valid = false;
//			  }
//			  else if (string.equals("end")) {
//				  index = (index + 1) % players.size(); // gets next player in list
//				  start();
//				  valid = false;
//			  }
//			  else {
//				  System.out.println("Invalid Input");
//			  }
//		  } else { // if player is not in an estate
//			  System.out.println("Type 'solve' to make a final solve attempt or 'end' to end turn");
//			  String string = sc.next();
//			  if(string.equals("solve")) {
//				  makeFinalGuess(player);
//				  valid = false;
//			  }
//			  else if (string.equals("end")) {
//				  index = (index + 1) % players.size();
//				  start();
//				  valid = false;
//			  }
//			  else {
//				  System.out.println("Invalid Input");
//			  }
//		  }
//	  }
//  }

  /**
   * Player makes a guess if they are in an estate
   *
   * @param Player
   * current player
   */
  public void makeGuess(Player player) {
	  boolean chooseChar = true;
	  boolean chooseWeapon = true;
	  System.out.println("Characters: 1-Lucilla, 2-Bert, 3-Maline, 4-Percy");
	  System.out.println("Weapons: 1-Broom, 2-Scissors, 3-Knife, 4-Shovel, 5-iPad");
	  Character character = null;
	  Weapon weapon = null;
	  while (chooseChar) { // checks for correct input
		  System.out.println("Type the corresponding number next to the character you want to choose:");
		  int c = sc.nextInt();
		  if (c == 1) {
			  character = orderedCharacters.get(0); // gets character from list which user has choosen
			  chooseChar = false;
		  } else if (c == 2) {
			  character = orderedCharacters.get(1);
			  chooseChar = false;
		  } else if (c == 3) {
			  character = orderedCharacters.get(2);
			  chooseChar = false;
		  } else if (c == 4) {
			  character = orderedCharacters.get(3);
			  chooseChar = false;
		  } else {
			  System.out.println("Invalid Character");
		  }
	  }
	  while (chooseWeapon) {
		  System.out.println("Type the corresponding number next to the weapon you think they used:");
		  int w = sc.nextInt();
		  if (w == 1) {
			  weapon = orderedWeapons.get(0); // gets weapon from list which user has choosen
			  chooseWeapon = false;
		  } else if (w == 2) {
			  weapon = orderedWeapons.get(1);
			  chooseWeapon = false;
		  } else if (w == 3) {
			  weapon = orderedWeapons.get(2);
			  chooseWeapon = false;
		  } else if (w == 4) {
			  weapon = orderedWeapons.get(3);
			  chooseWeapon = false;
		  } else if (w == 5) {
			  weapon = orderedWeapons.get(4);
			  chooseWeapon = false;
		  }else {
			  System.out.println("Invalid weapon");
		  }
	  }
	  System.out.println("Your guess is " + character.toString() + ", " + weapon.toString() + ", " + currentEstate.toString());
	  refute(player);
  }

  /**
   * Gives players ability to refute guess
   *
   * @param player
   * current player
   */
  public void refute(Player player) {
	  boolean next = true;
	  boolean done = true;
	  int cycle = 0;
	  int i = 1;
	  int point = orderedPlayers.indexOf(player); // gets index of current player in list
	  if(point == 3) { // makes sure all the values are in bound of the list
		  point = 0;
		  i = 0;
	  }
	  while(done) {
		  next = true;
		  if(point + i == 4) { // makes sure all the values are in bound of the list
			  i = 0;
			  point = 0;
		  }
		  Player currentPlayer = orderedPlayers.get(point+i); // gets player after current player
		  System.out.println("Pass the game over to " + currentPlayer + " type 'hand' to check hand");
		  String string = sc.next();
		  if(string.equals("hand")) {
			  while(next) {
				  System.out.println(currentPlayer.toString() + "'s Hand" + currentPlayer.getHand()); // displays players hand
				  System.out.println("Say the card which you want to refute with then type 'end' otherwise if you cant refute type 'next' to go to next player if there is one");
				  String refute = sc.next();
				  if(refute.equals("end")) { // end refution goes to next players turn
					  index = (index + 1) % players.size();
					  //start();
					  next = false;
					  done = false;
				  } else if (refute.equals("next")) {
					  cycle++; // keeps track of how many players we have been through
					  i++;
					  if(cycle == orderedPlayers.size()-1) { // if we have asked all 3 players if they can refute end turn go to next player
						  System.out.println("No one was able to refute the guess");
						  index = (index + 1) % players.size();
						  //start();
						  next = false;
						  done = false;
					  }
					  next = false;
				  } else {
					  System.out.println("Invalid Input");
				  }
			  }
		  } else {
			  System.out.println("Invalid Input");
		  }
	  }
  }

  /**
   * Makes a final guess and check whether not player has won or not
   *
   * @param player
   * current player
   */
  public void makeFinalGuess(Player player) {
	  System.out.println("Characters: 1-Lucilla, 2-Bert, 3-Maline, 4-Percy");
	  System.out.println("Weapons: 1-Broom, 2-Scissors, 3-Knife, 4-Shovel, 5-iPad");
	  System.out.println("Estates: 1-Haunted House, 2-Manic Manor, 3-Villa Celia, 4-Calamity Castle, 5-Peril Palace\n");
	  boolean chooseChar = true;
	  boolean chooseWeapon = true;
	  boolean chooseEstate = true;
	  Character character = null;
	  Weapon weapon = null;
	  Estate estate = null;
	  while (chooseChar) { // checks for correct input
		  System.out.println("Type the corresponding number next to the character you want to choose:");
		  int c = sc.nextInt();
		  if (c == 1) {
			  character = orderedCharacters.get(0); // gets character from list which user has choosen
			  chooseChar = false;
		  } else if (c == 2) {
			  character = orderedCharacters.get(1);
			  chooseChar = false;
		  } else if (c == 3) {
			  character = orderedCharacters.get(2);
			  chooseChar = false;
		  } else if (c == 4) {
			  character = orderedCharacters.get(3);
			  chooseChar = false;
		  } else {
			  System.out.println("Invalid Character");
		  }
	  }
	  while (chooseWeapon) {
		  System.out.println("Type the corresponding number next to the weapon you think they used:");
		  int w = sc.nextInt();
		  if (w == 1) {
			  weapon = orderedWeapons.get(0); // gets weapon from list which user has choosen
			  chooseWeapon = false;
		  } else if (w == 2) {
			  weapon = orderedWeapons.get(1);
			  chooseWeapon = false;
		  } else if (w == 3) {
			  weapon = orderedWeapons.get(2);
			  chooseWeapon = false;
		  } else if (w == 4) {
			  weapon = orderedWeapons.get(3);
			  chooseWeapon = false;
		  } else if (w == 5) {
			  weapon = orderedWeapons.get(4);
			  chooseWeapon = false;
		  }else {
			  System.out.println("Invalid weapon");
		  }
	  }
	  while (chooseEstate) {
		  System.out.println("Type the corresponding number next to the estate you think the murder took place");
		  int e = sc.nextInt();
		  if (e == 1) {
			  estate = orderedEstates.get(0); // gets estate from list which user has chosen
			  chooseEstate = false;
		  } else if (e == 2) {
			  estate = orderedEstates.get(1);
			  chooseEstate = false;
		  } else if (e == 3) {
			  estate = orderedEstates.get(2);
			  chooseEstate = false;
		  } else if (e == 4) {
			  estate = orderedEstates.get(3);
			  chooseEstate = false;
		  } else if (e == 5) {
			  estate = orderedEstates.get(4);
			  chooseEstate = false;
		  }else {
			  System.out.println("Invalid estate");
		  }
	  }
	  System.out.println("Your guess is " + character.toString() + ", " + weapon.toString() + ", " + estate.toString());
	  if(character.equals(finalGuess.character) && weapon.equals(finalGuess.weapon) && estate.equals(finalGuess.estate)) { // checks to see if user guessed correctly
		  System.out.println("That is correct " + player.toString() + " won the game");
		  sc.close();
	  } else {
		  // if incorrect user is removed from the game, game then checks if game over status is met
		  System.out.println("That is incorrect " + player.toString() + " is out of the game");
		  players.remove(player);
		  gameOver();
		  index = (index + 1) % players.size();
		  //start();
	  }
  }
//------------------------
  // INTERFACE
  //------------------------


  /**
   * Sets up all the players
   *
   */
  public void setPlayers() {
	  Player player1 = new Player("Lucilla");
	  Player player2 = new Player("Bert");
	  Player player3 = new Player("Maline");
	  Player player4 = new Player("Percy");
	  addPlayers(player1, player2, player3, player4); //adds all players to collections
  }
  /**
   * Sets up all estate cards
   *
   */
  public void setEstateWeapons() {
	  Collections.shuffle(weapons); // mixes weapons up
	  Estate haunted_house = new Estate("Haunted House", weapons.get(0));
	  Estate manic_manor = new Estate("Manic Manor", weapons.get(1));
	  Estate villa_celia = new Estate("Villa Celia", weapons.get(2));
	  Estate calamity_castle = new Estate("Calamity Castle", weapons.get(3));
	  Estate peril_palace = new Estate("Peril Palace", weapons.get(4));
	  estates.add(haunted_house);
	  estates.add(manic_manor);
	  estates.add(villa_celia);
	  estates.add(calamity_castle);
	  estates.add(peril_palace);
	  orderedEstates.add(haunted_house);
	  orderedEstates.add(manic_manor);
	  orderedEstates.add(villa_celia);
	  orderedEstates.add(calamity_castle);
	  orderedEstates.add(peril_palace);
  }

  /**
   * Sets up all character cards
   *
   *
   */
  public void setCharacters() {
	  Character lucilla = new Character("Lucilla");
	  Character bert = new Character("Bert");
	  Character maline = new Character("Maline");
	  Character percy = new Character("Percy");
	  characters.add(lucilla);
	  characters.add(bert);
	  characters.add(maline);
	  characters.add(percy);
	  orderedCharacters.add(lucilla);
	  orderedCharacters.add(bert);
	  orderedCharacters.add(maline);
	  orderedCharacters.add(percy);
  }

  /**
   * Sets up all weapon cards
   *
   */
  public void setWeapons() {
	  Weapon broom = new Weapon("Broom");
	  Weapon scissors = new Weapon("scissors");
	  Weapon knife = new Weapon("knife");
	  Weapon shovel = new Weapon("shovel");
	  Weapon ipad = new Weapon("ipad");
	  weapons.add(broom);
	  weapons.add(scissors);
	  weapons.add(knife);
	  weapons.add(shovel);
	  weapons.add(ipad);
	  orderedWeapons.add(broom);
	  orderedWeapons.add(scissors);
	  orderedWeapons.add(knife);
	  orderedWeapons.add(shovel);
	  orderedWeapons.add(ipad);
  }

  /**
   * Chooses the murderer randomly
   *
   */
  public static void chooseMurderer() {
	  //shuffles all of the collections
	  Collections.shuffle(characters);
	  Collections.shuffle(weapons);
	  Collections.shuffle(estates);
	  //creates guess object and stores murderer
	  finalGuess = new Guess(characters.remove(0), weapons.remove(0), estates.remove(0));
  }

  /**
   * Deals out the remainder of cards to all of the players
   *
   */
  public static void dealCards() {
	  List<Card> allCards = new ArrayList<Card>();
	  ArrayList<Card> hand;
	  allCards.addAll(characters);
	  allCards.addAll(weapons);
	  allCards.addAll(estates);
	  Collections.shuffle(allCards);
	  int hSize = allCards.size()/players.size(); // gets the average amount of cards for each hand
	  for (Player c : players) {
		  hand = new ArrayList<Card>();
		  for (int i = 0; i < hSize; i++) {
			  hand.add(allCards.remove(i)); // adds card to player hand then removes it
		  }
		  c.addToHand(hand); // add current hand to hand collection in player class for storage
	  }
	  while (!allCards.isEmpty()) { // while the remainder of cards are not empty divide them up amongst the players
		  for (Player c : players) {

			  for (int i = 0; i < 1; i++) {
				  if(allCards.isEmpty()) { // break out of loop if there are no more cards to hand out
					  break;
				  }
				  c.getHand().add(allCards.remove(i));
			  }
		  }
	  }
  }

  /**
   * gets estates from list
   *
   * @param name
   * name of estate
   * @return
   * estate name which equals the passed estate
   */
	public static Estate getEstate(String name) {
		Estate result = new Estate("", new Weapon(""));
		for(Estate estate : orderedEstates) {
			if(estate.getName().equals(name)) { // if the estate names are equal get estate then return it
				result = estate;
			}
		}
		return result;
	}

	/**
	 * gets player from list
	 *
	 * @param name
	 * name of player
	 * @return
	 * player name which equals the passed player
	 */
	public static Player getPlayer(String name) {
		Player result = new Player("");
		for (Player player : orderedPlayers) {
			if(player.getName().equals(name)) { // if player names are equal get player then return it
				result = player;
			}
		}
		return result;
	}

  /**
   * Rolls dice for random number
   *
   * @return
   * value of dice roll
   */
  public static int diceRoll() {
	  int diceA = 1 + (int)	(Math.random() * 7);
	  int diceB = 1 + (int) Math.random() * 7;
	  return diceA + diceB;
  }

  /**
   * Checks if player is in estate
   * @param player
   * current player
   */
  public void inEstate(Player player) {
		if(player.getEstate() != null) {
			currentEstate = player.getEstate();
			move = 0;
			player.setInEstate(true);
		} else {
			player.setInEstate(false);
		}
  }

//  /**
//   * Detects if valid key was pressed then calls method to move player
//   *
//   * @param player
//   * current player
//   */
//  public void keyPressed(Player player) {
//	  String input = sc.next();
//	  if(input.equals("a")) {
//		  if(count < getMove()) {
//				board.movePlayer(currentTurn, "left");
//				board.drawBoard();
//				inEstate(player);
//			 	count++;
//			 }
//	  } else if (input.equals("s")) {
//			if(count < getMove()) {
//				board.movePlayer(currentTurn, "down");
//				board.drawBoard();
//				inEstate(player);
//			 	count++;
//			 }
//	  } else if (input.equals("d")) {
//			if(count < getMove()) {
//				board.movePlayer(currentTurn, "right");
//				board.drawBoard();
//				inEstate(player);
//			 	count++;
//			 }
//	  } else if (input.equals("w")) {
//		  if(count < getMove()) {
//				board.movePlayer(currentTurn, "up");
//				board.drawBoard();
//				inEstate(player);
//				count++;
//			}
//	  } else {
//		  System.out.println("Invalid Input");
//	  }
//  }

  /**
   *
   * @return
   * the amount of spaces player is allowed to move
   */
  public int getMove() {
	  return move;
  }
  
  /**
   * Main function starts the game 
   * 
   * @param args
   */
  public static void main(String[] args) {
	  new Game();
  }
}
