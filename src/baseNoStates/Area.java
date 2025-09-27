package baseNoStates;

import java.util.ArrayList;

public class Area {
  protected final String ID;
  private ArrayList<Door> doors;
  Area(String ID, ArrayList<Door> doors) {
    this.ID=ID;
    this.doors = doors;
  }

}
