import java.io.*;
import java.util.*;

public class Problem17 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    // set up input
    Scanner input = new Scanner(new File("input.txt"));
    String line = input.nextLine();
    String[] raw = line.split(" ");

    int[] xRange = prepareMaxRange(raw[2], "x");
    int[] yRange = prepareMaxRange(raw[3], "y");

    // find answers
    int[] results = projectiles(xRange, yRange);

    // return answers
    System.out.println("Part 1: " + results[0]);
    System.out.println("Part 2: " + results[1]);
  }



  private static int[] projectiles(int[] xRange, int[] yRange) {
    int xPos = 0;
    int yPos = 0;

    // initial velocities to test, increments up
    int initialXVelocity = 0;
    int initialYVelocity = -250;

    int maxHeight = 0;
    int workingVelocities = 0;

    while (initialXVelocity < 250) {
      while (initialYVelocity < 250) {
        int currentXVelocity = initialXVelocity;
        int currentYVelocity = initialYVelocity;
        int currentMaxHeight = 0;
        xPos = 0;
        yPos = 0;

        while (xPos <= xRange[1] && yPos >= yRange[0]) {
          xPos += currentXVelocity;
          yPos += currentYVelocity;
          if (currentXVelocity > 0) currentXVelocity--;
          currentYVelocity--;

          currentMaxHeight = Math.max(currentMaxHeight, yPos);

          if (xPos >= xRange[0] && xPos <= xRange[1] && yPos <= yRange[1] && yPos >= yRange[0]) {
            workingVelocities++;
            maxHeight = Math.max(maxHeight, currentMaxHeight);
            // prevent overcounting of workingVelocities by making answer out of bounds
            xPos = xRange[1] + 1;
          }
        }
        initialYVelocity++;
      }
      initialXVelocity++;
      initialYVelocity = -250;
    }

    return new int[]{maxHeight, workingVelocities};
  }

  private static int[] prepareMaxRange(String raw, String coord) {
    raw = raw.substring(2);
    if (coord.equals("x")) {
      raw = raw.substring(0, raw.length()-1);
    }

    String[] rangeString = raw.split("\\.\\.");
    int[] range = new int[2];
    range[0] = Integer.parseInt(rangeString[0]);
    range[1] = Integer.parseInt(rangeString[1]);

    return range;
  }
}
