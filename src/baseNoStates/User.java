package baseNoStates;

public class User {
  private final String name;
  private final String credential;
  private byte permission;

  public User(String name, String credential, byte permission) {
    this.name = name;
    this.credential = credential;
    this.permission = permission;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
