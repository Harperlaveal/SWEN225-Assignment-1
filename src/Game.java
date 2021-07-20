import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
	public static List<Character> players = new ArrayList<Character>() ;
	public static List<Weapon> weapons = new ArrayList<Weapon>() ;
	public static List<Estate> estates = new ArrayList<Estate>() ;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(){
	  Character lucilla = new Character("Lucilla");
	  Character bert = new Character("Bert");
	  Character maline = new Character("Maline");
	  Character percy = new Character("Percy");
	  addPlayers(lucilla, bert, maline, percy);
	  Weapon broom = new Weapon("Broom");
	  Weapon scissors = new Weapon("Scissors");
	  Weapon knife = new Weapon("Knife");
	  Weapon shovel = new Weapon("Shovel");
	  Weapon ipad = new Weapon("iPad");
	  addWeapons(broom, scissors, knife, shovel, ipad);
	  setWeapons();
	  StartGame();
  }
  
  public void StartGame() {
	  int num;
	  do {
	  Scanner sc = new Scanner(System.in);
	  System.out.println("How many players 1-4");
	  num = sc.nextInt();
	  } while (!isValid(num));
  }
  
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

  //------------------------
  // INTERFACE
  //------------------------
  public void addPlayers(Character c1, Character c2, Character c3, Character c4) {
	  players.add(c1);
	  players.add(c2);
	  players.add(c3);
	  players.add(c4);
  }
  
  public void addWeapons(Weapon w1, Weapon w2, Weapon w3, Weapon w4, Weapon w5) {
	  weapons.add(w1);
	  weapons.add(w2);
	  weapons.add(w3);
	  weapons.add(w4);
	  weapons.add(w5);
  }
  
  public void addEstates(Estate e1, Estate e2, Estate e3, Estate e4, Estate e5) {
	  estates.add(e1);
	  estates.add(e2);
	  estates.add(e3);
	  estates.add(e4);
	  estates.add(e5);
  }
  
  public void setWeapons() {
	  Collections.shuffle(weapons);
	  Estate hauntedHouse = new Estate("Haunted House", weapons.get(0));
	  Estate manicManor = new Estate("Manic Manor", weapons.get(1));
	  Estate villaCelia = new Estate("Villa Celia", weapons.get(2));
	  Estate calamityCastle = new Estate("Calamity Castle", weapons.get(3));
	  Estate perilPalace = new Estate("Peril Palace", weapons.get(4));
	  addEstates(hauntedHouse, manicManor, villaCelia, calamityCastle, perilPalace);
  }
  
  public void delete(){}
  
  public static void main(String[] args) {
	  new Game();
	  System.out.println(players.toString());
	  System.out.println(weapons.toString());
	  System.out.println(estates.toString());
  }
}