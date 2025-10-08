package baseNoStates;

import baseNoStates.requests.RequestReader;

import java.util.ArrayList;

public class Area extends Door {
  protected final String id;
  private ArrayList<Door> doors;

  Area(Area parent, String id, Door ... doors) {
    this.id=id;
    this.doors = new ArrayList<>();
    for (Door door : doors) {
      this.doors.add(door);
    }
    if (parent != null) {
      parent.doors.add(this);
    }
  }

  private ArrayList<Door> getDoorsGivingAccessRecursive(Door door){
    ArrayList<Door> returnArray = new ArrayList<>();

    if(door instanceof Area){
      returnArray = ((Area) door).getDoorsGivingAccess();
    } else {

      returnArray.add(door);
    }
    return returnArray;
  }

  public ArrayList<Door> getDoorsGivingAccess(){
    ArrayList<Door> returnArray = new ArrayList<>();

    doors.forEach(door -> returnArray.addAll(getDoorsGivingAccessRecursive(door)));

    return returnArray;
  }


  public String getId() {
    return id;
  }
}
