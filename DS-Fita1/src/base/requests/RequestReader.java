package base.requests;

import base.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}     ";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUsers.findUserByCredential(credential);
    Door door = DirectoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    //TODO: get the who, where, when and what in order to decide, and if not

    authorized = false;

    // si user es intruso/desconocido
    if (user == null) {
        addReason("User doesn't exist");
        return;
    }

    // si user es blank
    if (user.getUserGroup().getName().equals("blank")) {
        addReason("User not authorized for this action");
        return;
    }

    // OPEN y CLOSE para todo el mundo excepto employees en parking
    if (action.equals(Actions.OPEN) || action.equals(Actions.CLOSE) || action.equals(Actions.UNLOCK_SHORTLY)) {
        if (user.getUserGroup().getName().equals("employees")) {

            Area fromArea = DirectoryAreas.getInstance().findAreaById(doorId);
            Area toArea = DirectoryAreas.getInstance().findAreaById(door.getTo());

            boolean isParkingDoor = (fromArea.getId().equals("parking")) || (toArea.getId().equals("parking"));

            if (isParkingDoor) {
                addReason("User not authorized for this action");
                return;
            }

          authorized = true;
          userName = user.getName();
          return;
        }

    }

    // Verificar si puede LOCK/UNLOCK/UNLOCK_SHORTLY en tiempo
    if (!user.canSendRequests(now)) {
        addReason("User not authorized at this time");
        return;
    }

    // Verificar si puede LOCK/UNLOCK/UNLOCK_SHORTLY
    if (!user.canDoAction(action)) {
        addReason("User not authorized for this action");
        return;
    }

    authorized = true;
    userName = user.getName();
  }
}

