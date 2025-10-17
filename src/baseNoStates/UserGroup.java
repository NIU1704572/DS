package baseNoStates;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class UserGroup {
    private final String name;
    private final List<User> users;
    private final List<String> allowedActions;

    // para horario de users
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final List<DayOfWeek> allowedDays;
    private final LocalTime startTime;
    private final LocalTime endTime;

    // contructor (larguisimo lo se, es lo q hay)
    public UserGroup(String name, List<String> allowedActions, LocalDateTime startDate, LocalDateTime endDate, List<DayOfWeek> allowedDays, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.users = new ArrayList<>();
        this.allowedActions = allowedActions;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allowedDays = allowedDays;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addUser(User user) {
        users.add(user);
        user.setUserGroup(this);
    }

    // verifica si grupo puede hacer la accion
    public boolean isActionAllowed(String action) { return allowedActions.contains(action); }

    // verifica si se puede accion en ese momento
    public boolean isAccessAllowed(LocalDateTime dateTime) {
        // verifica rango de horas (por ejemplo 8-15)
        if (dateTime.isBefore(startDate) || dateTime.isAfter(endDate)) {
            return false;
        }

        // verifica dia
        if (!allowedDays.contains(dateTime.getDayOfWeek())) {
            return false;
        }

        // verifica hora
        LocalTime time = dateTime.toLocalTime();
        return true;
    }

    public String getName() { return name; }

}
