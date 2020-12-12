package dynamicprogramming.tabulation;


import java.util.HashMap;
import java.util.Map;

/**
 * Count the no. of ways the target word can be constructed by concatenating the words from the word bank.
 * Reuse of words from word bank is allowed.
 */
public class TargetWordCountFromWordBank {

    public static void main(String[] args) {
        System.out.println(countConstructWithTabulation("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef"}));
        System.out.println(countConstructWithTabulation("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(countConstructWithTabulation("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(countConstructWithTabulation("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));

        int countConstruct = countConstructWithTabulation("eeeeeeeeeeeeee",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"});
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

    private static int countConstructWithTabulation(String targetString, String[] wordBank) {
        int[] countConstructTable = new int[targetString.length() + 1];
        countConstructTable[0] = 1;

        for (int i = 0; i <= targetString.length(); ++i) {
            if (countConstructTable[i] != 0) {
                for (String word : wordBank) {
                    // index to look further so as to create a small part of the target string at the position i.
                    int indexToLookAhead = i + word.length();
                    if (indexToLookAhead <= targetString.length()) {
                        String substringFromIthIndex = targetString.substring(i, indexToLookAhead);
                        // to perform exact word/substring match
                        if (substringFromIthIndex.equals(word)) {
                            countConstructTable[indexToLookAhead] += countConstructTable[i];
                        }
                    }
                }
            }
        }
        return countConstructTable[targetString.length()];
    }
}
