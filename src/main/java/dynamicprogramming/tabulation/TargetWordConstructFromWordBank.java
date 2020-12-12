package dynamicprogramming.tabulation;


import java.util.HashMap;
import java.util.Map;

/**
 * Check if the target word can be constructed by concatenating the words from the word bank.
 * Reuse of words from word bank is allowed.
 */
public class TargetWordConstructFromWordBank {

    public static void main(String[] args) {
        System.out.println(canConstructWithTabulation("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstructWithTabulation("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(canConstructWithTabulation("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(canConstructWithTabulation("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));


        boolean canConstruct = canConstructWithTabulation("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"});
        System.out.println(canConstruct);
    }

    private static boolean canConstructWithTabulation(String targetString, String[] wordBank) {
        boolean[] canConstructTable = new boolean[targetString.length() + 1];
        canConstructTable[0] = true;

        for (int i = 0; i <= targetString.length(); ++i) {
            if (canConstructTable[i]) {
                for (String word : wordBank) {
                    // index to look further so as to create a small part of the target string at the position i.
                    int indexToLookAhead = i + word.length();
                    if (indexToLookAhead <= targetString.length()) {
                        String substringFromIthIndex = targetString.substring(i, indexToLookAhead);
                        // to perform exact word/substring match
                        if (substringFromIthIndex.equals(word)) {
                            canConstructTable[indexToLookAhead] = true;
                        }
                    }
                }
            }
        }
        return canConstructTable[targetString.length()];
    }
}
