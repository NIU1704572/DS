package baseNoStates;

public abstract class DoorState {
  // Abstract class used to implement state pattern.
  // Most methods are implemented here and overriden when necessary.
  // We don't want to repeat all methods in every class that inherits from this one, when just one method is different.


  protected final String stateName;
  protected final Door door;

  public DoorState(String stateName, Door door) {
      this.stateName = stateName; // string to return when getState is called
      this.door = door;
  }
  public String getState() {return stateName;}

  public void open(){
    // Method is implemented in the abstract class because most states
    // share the same checks, and we want to avoid repeating code.
    // It may be overridden when necessary (LockedState, for example)
    if(door.isClosed()) door.setClosed(false); //check if door is open
    else System.out.println("Can't open door " + door.getId() + " because it's already open");
  }


  public void close(){
    // Method is implemented in the abstract class because most states
    // share the same checks, and we want to avoid repeating code.
    // It may be overridden when necessary (ProppedState, for example)
    if (!door.isClosed()) door.setClosed(true);
    else System.out.println("Can't close door " + door.getId() + " because it's already closed"); }

  public void changeState(DoorState newState, String action) {
    //This method does the most basic and common checks. It exists so we may not repeat them in other classes.

    // if new state = this state
    if (newState.stateName.equals(stateName)) {
      System.out.println("Can't " + action + " door " + door.getId()
          + " because it's already locked"); // check the new state isn't the same as the previous one
      return;

    // if door is closed
    } else if (!door.isClosed()) {
      System.out.println("Can't " + action + " door " + door.getId()
          + " because it may not change state while open");
    }

    door.setState(newState);
  }

  public void doAction(String action) {
    // This method calls the changeState, open or close methods, which can be overriden by inheritor
    // classes (this method may be, as well, if necessary; like in UnlockedShortlyState).
    switch (action) {
      case Actions.OPEN:
        this.open();
        break;

      case Actions.CLOSE:
        this.close();
        break;

      case Actions.LOCK:
        this.changeState(new LockedState(door),action);
        break;

      case Actions.UNLOCK:
        this.changeState(new UnlockedState(door),action);
        break;

      case Actions.UNLOCK_SHORTLY:
        this.changeState(new UnlockedShortlyState(door),action);
        break;

      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

}
