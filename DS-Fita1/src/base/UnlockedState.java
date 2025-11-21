package base;

public class UnlockedState extends DoorState{

  UnlockedState(Door door) { super("unlocked", door); }

  @Override
  public void close(){
    if (!door.isClosed()) {
      System.out.println("Door " + door.getId() + " is already closed");
    }else {
      door.setClosed(true);
    }
  }

  @Override
  public void open(){
    if (!door.isClosed()) {  //check if door is open
      System.out.println("Door " + door.getId() + " is already open");
    }
    door.setClosed(false);
  }

    @Override
   public void lock(){
      if (!door.isClosed()) {
        System.out.println("Door " + door.getId() + " is open. It may not be locked.");
        }else {
        door.setState(new LockedState(door));
      }
   }

   @Override
   public void unlock(){
     System.out.println("Door " + door.getId() + " is already unlocked");
   }

  @Override
  public void unlockShortly() {
    System.out.println("Door " + door.getId() + " is already unlocked.");
  }

}
