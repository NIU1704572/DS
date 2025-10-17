package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;

public class DoorTimer extends Observable {
  private Timer timer;
  public DoorTimer() {
    this.timer = new Timer();
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        setChanged();
        notifyObservers(LocalDateTime.now());
      }
    };
    timer.scheduleAtFixedRate(timerTask, 1000, 1000);
  }
}
