package baseNoStates;

import java.util.ArrayList;

abstract public class Area extends Door {
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract String getId();

}
