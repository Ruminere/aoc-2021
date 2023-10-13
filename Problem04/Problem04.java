import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem04 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    ArrayList<Bingo> boards = new ArrayList<Bingo>();
    int[] draw;
    boolean debug = true;

    try {
      String line;
      File file = new File(args[0]);
      Scanner input = new Scanner(file);

      // initialize drawing numbers
      String[] draww = input.nextLine().split(",");
      draw = new int[draww.length];
      for (int i = 0; i < draww.length; i++) {
        draw[i] = Integer.parseInt(draww[i]);
      }

      // initialize boards
      while (input.hasNextInt()) {
        int[] nums = new int[25];
        for (int i = 0; i < 25; i++) {
          nums[i] = input.nextInt();
        }
        boards.add(new Bingo(nums));
      }


      // call out numbers
      try {
        String mode = args[1];
        if (mode.equals("1")) { //==============================================
          boolean won = false;
          int drawNum = 0;
          while (!won) {
            for (int i = 0; i < boards.size(); i++) {
              boards.get(i).callNumber( draw[drawNum] );
              if (boards.get(i).hasWon()) {
                won = true;
                System.out.println(boards.get(i).calcScore( draw[drawNum] ));
              }
            }
            drawNum++;
          }
        } else if (mode.equals("2")) { //=======================================
          // find the last board
          boolean[] boardWon = new boolean[boards.size()];
          for (int i = 0; i < boardWon.length; i++) {
            boardWon[i] = false;
          }

          int drawNum = 0;
          while (!allButOneTrue(boardWon)) {
            for (int i = 0; i < boards.size(); i++) {
              boards.get(i).callNumber( draw[drawNum] );
              if (boards.get(i).hasWon()) {
                boardWon[i] = true;
              }
            }
            drawNum++;
          }

          // calculate score of the last board
          Bingo lastBoard = boards.get( findFalse(boardWon) );
          boolean won = false;
          while (!won) {
            lastBoard.callNumber( draw[drawNum] );
            if (lastBoard.hasWon()) {
              won = true;
              System.out.println(lastBoard.calcScore( draw[drawNum] ));
            }
            drawNum++;
          }
        } else { //=============================================================
          System.out.println(-1);
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Invalid syntax: use \"java Problem03 [filename] [1/2]\".");
      }


    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }
  }

  //================================================================================
  // HELPER METHODS (PART 2)
  //================================================================================
  private static boolean allButOneTrue(boolean[] b) {
    int falseCount = 0;
    for (int i = 0; i < b.length; i++) {
      if (!b[i]) falseCount++;
    }
    return (falseCount == 1);
  }
  private static int findFalse(boolean[] b) {
    for (int i = 0; i < b.length; i++) {
      if (!b[i]) return i;
    }
    return -1;
  }
}
