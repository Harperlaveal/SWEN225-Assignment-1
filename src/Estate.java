import java.util.List;

/**
 * Estate Class
 */
public class Estate implements Card{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Estate Attributes
  private String name;
  private Weapon weapon;
  private List<Estate> estates;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Estate Constructor
   * @param aName
   * @param aWeapon
   */
  public Estate(String aName, Weapon aWeapon){
    name = aName;
    weapon = aWeapon;
  }

  //------------------------
  // INTERFACE
  //------------------------
/**
 * Set estate name
 * @param aName
 * @return
 * 		--- boolean
 */
  public boolean setName(String aName){
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  /**
   * Set weapon in estate
   * @param aWeapon
   * @return
   * 	----- boolean
   */
  public boolean setWeapon(Weapon aWeapon){
    boolean wasSet = false;
    weapon = aWeapon;
    wasSet = true;
    return wasSet;
  }

  /**
   * Set Estate and then adds to list
   * @param e
   * 		estate
   */
  public void setEstate(Estate e) {
	  estates.add(e);
  }

  /**
   * returns estate name
   * @return
   */
  public String getName(){
    return name;
  }

  /**
   * Get the weapon in the estate
   * @return
   * 		--weapon
   */
  public Weapon getWeapon(){
    return weapon;
  }

  /**
   * ToString estate class
   */
  public String toString(){
    return getName();
  }
}
