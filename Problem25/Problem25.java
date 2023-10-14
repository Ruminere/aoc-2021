import java.io.*;
import java.util.*;

public class Problem25 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    char[][] map;

    // init
    Scanner input = new Scanner(new File("input.txt"));
    ArrayList<String> lines = new ArrayList<String>();
    while (input.hasNextLine()) {
      lines.add(input.nextLine());
    }
    map = new char[lines.size()][lines.get(0).length()];
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(i).length(); j++) {
        map[i][j] = lines.get(i).charAt(j);
      }
    }


    // find answers
    int step = 0;
    boolean hasMoved = true;
    while (hasMoved) {
      step++;

      Object[] r = moveRight(map);
      map = (char[][]) r[0];
      boolean hasMovedRight = (boolean) r[1];

      Object[] d = moveDown(map);
      map = (char[][]) d[0];
      boolean hasMovedDown = (boolean) d[1];

      hasMoved = (hasMovedRight || hasMovedDown);
    }


    // return answers
    System.out.println("Part 1: " + step);
    System.out.println("Part 2: " + "None, Merry Christmas!");
  }

  //================================================================================
  // SOLUTION LOGIC
  //================================================================================
  private static Object[] moveRight(char[][] map) { // returns {map, moved}
    boolean moved = false;

    char[][] newMap = new char[map.length][map[0].length];
    for (int i = 0; i < newMap.length; i++) {
      for (int j = 0; j < newMap[i].length; j++) {
        newMap[i][j] = '.';
      }
    }

    for (int i = 0; i < map.length; i++) {
      int j = 0;
      while (j < map[i].length) {
        if (map[i][j] == '>') { // check if cucumber is going right
          if (j != map[i].length-1 && map[i][j+1] == '.') { // check if it's not on the edge
            newMap[i][j+1] = '>';
            j += 2;
            moved = true;
          } else if (j == map[i].length-1 && map[i][0] == '.') { // check if it's on the edge and can loop over
            newMap[i][0] = '>';
            j++;
            moved = true;
          } else { // if the cucumber can't move, simply increment
            newMap[i][j] = '>';
            j++;
          }
        } else { // don't move cucumber, increment
          newMap[i][j] = map[i][j];
          j++;
        }
      }
    }

    Object[] result = new Object[]{newMap, moved};
    return result;
  }

  private static Object[] moveDown(char[][] map) { // returns {map, moved}
    boolean moved = false;

    char[][] newMap = new char[map.length][map[0].length];
    for (int i = 0; i < newMap.length; i++) {
      for (int j = 0; j < newMap[i].length; j++) {
        newMap[i][j] = '.';
      }
    }

    for (int j = 0; j < map[0].length; j++) {
      int i = 0;
      while (i < map.length) {
        if (map[i][j] == 'v') { // check if cucumber is going down
          if (i != map.length-1 && map[i+1][j] == '.') { // check if it's not on the edge
            newMap[i+1][j] = 'v';
            i += 2;
            moved = true;
          } else if (i == map.length-1 && map[0][j] == '.') { // check if it's on the edge and can loop over
            newMap[0][j] = 'v';
            i++;
            moved = true;
          } else { // if the cucumber can't move, simply increment
            newMap[i][j] = 'v';
            i++;
          }
        } else { // don't move cucumber, increment
          newMap[i][j] = map[i][j];
          i++;
        }
      }
    }

    Object[] result = new Object[]{newMap, moved};
    return result;
  }

  //================================================================================
  // DEBUGGING FUNCTIONS
  //================================================================================
  private static String mapToString(char[][] map) {
    String str = "";
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        str += map[i][j];
      }
      str += "\n";
    }
    return str;
  }
}
