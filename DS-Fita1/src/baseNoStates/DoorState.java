package baseNoStates;

public abstract class DoorState {
  // Classe abstracta. La hereden la resta de classes del patró state.
  // Els mètodes estan implementats aquí i se'ls fa override quan cal perquè no vol·lem
  // repetir quasi tot el codi quan només un mètode és diferent.
  protected final String stateName;
  protected final Door door;

  public DoorState(String stateName, Door door) {
    this.stateName = stateName; // string que es retorna quan es crida getState()
    this.door = door;
  }

  public String getState() {
    return stateName;
  }

  public abstract String open();

  public abstract String close();

  public abstract String lock();

  public abstract String unlock();

  public abstract String unlock_shortly();

}
