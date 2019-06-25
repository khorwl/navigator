package navigator.primitives;


import static navigator.Constants.Wall;

public class CityMap {

  private final char[][] map;
  private final int height;
  private final int width;

  public CityMap(char[][] map) {
    this.map = map;
    this.height = map.length;
    this.width = map[0].length;
  }

  public int height() {
    return height;
  }

  public int width() {
    return width;
  }

  public boolean isWall(Point point) {
    return map[point.getY()][point.getX()] == Wall;
  }

  public boolean inBound(Point point) {
    return inBound(point.getY(), point.getX());
  }

  private boolean inBound(int i, int j) {
    return i >= 0 && i < height && j >= 0 && j < width;
  }
}
