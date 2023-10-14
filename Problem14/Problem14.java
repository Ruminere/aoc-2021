import java.io.*;
import java.util.*;

public class Problem14 {
  //================================================================================
  // STORAGE VALUES
  //================================================================================
  private static String[] key; // the pair
  private static String[] convert; // the character it converts to
  private static long[] count; // the number each pair occurs
  private static int numPairTypes; // length of the three above arrays
  private static String polymer; // the initial polymer

  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    //===========================================================
    // INIT
    //===========================================================
    // simply get everything from the file first
    ArrayList<String[]> convertRaw = new ArrayList<String[]>();

    Scanner input = new Scanner(new File("input.txt"));
    polymer = input.nextLine();

    String line;
    String[] lineWords;
    while (input.hasNextLine()) {
      line = input.nextLine();
      if (!line.equals("")) {
        convertRaw.add(line.split(" -> "));
      }
    }

    // initialize arrays
    numPairTypes = convertRaw.size();
    key = new String[numPairTypes];
    convert = new String[numPairTypes];
    count = new long[numPairTypes];
    for (int i = 0; i < numPairTypes; i++) {
      key[i] = convertRaw.get(i)[0];
      convert[i] = convertRaw.get(i)[1];
      count[i] = 0;
    }

    // fill out initial number of pairs
    for (int i = 0; i < polymer.length()-1; i++) {
      String pair = polymer.substring(i,i+2);
      for (int j = 0; j < numPairTypes; j++) {
        if (key[j].equals(pair)) {
          count[j]++;
        }
      }
    }

    //===========================================================
    // CODE LOGIC RUNNER
    //===========================================================
    for (int i = 1; i <= 40; i++) {
      fillKey();
      if (i == 10) {
        System.out.println("Part 1: " + findRange());
      } else if (i == 40) {
        System.out.println("Part 2: " + findRange());
      }
    }
  }

  //================================================================================
  // SOLUTION LOGIC
  //================================================================================
  // converts the number of pairs in the converted polymer
  private static void fillKey() {
    long[] newCount = new long[numPairTypes];
    for (int i = 0; i < numPairTypes; i++) {
      newCount[i] = 0;
    }

    for (int i = 0; i < numPairTypes; i++) {
      String newPair1 = key[i].substring(0,1) + convert[i];
      String newPair2 = convert[i] + key[i].substring(1,2);
      for (int j = 0; j < numPairTypes; j++) {
        if (key[j].equals(newPair1) || key[j].equals(newPair2)) {
          newCount[j] += count[i];
        }
      }
    }

    count = newCount;
  }

  // counts twice the frequency of each character, and returns the range
  private static long findRange() {
    char[] c = new char[]{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
    ArrayList<Long> cCount = new ArrayList<Long>();
    for (int i = 0; i < c.length; i++) {
      cCount.add((long)0);
    }

    // count characters (middle characters are counted twice, first and last characters are counted once)
    for (int i = 0; i < numPairTypes; i++) {
      char char1 = key[i].charAt(0);
      char char2 = key[i].charAt(1);
      for (int j = 0; j < c.length; j++) {
        if (char1 != char2 && (char1 == c[j] || char2 == c[j])) { // pair of different characters
          cCount.set(j, cCount.get(j) + count[i]);
        } else if (char1 == char2 && (char1 == c[j])) { // pair of same characters
          cCount.set(j, cCount.get(j) + count[i]*2);
        }
      }
    }

    // add the first two characters
    for (int i = 0; i < cCount.size(); i++) {
      if (cCount.get(i) % 2 == 1) {
        cCount.set(i, cCount.get(i)+1);
      }
    }

    // remove 0s
    Collections.sort(cCount);
    for (int i = 0; cCount.get(i) == 0; i += 0) {
      cCount.remove(0);
    }

    // calculate range
    long least = cCount.get(0);
    long greatest = cCount.get(cCount.size()-1);
    return (greatest - least)/2; // since characters are counted twice
  }
}
