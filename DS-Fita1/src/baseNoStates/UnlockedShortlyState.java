package baseNoStates;

import java.util.Observable;
import java.util.Observer;
import java.time.LocalDateTime;

public class UnlockedShortlyState extends DoorState implements Observer {
  private LocalDateTime endTime;
  public UnlockedShortlyState(Door door) {
    super("unlocked_shortly", door);
    DoorTimer.getInstance().addObserver(this);        // Nos suscribimos al reloj global para recibir ticks cada segundo.
    this.endTime = LocalDateTime.now().plusSeconds(10);   // calculamos el tiempo en que se acaba el estado
  }


  @Override
  public String close(){
    if (door.isClosed()) {
      return "Door " + door.getId() + " is already closed";
    }
    door.setClosed(true);
    return null;
  }

  @Override
  public String open(){
    if (!door.isClosed()) {  //check if door is open
      return "Door" + door.getId() + " is already open";
    }
    door.setClosed(false);
    return null;
  }

  @Override
  public String lock(){
    // Locking a door requires higher credentials than unlocking it shortly, so it overrides the state.
    if (!door.isClosed()) {
      return "Door " + door.getId() + " is open. It may not be locked.";
    }
    door.setState(new LockedState(door));
    return null;
  }

  @Override
  public String unlock(){
    // Unlocking a door requires higher credentials than unlocking it shortly, so it overrides the state.
    door.setState(new UnlockedState(door));
    return null;
  }

  @Override
  public String unlock_shortly() {
    return "Door " + door.getId() + " is already unlocked.";
  }

  public void update(Observable o, Object arg) {

    if(endTime.isBefore((LocalDateTime)arg)) {
      if (door.getStateName().equals("unlocked_shortly")) {
        // Este check es para el caso en qué alguien sobreescribe el estado de la puerta.
        // No queremos afectar al nuevo estado.
        if (door.isClosed()) {
          door.setState(new LockedState(door));
        } else {
          door.setState(new ProppedState(door));
        }
      }
      // Dejamos de observar el temporizador para evitar fugas y callbacks extra.
      o.deleteObserver(this);
    }
  }


}
