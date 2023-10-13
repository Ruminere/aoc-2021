import java.io.*;
import java.util.*;

public class Problem07 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    if (args.length < 2 || !(args[1].equals("1") || args[1].equals("2"))) {
      System.err.println("Invalid syntax: use \"java Problem07 [filename] [1/2]\".");
      System.exit(1);
    }

    int[] nums;
    String mode = args[1];

    try {
      String line;
      File file = new File(args[0]);
      Scanner input = new Scanner(file);

      line = input.nextLine();
      String[] numss = line.split(",");
      nums = new int[numss.length];
      for (int i = 0; i < numss.length; i++) {
        nums[i] = Integer.parseInt(numss[i]);
      }

      System.out.println(findLeastSum(nums, mode));

    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }
  }

  //================================================================================
  // findLeastSum + helper methods
  //================================================================================
  private static int findLeastSum(int[] nums, String mode) {
    Arrays.sort(nums);
    if (mode.equals("1")) { //==================================
      int pos = findMedian(nums);
      int result = 0;
      for (int i = 0; i < nums.length; i++) {
        result += Math.abs(nums[i] - pos);
      }
      return result;
    } else if (mode.equals("2")) { //===========================
      int[] pos = findMean(nums);
      int[] result = {0,0};
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < nums.length; j++) {
          result[i] += triangularNumber(nums[j], pos[i]);
        }
      }
      return Math.min(result[0],result[1]);
    } else { //=================================================
      return -1;
    }
  }
  // for part 1
  private static int findMedian(int[] nums) {
    int midIndex = nums.length/2;
    return nums[midIndex];
  }
  // for part 2
  private static int[] findMean(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    int floor = sum / nums.length;
    int[] means = {floor, floor+1}; // second value is ceiling
    return means;
  }
  private static int triangularNumber(int mean, int pos) {
    int n = Math.abs(mean - pos);
    return (n * (n+1)) / 2;
  }
}
