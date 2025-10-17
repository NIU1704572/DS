package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Door implements Observer {
  private final String id;
  private final String from;
  private final String to;
  private boolean closed; // physically
  private DoorState state; // Digitally
  private int timer;


  public Door(){id=""; from="";to="";}

  public Door(String id, String from, String to) {
    this.id = id;
    this.from = from;
    this.to = to;
    closed = true;
    state = new LockedState();
    timer = 0;
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

  public boolean validAction(String action) {
    //function that unifies common state change checks
    if (action.equals(state.getState())) {
      System.out.println("Can't " + action + " door " + id + " because it's already" + state.getState()); // check the new state isn't the same as the previous one
      return false;
    } else if (!closed) {
      System.out.println("Can't " + action + " door " + id + " because it may not change state while open");           //check door isn't open
      return false;
    }
    return true;
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if (!closed) {
          System.out.println("Can't open door " + id + " because it's already open"); // check door isn't already open
        } else if (state.getState().equals("locked")){
          System.out.println("Can't open door " + id + " because it's locked"); // check door isn't locked
        }
        else {
          closed = false;
        } break;

      case Actions.CLOSE:
        if (closed) {
          System.out.println("Can't close door " + id + " because it's already closed"); // check door isn't already closed
        } else {
          closed = true;
          state = new LockedState();
        } break;

      case Actions.LOCK:
        if(validAction(action)){  // call function that verifies common checks
          state = new LockedState();
        } break;

      case Actions.UNLOCK:
        if(validAction(action)){  // call function that verifies common checks
          state = new UnlockedState();
        } break;

      case Actions.UNLOCK_SHORTLY:
        DirectoryDoors.getTimer().addObserver(this);
        state = new UnlockedShortlyState();
        break;

      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }
  @Override
  public void update(Observable o, Object arg) {
    timer++;
    if (timer == 10) {
      state = ((UnlockedShortlyState)state).nextState(closed);
      DirectoryDoors.getTimer().deleteObserver(this);
      timer = 0;
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {return state.getState(); }

  public String getFrom() { return from; }

  public String getTo() { return to; }

  public void setState(DoorState state) { this.state = state; }

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
