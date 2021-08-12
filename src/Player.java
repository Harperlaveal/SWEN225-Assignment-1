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

	/**
	 * Player constructor
	 * @param aName
	 */
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

	/**
	 * Gets players name
	 * @return
	 * 		----players name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets players hand
	 * @return
	 * 		players hand
	 */

	public List<Card> getHand(){
		return hand;
	}

	/**
	 * Get players X position
	 * @return
	 * 		---- player's x position
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Get players Y position
	 * @return
	 * 		----- player's y position
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Sets players position
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	/**
	 * Returns players Estate card
	 * @return
	 * 		---- Estates card
	 */
	public Estate getEstate() {
		return estate;
	}

	/**
	 * Sets players estate
	 * @param estate
	 */
	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	/**
	 * Sets players name
	 * @param name
	 */
	public void setName(String name) { this.name = name; }

	/**
	 * Checks if player is in estate
	 * @return
	 * 		---- inEstate or Not
	 */
	public boolean inEstate() {
		return inEstate;
	}

	/**
	 * Checks whether player is in estate
	 * @param bool
	 */
	public void setInEstate(boolean bool) {
		inEstate = bool;
	}

	/**
	 * Custom equals method
	 * 	@param
	 * 		----obj
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

	/**
	 * ToString method
	 */
	public String toString() {
		return getName();
	}
}
