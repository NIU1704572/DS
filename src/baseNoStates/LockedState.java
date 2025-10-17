package baseNoStates;

public class LockedState extends DoorState {
  LockedState(Door door) { super("locked", door); }
  @Override
  public void open(){
    System.out.println("Can't open door " + door.getId() + " because it's locked");
  }
}
