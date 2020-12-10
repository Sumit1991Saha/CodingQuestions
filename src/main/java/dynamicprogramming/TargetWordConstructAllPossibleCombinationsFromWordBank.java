package dynamicprogramming;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Return all the ways the target word can be constructed by concatenating the words from the word bank in a 2D array format.
 * Reuse of words from word bank is allowed.
 */
public class TargetWordConstructAllPossibleCombinationsFromWordBank {

    public static void main(String[] args) {
        System.out.println(allConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
        System.out.println(allConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(allConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(allConstruct("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));

        Map<String, List<List<String>>> memo1 = new HashMap<>();
        List<List<String>> allConstruct = allConstructWithMemoization("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"}, memo1);
        System.out.println(allConstruct);
    }

    private static List<List<String>> allConstruct(String targetString, String[] wordBank) {
        if (targetString.isEmpty()) {
            List<List<String>> emptyListOfList = new ArrayList<>();
            emptyListOfList.add(new ArrayList<>());
            return emptyListOfList;
        }

        List<List<String>> allCombinations = new ArrayList<>();
        for (String prefix : wordBank) {
            if (targetString.indexOf(prefix) == 0) {
                String suffix = targetString.substring(prefix.length());
                List<List<String>> suffixCombinations = allConstruct(suffix, wordBank);
                if (!suffixCombinations.isEmpty()) {
                    for(List<String> suffixCombination : suffixCombinations) {
                        suffixCombination.add(0, prefix);
                    }
                    allCombinations.addAll(suffixCombinations);
                }
            }
        }
        return allCombinations;
    }

    private static List<List<String>> allConstructWithMemoization(String targetString, String[] wordBank, Map<String, List<List<String>>> memo) {
        if (memo.containsKey(targetString)) {
            return memo.get(targetString);
        }

        if (targetString.isEmpty()) {
            List<List<String>> emptyListOfList = new ArrayList<>();
            emptyListOfList.add(new ArrayList<>());
            return emptyListOfList;
        }

        List<List<String>> allCombinations = new ArrayList<>();
        for (String prefix : wordBank) {
            if (targetString.indexOf(prefix) == 0) {
                String suffix = targetString.substring(prefix.length());
                List<List<String>> suffixCombinations = allConstruct(suffix, wordBank);
                if (!suffixCombinations.isEmpty()) {
                    for(List<String> suffixCombination : suffixCombinations) {
                        suffixCombination.add(0, prefix);
                    }
                    allCombinations.addAll(suffixCombinations);
                }
            }
        }
        memo.put(targetString, allCombinations);
        return allCombinations;
    }
}
