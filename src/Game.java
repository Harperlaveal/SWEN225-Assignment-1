import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
	//List of collections containing cards and players
	public static List<Player> players = new ArrayList<Player>();
	public static List<Character> characters = new ArrayList<Character>();
	public static List<Weapon> weapons = new ArrayList<Weapon>();
	public static List<Estate> estates = new ArrayList<Estate>();
	public static Guess finalGuess = new Guess(null, null, null);
	Board board = new Board();
	Scanner sc;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(){
	  setCharacters();
	  setWeapons();
	  setEstateWeapons();
	  StartGame();
	  chooseMurderer();
	  dealCards();
	  board.setEstates();
  }
  
  /**
   * 
   * Initialises players and starts the game
   * 
   */
  public void StartGame() {
	  int num;
	  Player player1 = new Player("Lucilla");
	  Player player2 = new Player("Bert");
	  Player player3 = new Player("Maline");
	  Player player4 = new Player("Percy");
	  addPlayers(player1, player2, player3, player4);
	  do {
	  sc = new Scanner(System.in);
	  System.out.println("How many players 1-4:");
	  num = sc.nextInt();
	  } while (!isValid(num));
  }
  
  public void addPlayers(Player player1, Player player2, Player player3, Player player4) {
	players.add(player1);
	players.add(player2);
	players.add(player3);
	players.add(player4);
  }
  
  /**
   * 
   * Checks for valid number of players in game 
   * 
   * @param num
   * number of players given from input
   * @return
   * True or false depending on if valid number is given
   */
  public boolean isValid(int num) {
	  if (num > 4) {
		  System.out.println("Too many players");
		  return false;
	  }
	  else if (num < 1) {
		  System.out.println("Too few players");
		  return false;
	  }
	  sc.close();
	  return true;
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  /**
   * Sets up all estate cards
   * 
   */
  public void setEstateWeapons() {
	  Collections.shuffle(weapons); // mixes weapons up
	  estates.add(new Estate("Haunted House", weapons.get(0)));
	  estates.add(new Estate("Manic Manor", weapons.get(1)));
	  estates.add(new Estate("Villa Celia", weapons.get(2)));
	  estates.add(new Estate("Calamity Castle", weapons.get(3)));
	  estates.add(new Estate("Peril Palace", weapons.get(4)));
  }

	/**
	 * Gets an estate from the list
	 *
	 */
	public static Estate getEstate(String name) {
		Estate result = new Estate("", new Weapon(""));
		for(Estate estate : estates) {
			if(estate.getName().equals(name)) {
				result = estate;
			}
		}
		return result;
	}

	/**
   * Sets up all character cards
   * 
   * 
   */
  public void setCharacters() {
	  characters.add(new Character("Lucilla"));
	  characters.add(new Character("Bert"));
	  characters.add(new Character("Maline"));
	  characters.add(new Character("Percy"));
  }
  
  /**
   * Sets up all weapon cards
   * 
   */
  public void setWeapons() {
	  weapons.add(new Weapon("Broom"));
	  weapons.add(new Weapon("Scissors"));
	  weapons.add(new Weapon("Knife"));
	  weapons.add(new Weapon("Shovel"));
	  weapons.add(new Weapon("iPad"));
  }
  
  /**
   * Chooses the murderer randomly
   * 
   */
  public void chooseMurderer() {
	  //shuffles all of the collections 
	  Collections.shuffle(characters);
	  Collections.shuffle(weapons);
	  Collections.shuffle(estates);
	  //creates guess object and stores murderer
	  Guess guess = new Guess(characters.remove(0), weapons.remove(0), estates.remove(0));
	  finalGuess.setMurderer(guess);
	  
  }

  public void startBoard() {
  	Board.drawBoard();
  }
  
  /**
   * Deals out the remainder of cards to all of the players
   * 
   */
  public void dealCards() {
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
  
  public static void main(String[] args) {
	  new Game();
	  System.out.println("Murderer" + " = " + finalGuess.getMurderer().toString());
	  for (Player c : players) {
		  System.out.println(c.getName() + " = Hand" + c.getHand());

	  }
  }
}
