package navigator;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void main(String[] args){
    AlgorithmComparator comparator = new AlgorithmComparator();
    comparator.compare();
  }

  private static void writeMapToFile(char[][] map, String fileName) throws IOException {
    try (FileWriter fileWriter = new FileWriter(fileName)) {
      StringBuilder result = new StringBuilder();
      for (char[] chars : map) {
        result.append(chars);
        result.append(System.lineSeparator());
      }

      fileWriter.write(result.toString());
    }
  }


}
