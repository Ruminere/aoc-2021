import java.io.*;
import java.util.*;

public class Problem15 {
  private static int[][] map;

  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
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

    ArrayList<Integer> pathSizes = new ArrayList<Integer>();
    ArrayList<int[]> alreadyCounted = new ArrayList<int[]>();

    findPathRisks(new int[]{0,0}, 0, pathSizes, alreadyCounted);
    Collections.sort(pathSizes);
    System.out.println(pathSizes);

    // return answers
    System.out.println("Part 1: " + pathSizes.get(0));
    //System.out.println("Part 2: " + part2);
  }


  //================================================================================
  // FIND ALL PATH RISKS
  //================================================================================
  private static void findPathRisks(int[] coords, int pathRisk, ArrayList<Integer> pathSizes, ArrayList<int[]> alreadyCounted) {
    System.out.println(Arrays.toString(coords));
    pathRisk += map[coords[0]][coords[1]];
    alreadyCounted.add(coords);

    // check if this is the end of the map, and if so, end this recursion path
    if (coords[0] == map.length-1 && coords[1] == map[0].length-1) {
      pathSizes.add(pathRisk);
      System.out.println("Finished path: " + pathRisk);
    } else {
      int numRestricted = alreadyCounted.size();
      // account for adjacent coordinates
      ArrayList<int[]> adjacentCoordsList = adjacentCoords(coords, new int[]{map.length, map[0].length});
      for (int[] adjacent : adjacentCoordsList) {
        clearListFromIndex(alreadyCounted, numRestricted);
        if (!arrayListHasCoords(alreadyCounted, adjacent)) {
          findPathRisks(adjacent, pathRisk, pathSizes, alreadyCounted);
        }
      }
    }
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

  // delete all items from an ArrayList until and including startIndex
  private static void clearListFromIndex(ArrayList<int[]> arr, int startIndex) {
    while (arr.size() != startIndex){
      arr.remove(arr.size()-1);
    }
  }
}
