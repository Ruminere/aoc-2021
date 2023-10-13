import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem02 {
  //================================================================================
  // TESTER
  //================================================================================
  public static void main(String[] args) {
    int horizontal = 0;
    int depth = 0;
    int aim = 0;

    try {
      String line;
      File file = new File(args[0]);
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        line = input.nextLine();
        String[] data = line.split(" ");

        try {

          String mode = args[1];
          if (mode.equals("1")) {
            if (data[0].equals("forward")) {
              horizontal += Integer.parseInt(data[1]);
            } else if (data[0].equals("up")) {
              depth -= Integer.parseInt(data[1]);
            } else if (data[0].equals("down")) {
              depth += Integer.parseInt(data[1]);
            }
          } else if (mode.equals("2")) {
            if (data[0].equals("forward")) {
              horizontal += Integer.parseInt(data[1]);
              depth += Integer.parseInt(data[1]) * aim;
            } else if (data[0].equals("up")) {
              aim -= Integer.parseInt(data[1]);
            } else if (data[0].equals("down")) {
              aim += Integer.parseInt(data[1]);
            }
          }

        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Invalid syntax: use \"java Problem02 [filename] [1/2]\".");
        }
      }
      input.close();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found!");
    }

    System.out.println(horizontal*depth);
  }
}
