import java.util.*;

public class SnailNumber {
  private ArrayList<Integer> elements = new ArrayList<Integer>();
  private ArrayList<Integer> depths = new ArrayList<Integer>();

  //================================================================================
  // INIT
  //================================================================================
  public SnailNumber(String raw) {
    int depth = 0;
    for (int i = 0; i < raw.length(); i++) {
      char currentChar = raw.charAt(i);
      if (currentChar == '[') {
        depth++;
      } else if (currentChar == ']') {
        depth--;
      } else if (Character.isDigit(currentChar)) {
        elements.add(Character.getNumericValue(currentChar));
        depths.add(depth);
      }
    }
  }

  //================================================================================
  // SNAILNUMBER ADD METHODS
  //================================================================================
  public void add(SnailNumber s) {
    elements.addAll(s.elements);
    depths.addAll(s.depths);

    for (int i = 0; i < depths.size(); i++) {
      depths.set(i, depths.get(i)+1);
    }

    this.reduce();
  }

  private void reduce() {
    while (this.needsExplode() || this.needsSplit()) {
      this.explode();
      this.split();
    }
  }

  private void explode() {
    for (int i = 0; i < elements.size(); i++) {
      if (depths.get(i) > 4) {
        // record original size
        int s = elements.size();

        // find pair to explode, and remove them
        int e1 = elements.remove(i);
        int e2 = elements.remove(i);
        int depth = depths.remove(i);
        depths.remove(i);

        // add left
        if (i != 0) {
          elements.set(i-1, elements.get(i-1)+e1);
        }

        // add right
        if (i != s-2) {
          elements.set(i, elements.get(i)+e2);
        }

        // add 0
        elements.add(i, 0);
        depths.add(i, depth-1);
      }
    }
  }

  private boolean split() { // boolean so it can terminate and check for explosions after one split
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i) >= 10) {
        // find number to split, then remove
        int currentElement = elements.remove(i);
        int currentDepth = depths.remove(i);

        elements.add(i, currentElement/2);
        if (currentElement % 2 == 0) {
          elements.add(i+1, currentElement/2);
        } else {
          elements.add(i+1, currentElement/2+1);
        }

        depths.add(i, currentDepth+1);
        depths.add(i, currentDepth+1);

        return true;
      }
    }
    return false;
  }

  private boolean needsExplode() {
    for (int depth : depths) {
      if (depth > 4) return true;
    }
    return false;
  }

  private boolean needsSplit() {
    for (int element : elements) {
      if (element >= 10) return true;
    }
    return false;
  }

  //================================================================================
  // RETURN MAGNITUDE
  //================================================================================
  public int getMagnitude() {
    ArrayList<Integer> e = new ArrayList<Integer>();
    ArrayList<Integer> d = new ArrayList<Integer>();
    e.addAll(elements);
    d.addAll(depths);

    for (int depth = 4; depth > 0; depth--) {
      for (int i = 0; i < e.size(); i++) {
        if (d.get(i) == depth && d.get(i) == d.get(i+1)) {
          int e1 = e.remove(i);
          int e2 = e.remove(i);
          d.remove(i);
          d.remove(i);

          int currentMag = 3 * e1 + 2 * e2;
          e.add(i, currentMag);
          d.add(i, depth-1);
          i--;
        }
      }
    }

    return e.get(0);
  }

  //================================================================================
  // VALUE RETURNERS
  //================================================================================
  public String toString() {
    return "=====\n" + elements.toString() + "\n" + depths.toString() + "\n=====";
  }
}
