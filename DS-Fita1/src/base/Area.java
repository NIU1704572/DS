package base;

import java.util.ArrayList;

public abstract class Area {
  protected final String id;

  Area(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  // This abstract class allows us to use polymorphism to
  // obtain all the doors ins a given Partition or Space
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract <T> T accept(Visitor<T> visitor);

}
