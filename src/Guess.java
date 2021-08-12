/**
 *Guess class which handles guessing the character, weapon and estate
 *
 */
public class Guess {
	Character character;
	Weapon weapon;
	Estate estate;
	Guess murderer;

	/**
	 * Guess class constructor
	 * @param c
	 * @param w
	 * @param e
	 */
	public Guess(Character c, Weapon w, Estate e) {
		this.character = c;
		this.weapon = w;
		this.estate = e;
	}

	/**
	 *Setter class for setting the murderer
	 * @param g
	 */
	public void setMurderer(Guess g) {
		murderer = g;
	}

	/**
	 * Getter class for murderer
	 * @return
	 */		///returns the murderer
	public Guess getMurderer() {
		return murderer;
	}

	/**
	 * toString method for Guess class
	 */
	public String toString() {
		return character.toString() + ", " + weapon.toString() + ", " + estate.toString();
	}
}
