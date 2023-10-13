import java.io.*;
import java.util.*;

public class Problem09 {
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


    // find low points, then the sizes of all basins
    ArrayList<int[]> lowPoints = findLowPoints(map);
    ArrayList<Integer> basinSizes = new ArrayList<Integer>();

    ArrayList<int[]> queue = new ArrayList<int[]>();
    ArrayList<int[]> alreadyCounted = new ArrayList<int[]>();
    for (int[] coords : lowPoints) {
      queue.clear();
      alreadyCounted.clear();
      queue.add(coords);
      basinSizes.add( basinSize(map, queue, alreadyCounted) );
    }


    // finalize answer for part 2
    Collections.sort(basinSizes);
    int part2 = basinSizes.get(basinSizes.size()-1) * basinSizes.get(basinSizes.size()-2) * basinSizes.get(basinSizes.size()-3);

    // return answers
    System.out.println("Part 1: " + riskLevelSum(map, lowPoints));
    System.out.println("Part 2: " + part2);
  }


  //================================================================================
  // FIND LOW POINTS, THEN BASIN SIZE
  //================================================================================
  private static ArrayList<int[]> findLowPoints(int[][] map) {
    ArrayList<int[]> lowPoints = new ArrayList<int[]>();

    ArrayList<int[]> adjacentCoordsList;
    int smallerCount;

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {

        smallerCount = 0;
        adjacentCoordsList = adjacentCoords(new int[]{i,j}, new int[]{map.length, map[0].length});
        for (int[] adjacent : adjacentCoordsList) { // check if each adjacent field is bigger than the current one
          if (map[i][j] < map[adjacent[0]][adjacent[1]]) {
            smallerCount++;
          }
        }

        if (smallerCount == adjacentCoordsList.size()) {
          lowPoints.add(new int[]{i,j});
        }

      }
    }

    return lowPoints;
  }

  private static int basinSize(int[][] map, ArrayList<int[]> queue, ArrayList<int[]> alreadyCounted) {
    int size = 0;

    while (queue.size() > 0) {
      int[] coords = queue.remove(0);

      if (!arrayListHasCoords(alreadyCounted, coords) && map[coords[0]][coords[1]] != 9) { // check that coords hasn't already been visited and this isn't a wall
        // add to basin size and mark as visited
        alreadyCounted.add(coords);

        // account for adjacent coordinates
        ArrayList<int[]> adjacentCoordsList = adjacentCoords(coords, new int[]{map.length, map[0].length});
        for (int[] adjacentCoords : adjacentCoordsList) {
          queue.add(adjacentCoords);
        }
      }
    }

    size = alreadyCounted.size();
    return size;
  }


  //================================================================================
  // OTHER HELPER FUNCTIONS: adjacentCoords, arrayListHasCoords
  //================================================================================

  // returns adjacent coordinates
  private static ArrayList<int[]> adjacentCoords(int[] coords, int[] coordLimit) {
    ArrayList<int[]> coordList = new ArrayList<int[]>();
    int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // up, then go around clockwise (no diagonals)

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
  // PART 1-SPECIFIC FUNCTION: FIND SUM OF RISK LEVELS
  //================================================================================
  private static int riskLevelSum(int[][] map, ArrayList<int[]> lowPoints) {
    int sum = 0;

    for (int[] coords : lowPoints) {
      sum += map[coords[0]][coords[1]] + 1;
    }

    return sum;
  }
}
