import java.util.ArrayList;

/**
 * Board class stores each individual Cell and uses them to convert the board to a printable string
 */
public class Board{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  public Cell[][] cells = new Cell[24][24];
  public ArrayList<String> board;

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
        cell.setEmpty(false); // sets cell to have something in it
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
   * Sets players onto the board
   * 
   */
  public void setPlayers() {
	  Player lucilla = Game.getPlayer("Lucilla");
	  Player bert = Game.getPlayer("Bert");
	  Player maline = Game.getPlayer("Maline");
	  Player percy = Game.getPlayer("Percy");
	  Cell cell;
	  cell = cells[1][12]; 
	  lucilla.setPos(1, 12); // sets player at those co-ordinates
	  cell.setEmpty(false);
	  cell.setPlayer(lucilla); // places player in that cell
	  
	  cell = cells[10][1];
	  bert.setPos(10, 1);
	  cell.setEmpty(false);
	  cell.setPlayer(bert);
	  
	  cell = cells[22][10];
	  maline.setPos(22, 10);
	  cell.setEmpty(false);
	  cell.setPlayer(maline);
	  
	  cell = cells[10][22];
	  percy.setPos(10, 22);
	  cell.setEmpty(false);
	  cell.setPlayer(percy);
  }
  
  /**
   * Moves a player on the board
   */
  public void movePlayer(Player player, String direction) {
	  // x and y position of player
	  int x = player.getXPos();
	  int y = player.getYPos();
	  cells[x][y].setPlayer(null);
	  if(cells[x][y].getEstate() == null) {
		  cells[x][y].setEmpty(true);
	  }
	  if(direction.equals("up")) { 
		  Cell newCell = cells[x-1][y]; // sets cell at next position
		  player.setEstate(newCell.getEstate()); //sets player at estate if player is on an estate
		  cells[x-1][y].setPlayer(player); // sets player at next cell
		  cells[x-1][y].setEmpty(false); // cells cell as not empty
		  player.setPos(x-1, y); // updates players co-ordinates on board
	  }else if(direction.equals("down")) {
		  Cell newCell = cells[x+1][y];
		  player.setEstate(newCell.getEstate());
		  cells[x+1][y].setPlayer(player);
		  cells[x+1][y].setEmpty(false);
		  player.setPos(x+1, y);
	  }else if(direction.equals("left")) {
		  Cell newCell = cells[x][y-1];
		  player.setEstate(newCell.getEstate());
		  cells[x][y-1].setPlayer(player);
		  cells[x][y-1].setEmpty(false);
		  player.setPos(x, y-1);
	  }else if(direction.equals("right")) {
		  Cell newCell = cells[x][y+1];
		  player.setEstate(newCell.getEstate());
		  cells[x][y+1].setPlayer(player);
		  cells[x][y+1].setEmpty(false);
		  player.setPos(x, y+1);
	  }
  }

  /**
   * Draws the board for the current game state
   */
  public void drawBoard() {
  	board = new ArrayList<String>(); // Board will be added to this list to be stored and decompiled when necessary
	  board.add("<html>");
	  for(int i = 0; i < 24; i++) {
    	board.add("<br/>");
      for(int j = 0; j < 24; j++) {
    	board.add("|");
        Cell cell = cells[i][j];
        // Places player icons on board
        if(!cell.isEmpty && cell.getPlayer() != null) {
        	if(cell.getPlayer().getName().equals("Lucilla")) {
        		board.add("L");
        	}else if(cell.getPlayer().getName().equals("Bert")) {
        		board.add("B");
        	}else if(cell.getPlayer().getName().equals("Maline")) {
        		board.add("M");
        	}else if(cell.getPlayer().getName().equals("Percy")) {
        		board.add("P");
        	}
        	// Places estate icons on board
        }else if(!cell.isEmpty && cell.getEstate() != null) {
        	if(cell.getEstate().getName().equals("Haunted House")) {
        		board.add("H");
        	}else if(cell.getEstate().getName().equals("Manic Manor")) {
        		board.add("M");
        	}else if(cell.getEstate().getName().equals("Villa Celia")) {
        		board.add("V");
        	}else if(cell.getEstate().getName().equals("Calamity Castle")) {
        		board.add("C");
        	}else if(cell.getEstate().getName().equals("Peril Palace")) {
        		board.add("P");
        	}
        	
        }else {
          board.add("_");
        }
      }
    }
	  board.add("</html>");
  }

	public ArrayList<String> getBoard() {
		return board;
	}
}
