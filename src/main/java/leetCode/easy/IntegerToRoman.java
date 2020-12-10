package leetCode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class IntegerToRoman {

    private static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static void main(String[] args) {
        System.out.println(convertToRoman(4));
        System.out.println(convertToRoman(1906));
        System.out.println(convertToRoman(2109));
    }

    private static String convertToRoman(int num) {
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; ++i) {
            while (num >= values[i]) {
                num = num - values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
}
