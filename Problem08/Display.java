import java.util.*;

public class Display {
  //================================================================================
  // STORAGE VALUES
  //================================================================================
  private static String[] trbr; // top right and bottom right
  // key, then the number corresponding to each key
  private static String[] key;
  private static int[] keyNums;
  // the strings for the output
  private static String[] outputString;

  //================================================================================
  // SETUP
  //================================================================================
  public Display(String[] pattern) {
    // init key and outputString arrays
    key = new String[10];
    for (int i = 0; i < 10; i++) {
      key[i] = stringAlphabeticalSort(pattern[i]);
    }
    outputString = new String[4];
    for (int i = 0; i < 4; i++) {
      outputString[i] = stringAlphabeticalSort(pattern[i+11]);
    }

    // start filling the key out for 1, 4, 7, 8, and find the string for 1
    String one = "";
    keyNums = new int[10];
    for (int i = 0; i < 10; i++) {
      if (key[i].length() == 2) {
        one = key[i];
        keyNums[i] = 1;
      } else if (key[i].length() == 4) {
        keyNums[i] = 4;
      } else if (key[i].length() == 3) {
        keyNums[i] = 7;
      } else if (key[i].length() == 7) {
        keyNums[i] = 8;
      } else {
        keyNums[i] = -1;
      }
    }

    // find all the strings of lengths 5 and 6
    ArrayList<String> length5 = new ArrayList<String>();
    ArrayList<String> length6 = new ArrayList<String>();
    for (String code : key) {
      if (code.length() == 5) {
        length5.add(code);
      } else if (code.length() == 6) {
        length6.add(code);
      }
    }

    // run helper functions
    find6_tr_br(length6, one);
    String three = find2_3_5(length5, trbr);
    find0_9(length6, three);
  }


  //================================================================================
  // SETUP HELPER FUNCTIONS
  //================================================================================

  // compare strings of length 6 to the string for 1, and find 6, top right character, and bottom right character
  private static void find6_tr_br(ArrayList<String> length6, String one) {
    // find 6
    String six = "";
    for (String code : length6) {
      if (!hasAllCharacters(code, one)) {
        six = code;
      }
    }
    length6.remove(six);

    // register 6
    for (int i = 0; i < 10; i++) {
      if (key[i].equals(six)) {
        keyNums[i] = 6;
      }
    }

    // differentiate tr, br
    String first = one.substring(0,1);
    String second = one.substring(1,2);
    if (six.contains(first)) {
      trbr = new String[]{second, first};
    } else {
      trbr = new String[]{first, second};
    }
  }

  // use tr and br to differentiate strings of length 5 into 2, 3, and 5, then return the string for 3
  private static String find2_3_5(ArrayList<String> length5, String[] trbr) {
    String two = "";
    String three = "";
    String five = "";

    // find 2, 3, 5
    for (String code : length5) {
      if (code.contains(trbr[0]) && !code.contains(trbr[1])) {
        two = code;
      } else if (code.contains(trbr[0]) && code.contains(trbr[1])) {
        three = code;
      } else if (!code.contains(trbr[0]) && code.contains(trbr[1])) {
        five = code;
      }
    }

    // register 2, 3, 5
    for (int i = 0; i < 10; i++) {
      if (key[i].equals(two)) {
        keyNums[i] = 2;
      } else if (key[i].equals(three)) {
        keyNums[i] = 3;
      } else if (key[i].equals(five)) {
        keyNums[i] = 5;
      }
    }

    return three;
  }

  // use the string for 3 to differentiate the remaining two strings of length 6 into 0 and 9
  private static void find0_9(ArrayList<String> length6, String three) {
    String zero = "";
    String nine = "";

    // find 0, 9
    for (String code : length6) {
      if (!hasAllCharacters(code, three)) {
        zero = code;
      } else {
        nine = code;
      }
    }

    // register 0, 9
    for (int i = 0; i < 10; i++) {
      if (key[i].equals(zero)) {
        keyNums[i] = 0;
      } else if (key[i].equals(nine)) {
        keyNums[i] = 9;
      }
    }
  }

  //================================================================================
  // ACCESSOR FUNCTIONS
  //================================================================================
  public String getKey() {
    String keyString = "";
    for (int i = 0; i < key.length; i++) {
      keyString += key[i] + ": " + keyNums[i] + "\n";
    }
    return keyString;
  }

  public int output() {
    String output = "";
    for (String str : outputString) {
      output += findStringNum(str);
    }
    return Integer.parseInt(output);
  }


  //================================================================================
  // OTHER HELPER FUNCTIONS: stringAlphabeticalSort, hasAllCharacters, findStringNum
  //================================================================================
  private static String stringAlphabeticalSort(String str) {
    char[] strArray = str.toCharArray();
    Arrays.sort(strArray);
    return new String(strArray);
  }
  private static boolean hasAllCharacters(String str, String characters) {
    for (int i = 0; i < characters.length(); i++) {
      String currentChar = characters.substring(i,i+1);
      if (!str.contains(currentChar)) return false;
    }
    return true;
  }
  private static int findStringNum(String str) {
    for (int i = 0; i < 10; i++) {
      if (key[i].equals(str)) {
        return keyNums[i];
      }
    }
    return -1;
  }
}
