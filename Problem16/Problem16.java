import java.io.*;
import java.util.*;

public class Problem16 {
  private static int i = 0;
  private static int versionNumberSum = 0; // part 1

  //================================================================================
  // CODE RUNNER
  //================================================================================
  public static void main(String[] args) throws IOException {
    // init
    Scanner input = new Scanner(new File("input.txt"));
    String hex = input.nextLine();
    String raw = "";
    for (int i = 0; i < hex.length(); i++) {
      raw += hexToBinary(hex.substring(i,i+1));
    }

    // find answers
    long code = parsePackets(raw);

    // return answers
    System.out.println("Part 1: " + versionNumberSum);
    System.out.println("Part 2: " + code);
  }

  //================================================================================
  // PARSING THE MESSAGE
  //================================================================================
  private static long parsePackets(String raw) {
    int version;
    int packetTypeID;

    version = Integer.parseInt(raw.substring(i,i+3), 2);
    i += 3;
    versionNumberSum += version;

    packetTypeID = Integer.parseInt(raw.substring(i,i+3), 2);
    i += 3;

    if (packetTypeID == 4) { // literal
      String binaryNum = "";
      boolean end = false;
      while (!end) {
        if (raw.charAt(i) == '0') {
          end = true;
        }
        binaryNum += raw.substring(i+1, i+5);
        i += 5;
      }
      return Long.parseLong(binaryNum, 2);

    } else { // operator
      int lengthTypeID = Integer.parseInt(raw.substring(i,i+1));
      i += 1;

      ArrayList<Long> subpacketVals = new ArrayList<Long>();

      // get subpackets
      if (lengthTypeID == 0) {
        int lengthSubpackets = Integer.parseInt(raw.substring(i,i+15), 2);
        i += 15;

        int endIndex = i + lengthSubpackets;
        while (i < endIndex) {
          subpacketVals.add(parsePackets(raw));
        }

      } else if (lengthTypeID == 1) {
        int totalSubpackets = Integer.parseInt(raw.substring(i,i+11), 2);
        i += 11;

        int currentSubpacket = 0;
        while (currentSubpacket < totalSubpackets) {
          subpacketVals.add(parsePackets(raw));
          currentSubpacket++;
        }
      }

      // calcluate values
      if (packetTypeID == 0) {
        long sum = 0;
        for (long val : subpacketVals) sum += val;
        return sum;

      } else if (packetTypeID == 1) {
        long product = 1;
        for (long val : subpacketVals) product *= val;
        return product;

      } else if (packetTypeID == 2) {
        Collections.sort(subpacketVals);
        return subpacketVals.get(0);

      } else if (packetTypeID == 3) {
        Collections.sort(subpacketVals, Collections.reverseOrder());
        return subpacketVals.get(0);

      } else { // 5, 6, 7
        long first = subpacketVals.get(0);
        long second = subpacketVals.get(1);

        long result = 0L;
        if (packetTypeID == 5) {
          result = (first > second) ? 1L : 0L;
        } else if (packetTypeID == 6) {
          result = (first < second) ? 1L : 0L;
        } else if (packetTypeID == 7) {
          result = (first == second) ? 1L : 0L;
        }

        return result;
      }
    }
  }

  //================================================================================
  // CONVERT A HEXADECIMAL CHARACTER TO BINARY
  //================================================================================
  private static String hexToBinary(String hexChar) throws IllegalArgumentException {
    String[] hex = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    String[] binary = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    for (int i = 0; i < hex.length; i++) {
      if (hex[i].equals(hexChar)) {
        return binary[i];
      }
    }
    throw new IllegalArgumentException("Invalid character: " + hexChar);
  }
}
