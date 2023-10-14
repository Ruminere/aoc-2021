import java.io.*;
import java.util.*;

public class Problem12 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    ArrayList<String[]> paths = new ArrayList<String[]>(); // format for each path array: start, end

    // init
    Scanner input = new Scanner(new File("input.txt"));
    String[] lines;
    while (input.hasNextLine()) {
      lines = input.nextLine().split("-");
      paths.add(new String[]{lines[0], lines[1]});
      paths.add(new String[]{lines[1], lines[0]});
    }

    ArrayList<String> currentPath = new ArrayList<String>();
    ArrayList<String> smallCavesRestricted = new ArrayList<String>();

    // run part 1
    //System.out.println("---------Part 1---------");
    int part1 = findPaths1(paths, "start", currentPath, smallCavesRestricted);

    // run part 2
    currentPath.clear();
    smallCavesRestricted.clear();
    //System.out.println("\n\n\n---------Part 2---------");
    int part2 = findPaths2(paths, "start", currentPath, smallCavesRestricted);

    // return answers
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: " + part2);
  }


  //================================================================================
  // FINDPATHS FUNCTIONS
  //================================================================================
  private static int findPaths1(ArrayList<String[]> paths, String currentCave, ArrayList<String> currentPath, ArrayList<String> smallCavesRestricted) {
    int numPaths = 0;

    // add the current path to the log of paths
    currentPath.add(currentCave);

    // if we're at a small cave other than "end", add it to the lilst of visited small caves so it's not visited again
    if (isSmallCave(currentCave)) {
      smallCavesRestricted.add(currentCave);
    }

    // debugging purposes
    //System.out.println("Current Path: " + currentPath);
    //System.out.println("Restricted Caves: " + smallCavesRestricted);

    // if we're at the end, return 1
    if (currentCave.equals("end")) {
      return 1;
    }

    // save the number of restricted small caves (to delete the rest through each iteration)
    int currentPathLength = currentPath.size();
    int numRestricted = smallCavesRestricted.size();

    // find small paths and repeat recursion
    for (String[] path : paths) {
      clearListFromIndex(currentPath, currentPathLength);
      clearListFromIndex(smallCavesRestricted, numRestricted);
      if (path[0].equals(currentCave) && !isRestricted(smallCavesRestricted, path[1])) {
        numPaths += findPaths1(paths, path[1], currentPath, smallCavesRestricted);
      }
    }

    return numPaths;
  }

  private static int findPaths2(ArrayList<String[]> paths, String currentCave, ArrayList<String> currentPath, ArrayList<String> smallCavesRestricted) {
    int numPaths = 0;

    // add the current path to the log of paths
    currentPath.add(currentCave);

    // if we're at a small cave other than "end" and a small cave hasn't been visited twice, add it to the list of visited small caves so it's not visited again
    if (currentCave.equals("start") || (isSmallCave(currentCave) && smallCaveVisitedTwice(currentPath))) {
      for (String cave : currentPath) { // add previous caves from the current path (messy and repetitive, but it works)
        if (isSmallCave(cave)) smallCavesRestricted.add(cave);
      }
      smallCavesRestricted.add(currentCave);
    }

    // debugging purposes
    //System.out.println("Current Path: " + currentPath);
    //System.out.println("Restricted Caves: " + smallCavesRestricted);

    // if we're at the end, return 1
    if (currentCave.equals("end")) return 1;

    // save the number of caves visited and restricted small caves (to delete the rest through each iteration)
    int currentPathLength = currentPath.size();
    int numRestricted = smallCavesRestricted.size();

    // find small paths and repeat recursion
    for (String[] path : paths) {
      clearListFromIndex(currentPath, currentPathLength);
      clearListFromIndex(smallCavesRestricted, numRestricted);
      if (path[0].equals(currentCave) && !isRestricted(smallCavesRestricted, path[1])) {
        numPaths += findPaths2(paths, path[1], currentPath, smallCavesRestricted);
      }
    }

    return numPaths;
  }


  //================================================================================
  // CAVE-SPECIFIC HELPER FUNCTIONS
  //================================================================================

  // check if the cave is small (meaning all lowercase letters)
  private static boolean isSmallCave(String location) {
    for (int i = 0; i < location.length(); i++) {
      if (!Character.isLowerCase(location.charAt(i))) return false;
    }
    return true;
  }

  // check if the target cave is included in the list of restricted small caves
  private static boolean isRestricted(ArrayList<String> smallCavesRestricted, String targetCave) {
    for (String cave : smallCavesRestricted) {
      if (cave.equals(targetCave)) return true;
    }
    return false;
  }

  private static boolean smallCaveVisitedTwice(ArrayList<String> currentPath) {
    for (String cave : currentPath) {
      if (isSmallCave(cave) && (Collections.frequency(currentPath, cave) == 2)) {
        return true;
      }
    }
    return false;
  }


  //================================================================================
  // OTHER HELPER FUNCTIONS: clearListFromIndex
  //================================================================================
  // delete all items from an ArrayList until and including startIndex
  private static void clearListFromIndex(ArrayList<String> arr, int startIndex) {
    while (arr.size() != startIndex){
      arr.remove(arr.size()-1);
    }
  }

}
