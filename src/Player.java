import java.util.ArrayList;
import java.util.List;

/**
 * Player class which stores all the details of a player for use within the game class
 */
public class Player {
	
	private String name;
	List<Card> hand = new ArrayList<Card>();
	private int xPos = 0;
	private int yPos = 0;
	private Estate estate = null;
	private boolean inEstate = false;
	public String character;
	
	public Player(String aName){
		name = aName;
	}
	
	/**
	 * get players hand directly from player
	 * 
	 * @param c
	 * the list of cards the player has been assigned 
	 */
	public void addToHand(List<Card> c) {
		for (Card card : c) {
			hand.add(card);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public Estate getEstate() {
		return estate;
	}
	
	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public void setName(String name) { this.name = name; }
	
	public boolean inEstate() {
		return inEstate;
	}
	
	public void setInEstate(boolean bool) {
		inEstate = bool;
	}
	
	/**
	 * Custom equals method
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Player player = (Player) obj;
		return name == player.name;
	}
	
	public String toString() {
		return getName();
	}
}
