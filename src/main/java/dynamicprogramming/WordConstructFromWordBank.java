package dynamicprogramming;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Check if the target word can be constructed by concatenating the words from the word bank.
 * Reuse of words from word bank is allowed.
 */
public class WordConstructFromWordBank {

    public static void main(String[] args) {
        System.out.println(canConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));

        Map<String, Boolean> memo1 = new HashMap<>();
        boolean canConstruct = canConstructWithMemoization("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"}, memo1);
        System.out.println(canConstruct);
    }

    private static boolean canConstruct(String targetString, String[] wordBank) {
        if (targetString == null) {
            return false;
        }

        if (targetString.isEmpty()) {
            return true;
        }

        for (String word : wordBank) {
            if (targetString.startsWith(word)) {
                String suffix = targetString.substring(word.length());
                if (canConstruct(suffix, wordBank)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canConstructWithMemoization(String targetString, String[] wordBank, Map<String, Boolean> memo) {
        if (memo.containsKey(targetString)) {
            return memo.get(targetString);
        }

        if (targetString == null) {
            return false;
        }

        if (targetString.isEmpty()) {
            return true;
        }

        for (String word : wordBank) {
            if (targetString.startsWith(word)) {
                String suffix = targetString.substring(word.length());
                if (canConstructWithMemoization(suffix, wordBank, memo)) {
                    memo.put(targetString, true);
                    return true;
                }
            }
        }
        memo.put(targetString, false);
        return false;
    }
}
