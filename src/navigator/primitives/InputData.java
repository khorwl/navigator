package navigator.primitives;

public class InputData {

  private final CityMap map;
  private final Point start;
  private final Point finish;

  public InputData(CityMap map, Point start, Point finish) {
    this.map = map;
    this.start = start;
    this.finish = finish;
  }

  public CityMap map() {
    return map;
  }

  public Point start() {
    return start;
  }

  public Point finish() {
    return finish;
  }
}
