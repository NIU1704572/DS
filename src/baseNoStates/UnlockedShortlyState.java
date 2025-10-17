package baseNoStates;

import java.util.Observer;

public class UnlockedShortlyState extends DoorState{
  private int secondsElapsed = 0; //IMPLEMENTAR BIEN PATRON STATE
  public UnlockedShortlyState() { super("unlocked_shortly"); }


  public DoorState nextState(boolean closed){
    if(closed){
      return new LockedState();
    }
    return new ProppedState();
  }
}
