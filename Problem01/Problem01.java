import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem01 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<Integer>();

    try {
      File file = new File(args[0]);
      Scanner input = new Scanner(file);
      while (input.hasNextInt()) {
        nums.add(input.nextInt());
      }
      input.close();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }

    try {
      String mode = args[1];
      if (mode.equals("1")) {
        System.out.println(findGreaterSingle(nums));
      } else if (mode.equals("2")) {
        System.out.println(findGreaterTriple(nums));
      } else {
        System.out.println(-1);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid syntax: use \"java Problem01 [filename] [1/2]\".");
    }
  }

  //================================================================================
  // PART 1
  //================================================================================
  private static int findGreaterSingle(ArrayList<Integer> nums) {
    int result = 0;
    for (int i = 1; i < nums.size(); i++) {
      if (nums.get(i) > nums.get(i-1)) result++;
    }
    return result;
  }

  //================================================================================
  // PART 2
  //================================================================================
  private static int findGreaterTriple(ArrayList<Integer> nums) {
    int result = 0;
    int sum1, sum2;
    for (int i = 3; i < nums.size(); i++) {
      sum1 = nums.get(i-3)+nums.get(i-2)+nums.get(i-1);
      sum2 = nums.get(i-2)+nums.get(i-1)+nums.get(i);
      if (sum2 > sum1) result++;
    }
    return result;
  }
}
