import java.io.*;
import java.util.*;

public class Problem10 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(new File("input.txt"));
    ArrayList<String> lines = new ArrayList<String>();

    // run part 1 (discarding lines + adding points) while setting up part 2 (saving incomplete lines)
    long points = 0;
    while (input.hasNextLine()) {
      String current = input.nextLine();
      long addPoints = checkCorrupted(current);
      if (addPoints == 0) {
        lines.add(current); // only add the non-corrupted lines
      }
      points += addPoints;
    }

    // run part 2
    ArrayList<Long> pointsList = new ArrayList<Long>();
    for (String line : lines) {
      pointsList.add(fixIncorrect(line));
    }
    Collections.sort(pointsList);

    // return answers
    System.out.println("Part 1: " + points);
    System.out.println("Part 2: " + findMedian(pointsList));
  }


  //================================================================================
  // PART-SPECIFIC FUNCTIONS: FINDING CORRUPTED LINES AND FINISHING INCOMPLETE LINES
  //================================================================================
  private static long checkCorrupted(String str) {
    ArrayList<Character> brackets = new ArrayList<Character>();
    char[] chunkStart = {'(', '[', '{', '<'};
    char[] chunkEnd = {')', ']', '}', '>'};
    int[] points = {3, 57, 1197, 25137};

    for (int i = 0; i < str.length(); i++) {
      char current = str.charAt(i);
      if (arrayIndexOf(chunkStart, current) != -1) { // current is a starting bracket
        brackets.add(current);
      } else { // current is an ending bracket
        int chunkIndex = arrayIndexOf(chunkEnd, current);
        if (brackets.get(brackets.size()-1) != chunkStart[chunkIndex]) { // if last starting bracket doesn't correspond with the ending bracket
          return points[chunkIndex];
        } else {
          brackets.remove(brackets.size()-1);
        }
      }
    }

    return 0;
  }
  private static long fixIncorrect(String str) {
    ArrayList<Character> brackets = new ArrayList<Character>();
    char[] chunkStart = {'(', '[', '{', '<'};
    char[] chunkEnd = {')', ']', '}', '>'};
    int[] points = {1, 2, 3, 4};

    for (int i = 0; i < str.length(); i++) {
      char current = str.charAt(i);
      if (arrayIndexOf(chunkStart, current) != -1) { // current is a starting bracket
        brackets.add(current);
      } else { // current is an ending bracket
        brackets.remove(brackets.size()-1); // no need to worry about corruption
      }
    }


    long result = 0;

    for (int i = brackets.size()-1; i >= 0; i--) {
      int chunkIndex = arrayIndexOf(chunkStart, brackets.get(i));
      result = result * 5 + points[chunkIndex];
    }

    return result;
  }


  //================================================================================
  // OTHER HELPER FUNCTIONS: arrayIndexOf and findMedian
  //================================================================================
  private static int arrayIndexOf(char[] chars, char target) {
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == target) return i;
    }
    return -1;
  }
  private static long findMedian(ArrayList<Long> nums) {
    int midIndex = nums.size()/2;
    return nums.get(midIndex);
  }
}
