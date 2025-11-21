package base;

public class ProppedState extends DoorState {
  public ProppedState(Door door) {
    super("propped", door);
  }

  @Override
  public void close() {
    door.setClosed(true);
    door.setState(new LockedState(door));
  }

  @Override
  public void open() {
    System.out.println("Door " + door.getId() + " is already open");
  }

  @Override
  public void lock() {
    System.out.println("Door " + door.getId() + " is propped.");
  }

  @Override
  public void unlock() {
    System.out.println("Door " + door.getId() + " is propped.");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Door " + door.getId() + " is propped.");
  }
}
