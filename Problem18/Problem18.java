import java.io.*;
import java.util.*;

public class Problem18 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    //init (uses raw because there's no clone method for SnailNumbers)
    Scanner input = new Scanner(new File("input.txt"));
    ArrayList<String> raw = new ArrayList<String>();
    while (input.hasNextLine()) {
      raw.add(input.nextLine());
    }


    // return answers
    System.out.println("Part 1: " + totalSum(raw));
    System.out.println("Part 2: " + biggestDoubleSum(raw));
  }

  //================================================================================
  // PART-SPECIFIC HELPER FUNCTIONS
  //================================================================================
  private static int totalSum(ArrayList<String> raw) {
    SnailNumber sum = new SnailNumber(raw.get(0));
    for (int i = 1; i < raw.size(); i++) {
      sum.add(new SnailNumber(raw.get(i)));
    }
    return sum.getMagnitude();
  }

  private static int biggestDoubleSum(ArrayList<String> raw) {
    int biggestSum = 0;
    for (int i = 0; i < raw.size(); i++) {
      for (int j = 0; j < raw.size(); j++) {
        SnailNumber s1 = new SnailNumber(raw.get(i));
        SnailNumber s2 = new SnailNumber(raw.get(j));
        s1.add(s2);
        biggestSum = Math.max(biggestSum, s1.getMagnitude());
      }
    }
    return biggestSum;
  }
}
