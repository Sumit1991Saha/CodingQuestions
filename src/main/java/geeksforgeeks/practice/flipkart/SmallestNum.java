package geeksforgeeks.practice.flipkart;

import java.util.ArrayList;
import java.util.Arrays;

public class SmallestNum {

    public static void main(String[] args) {
        System.out.println(smallestnum("55010"));

    }

    public static String smallestnum(String N) {
        char[] numberArray = N.toCharArray();
        Arrays.sort(numberArray);
        int i = 0;
        for (; i < numberArray.length; ++i) {
            if (numberArray[i] != '0') {
                break;
            }
        }
        if (i != 0) {
            char temp = numberArray[0];
            numberArray[0] = numberArray[i];
            numberArray[i] = temp;
        }
        return String.valueOf(numberArray);
    }
}
