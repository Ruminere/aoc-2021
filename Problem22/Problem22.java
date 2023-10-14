import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Problem22 {
  private static ArrayList<int[]> cuboids = new ArrayList<int[]>();
  // structure of each cuboid array:
  // {multiplier -1 or 1, xMin, xMax, yMin, yMax, zMin, zMax}

  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    // //init
    // Scanner input = new Scanner(new File("input1.txt"));
    // String[] line;
    //
    // int steps = 0;
    // int part1 = 0;
    // while (input.hasNextLine()) {
    //   steps++;
    //
    //   //========================================
    //   // INIT AND RUN FOR EACH LINE
    //   //========================================
    //   line = input.nextLine().split(" ");
    //
    //   String toggle = line[0];
    //
    //   String[] ranges = line[1].split(",");
    //   for (int i = 0; i < ranges.length; i++) {
    //     ranges[i] = ranges[i].substring(2);
    //   }
    //   System.out.println("\nNow Doing: " + Arrays.toString(ranges));
    //
    //   int[] xRange = Stream.of( ranges[0].split("\\.\\.") ).mapToInt(Integer::parseInt).toArray();
    //   int[] yRange = Stream.of( ranges[1].split("\\.\\.") ).mapToInt(Integer::parseInt).toArray();
    //   int[] zRange = Stream.of( ranges[2].split("\\.\\.") ).mapToInt(Integer::parseInt).toArray();
    //
    //   if (toggle.equals("on")) {
    //     addCuboid(xRange[0], xRange[1], yRange[0], yRange[1], zRange[0], zRange[1]);
    //   } else if (toggle.equals("off")) {
    //     subtractCuboid(xRange[0], xRange[1], yRange[0], yRange[1], zRange[0], zRange[1]);
    //   }
    //
    //   System.out.println("Cuboids: ");
    //   for (int[] cuboid : cuboids) {
    //     System.out.println(Arrays.toString(cuboid));
    //   }
    //
    //   if (steps <= 20) part1 = totalVolume();
    // }
    addCuboid(10, 12, 10, 12, 10, 12);
    addCuboid(11, 13, 11, 13, 11, 13);
    subtractCuboid(11, 13, 11, 13, 11, 13);
    System.out.println("Cuboids: ");
    for (int[] cuboid : cuboids) {
      System.out.println(Arrays.toString(cuboid));
    }
    //System.out.println("Part 1: " + part1);
    System.out.println(totalVolume());
  }

  //================================================================================
  // PART-SPECIFIC HELPER FUNCTIONS
  //================================================================================
  private static void addCuboid(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
    System.out.println("Adding: " + xMin + " " + xMax + " " + yMin + " " + yMax + " " + zMin + " " + zMax);
    int originalSize = cuboids.size(); // avoids ConcurrentModificationException
    // add a "deleting" cuboid for the overlap
    for (int i = 0; i < originalSize; i++) {
      int[] cuboid = cuboids.get(i);
      if (cuboid[0] == 1) { // check only for the "on" cuboids, then get rid of overlap
        if (cuboid[1] <= xMin || cuboid[2] >= xMax || cuboid[3] <= yMin || cuboid[4] >= yMax || cuboid[5] <= zMin || cuboid[6] >= zMax) {
          subtractCuboid(Math.max(cuboid[1], xMin), Math.min(cuboid[2], xMax), Math.max(cuboid[3], yMin), Math.min(cuboid[4], yMax), Math.max(cuboid[5], zMin), Math.min(cuboid[6], zMax));
        }
      }
    }
    cuboids.add(new int[]{1, xMin, xMax, yMin, yMax, zMin, zMax});
  }
  private static void subtractCuboid(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
    System.out.println("Subtracting: " + xMin + " " + xMax + " " + yMin + " " + yMax + " " + zMin + " " + zMax);
    int originalSize = cuboids.size(); // avoids ConcurrentModificationException
    // check if any cubes overap with the subtraction, then subtract that overlap
    for (int i = 0; i < originalSize; i++) {
      int[] cuboid = cuboids.get(i);
      if (cuboid[1] <= xMin || cuboid[2] >= xMax || cuboid[3] <= yMin || cuboid[4] >= yMax || cuboid[5] <= zMin || cuboid[6] >= zMax) {
        cuboids.add(new int[]{-1, Math.max(cuboid[1], xMin), Math.min(cuboid[2], xMax), Math.max(cuboid[3], yMin), Math.min(cuboid[4], yMax), Math.max(cuboid[5], zMin), Math.min(cuboid[6], zMax)});
      }
    }
  }


  private static int totalVolume() {
    int result = 0;
    for (int[] cuboid : cuboids) {
      result += (cuboid[0] * (cuboid[2]-cuboid[1]+1) * (cuboid[4]-cuboid[3]+1) * (cuboid[6]-cuboid[5]+1));
    }
    return result;
  }
}
