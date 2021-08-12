/**
 *Character class
 */
public class Character implements Card{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Character Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Character constructor
   * @param aName
   */
  public Character(String aName){
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * Set name of character
   * @param aName
   * @return
   * 	--- boolean wasSet
   */
  public boolean setName(String aName){
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  /**
   * Getter method for characters name
   * @return
   * 	---- character's name
   */
  public String getName(){
    return name;
  }

  /**
   * Delete method for deleting a character
   */
  public void delete() {}

/**
 * toString method
 */
  public String toString(){
    return getName();
  }
}
