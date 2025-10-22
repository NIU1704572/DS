package baseNoStates;

import java.util.Observable;
import java.util.Observer;

public class UnlockedShortlyState extends DoorState implements Observer {
  private int secondsElapsed; //IMPLEMENTAR BIEN PATRON STATE
  public UnlockedShortlyState(Door door) {
    // Durante ~10s (cadencia del DoorTimer), la puerta permite abrirse.
    // Nos suscribimos al reloj global para recibir ticks de 1s (DoorTimer).
    super("unlocked_shortly", door);
    DirectoryDoors.getTimer().addObserver(this);
    secondsElapsed = 0; // arranca la cuenta atrás al entrar en el estado
  }

  @Override
  public void update(Observable o, Object arg) {
    secondsElapsed++;
    if(secondsElapsed == 10) {
      if(door.isClosed()){
        // Si nadie la abrió durante la ventana, volvemos a estado bloqueado.
        door.setState(new LockedState(door));

      } else{
        // Sino pasamos a Propped.
        door.setState(new ProppedState(door));
      }
      // Dejamos de observar el temporizador para evitar fugas y callbacks extra.
      DirectoryDoors.getTimer().deleteObserver(this);
      secondsElapsed = 0;
    }
  }
}
