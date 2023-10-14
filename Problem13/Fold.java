import java.util.*;

public class Fold {
  private char[][] paper;

  //================================================================================
  // INIT
  //================================================================================
  public Fold(ArrayList<String> coords, int initialX, int initialY) {
    paper = new char[initialY][initialX];
    for (int y = 0; y < paper.length; y++) {
      for (int x = 0; x < paper[y].length; x++) {
        paper[y][x] = '.';
      }
    }
    // map coordinates
    for (String coord : coords) {
      String[] coordArray = coord.split(",");
      int x = Integer.parseInt( coordArray[0] );
      int y = Integer.parseInt( coordArray[1] );
      paper[y][x] = '#';
    }
  }

  //================================================================================
  // FOLDLINE METHODS
  //================================================================================
  public void foldLine(String line) {
    char lineType = line.charAt(0);
    int lineVal = Integer.parseInt( line.substring(2) );
    if (lineType == 'y') {
      foldLineHorizontal(lineVal);
    } else if (lineType== 'x') {
      foldLineVertical(lineVal);
    }
  }
  private void foldLineHorizontal(int yLine) {
    int verticalLength = paper.length;
    int horizontalLength = paper[0].length;
    char[][] foldedPaper = new char[yLine][horizontalLength];
    for (int y = 0; y < yLine; y++) {
      for (int x = 0; x < horizontalLength; x++) {
        if (paper[y][x] == '#' || paper[verticalLength-1-y][x] == '#') {
          foldedPaper[y][x] = '#';
        } else {
          foldedPaper[y][x] = '.';
        }
      }
    }
    paper = foldedPaper;
  }
  private void foldLineVertical(int xLine) {
    int verticalLength = paper.length;
    int horizontalLength = paper[0].length;
    char[][] foldedPaper = new char[verticalLength][xLine];
    for (int y = 0; y < verticalLength; y++) {
      for (int x = 0; x < xLine; x++) {
        if (paper[y][x] == '#' || paper[y][horizontalLength-1-x] == '#') {
          foldedPaper[y][x] = '#';
        } else {
          foldedPaper[y][x] = '.';
        }
      }
    }
    paper = foldedPaper;
  }

  //================================================================================
  // NUMVISIBLEDOTS
  //================================================================================
  public int numVisibleDots() {
    int count = 0;
    for (int y = 0; y < paper.length; y++) {
      for (int x = 0; x < paper[y].length; x++) {
        if (paper[y][x] == '#') count++;
      }
    }
    return count;
  }

  //================================================================================
  // TOSTRING
  //================================================================================
  public String toString(){
    String result = "";
    for (int y = 0; y < paper.length; y++) {
      for (int x = 0; x < paper[y].length; x++) {
        result += paper[y][x];
      }
      result += "\n";
    }
    return result;
  }
}
