import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	List<Card> hand = new ArrayList<Card>();
	
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
