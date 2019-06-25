package navigator.searcher;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import navigator.primitives.CityMap;
import navigator.primitives.InputData;
import navigator.primitives.Point;

public class AStarPathSearcher implements PathSearcher {

  @Override
  public List<Point> searchPath(InputData data) {
    Map<Point, Point> pointToPrevious = new HashMap<>();
    Map<Point, Integer> pointToDistance = new HashMap<>();
    CityMap map = data.map();
    Point finish = data.finish();
    int initialCapacity = map.height() * map.width();
    Queue<PriorityPoint> queue = new PriorityQueue<>(
        initialCapacity,
        Comparator.comparingInt(o -> o.priority));

    pointToPrevious.put(data.start(), null);
    pointToDistance.put(data.start(), 0);
    queue.add(new PriorityPoint(data.start(), 0));

    while (queue.size() != 0) {
      Point current = queue.poll().point;

      if (current.equals(finish)) {
        return PathSearcherHelper.combinePath(pointToPrevious, finish);
      }

      for (Point neighbor : PathSearcherHelper.getNeighbors(map, current)) {

        Integer cost = pointToDistance.get(current);
        if (!pointToPrevious.containsKey(neighbor)) {
          pointToDistance.put(neighbor, cost + 1);
          //5 подобран исходя из нескольких экспериментов
          int priority = cost + 5 * neighbor.computeManhattanDistance(finish);
          pointToPrevious.put(neighbor, current);
          queue.add(new PriorityPoint(neighbor, priority));
        }
      }
    }

    return null;
  }


  private class PriorityPoint {

    private Point point;
    private int priority;

    private PriorityPoint(Point point, int priority) {
      this.point = point;
      this.priority = priority;
    }
  }
}