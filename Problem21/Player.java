public class Player {
  private int score;
  private int position;

  public Player(int startPosition) {
    score = 0;
    position = startPosition;
  }

  public void move(int roll) {
    position = (position + roll - 1) % 10 + 1;
  }

  public void addScore() {
    score += position;
  }

  public int getScore() {
    return score;
  }

  public int getPosition() {
    return position;
  }
}
