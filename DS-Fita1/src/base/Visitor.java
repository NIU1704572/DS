package base;

public interface Visitor<T> {
  T visitPartition(Partition partition);
  T visitSpace(Space space);
}