import java.util.ArrayList;
import java.util.List;

public class Board{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  public Cell[][] cells = new Cell[24][24];
  public static List<Estate> allEstates = new ArrayList<Estate>();

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

  public void setEstates() {
      for(int i = 0; i < 5; i++) { // nested for loop to set an estate to all the cells that need it
        for(int j = 0; j < 5; j++) {
          Cell cell;
          cell = cells[2 + i][2 + j];
          cell.setEmpty(false);
          cell.setEstate(allEstates.get(0));

          cell = cells[2 + i][17 + j];
          cell.setEmpty(false);
          cell.setEstate(allEstates.get(1));

          cell = cells[9 + i][10 + j];
          cell.setEmpty(false);
          cell.setEstate(allEstates.get(2));

          cell = cells[17 + i][2 + j];
          cell.setEmpty(false);
          cell.setEstate(allEstates.get(3));

          cell = cells[17 + i][17 + j];
          cell.setEmpty(false);
          cell.setEstate(allEstates.get(4));
        }
    }
  }

}
