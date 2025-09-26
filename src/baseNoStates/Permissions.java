package baseNoStates;

public class Permissions {
  public static final byte noPermission = 0, employee = 1, manager = 2, admin = 3;
  public boolean hasAccess(String action, byte permission) {
    if (action.equals(Actions.LOCK) && permission > 1)
    return false;
  }
};
