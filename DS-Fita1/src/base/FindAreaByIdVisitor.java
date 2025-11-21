package base;

public class FindAreaByIdVisitor implements Visitor<Area> {
  private final String targetId;

  public FindAreaByIdVisitor(String id) {
    this.targetId = id;
  }

  @Override
  public Area visitPartition(Partition partition) {
    // Primero verifica si esta partición es la buscada
    if (partition.getId().equals(targetId)) {
      return partition;
    }

    // Luego busca recursivamente en los hijos
    for (Area area : partition.getSpaces()) {
      Area found = area.accept(this);
      if (found != null) {
        return found;
      }
    }
    return null;
  }

  @Override
  public Area visitSpace(Space space) {
    return space.getId().equals(targetId) ? space : null;
  }
}