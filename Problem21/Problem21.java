import java.io.*;
import java.util.*;

public class Problem21 {
  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    // init
    Scanner input = new Scanner(new File("input.txt"));
    int[] startPos = new int[2];
    for (int i = 0; i < 2; i++) {
      startPos[i] = Integer.parseInt( input.nextLine().split(" ")[4] );
    }

    // find answers
    int part1 = part1(startPos);
    long[] results = winTimes(new int[]{0, startPos[1] * -1}, startPos, 1, 0);

    // return answers
    System.out.println("Part 1: " + part1);
    System.out.println("Part 2: " + Math.max(results[0], results[1]));
  }

  //================================================================================
  // RECURSIVE PART 2
  //================================================================================
  private static long[] winTimes(int[] scores, int[] pos, int turn, int currentRoll) {
    // setup, increments throughout the recursion
    long[] wins = new long[]{0,0};

    // move the player and check the scores
    pos[turn] = (pos[turn] + currentRoll - 1) % 10 + 1;
    scores[turn] += pos[turn];

    // check if any of the scores are 21
    if (scores[0] >= 21) {
      return new long[]{1L, 0L};
    } else if (scores[1] >= 21) {
      return new long[]{0L, 1L};
    }

    // big list of all possible sums and how many ways there are to get each
    int[][] rolls = new int[][]{{3, 1}, {4, 3}, {5, 6}, {6, 7}, {7, 6}, {8, 3}, {9, 1}};

    // below: setup for recursion, then do recursion itself, then increment the win count and
    // multiply it by the frequency of the roll

    // find out whose turn it is next
    turn = (turn == 0) ? 1 : 0;

    // go through each possible sum
    for (int[] nextRoll : rolls) {
      // clean for each iteration
      int newScores[] = new int[2];
      int newPos[] = new int[2];
      for (int i = 0; i < 2; i++) {
        newScores[i] = scores[i];
        newPos[i] = pos[i];
      }

      // recursion
      long[] results = winTimes(newScores, newPos, turn, nextRoll[0]);
      wins[0] += results[0] * nextRoll[1];
      wins[1] += results[1] * nextRoll[1];
    }
    return wins;
  }

  //================================================================================
  // NAIVE PART 1
  //================================================================================
  private static int part1(int[] startPos) {
    Player p1 = new Player(startPos[0]);
    Player p2 = new Player(startPos[1]);

    int playerTurn = 1;
    int rollCount = 0;
    while (p1.getScore() < 1000 && p1.getScore() < 1000) {
      for (int i = 1; i <= 3; i++) {
        rollCount++;
        int currentRoll = (rollCount - 1) % 100 + 1;

        if (playerTurn == 1) p1.move(currentRoll);
        if (playerTurn == 2) p2.move(currentRoll);
      }
      if (playerTurn == 1) p1.addScore();
      if (playerTurn == 2) p2.addScore();

      playerTurn = (playerTurn == 1) ? 2 : 1;
    }
    return Math.min(p1.getScore(), p2.getScore()) * rollCount;
  }
}
