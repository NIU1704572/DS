package baseNoStates;

public class LockedState extends DoorState {
  //Aquesta classe és l'estat al patró State de les portes bloquejades pel sistema.
  LockedState(Door door) { super("locked", door); }
  @Override
  public void open(){
    //Com la porta està bloquejada, no es pot obrir.
    System.out.println("Can't open door " + door.getId() + " because it's locked");
  }
}
