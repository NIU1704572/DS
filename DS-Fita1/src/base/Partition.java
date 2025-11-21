package base;

import java.util.ArrayList;
import java.util.Arrays;

public class Partition extends Area {
  ArrayList<Area> spaces;

  public Partition(Partition parent, String id, Area... spaces) {
    // Construye una partición dentro de la jerarquía de áreas.
    super(id);
    this.spaces = new ArrayList<>();
    this.spaces.addAll(Arrays.asList(spaces));
    if (parent != null) {
      parent.spaces.add(this);
    }
  }

  // Acceso de las puertas pasado en un array
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> returnArray = new ArrayList<>();

    spaces.forEach(space -> returnArray.addAll(space.getDoorsGivingAccess()));

    return returnArray;
  }

  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitPartition(this);
  }

  public void addSpace(Area area) {
    spaces.add(area);
  }

  public ArrayList<Area> getSpaces() {
    return spaces;
  }

}
