import java.util.ArrayList;
import java.util.List;

public class Board{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  public Cell[][] cells = new Cell[24][24];

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(){
    for(int i = 0; i < 24; i++) {
      for(int j = 0; j < 24; j++) {
        Cell cell = new Cell();
        cells[i][j] = cell;
      }
    }
  }


  public void delete(){}

  /**
   * Set all the cells that need estates
   */
  public void setEstates() {
    Estate hauntedHouse = Game.getEstate("Haunted House");
    Estate manicManor = Game.getEstate("Manic Manor");
    Estate villaCelia = Game.getEstate("Villa Celia");
    Estate calamityCastle = Game.getEstate("Calamity Castle");
    Estate perilPalace = Game.getEstate("Peril Palace");
      for(int i = 0; i < 5; i++) { // nested for loop to set an estate to all the cells that need it
        for(int j = 0; j < 5; j++) {
          Cell cell;
          cell = cells[2 + i][2 + j];
          cell.setEmpty(false);
          cell.setEstate(hauntedHouse);

          cell = cells[2 + i][17 + j];
          cell.setEmpty(false);
          cell.setEstate(manicManor);

          cell = cells[9 + i][10 + j];
          cell.setEmpty(false);
          cell.setEstate(villaCelia);

          cell = cells[17 + i][2 + j];
          cell.setEmpty(false);
          cell.setEstate(calamityCastle);

          cell = cells[17 + i][17 + j];
          cell.setEmpty(false);
          cell.setEstate(perilPalace);
        }
    }
  }

  /**
   * Draws the board for the current game state
   */
  public void drawBoard() {
    for(int i = 0; i < 24; i++) {
      for(int j = 0; j < 24; j++) {
        Cell cell = cells[i][j];
        if(!cell.isEmpty() && cell.getPlayer() != null) {
          System.out.print("");
        }else if(!cell.isEmpty && cell.getEstate() != null) {
          System.out.print("");
        }else {
          System.out.print("");
        }
      }
    }
  }
}