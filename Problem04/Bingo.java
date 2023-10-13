import java.util.Arrays;

public class Bingo {
  //================================================================================
  // SETUP
  //================================================================================
  private int[][] board;
  private boolean[][] status; // whether the number has been called or not, true is called

  public Bingo(int[] nums) {
    board = new int[5][5];
    // inefficient but can't come up with a better way
    board[0][0] = nums[0];
    board[0][1] = nums[1];
    board[0][2] = nums[2];
    board[0][3] = nums[3];
    board[0][4] = nums[4];
    board[1][0] = nums[5];
    board[1][1] = nums[6];
    board[1][2] = nums[7];
    board[1][3] = nums[8];
    board[1][4] = nums[9];
    board[2][0] = nums[10];
    board[2][1] = nums[11];
    board[2][2] = nums[12];
    board[2][3] = nums[13];
    board[2][4] = nums[14];
    board[3][0] = nums[15];
    board[3][1] = nums[16];
    board[3][2] = nums[17];
    board[3][3] = nums[18];
    board[3][4] = nums[19];
    board[4][0] = nums[20];
    board[4][1] = nums[21];
    board[4][2] = nums[22];
    board[4][3] = nums[23];
    board[4][4] = nums[24];

    status = new boolean[5][5];
    for (int i = 0; i < status.length; i++) {
      for (int j = 0; j < status[i].length; j++) {
        status[i][j] = false;
      }
    }
  }

  //================================================================================
  // ACCESSORS/MUTATORS
  //================================================================================

  public void callNumber(int draw) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == draw) {
          status[i][j] = true;
        }
      }
    }
  }

  public boolean hasWon() {
    // horizontal
    for (int i = 0; i < 5; i++) {
      if (isAllTrue(status[i])) return true;
    }

    // vertical
    for (int j = 0; j < 5; j++) {
      boolean[] bv = new boolean[]{status[0][j], status[1][j], status[2][j], status[3][j], status[4][j]};
      if (isAllTrue(bv)) return true;
    }

    // diagonal
    //boolean bd1[] = new boolean[]{status[0][0], status[1][1], status[2][2], status[3][3], status[4][4]};
    //boolean bd2[] = new boolean[]{status[4][0], status[3][1], status[2][2], status[1][3], status[0][4]};
    //if (isAllTrue(bd1) || isAllTrue(bd2)) return true;

    return false;
  }
  private static boolean isAllTrue(boolean[] b) {
    for (int i = 0; i < b.length; i++) {
      if (!b[i]) return false;
    }
    return true;
  }

  // calculate the score of the winning board
  public int calcScore(int lastDrawed) {
    int sumUnmarked = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (!status[i][j]) {
          sumUnmarked += board[i][j];
        }
      }
    }
    return (sumUnmarked * lastDrawed);
  }

  // print the board and the status of each number
  public String toString() {
    String b = "==============================";
    b += "\nBoard Numbers:\n";
    for (int i = 0; i < board.length; i++) {
      b += (board[i][0] + " " + board[i][1] + " " + board[i][2] + " " + board[i][3] + " " + board[i][4] + "\n");
    }
    b += "\nBoard Status:\n";
    for (int i = 0; i < status.length; i++) {
      b += (status[i][0] + " " + status[i][1] + " " + status[i][2] + " " + status[i][3] + " " + status[i][4] + "\n");
    }
    return b + "==============================";
  }
}
