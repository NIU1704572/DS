package baseNoStates;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    createUserGroups();

    // crear y asignar users a grupos
  }

  private static void createUserGroups() {

    UserGroup blank = new UserGroup( // user sin privilegios ("blank")
            "blank",
            Arrays.asList(),
            LocalDateTime.of(1, 1, 1, 0, 0),
            LocalDateTime.of(1, 1, 1, 0, 0),
            Arrays.asList(),
            LocalTime.of(0, 0),
            LocalTime.of(0, 0)
    );


    UserGroup employees = new UserGroup("employees",
              Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.CLOSE, Actions.OPEN),
            LocalDateTime.of(2025, 9, 1, 0, 0),
              LocalDateTime.of(2026, 3, 1, 0, 0),
              Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                      DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
              LocalTime.of(9, 0),
              LocalTime.of(17, 0)
    );


    UserGroup managers = new UserGroup("managers",
              Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.CLOSE, Actions.OPEN),
              LocalDateTime.of(2025, 9, 1, 0, 0),
              LocalDateTime.of(2026, 3, 1, 0, 0),
              Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                      DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
              LocalTime.of(8, 0),
              LocalTime.of(20, 0)
    );


    UserGroup admin = new UserGroup("admin",
              Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.CLOSE, Actions.OPEN),
            LocalDateTime.of(2024, 1, 1, 0, 0),
              LocalDateTime.of(2100, 1, 1, 0, 0),
              Arrays.asList(DayOfWeek.values()),
              LocalTime.MIN,
              LocalTime.MAX
    );


    // user sin privilegios ("blank")
    User bernat = new User("Bernat", "12345");
    User blai = new User("Blai", "77532");
    blank.addUser(bernat); // blank group
    blank.addUser(blai);
    users.add(bernat);
    users.add(blai);

    // employees
    User ernest = new User("Ernest", "74984");
    User eulalia = new User("Eulalia", "43295");
    employees.addUser(ernest);
    employees.addUser(eulalia);
    users.add(ernest);
    users.add(eulalia);

    // managers
    User manel = new User("Manel", "95783");
    User marta = new User("Marta", "05827");
    managers.addUser(manel);
    managers.addUser(marta);
    users.add(manel);
    users.add(marta);

    // admin
    User ana = new User("Ana", "11343");
    admin.addUser(ana);
    users.add(ana);
}

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
