package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

public class Door{
  private final String id;
  private final String from;
  private final String to;
  private boolean closed; // physically
  private DoorState state; // Digitally
  // funcion para inicializar Door
  public Door(String id, String from, String to) {
    this.id = id;
    this.from = from;
    this.to = to;
    closed = true;
    state = new LockedState(this);
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      state.doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
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

  public void setClosed(boolean closed) { this.closed = closed; }

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
