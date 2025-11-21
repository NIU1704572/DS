package base;

import java.util.HashSet;
import java.util.Set;

public class DoorsGivingAccessVisitor implements Visitor<Set<Door>> {

  @Override
  public Set<Door> visitPartition(Partition partition) {
    Set<Door> allDoors = new HashSet<>();
    for (Area area : partition.getSpaces()) {
      Set<Door> areaDoors = area.accept(this);
      allDoors.addAll(areaDoors);
    }
    return allDoors;
  }

  @Override
  public Set<Door> visitSpace(Space space) {
    return new HashSet<>(space.getDoors());
  }
}
