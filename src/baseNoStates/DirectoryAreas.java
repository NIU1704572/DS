package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  private static ArrayList<Area> allAreas;

  public static void makeAreas(){
    ArrayList<Door> doors = DirectoryDoors.getAllDoors();

    Partition building = new Partition(null,"building");


    Partition basement = new Partition(building,"basement");
    Space parking = new Space(basement,"parking", doors.getFirst(), doors.get(1)); // d1,d2

    Partition ground_floor = new Partition(building,"ground_floor");

    Space hall = new Space(ground_floor,"hall", doors.get(2), doors.get(3)); // d3, d4
    Space room1 = new Space(ground_floor,"room1", doors.get(4)); // d5
    Space room2 = new Space(ground_floor,"room2", doors.get(5)); // d6

    Partition floor1 = new Partition(building,"floor1");

    Space corridor = new Space(floor1,"corridor", doors.get(6)); //d7
    Space room3 = new Space(floor1,"room3", doors.get(7));      // d8
    Space IT = new Space(floor1,"IT", doors.get(8));            // d9

    Space stairs = new Space(building,"stairs", doors.get(1), doors.get(3), doors.get(6)); // d2, d4, d7
    Space exterior = new Space(building,"exterior", doors.getFirst(), doors.get(2)); // d1, d3

    allAreas = new ArrayList<>(Arrays.asList(ground_floor, floor1, parking, hall, room1, room2, corridor, basement, room3, IT, stairs, exterior, building));
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
