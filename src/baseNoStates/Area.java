package baseNoStates;

import java.util.ArrayList;

abstract public class Area{
  //This abstract class allows us to use polymorphism to obtain all the doors ins a given Partition or Space
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract String getId();
}
