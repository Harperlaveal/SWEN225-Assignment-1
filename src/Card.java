/**
 * Card interface to be extended by Estate, Weapon and character
 */
public interface Card {

	/**
	 * Getter method for name
	 * @return
	 * 		--- cards name
	 */
	public String getName();

	/**
	 * Setter method for name
	 * @param name
	 * @return
	 * 		--- name
	 */
	public boolean setName(String name);

	/**
	 * toString method for card
	 * @return
	 * 		--- toString
	 */
	public String toString();

}
