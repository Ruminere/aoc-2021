import java.io.*;
import java.util.*;

public class Problem11 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    int[][] map;

    // init
    Scanner input = new Scanner(new File("input.txt"));
    ArrayList<String> lines = new ArrayList<String>();
    while (input.hasNextLine()) {
      lines.add(input.nextLine());
    }
    map = new int[lines.size()][lines.get(0).length()];
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(i).length(); j++) {
        map[i][j] = Integer.parseInt( lines.get(i).substring(j,j+1) );
      }
    }

    // increment and flash
    int part1 = 0;
    int step = 1;

    int[] coords;
    int currentFlash;
    ArrayList<int[]> alreadyFlashed = new ArrayList<int[]>();
    while (!isAllFlashing(map)) {
      alreadyFlashed.clear();
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          coords = new int[]{i,j};
          currentFlash = flash(map, new int[]{i,j}, alreadyFlashed);
          if (step <= 100) part1 += currentFlash;
        }
      }
      step++;
    }

    // return answers
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: " + (step-1));
  }


  //================================================================================
  // FLASH-SPECIFIC HELPER FUNCTIONS
  //================================================================================
  private static int flash(int[][] map, int[] coords, ArrayList<int[]> alreadyFlashed) {
    int flashCount = 0;

    if (!arrayListHasCoords(alreadyFlashed, coords)) { // check that the octopus hasn't already flashed this turn (and thus can increment up)
      map[coords[0]][coords[1]] += 1;

      if (map[coords[0]][coords[1]] == 10) { // check for flashes

        // first count the flash for the current coordinates
        map[coords[0]][coords[1]] = 0;
        alreadyFlashed.add(coords);
        flashCount += 1;

        // next count the flashes for the adjacent coordinates
        ArrayList<int[]> adjacentCoordsList = adjacentCoords(coords, new int[]{map.length, map[0].length});
        for (int[] adjacent : adjacentCoordsList) {
          int currentAdjacent = map[adjacent[0]][adjacent[1]];
          flashCount += flash(map, adjacent, alreadyFlashed);
        }

      }
    }

    return flashCount;
  }

  private static boolean isAllFlashing(int[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != 0) return false;
      }
    }
    return true;
  }


  //================================================================================
  // OTHER HELPER FUNCTIONS: adjacentCoords, arrayListHasCoords
  //================================================================================

  // returns adjacent coordinates
  private static ArrayList<int[]> adjacentCoords(int[] coords, int[] coordLimit) {
    ArrayList<int[]> coordList = new ArrayList<int[]>();
    int[][] directions = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}}; // up, then go around clockwise

    for (int i = 0; i < directions.length; i++) {
      int newRow = coords[0] + directions[i][0];
      int newColumn = coords[1] + directions[i][1];
      // check for out of bounds
      if (newRow != -1 && newColumn != -1 && newRow != coordLimit[0] && newColumn != coordLimit[1]) {
        coordList.add(new int[]{newRow, newColumn});
      }
    }

    return coordList;
  }

  // finds if an int[] of length 2 is in an arraylist of int[]s with length 2
  private static boolean arrayListHasCoords(ArrayList<int[]> arr, int[] target) {
    for (int i = 0; i < arr.size(); i++) {
      int[] current = arr.get(i);
      if (current[0] == target[0] && current[1] == target[1]) return true;
    }
    return false;
  }


  //================================================================================
  // DEBUGGING FUNCTIONS
  //================================================================================
  private static String mapToString(int[][] map) {
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
