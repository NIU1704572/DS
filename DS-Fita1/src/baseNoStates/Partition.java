package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Partition extends Area{
  private final String id;
  ArrayList<Area> spaces;

  public Partition(Partition parent, String id, Area ... spaces) {
    //Construye una partición dentro de la jerarquía de áreas.
    this.id=id;
    this.spaces = new ArrayList<>();
    this.spaces.addAll(Arrays.asList(spaces));
    if (parent != null) {
      parent.spaces.add(this);
    }
  }

  @Override
  public String getId() {
    return id;
  }

// Acceso de las puertas pasado en un array
  public ArrayList<Door> getDoorsGivingAccess(){
    ArrayList<Door> returnArray = new ArrayList<>();

    spaces.forEach(space -> returnArray.addAll(space.getDoorsGivingAccess()));

    return returnArray;
  }

  public void addSpace(Area area){
    spaces.add(area);
  }

}
