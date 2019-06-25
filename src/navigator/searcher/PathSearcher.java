package navigator.searcher;

import java.util.List;
import navigator.primitives.InputData;
import navigator.primitives.Point;

public interface PathSearcher {

  List<Point> searchPath(InputData data);
}
