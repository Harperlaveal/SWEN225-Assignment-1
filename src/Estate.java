public class Estate{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Estate Attributes
  private String name;
  private Weapon weapon;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Estate(String aName, Weapon aWeapon){
    name = aName;
    weapon = aWeapon;
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

  public String getName(){
    return name;
  }

  public Weapon getWeapon(){
    return weapon;
  }

  public void delete(){}


  public String toString(){
    return getName() + " = " + getWeapon();
  }
}