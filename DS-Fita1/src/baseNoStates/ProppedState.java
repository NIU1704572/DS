package baseNoStates;

public class ProppedState extends DoorState {
  public ProppedState(Door door) { super("propped", door); }

  @Override
  public void close() {
    //if a propped door closes it becomes locked
    super.close();
    door.setState(new LockedState(door));
  }
}
