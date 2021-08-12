import java.util.List;

/**
 *
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

  public Estate(String aName, Weapon aWeapon){
    name = aName;
    weapon = aWeapon;
  }
  
  public Estate(String aName) {
	  name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName){
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeapon(Weapon aWeapon){
    boolean wasSet = false;
    weapon = aWeapon;
    wasSet = true;
    return wasSet;
  }
  
  public void setEstate(Estate e) {
	  estates.add(e);
  }

  public String getName(){
    return name;
  }

  public Weapon getWeapon(){
    return weapon;
  }

  public String toString(){
    return getName();
  }
}
