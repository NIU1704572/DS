package baseNoStates;
import java.time.LocalDateTime;

public class User {
  private final String name;
  private final String credential;
  private UserGroup userGroup;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public void setUserGroup(UserGroup userGroup) {
      this.userGroup = userGroup;
  }

  // verifica si puede LOCK/UNLOCK en ese momento
  public boolean canSendRequests(LocalDateTime dateTime) {
      return userGroup.isAccessAllowed(dateTime);
  }

  // verifica si puede LOCK/UNLOCK
  public boolean canDoAction(String action) {
      return userGroup.isActionAllowed(action);
  }

  public String getCredential() { return credential; }

  public String getName() {return name; }

  public UserGroup getUserGroup() { return userGroup; }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
