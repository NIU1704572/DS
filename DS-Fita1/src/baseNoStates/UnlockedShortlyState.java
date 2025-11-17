package baseNoStates;

import java.util.Observable;
import java.util.Observer;
import java.time.LocalDateTime;

public class UnlockedShortlyState extends DoorState implements Observer {
  private LocalDateTime endTime;
  public UnlockedShortlyState(Door door) {
    // Durante ~10s (cadencia del DoorTimer), la puerta permite abrirse.
    // Nos suscribimos al reloj global para recibir ticks de 1s (DoorTimer).
    super("unlocked_shortly", door);
    DoorTimer.getInstance().addObserver(this);
    this.endTime = LocalDateTime.now().plusSeconds(10); // arranca la cuenta atrás al entrar en el estado
  }


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
    if(endTime.isAfter((LocalDateTime)arg)) {
      if(door.isClosed() && door.getStateName().equals("unlocked_shortly")) {
        // Si nadie la abrió durante la ventana, volvemos a estado bloqueado.
        door.setState(new LockedState(door));

      } else if (door.getStateName().equals("unlocked_shortly")) {
        // Sino pasamos a Propped.
        door.setState(new ProppedState(door));
      }
      // Dejamos de observar el temporizador para evitar fugas y callbacks extra.
      o.deleteObserver(this);
    }
  }


}
