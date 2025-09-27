package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState state; // Digitally

  public Door(String id) {
    this.id = id;
    closed = true;
    state = new LockedState();
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
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {return state.getState(); }

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
