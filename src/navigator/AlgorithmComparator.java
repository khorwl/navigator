package navigator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import navigator.parser.StandardInputDataParser;
import navigator.searcher.AStarPathSearcher;
import navigator.searcher.BfsPathSearcher;

class AlgorithmComparator {

  private Map<Navigator, AlgoType> navigators = new HashMap<>();
  private List<ExperimentData> data = new ArrayList<>();
  private final int runAmount = 200;
  private final int mapAmount = 5;

  AlgorithmComparator() {
    StandardInputDataParser parser = new StandardInputDataParser();
    navigators.put(new AwesomeNavigator(new BfsPathSearcher(), parser), AlgoType.Bfs);
    navigators.put(new AwesomeNavigator(new AStarPathSearcher(), parser), AlgoType.AStar);
  }


  void compare() {
    for (Navigator n : navigators.keySet()) {
      runOnMultipleMaps(n);
    }
    writeReport();
  }

  private void writeReport() {
    String fileName = "report " + new Date().toString();
    try (FileWriter fileWriter = new FileWriter(fileName)) {

      for (ExperimentData data : data) {
        fileWriter.write(data.toString());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void runOnMultipleMaps(Navigator navigator) {
    for (int i = 0; i < mapAmount; i++) {
      List<Long> time = run(navigator, getMap(i));
      data.add(new ExperimentData(navigators.get(navigator), "map" + i,
          time.get(runAmount / 2), getAverage(time)));
    }
  }

  private static char[][] getMap(int index) {
    ArrayList<String> map = new ArrayList<>();

    try (FileReader reader = new FileReader(new File("map" + index + ".txt"))) {
      BufferedReader bf = new BufferedReader(reader);
      String line;
      while ((line = bf.readLine()) != null) {
        map.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    char[][] result = new char[map.size()][];
    for (int i = 0; i < map.size(); i++) {
      String row = map.get(i);
      result[i] = row.toCharArray();
    }
    return result;
  }

  private List<Long> run(Navigator navigator, char[][] data) {
    List<Long> executionTime = new ArrayList<>();
    for (int i = 0; i < runAmount; i++) {
      long time = System.currentTimeMillis();
      navigator.searchRoute(data);
      executionTime.add(System.currentTimeMillis() - time);
    }
    executionTime.sort(Comparator.comparingLong(c -> c));

    return executionTime;
  }

  private long getAverage(List<Long> list) {
    long sum = 0;
    for (long l : list) {
      sum += l;
    }

    return sum / list.size();
  }

  private class ExperimentData {

    private AlgoType type;
    private String mapName;
    private long medianRunTime;
    private long averageRunTime;

    private ExperimentData(AlgoType type, String mapName, long medianRunTime, long averageRunTime) {
      this.type = type;
      this.mapName = mapName;
      this.medianRunTime = medianRunTime;
      this.averageRunTime = averageRunTime;
    }

    @Override
    public String toString() {
      return String.format("Algorithm %s    Map Name %s    "
              + "Median run-time %d    Average run-time %d", type, mapName, medianRunTime,
          averageRunTime) + System.lineSeparator();
    }
  }

  private enum AlgoType {Bfs, AStar}
}
