package navigator.searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import navigator.primitives.CityMap;
import navigator.primitives.Point;

class PathSearcherHelper {

  private final static List<Point> neighborsOffsets = Arrays.asList(
      Point.UpOffset,
      Point.RightOffset,
      Point.DownOffset,
      Point.LeftOffset
  );

  static Set<Point> getNeighbors(CityMap map, Point point) {
    HashSet<Point> result = new HashSet<>();
    for (Point i : neighborsOffsets) {
      Point neighbor = point.sum(i);
      if (map.inBound(neighbor) && !map.isWall(neighbor)) {
        result.add(neighbor);
      }
    }
    return result;
  }

  static List<Point> combinePath(Map<Point, Point> pointToPrevious, Point finish) {
    ArrayList<Point> path = new ArrayList<>();
    Point current = pointToPrevious.get(finish);

    while (pointToPrevious.get(current) != null) {
      path.add(current);
      current = pointToPrevious.get(current);
    }

    Collections.reverse(path);

    return path;
  }
}
