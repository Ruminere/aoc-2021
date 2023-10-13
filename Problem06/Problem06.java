import java.io.*;
import java.util.*;

public class Problem06 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Invalid syntax: use \"java Problem06 [filename] [1/2]\".");
      System.exit(1);
    }

    ArrayList<Long> nums = new ArrayList<Long>();
    String mode = args[1];

    try {
      String line;
      File file = new File(args[0]);
      Scanner input = new Scanner(file);
      line = input.nextLine();
      String[] numss = line.split(",");
      for (int i = 0; i < numss.length; i++) {
        nums.add(Long.parseLong(numss[i]));
      }
    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }

    if (mode.equals("1")) {
      System.out.println(countFish(nums, 80));
    } else if (mode.equals("2")) {
      System.out.println(countFish(nums, 256));
    }
  }

  //================================================================================
  // FISH-COUNTING METHODS
  //================================================================================
  private static long countFish(ArrayList<Long> nums, int days) {
    // sort fish into groups of how many days they have left
    long[] c = new long[9]; // count fish by number of days they have left to give birth to new fish
    for (int i = 0; i < c.length; i++) {
      c[i] = countInitialFish(nums, i);
    }

    // count how many fish are in each countdown group
    long c0s;
    for (int d = 1; d <= days; d++) {
      c0s = c[0];
      for (int i = 0; i < c.length-1; i++) {
        c[i] = c[i+1];
      }
      c[6] += c0s; // since they are restarting the cycle
      c[8] = c0s; // new fish
    }

    // count and return total number of fish
    long result = 0;
    for (long count : c) result += count;
    return result;
  }
  private static long countInitialFish(ArrayList<Long> nums, int count) {
    long result = 0;
    for (long num : nums) {
      if (num == count) result++;
    }
    return result;
  }
}
