package baseNoStates;

public abstract class DoorState {
  protected final String stateName;
  public DoorState(String stateName) {
      this.stateName = stateName;
  }
  public String getState() {return stateName;}

}
