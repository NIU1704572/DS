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
  public String getState() {return stateName;}

  public void open(){
    // El mètode està implementat a la classe abstracta per evitar repetir codi.
    // Les classes que ho necessitin, poden fer override (LockedState, per exemple).
    if(door.isClosed()) door.setClosed(false); //check if door is open
    else System.out.println("Can't open door " + door.getId() + " because it's already open");
  }


  public void close(){
    // Method is implemented in the abstract class because most states
    // share the same checks, and we want to avoid repeating code.
    // It may be overridden when necessary (ProppedState, for example)
    if (!door.isClosed()) door.setClosed(true);
    else System.out.println("Can't close door " + door.getId() + " because it's already closed"); }

  public void changeState(DoorState newState, String action) {
    // Aquest mètode fa les comprovacions comuns. Existeix per no repetir-les en altres classes.

    // si l'estat anterior és el mateix que el nou, es conserva l'estat anterior
    if (newState.stateName.equals(stateName)) {
      System.out.println("Can't " + action + " door " + door.getId()
          + " because it's already locked"); // check the new state isn't the same as the previous one
      return;

    //Si la porta és oberta, no pot canviar d'estat
    } else if (!door.isClosed()) {
      if(stateName.equals("propped")){
        //Si la porta està bloquejada donem un missatge més concret
        System.out.println("Can't " + action + " door " + door.getId() +
            " because the door is currently propped and may not change state until closed");
      }
      else {
        System.out.println("Can't " + action + " door " + door.getId()
            + " because it may not change state while open");
      }
    }
    // Si la porta està tancada i el nou estat és diferent
    door.setState(newState);
  }

  public void doAction(String action) {
    // Aquest és un mètode és "template method"
    // Les accions que no són open i close no tenen funcions designades perquè s'executen de la mateixa
    // manera en totes les classes. Hem decidit fer aquesta implemetació perquè, encara que no fa
    // un bon ús del polimorfisme, només necessitem una sola funció i repetim molt menys codi.
    switch (action) {
      case Actions.OPEN:
        this.open();
        break;

      case Actions.CLOSE:
        this.close();
        break;

      case Actions.LOCK:
        this.changeState(new LockedState(door),action);
        break;

      case Actions.UNLOCK:
        this.changeState(new UnlockedState(door),action);
        break;

      case Actions.UNLOCK_SHORTLY:
        this.changeState(new UnlockedShortlyState(door),action);
        break;

      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

}
