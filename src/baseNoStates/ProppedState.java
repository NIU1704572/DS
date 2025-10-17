package baseNoStates;

public class ProppedState extends DoorState {
  public ProppedState(Door door) { super("propped", door); }

  @Override
  public void doAction(String action) {
    switch (action) {
      case Actions.OPEN:{open();}

      case Actions.CLOSE: {
        close();
        door.setState(new LockedState(door));
      }

      default:
        System.out.println("Invalid action: '" + action + "' on door" +
            door.getId() + " for current state: " + stateName);
    }
  }

  @Override
  public void close() {
    //if a propped door closes it becomes locked
    super.close();
    door.setState(new LockedState(door));
  }
}
