package baseNoStates;

import java.util.Observable;
import java.util.Observer;

public class UnlockedShortlyState extends DoorState implements Observer {
  private int secondsElapsed; //IMPLEMENTAR BIEN PATRON STATE
  public UnlockedShortlyState(Door door) {
    super("unlocked_shortly", door);
    DirectoryDoors.getTimer().addObserver(this);
    secondsElapsed = 0;
  }


  public DoorState nextState(boolean closed){
    if(closed){
      return new LockedState(door);
    }
    return new ProppedState(door);
  }

  @Override
  public void update(Observable o, Object arg) {
    secondsElapsed++;
    if(secondsElapsed == 10) {
      if(door.isClosed()){
        door.setState(new LockedState(door));

      } else{
        door.setState(new ProppedState(door));
      }
      DirectoryDoors.getTimer().deleteObserver(this);
      secondsElapsed = 0;
    }
  }
}
