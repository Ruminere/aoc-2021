import java.io.*;
import java.util.*;

public class Problem05 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    ArrayList<String> points = new ArrayList<String>();

    try {
      String line;
      File file = new File(args[0]);
      Scanner input = new Scanner(file);
      String mode = args[1];
      while (input.hasNextLine()) {
        line = input.nextLine();
        String[] coords = line.split(" ");
        addPoints(points, coords, mode);
      }
    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }

    System.out.println(countGreaterFrequency(points));
  }

  private static void addPoints(ArrayList<String> points, String[] coords, String mode) {
    String a[] = coords[0].split(",");
    String b[] = coords[2].split(",");

    int ax = Integer.parseInt(a[0]);
    int ay = Integer.parseInt(a[1]);
    int bx = Integer.parseInt(b[0]);
    int by = Integer.parseInt(b[1]);

    if (ax == bx) { // vertical lines
      int i = Math.min(ay,by);
      while (i <= Math.max(ay,by)) {
        points.add(ax + "," + i);
        i++;
      }
    } else if (ay == by) {
      int i = Math.min(ax,bx);
      while (i <= Math.max(ax,bx)) { // horizontal lines
        points.add(i + "," + ay);
        i++;
      }
    } else if (mode.equals("2")) { // diagonal lines
      // strategy: start at x with smaller coord, then for y move up/down accordingly

      // redo ax, bx
      ax = Math.min(Integer.parseInt(a[0]), Integer.parseInt(b[0]));
      bx = Math.max(Integer.parseInt(a[0]), Integer.parseInt(b[0]));
      // redo ay, by
      if (ax == Integer.parseInt(b[0])) ay = Integer.parseInt(b[1]);
      if (bx == Integer.parseInt(a[0])) by = Integer.parseInt(a[1]);

      // add points
      int i = ax;
      int j = ay;
      while (i <= bx) {
        points.add(i + "," + j);
        i++;
        if (ay < by) j++;
        if (ay > by) j--;
      }
    }
  }

  private static int countGreaterFrequency(ArrayList<String> points) {
    Collections.sort(points);

    int result = 0;

    String current = points.get(0);
    int count = 0;
    for (int i = 0; i <= points.size(); i++) {
      if (i != points.size() && current.equals( points.get(i) )) { // count same number of Strings in a row, check that it's not the last element
        count++;
      } else { // end at different String, add to result as necessary, start the count over with the new String
        if (count >= 2) result++;
        if (i != points.size()) { // use new String if not at end of list
          current = points.get(i);
          count = 1; // not 0 since that would undercount by 1
        }
      }
    }

    return result;
  }
}
