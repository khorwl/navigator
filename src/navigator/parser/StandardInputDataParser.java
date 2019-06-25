package navigator.parser;

import navigator.Constants;
import navigator.primitives.CityMap;
import navigator.primitives.InputData;
import navigator.primitives.Point;

public class StandardInputDataParser implements InputDataParser {

  @Override
  public InputData parse(char[][] input) {
    CityMap map = new CityMap(input);
    int n = map.height();
    int m = map.width();
    Point start = null, finish = null;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (input[i][j] == Constants.StartPosition) {
          start = new Point(j, i);
        } else if (input[i][j] == Constants.FinishPosition) {
          finish = new Point(j, i);
        }
      }

      if (start != null && finish != null) {
        break;
      }
    }

    if (start == null) {
      throw new IllegalArgumentException("There is no start point at given map");
    }

    if (finish == null) {
      throw new IllegalArgumentException("There is no finish point at given map");
    }

    return new InputData(map, start, finish);
  }
}
