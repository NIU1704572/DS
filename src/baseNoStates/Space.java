package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area{
  private final String id;
  private final ArrayList<Door> doors;
  Space(Partition parent, String id, Door ... doors) {
    this.id=id;
    this.doors = new ArrayList<>();
    this.doors.addAll(Arrays.asList(doors));
    if (parent != null) {
      parent.spaces.add(this);
    }
  }
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> returnArray = new ArrayList<>();
    doors.forEach(door -> returnArray.addAll(doors));

    return returnArray;
  }

  @Override
  public String getId() {
    return id;
  }
}
