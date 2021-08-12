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

  /**
   * Cell constructor
   */
  public Cell(){
    this.isEmpty = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * delete method for cell
   */
  public void delete(){}

  /**
   * Set the player
   * @param p
   * current player
   */
  public void setPlayer(Player p) {
    player = p;
  }

  /**
   * Set the estate
   * @param e
   * Estate
   */
  public void setEstate(Estate e) {
    estate = e;
  }

  /**
   * Set empty cells
   * @param b
   * cells
   */
  public void setEmpty(boolean b) {
    isEmpty = b;
  }

  /**
   * Checks if cell is empty
   * @return
   * boolean isEmpty
   */
  public boolean isEmpty() {
    return isEmpty;
  }

  /**
   * Getter for estate
   * @return
   * 	---- Estate
   */
  public Estate getEstate() {
    return estate;
  }

  /**
   * Getter for player
   * @return
   */
  public Player getPlayer() {
    return player;
  }

}
