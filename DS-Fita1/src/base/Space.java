package base;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area{
  private final ArrayList<Door> doors;

  Space(Partition parent, String id, Door ... doors) { //inicializa el espacio
    super(id);
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
  public Object accept(Visitor visitor) {
    return visitor.visitSpace(this);
  }
  public ArrayList<Door> getDoors() { return doors; }
}
