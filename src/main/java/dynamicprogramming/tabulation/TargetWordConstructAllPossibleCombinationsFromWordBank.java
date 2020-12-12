package dynamicprogramming.tabulation;


import java.util.ArrayList;
import java.util.List;

/**
 * Return all the ways the target word can be constructed by concatenating the words from the word bank in a 2D array format.
 * Reuse of words from word bank is allowed.
 */
public class TargetWordConstructAllPossibleCombinationsFromWordBank {

    public static void main(String[] args) {
        System.out.println(allConstructWithTabulation("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
        System.out.println(allConstructWithTabulation("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(allConstructWithTabulation("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(allConstructWithTabulation("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));

        // n :- wordbank.length and m :- target word length
        // In this case if there are n^m leaf nodes, there re n^m sub arrays.
        // So even with memoization, the time complexity will not improved. So the Time complexity remains O(n^m * m)
        // As for space complexity, its O(m). In case of when o/p is exponential, so will the space complexity too,
        // so in that case we just include the height of the recursion tree or size of call stack.
        List<List<String>> allConstruct = allConstructWithTabulation("eeeeeeeeeeeeee",
                new String[]{"e", "ee", "eeee", "eeeeeee", "eeeeeeeeee", "eeeeeeeeeeeeee"});
        System.out.println(allConstruct);
    }

    /*private static List<List<String>> allConstruct(String targetString, String[] wordBank) {
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
    }*/

    private static List<List<String>> allConstructWithTabulation(String targetString, String[] wordBank) {
        Object[] allConstructTable = new Object[targetString.length() + 1];
        for (int i = 0; i <= targetString.length(); ++i) {
            allConstructTable[i] = new ArrayList<Integer>();
        }
        // making empty combination to represent 1 combination ie empty string can be created by taking nothing from the word bank.
        List<List<String>> emptyListOfList = new ArrayList<>();
        emptyListOfList.add(new ArrayList<>());
        allConstructTable[0] = emptyListOfList;

        for (int currentIndex = 0; currentIndex <= targetString.length(); ++currentIndex) {
            // This check represents null case as in that combination can not be formed so in that case 1 dimension array remains empty.
            if (!((List<Integer>)allConstructTable[currentIndex]).isEmpty()) {
                for (String word : wordBank) {
                    // index to look further so as to create a small part of the target string at the position i.
                    int indexToLookAhead = currentIndex + word.length();
                    if (indexToLookAhead <= targetString.length()) {
                        String substringFromIthIndex = targetString.substring(currentIndex, indexToLookAhead);
                        // to perform exact word/substring match
                        if (substringFromIthIndex.equals(word)) {
                            List<List<String>> dataToBeAppended = deepCopy2DArrayLists((List<List<String>>)allConstructTable[currentIndex]);
                            for (List<String> listOfData : dataToBeAppended) {
                                listOfData.add(word);
                            }
                            ((List<List<String>>)allConstructTable[indexToLookAhead]).addAll(dataToBeAppended);
                        }
                    }
                }
            }
        }
        return ((List<List<String>>)allConstructTable[targetString.length()]);
    }

    private static List<List<String>> deepCopy2DArrayLists(List<List<String>> sourceList) {
        List<List<String>> targetList = new ArrayList<>();
        for (List<String> listData : sourceList) {
            List<String> targetData = new ArrayList<>(listData);
            targetList.add(targetData);
        }
        return targetList;
    }
}
