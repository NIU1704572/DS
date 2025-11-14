package baseNoStates;

public class UnlockedState extends DoorState{
  //Aquesta classe no modifica cap dels mètodes implementats  a doorState, perquè no té restriccions específiques
  UnlockedState(Door door) { super("unlocked", door); }

  @Override
  protected void changeState(DoorState newState, String action){
    if (newState.stateName.equals("unlocked_shortly")) {
      System.out.println("Door " + door.getId() + " is already unlocked");
    }else {
      super.changeState(newState, action);
    }
  }
}
