package baseNoStates;

public class ProppedState extends DoorState {
  public ProppedState(Door door) {
    super("propped", door);
  }

  @Override
  public String close() {
    door.setClosed(true);
    door.setState(new LockedState(door));
    return null;
  }

  @Override
  public String open() {
    return "Door " + door.getId() + " is already open";
  }

  @Override
  public String lock() {
    return "Door " + door.getId() + " is propped.";
  }

  @Override
  public String unlock() {
    return "Door " + door.getId() + " is propped.";
  }

  @Override
  public String unlock_shortly() {
    return "Door " + door.getId() + " is propped.";
  }
}
