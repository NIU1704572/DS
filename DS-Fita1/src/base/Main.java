package base;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {
    DirectoryDoors.makeDoors();
    // DirectoryAreas.makeAreas(); <-- dado que hemos aplicado el patrón estructural singleton
    // no hace falta instanciar las areas hasta el momento en que se van a utilizar
    DirectoryUsers.makeUsers();
    new WebServer();
  }
}
