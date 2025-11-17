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
      String reason = doAction(action);
      if (reason != null) {
        request.addReason(reason);
      }
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }
  public String doAction(String action) {
    // Aquest és un mètode és "template method"
    // Les accions que no són open i close no tenen funcions designades perquè s'executen de la mateixa
    // manera en totes les classes. Hem decidit fer aquesta implemetació perquè, encara que no fa
    // un bon ús del polimorfisme, només necessitem una sola funció i repetim molt menys codi.
    String reason = null;
    switch (action) {
      case Actions.OPEN:
        reason = state.open();
        break;

      case Actions.CLOSE:
        reason = state.close();
        break;

      case Actions.LOCK:
        reason = state.lock();
        break;

      case Actions.UNLOCK:
        reason = state.unlock();
        break;

      case Actions.UNLOCK_SHORTLY:
        reason = state.unlock_shortly();
        break;

      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
        reason = "Unknown action " + action;
    }
    return reason;
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
