package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;

// Reloj periódico que notifica a los observadores “ticks” cada segundo.

public class DoorTimer extends Observable {
  private Timer timer;
  private static DoorTimer instance;
  private DoorTimer() {
    this.timer = new Timer();

    // Tarea que se ejecutará en cada “tick”.
    // Marca que hay cambios por notificar y envía a los observadores el instante actual.
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        setChanged();
        notifyObservers(LocalDateTime.now());
      }
    };
    timer.scheduleAtFixedRate(timerTask, 1000, 1000);
  }

  public static DoorTimer getInstance() {
    if (instance == null) {
      instance = new DoorTimer();
    }
    return instance;
  }
}
