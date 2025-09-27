package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;
  private static ArrayList<Area> areas;       //THINK WHERE WE WANT THE AREAS TO BE

  public static void makeDoors() {
    // basement
    Door d1 = new Door("D1"); // exterior, parking
    Door d2 = new Door("D2"); // stairs, parking

    Area a1 = new Area("basement",new ArrayList<>(Arrays.asList(d1,d2)));

    // ground floor
    Door d3 = new Door("D3"); // exterior, hall
    Door d4 = new Door("D4"); // stairs, hall
    Door d5 = new Door("D5"); // hall, room1
    Door d6 = new Door("D6"); // hall, room2

    Area a2 = new Area("ground_floor",new ArrayList<>(Arrays.asList(d3, d4, d5, d6)));

    // first floor
    Door d7 = new Door("D7"); // stairs, corridor
    Door d8 = new Door("D8"); // corridor, room3
    Door d9 = new Door("D9"); // corridor, IT

    Area a3 = new Area("ground_floor",new ArrayList<>(Arrays.asList(d7, d8, d9)));


    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

    Area a4 = new Area("ground_floor", allDoors);

    areas = new ArrayList<>(Arrays.asList(a1,a2,a3,a4)); //
  }


  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
