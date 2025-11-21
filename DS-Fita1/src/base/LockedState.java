package base;

public class LockedState extends DoorState {
  // Aquesta classe és l'estat al patró State de les portes bloquejades pel sistema.
  LockedState(Door door) {
    super("locked", door);
  }

  @Override
  public void close() {
    System.out.println("Door " + door.getId() + " is already closed");
  }

  @Override
  public void open() {
    System.out.println("Door" + door.getId() + " is locked. It may not open.");
  }

  @Override
  public void lock() {
    System.out.println("Door " + door.getId() + " is already locked.");
  }

  @Override
  public void unlock() {
    door.setState(new UnlockedState(door));
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortlyState(door));
  }
}
