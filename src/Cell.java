/**
 * Cell class that simply stores either a player, an estate or is empty for use in Board class
 */
public class Cell{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  public Estate estate = null;
  public boolean isEmpty;
  public Player player = null;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell(){
    this.isEmpty = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete(){}

  public void setPlayer(Player p) {
    player = p;
  }

  public void setEstate(Estate e) {
    estate = e;
  }

  public void setEmpty(boolean b) {
    isEmpty = b;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public Estate getEstate() {
    return estate;
  }

  public Player getPlayer() {
    return player;
  }

}
