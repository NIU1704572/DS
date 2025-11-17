package baseNoStates;

public class LockedState extends DoorState {
  //Aquesta classe és l'estat al patró State de les portes bloquejades pel sistema.
  LockedState(Door door) { super("locked", door); }

  @Override
  public String close(){
    return "Door " + door.getId() + " is already closed";
  }

  @Override
  public String open(){
      return "Door" + door.getId() + " is locked. It may not open.";
  }

  @Override
  public String lock(){
      return "Door " + door.getId() + " is already locked.";
  }

  @Override
  public String unlock(){
    door.setState(new UnlockedState(door));
    return null;
  }

  @Override
  public String unlock_shortly() {
    door.setState(new UnlockedShortlyState(door));
    return null;
  }
}
