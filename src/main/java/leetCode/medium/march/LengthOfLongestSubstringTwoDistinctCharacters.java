package leetCode.medium.march;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstringTwoDistinctCharacters {

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("caacbbb"));
    }

    private static int lengthOfLongestSubstringTwoDistinct(String data) {
        int longest = 0;
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < data.length(); ++i) {
            char currentChar = data.charAt(i);
            if (characterIntegerMap.size() <= 2) {
                characterIntegerMap.put(currentChar, i);
                endIndex++;
            } else {
                longest = Math.max(longest, endIndex - startIndex);
                startIndex = removeLowestIndexCharAndReturnNextLowestIndex(characterIntegerMap);
            }
        }
        return longest;
    }

    private static int removeLowestIndexCharAndReturnNextLowestIndex(Map<Character, Integer> characterIntegerMap) {
        if (characterIntegerMap.size() < 2) {
            return characterIntegerMap.size() == 0 ? 0 : characterIntegerMap.keySet().iterator().next();
        }
        int lowestIndex = Integer.MAX_VALUE;
        char charAgainstLowestIndex = 0;
        for (Map.Entry<Character, Integer> entry : characterIntegerMap.entrySet()) {
            if (entry.getValue() < lowestIndex) {
                lowestIndex = entry.getValue();
                charAgainstLowestIndex = entry.getKey();
            }
        }
        characterIntegerMap.remove(charAgainstLowestIndex);

        //Finding next lowest index
        lowestIndex = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : characterIntegerMap.entrySet()) {
            if (entry.getValue() < lowestIndex) {
                lowestIndex = entry.getValue();
            }
        }
        return lowestIndex;
    }
}
