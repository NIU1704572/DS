package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  private static ArrayList<Area> allAreas;

  public static void makeAreas(){
    ArrayList<Door> doors = DirectoryDoors.getAllDoors();

    Area building = new Area(null,"building");


    Area basement = new Area(building,"basement");
    Area parking = new Area(basement,"parking", doors.getFirst(), doors.get(1)); // d1,d2

    Area ground_floor = new Area(building,"ground_floor");

    Area hall = new Area(ground_floor,"hall", doors.get(2), doors.get(3)); // d3, d4
    Area room1 = new Area(ground_floor,"room1", doors.get(4)); // d5
    Area room2 = new Area(ground_floor,"room2", doors.get(5)); // d6

    Area firstFloor = new Area(building,"floor1");

    Area corridor = new Area(firstFloor,"corridor", doors.get(6)); //d7
    Area room3 = new Area(firstFloor,"room3", doors.get(7));      // d8
    Area IT = new Area(firstFloor,"IT", doors.get(8));            // d9

    Area stairs = new Area(building,"stairs", doors.get(1), doors.get(3), doors.get(6)); // d2, d4, d7
    Area exterior = new Area(building,"exterior", doors.getFirst(), doors.get(2)); // d1, d3

    allAreas = new ArrayList<>(Arrays.asList(basement, ground_floor, firstFloor, parking, hall, room1, room2, corridor, room3, IT, stairs, exterior));
  }

  public static Area findAreaById(String areaId) {
    for (Area area : allAreas) {
      if (area.getId().equals(areaId)) {
        return area;
      }
    }
    System.out.println("area with id " + areaId + " not found");
    return null; // otherwise we get a Java error
  }
}
