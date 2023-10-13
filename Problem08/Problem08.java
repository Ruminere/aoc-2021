import java.io.*;
import java.util.*;

public class Problem08 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    System.out.println("Part 1: " + count1478("input.txt"));
    System.out.println("Part 2: " + sumOutput("input.txt"));
  }

  //================================================================================
  // PART 1-SPECIFIC FUNCTION: find the number of 1s, 4s, 7s, AND 8s in the output
  //================================================================================
  private static int count1478(String fileName) throws IOException {
    int result = 0;

    // init and count
    String line;
    Scanner input = new Scanner(new File(fileName));
    while (input.hasNextLine()) {
      String[] pattern = input.nextLine().split(" ");
      String[] output = new String[]{pattern[11], pattern[12], pattern[13], pattern[14]};
      for (String code : output) {
        if (code.length() == 2 || code.length() == 3 || code.length() == 4 || code.length() == 7) {
          result++;
        }
      }
    }

    return result;
  }

  //================================================================================
  // PART 2-SPECIFIC FUNCTION: find the sum of all outputs
  //================================================================================
  private static int sumOutput(String fileName) throws IOException {
    int result = 0;

    // init and count
    Scanner input = new Scanner(new File(fileName));
    int line = 0;
    while (input.hasNextLine()) {
      String[] pattern = input.nextLine().split(" ");
      Display current = new Display(pattern);
      result += current.output();
      line++;
    }

    return result;
  }
}
