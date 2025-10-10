package baseNoStates;

import java.util.ArrayList;

abstract public class Area {
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract String getId();
}
