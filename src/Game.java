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
	
	public static List<Character> orderedCharacters = new ArrayList<Character>();
	public static List<Weapon> orderedWeapons = new ArrayList<Weapon>();
	public static List<Estate> orderedEstates = new ArrayList<Estate>();
	
	public static Guess finalGuess = new Guess(null, null, null);
	public static int move;
	public static int index = 0;
	Scanner sc = new Scanner(System.in);

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
	  start();
  }
  
  /**
   * 
   * Initialises players and starts the game
   * 
   */
  public void StartGame() {
	  System.out.println("Each player choose a character out of Lucilla, Bert, Maline and Percy");
	  int num;
	  Player player1 = new Player("Lucilla");
	  Player player2 = new Player("Bert");
	  Player player3 = new Player("Maline");
	  Player player4 = new Player("Percy");
	  addPlayers(player1, player2, player3, player4); //adds all players to collections
	  Collections.shuffle(players); // shuffles the collection
	  do {
	  System.out.println("How many players 1-4:");
	  num = sc.nextInt();
	  } while (!isValid(num));
  }
  
  /**
   * Adds all players to a list
   * 
   * @param player1
   * @param player2
   * @param player3
   * @param player4
   */
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
	  return true;
  }
  
  /**
   * Starts game by choosing a random player
   * 
   */
  public void start() {
	  System.out.println("\n");
	  System.out.println("\n");
	  System.out.println("\n");
	  System.out.println("\n");
	  System.out.println("\n");
	  Player player = players.get(index); // gets player at start of list for first turn
	  System.out.println("It is " + player.toString() + "'s turn");
	  System.out.println("Type 'ready' to begin turn");
	  String ready = sc.next(); // checks if player is ready 
	  if(ready.equals("ready")) {
		  handOrDice(player);
	  } else {
		  System.out.println("Invalid Input");
		  start();
	  }
  }
  
  /**
   * Checks for game over
   * 
   */
  public void gameOver() {
	  if (players.isEmpty()) { // checks if no players are left in the game
		  System.out.println("Game Over no body wins");
		  System.exit(0);
	  }
  }
  
  /**
   * Choose between checking hand or dice
   * 
   * @param player
   * current player
   */
  public void handOrDice(Player player) {
	  System.out.println("Type 'hand' to see cards in hand or 'dice' to roll the dice");
	  String next = sc.next();
	  if(next.equals("hand")) { // checks to see if player wants to check his hand or roll the dice
		  System.out.println(player.toString() + "'s Hand" + player.getHand());
		  handOrDice(player);
	  } else if (next.equals("dice")) {
		  move = diceRoll(); // rolls dice
		  System.out.println("You rolled a " + move);
		  guessOrEnd(player);
	  } else {
		  System.out.println("Invalid Input");
		  handOrDice(player);
	  }
  }
  
  /**
   * Checks if player want to make a guess or end turn
   * 
   * 
   * @param player
   * current player
   */
  public void guessOrEnd(Player player) {
	  boolean valid = true;
	  boolean inEstate = true;
	  while(valid) { // makes sure player gives valid input
		  if (inEstate) { // checks to see if player is currently inside an estate
			  System.out.println("Type 'guess' to make a guess using the estate you are in, 'finalguess' to make a final guess or 'end' to end turn");
			  String string = sc.next();
			  if(string.equals("guess")) { 
				  makeGuess(); 
				  valid = false; // acknowledges valid input
			  }
			  else if(string.equals("finalguess")) { 
				  makeFinalGuess(player); 
				  valid = false;
			  }
			  else if (string.equals("end")) {
				  index = (index + 1) % players.size(); // gets next player in list
				  start(); 
				  valid = false;
			  }
			  else { 
				  System.out.println("Invalid Input"); 
			  }
		  } else { // if player is not in an estate
			  System.out.println("Type 'finalguess' to make a final guess or 'end' to end turn");
			  String string = sc.next();
			  if(string.equals("finalguess")) { 
				  makeFinalGuess(player); 
				  valid = false;
			  }
			  else if (string.equals("end")) {
				  index = (index + 1) % players.size(); 
				  start(); 
				  valid = false;
			  }
			  else { 
				  System.out.println("Invalid Input"); 
			  }
		  }
	  }
  }
  
  /**
   * Player makes a guess if they are in an estate
   * 
   * 
   */
  public void makeGuess() {
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
	  System.out.println("Your guess is " + character.toString() + ", " + weapon.toString() + ", [Estate]");
	  index = (index + 1) % players.size();
	  start();
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
			  estate = orderedEstates.get(0); // gets estate from list which user has choosen
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
	  } else {
		  // if incorrect user is removed from the game, game then checks if game over status is met
		  System.out.println("That is incorrect " + player.toString() + " is out of the game");
		  players.remove(player);
		  gameOver();
		  index = (index + 1) % players.size();
		  start();
	  }
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
  public void chooseMurderer() {
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

	  //System.out.println(diceRoll());
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
  
  public int getMove() {
	  return move;
  }
  
  public static void main(String[] args) {
	  new Game();
  }
}
