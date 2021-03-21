package leetCode.medium.march;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwpke"));

    }

    public static int lengthOfLongestSubstring(String s) {
        int longestSubstringLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int startIndex = 0;
        for (int endIndex = 0; endIndex < s.length(); ++endIndex) {
            char charValue = s.charAt(endIndex);
            if (map.containsKey(charValue)) {
                startIndex = Math.max(map.get(charValue), startIndex);
            }
            longestSubstringLength = Math.max(longestSubstringLength, endIndex - startIndex + 1);
            map.put(charValue, endIndex + 1);
        }


        return longestSubstringLength;
    }
}
