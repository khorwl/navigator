package navigator;

import java.util.List;
import navigator.parser.InputDataParser;
import navigator.parser.StandardInputDataParser;
import navigator.primitives.InputData;
import navigator.primitives.Point;
import navigator.searcher.AStarPathSearcher;
import navigator.searcher.PathSearcher;

public class AwesomeNavigator implements Navigator {

  private final PathSearcher searcher;
  private final InputDataParser parser;

  public AwesomeNavigator() {
    this.searcher = new AStarPathSearcher();
    this.parser = new StandardInputDataParser();
  }

  //более универсальный, использовался для тестирования алгоритмов
  public AwesomeNavigator(PathSearcher searcher, InputDataParser parser) {
    this.searcher = searcher;
    this.parser = parser;
  }

  @Override
  public char[][] searchRoute(char[][] map) {
    try {
      InputData input = parser.parse(map);
      List<Point> path = searcher.searchPath(input);
      if (path != null) {
        return createMapWithRoute(map, path);
      }
    } catch (IllegalArgumentException e) {
      return null;
    }

    return null;
  }

  private static char[][] createMapWithRoute(char[][] map, List<Point> path) {
    char[][] output = clone(map);

    for (Point p : path) {
      output[p.getY()][p.getX()] = Constants.Route;
    }

    return output;
  }

  private static char[][] clone(char[][] source) {
    int n = source.length;
    int m = source[0].length;
    char[][] clone = new char[n][m];

    for (int i = 0; i < n; i++) {
      System.arraycopy(source[i], 0, clone[i], 0, m);
    }

    return clone;
  }
}
