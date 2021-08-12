/**
 *
 */
public class Guess {
	Character character;
	Weapon weapon;
	Estate estate;
	Guess murderer;
	
	public Guess(Character c, Weapon w, Estate e) {
		this.character = c;
		this.weapon = w;
		this.estate = e;
	}
	
	public void setMurderer(Guess g) {
		murderer = g;	
	}
	
	public Guess getMurderer() {
		return murderer;
	}
	
	public String toString() {
		return character.toString() + ", " + weapon.toString() + ", " + estate.toString();
	}
}
