package facebook.mediumplatform;

/**
 * https://medium.com/javascript-in-plain-english/facebook-coding-interview-questions-9e40bdbbec35
 */

import com.sun.tools.javac.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Given the mapping a = 1, b = 2, … z = 26, and an encoded message, count the number of ways it can be decoded.
 * For example, the message ‘111’ would give 3, since it could be decoded as ‘aaa’, ‘ka’, and ‘ak’.
 * You can assume that the messages are decodable. For example, ‘001’ is not allowed.
 */
public class Encoding {

    public static void main(String[] args) throws IOException {
        String[] testCases = {"", "1", "11", "111", "1111", "12", "123", "1234", "1a"};

        for (String data : testCases) {
            int noOfCombination = 0;
            boolean isDecodable = checkIsDecodable(data);
            if (!isDecodable) {
                System.out.println("For :- " + data + ", Not Decodable");
            } else {
                if (data.length() != 0) {
                    noOfCombination = noOfDecodableCombination(data);
                }
                System.out.println("For :- " + data + " ,combination :- " + noOfCombination);
            }
        }
    }

    private static int noOfDecodableCombination(String data) {
        int noOfCombination = 0;
        int messageLength = data.length();
        if (messageLength <= 1) {
            noOfCombination = 1;
        } else {
            int prefix = Integer.parseInt(data.substring(0, 2));
            if (prefix > 0 && prefix <= 26) { //This case is required for "1234", since 34 is decodable;
                noOfCombination = noOfDecodableCombination(data.substring(1, messageLength))
                        + noOfDecodableCombination(data.substring(2, messageLength));
            }
            else {
                noOfCombination = noOfDecodableCombination(data.substring(1, messageLength));
            }
        }
        // handles the case if message length is 0;
        return noOfCombination;
    }

    private static boolean checkIsDecodable(String data) {
        for (int i = 0; i < data.length(); ++i) {
            try {
                int val = Integer.parseInt(String.valueOf(data.charAt(i)));
                if (val == 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}

