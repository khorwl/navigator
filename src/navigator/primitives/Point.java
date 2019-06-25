package navigator.primitives;

public class Point {

  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Point sum(Point other) {
    return new Point(x + other.getX(), y + other.getY());
  }

  public int computeManhattanDistance(Point destination) {
    return Math.abs(x - destination.getX()) + Math.abs(y - destination.getY());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Point) {
      Point other = (Point) obj;

      return other.x == x && other.y == y;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return (x * 73856093) ^ (y * 19349663);
  }

  @Override
  public String toString() {
    return String.format("(x = %d, y = %d)", x, y);
  }

  public static final Point UpOffset = new Point(0, -1);
  public static final Point DownOffset = new Point(0, 1);
  public static final Point RightOffset = new Point(1, 0);
  public static final Point LeftOffset = new Point(-1, 0);

}
