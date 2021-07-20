public class Weapon{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weapon Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weapon(String aName){
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

  public String getName(){
    return name;
  }

  public void delete(){}


  public String toString(){
    return getName();
  }
}