package baseNoStates;

public class UnlockedState extends DoorState{

  UnlockedState(Door door) { super("unlocked", door); }

  @Override
  public String close(){
    if (!door.isClosed()) {
      return "Door " + door.getId() + " is already closed";
    }
    door.setClosed(true);
    return null;
  }

  @Override
  public String open(){
    if (!door.isClosed()) {  //check if door is open
      return "Door " + door.getId() + " is already open";
    }
    door.setClosed(false);
    return null;
  }

    @Override
   public String lock(){
      if (!door.isClosed()) {
        return "Door " + door.getId() + " is open. It may not be locked.";
        }
      door.setState(new LockedState(door));
      return null;
   }

   @Override
   public String unlock(){
    return "Door " + door.getId() + " is already unlocked";
   }

  @Override
  public String unlock_shortly() {
    return "Door " + door.getId() + " is already unlocked.";
  }

}
