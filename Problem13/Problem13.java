import java.io.*;
import java.util.*;

public class Problem13 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    // init
    ArrayList<String> coords = new ArrayList<String>();
    ArrayList<String> folds = new ArrayList<String>();

    Scanner input = new Scanner(new File("input.txt"));
    String line;
    String[] lineWords;
    boolean addFolds = false;
    while (input.hasNextLine()) {
      line = input.nextLine();
      if (!addFolds) {
        if (line.equals("")) {
          addFolds = true;
        } else {
          coords.add(line);
        }
      } else {
        lineWords = line.split(" ");
        folds.add(lineWords[2]);
      }
    }

    String first = folds.get(0);
    String second = folds.get(1);
    int initialX;
    int initialY;
    if (first.charAt(0) == 'x') {
      initialX = Integer.parseInt( first.substring(2) ) * 2 + 1;
      initialY = Integer.parseInt( second.substring(2) ) * 2 + 1;
    } else {
      initialX = Integer.parseInt( second.substring(2) ) * 2 + 1;
      initialY = Integer.parseInt( first.substring(2) ) * 2 + 1;
    }

    Fold paper = new Fold(coords, initialX, initialY);


    // fold
    int part1 = 0;
    int foldCount = 0;
    for (String currentFold : folds) {
      paper.foldLine(currentFold);
      foldCount++;
      if (foldCount == 1) {
        part1 = paper.numVisibleDots();
      }
    }


    // return answers
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: \n" + paper);
  }
}
