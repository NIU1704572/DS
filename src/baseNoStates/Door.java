package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import java.util.Date;

public class Door {
  private static final byte unlocked = 0, locked = 1, unlockedShortly = 2, propped = 3; //Declaring states
  private final String id;
  private boolean closed; // physically
  private byte state;

  public Door(String id) {
    this.id = id;
    closed = true;
    state = 0;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void lockShortly(){
    Runnable lockingThread = new Runnable() {
      @Override
      public void run() {
        try{
          Thread.sleep(10000);
        }
        catch (InterruptedException e){
          System.out.println("Unlock shortly thread couldn't be interrupted");
        }
        if (closed) {
          state = locked;
        } else {
          state = propped; //Could add additional propped timer
        }
      }
    };
    Thread thread = new Thread(lockingThread);
    thread.start();
  }

  private void doAction(String action) {
    byte newState = - 1;

    switch (action) {
      case Actions.OPEN:
        if (closed && (state == unlocked || state == unlockedShortly)) {
          closed = false;
        } else {
          System.out.println("Can't open door " + id + " because it's already open or locked");
        }
        break;
      case Actions.CLOSE:
        if (closed) {
          System.out.println("Can't close door " + id + " because it's already closed");
        } else {
          closed = true;
        }
        break;
      case Actions.LOCK:
        if (state == locked){
          System.out.println("Can't lock door " + id + " because it's already locked");
        } else {
          newState = locked;
        }
        break;
      case Actions.UNLOCK:
        if (state == unlocked){
          System.out.println("Can't unlock door " + id + " because it's already unlocked");
        } else {
          newState = unlocked;
        }
        break;
      case Actions.UNLOCK_SHORTLY:
        if (state == unlocked){
          System.out.println("Can't unlock door " + id + " because it's already unlocked");
        } else {
          newState = unlockedShortly;
          lockShortly();
        }
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
    if(newState != -1 && !closed){
      System.out.println("Can't change door " + id + "'s state because it's open");
    } else{
      state = newState;
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    String stateName = switch (state) {
      case locked -> "locked";
      case unlocked -> "unlocked";
      case unlockedShortly -> "unlocked_shortly";
      case propped -> "propped";
      default -> "";
    };

    return stateName;
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
