package navigator.searcher;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import navigator.primitives.CityMap;
import navigator.primitives.InputData;
import navigator.primitives.Point;

public class BfsPathSearcher implements PathSearcher {

  @Override
  public List<Point> searchPath(InputData data) {
    Queue<Point> queue = new LinkedList<>();
    Map<Point, Point> pointToPrevious = new HashMap<>();
    CityMap map = data.map();
    Point finish = data.finish();

    pointToPrevious.put(data.start(), null);
    queue.add(data.start());

    while (queue.size() != 0) {
      Point point = queue.poll();

      if (point.equals(finish)) {
        return PathSearcherHelper.combinePath(pointToPrevious, finish);
      }

      Set<Point> neighbors = PathSearcherHelper.getNeighbors(map, point);
      for (Point i : neighbors) {
        if (!pointToPrevious.containsKey(i)) {
          pointToPrevious.put(i, point);
          queue.add(i);
        }
      }
    }

    return null;
  }
}
