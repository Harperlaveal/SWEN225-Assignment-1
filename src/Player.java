import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	List<Card> hand = new ArrayList<Card>();
	
	public Player(String aName){
		name = aName;
	}
	
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
	
}
