package baseNoStates;

public class UnlockedState extends DoorState{
  //Aquesta classe no modifica cap dels mètodes implementats  a doorState, perquè no té restriccions específiques
  UnlockedState(Door door) { super("unlocked", door); }
}
