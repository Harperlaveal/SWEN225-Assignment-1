/**
 * Weapon class
 */
public class Weapon implements Card{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weapon Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Weapon constructor
   * @param aName
   */
  public Weapon(String aName){
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * Set weapons name
   * @param aName
   * @return
   * 	---- boolean whether weapon was set or not
   */
  public boolean setName(String aName){
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  /**
   * Getter method for weapon
   * @return
   * 		--- weapons name
   */
  public String getName(){
    return name;
  }

  /**
   * Delete method for weapon
   */
  public void delete(){}

/**
 * toString method for weapon
 */
  public String toString(){
    return getName();
  }
}
