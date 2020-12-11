package dynamicprogramming.memoization;


import java.util.HashMap;
import java.util.Map;

/**
 * Count the no. of ways the target word can be constructed by concatenating the words from the word bank.
 * Reuse of words from word bank is allowed.
 */
public class TargetWordCountFromWordBank {

    public static void main(String[] args) {
        System.out.println(countConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(countConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(countConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(countConstruct("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));

        Map<String, Integer> memo1 = new HashMap<>();
        int countConstruct = countConstructWithMemoization("eeeeeeeeeeeeee",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"}, memo1);
        System.out.println(countConstruct);
    }

    private static int countConstruct(String targetString, String[] wordBank) {
        if (targetString.isEmpty()) {
            return 1;
        }

        int count = 0;
        for (String word : wordBank) {
            if (targetString.indexOf(word) == 0) {
                String suffix = targetString.substring(word.length());
                count = count + countConstruct(suffix, wordBank);
            }
        }
        return count;
    }

    private static int countConstructWithMemoization(String targetString, String[] wordBank, Map<String, Integer> memo) {
        if (memo.containsKey(targetString)) {
            return memo.get(targetString);
        }

        if (targetString.isEmpty()) {
            return 1;
        }

        int count = 0;
        for (String word : wordBank) {
            if (targetString.indexOf(word) == 0) {
                String suffix = targetString.substring(word.length());
                count = count + countConstructWithMemoization(suffix, wordBank, memo);
            }
        }
        memo.put(targetString, count);
        return count;
    }
}
