package base;

import java.util.HashSet;
import java.util.Set;

public class ProppedDoorsVisitor implements Visitor<Set<Door>> {

  @Override
  public Set<Door> visitPartition(Partition partition) {
    Set<Door> proppedDoors = new HashSet<>();
    for (Area area : partition.getSpaces()) {
      Set<Door> areaProppedDoors = area.accept(this);
      proppedDoors.addAll(areaProppedDoors);
    }
    return proppedDoors;
  }

  @Override
  public Set<Door> visitSpace(Space space) {
    Set<Door> proppedDoors = new HashSet<>();
    for (Door door : space.getDoors()) {
      if (door.getStateName().equals("propped")) { // Asume que Door tiene un método isPropped()
        proppedDoors.add(door);
      }
    }
    return proppedDoors;
  }
}