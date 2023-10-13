import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem03 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    ArrayList<String> binary = new ArrayList<String>();

    try {
      File file = new File(args[0]);
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        binary.add(input.nextLine());
      }
      input.close();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }

    try {
      String mode = args[1];
      if (mode.equals("1")) {
        String[] ge = gammaEpsilon(binary);
        System.out.println(Integer.parseInt(ge[0],2)*Integer.parseInt(ge[1],2));
      } else if (mode.equals("2")) {
        String oxygen = oxygen(binary);
        String carbon = carbon(binary);
        System.out.println(Integer.parseInt(oxygen,2)*Integer.parseInt(carbon,2));
      } else {
        System.out.println(-1);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid syntax: use \"java Problem03 [filename] [1/2]\".");
    }
  }

  //================================================================================
  // gammaEpsilon(): make strings with the most (gamma) and least (epsilon) used bit
  //================================================================================
  private static String[] gammaEpsilon(ArrayList<String> nums) {
    int[] num1s = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};

    for (int i = 0; i < nums.size(); i++) {
      for (int j = 0; j < nums.get(i).length(); j++) {
        if (nums.get(i).charAt(j) == '1') num1s[j]++;
      }
    }

    String gamma = "";
    String epsilon = "";
    for (int i = 0; i < num1s.length; i++) {
      if (num1s[i] > nums.size()/2) {
        gamma += "1";
        epsilon += "0";
      } else if (num1s[i] < nums.size()/2 || (num1s[i] == nums.size()/2 && nums.size()%2 != 0)) {
        gamma += "0";
        epsilon += "1";
      } else {
        gamma += "2";
        epsilon += "2";
      }
    }

    String[] result = new String[2];
    result[0] = gamma;
    result[1] = epsilon;

    return (result);
  }

  //================================================================================
  // PART 2 SPECIFIC METHODS: oxygen() and carbon()
  //================================================================================
  private static String oxygen(ArrayList<String> numss) {
    ArrayList<String> nums=new ArrayList<>(numss);
    int pos = 0;
    char greatest;
    String gamma;
    while (nums.size() > 1) {
      gamma = gammaEpsilon(nums)[0];
      greatest = gamma.charAt(pos);
      if (greatest == '2') greatest = '1';
      for (int i = 0; i < nums.size(); i++) {
        if (nums.get(i).charAt(pos) != greatest) {
          nums.remove(i);
          i--;
        }
      }
      pos++;
    }
    return (nums.get(0));
  }

  private static String carbon(ArrayList<String> numss) {
    ArrayList<String> nums=new ArrayList<>(numss);
    int pos = 0;
    char least;
    String epsilon;
    while (nums.size() > 1) {
      epsilon = gammaEpsilon(nums)[1];
      least = epsilon.charAt(pos);
      if (least == '2') least = '0';
      for (int i = 0; i < nums.size(); i++) {
        if (nums.get(i).charAt(pos) != least) {
          nums.remove(i);
          i--;
        }
      }
      pos++;
    }
    return (nums.get(0));
  }

}
