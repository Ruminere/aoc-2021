import java.io.*;
import java.util.*;

public class Problem20 {
  public static String alg; // the image enhancement algorithm, but in bits

  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    int[][] map = initialize("input.txt");

    // find and return answers
    for (int i = 1; i <= 50; i++) {
      int pad = (i % 2 == 1) ? 0 : (alg.charAt(0) == '0') ? 0 : 1;
      map = enhanceImage(map, pad);

      if (i == 2) {
        System.out.println("Part 1: " + countPixels(map));
      } else if (i == 50) {
        System.out.println("Part 2: " + countPixels(map));
      }
    }
  }

  //================================================================================
  // IMAGE PROCESSING
  //================================================================================
  private static int[][] enhanceImage(int[][] map, int pad) {
    map = mapAddPad(map, pad);
    map = mapAddPad(map, pad);

    // deep copy to a new array, since old map is needed for reference
    int[][] newMap = new int[map.length][map[0].length];
    for (int i = 0; i < newMap.length; i++) {
      for (int j = 0; j < newMap[i].length; j++) {
        newMap[i][j] = map[i][j];
      }
    }

    // go through each pixel and process them
    for (int i = 1; i < map.length-1; i++) {
      for (int j = 1; j < map[i].length-1; j++) {
        newMap[i][j] = processPixel(map, new int[]{i, j});
      }
    }

    newMap = mapRemovePad(newMap);

    return newMap;
  }

  private static int processPixel(int[][] map, int[] coords) {
    int i = coords[0];
    int j = coords[1];

    String binary = "";
    binary += map[i-1][j-1];
    binary += map[i-1][j];
    binary += map[i-1][j+1];
    binary += map[i][j-1];
    binary += map[i][j];
    binary += map[i][j+1];
    binary += map[i+1][j-1];
    binary += map[i+1][j];
    binary += map[i+1][j+1];

    int target = Integer.parseInt(binary, 2);
    return (alg.charAt(target) == '0') ? 0 : 1;
  }

  private static int countPixels(int[][] map) {
    int result = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        result += map[i][j];
      }
    }
    return result;
  }

  //================================================================================
  // INIT
  //================================================================================
  private static int[][] initialize(String fileName) throws IOException {
    Scanner input = new Scanner(new File(fileName));

    // convert the image enhancement algorithm to bits
    String algRaw = input.nextLine();
    alg = "";
    for (int i = 0; i < algRaw.length(); i++) {
      alg += (algRaw.charAt(i) == '.') ? 0 : 1;
    }

    input.nextLine(); // get rid of the empty line

    // convert the map to bits
    ArrayList<String> lines = new ArrayList<String>();
    while (input.hasNextLine()) {
      lines.add(input.nextLine());
    }
    int[][] map = new int[lines.size()][lines.get(0).length()];
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(i).length(); j++) {
        map[i][j] = (lines.get(i).charAt(j) == '.') ? 0 : 1;
      }
    }

    return map;
  }

  //================================================================================
  // MAP FUNCTIONS
  //================================================================================
  private static int[][] mapAddPad(int[][] map, int pad) {
    int[][] newMap = new int[map.length+2][map[0].length+2];
    for (int i = 0; i < newMap.length; i++) {
      if (i == 0 || i == newMap.length-1) { // outer padding: top and bottom
        for (int j = 0; j < newMap[i].length; j++) newMap[i][j] = pad;
      } else {
        newMap[i][0] = pad;
        for (int j = 1; j < newMap[i].length-1; j++) newMap[i][j] = map[i-1][j-1];
        newMap[i][newMap[i].length-1] = pad;
      }
    }
    return newMap;
  }
  private static int[][] mapRemovePad(int[][] map) {
    int[][] newMap = new int[map.length-2][map[0].length-2];
    for (int i = 0; i < newMap.length; i++) {
      for (int j = 0; j < newMap[i].length; j++) {
        newMap[i][j] = map[i+1][j+1];
      }
    }
    return newMap;
  }

  private static String mapToString(int[][] map) {
    String str = "";
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        str += (map[i][j] == 0) ? '.' : '#';
      }
      str += "\n";
    }
    return str;
  }
}
