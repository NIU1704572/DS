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
      parent.addSpace(this);
    }
  }
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {

    return  new ArrayList<>(doors);
  }

  @Override
  public String getId() {
    return id;
  }
}
