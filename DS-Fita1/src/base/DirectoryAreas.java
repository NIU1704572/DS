package base;

import java.util.ArrayList;
import java.util.Set;

public class DirectoryAreas {
  private static DirectoryAreas instance;

  // area raíz del àrbol de areas
  private final Area rootArea;

  private DirectoryAreas() {
    rootArea = makeDoors();
  }

  public Area makeDoors() {
    ArrayList<Door> doors = DirectoryDoors.getAllDoors();

    // Nodo raíz de la jerarquía
    Partition building = new Partition(null, "building");

    // Nivel -1: sótano y su aparcamiento.
    Partition basement = new Partition(building, "basement");
    Space parking = new Space(basement, "parking", doors.getFirst(), doors.get(1)); // d1,d2


    // Planta baja y sus espacios.
    Partition groundFloor = new Partition(building, "ground_floor");

    Space hall = new Space(groundFloor, "hall", doors.get(2), doors.get(3)); // d3, d4
    Space room1 = new Space(groundFloor, "room1", doors.get(4)); // d5
    Space room2 = new Space(groundFloor, "room2", doors.get(5)); // d6

    // Primera planta y sus espacios.
    Partition floor1 = new Partition(building, "floor1");

    Space corridor = new Space(floor1, "corridor", doors.get(6)); // d7
    Space room3 = new Space(floor1, "room3", doors.get(7));       // d8
    Space IT = new Space(floor1, "IT", doors.get(8));             // d9

    // Áreas "extras"
    Space stairs = new Space(building, "stairs", doors.get(1), doors.get(3), doors.get(6));
    // d2, d4, d7

    Space exterior = new Space(building, "exterior", doors.getFirst(), doors.get(2)); // d1, d3

    return building;
  }

  public static DirectoryAreas getInstance() {
    if (instance == null) {
      instance = new DirectoryAreas();
    }
    return instance;
  }

  public Area findAreaById(String id) {
    return rootArea.accept(new FindAreaByIdVisitor(id));
  }

  public Set<Door> getDoorsGivingAccess(String areaId) {
    Area area = findAreaById(areaId);
    if (area == null) {
      throw new IllegalArgumentException("Area not found: " + areaId);
    }
    return area.accept(new DoorsGivingAccessVisitor());
  }

  public Set<Door> getProppedDoors(String areaId) {
    Area area = findAreaById(areaId);
    if (area == null) {
      throw new IllegalArgumentException("Area not found: " + areaId);
    }
    return area.accept(new ProppedDoorsVisitor());
  }





}
