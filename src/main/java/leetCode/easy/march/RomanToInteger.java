package leetCode.easy.march;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    private static Map<Character, Integer> data = new HashMap<>();
    static {
        data.put('I', 1);
        data.put('V', 5);
        data.put('X', 10);
        data.put('L', 50);
        data.put('C', 100);
        data.put('D', 500);
        data.put('M', 1000);
    }
    public static void main(String[] args) {
        System.out.println(convertToInt("IV"));
        System.out.println(convertToInt("MCMVI"));
        System.out.println(convertToInt("MMVCXIV"));
    }

    private static int convertToInt(String roman) {
        int value = 0;
        int i = 0;
        for (; i <roman.length() - 1; ++i) {
            int curVal = data.get(roman.charAt(i));
            int nextVal = data.get(roman.charAt(i+1));
            if (curVal < nextVal) {
                value = value + (nextVal - curVal);
                i++;
            } else {
                value = value + curVal;
            }
        }
        if (i == roman.length() - 1) {
            int curVal = data.get(roman.charAt(i));
            value = value + curVal;
        }
        return value;
    }
}
