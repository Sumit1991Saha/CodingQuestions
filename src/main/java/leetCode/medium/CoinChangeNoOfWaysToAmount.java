package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CoinChangeNoOfWaysToAmount {
    static int[] coins = {1,2,5};
    public static void main(String[] args) {
        System.out.println(countDistinctCombination(coins, coins.length-1, 5 ));
    }

    // Function to find the total number of distinct ways to get
    // change of N from unlimited supply of coins in set S
    public static int countDistinctCombination(int[] S, int n, int N)
    {
        // if total is 0, return 1 (solution found)
        if (N == 0) {
            return 1;
        }

        // return 0 (solution do not exist) if total become negative or
        // no elements are left
        if (N < 0 || n < 0) {
            return 0;
        }

        // Case 1. include current coin S[n] in solution and recur
        // with remaining change (N - S[n]) with same number of coins
        int incl = countDistinctCombination(S, n, N - S[n]);

        // Case 2. exclude current coin S[n] from solution and recur
        // for remaining coins (n - 1)
        int excl = countDistinctCombination(S, n - 1, N);

        // return total ways by including or excluding current coin
        return incl + excl;
    }


    private static int coinChangeUsingMapToDetectUniqueCombination(int[] coins, int amount) {
        List<List<Integer>> combinationForAmount = allConstructWithTabulation(coins, amount);
        Map<Long, List<Integer>> uniqueCombinations = new HashMap<>();
        for (List<Integer> combination : combinationForAmount) {
            Collections.sort(combination);
            int[] combinationInArray = listToArray(combination);
            long hash = Arrays.hashCode(combinationInArray);
            uniqueCombinations.putIfAbsent(hash, combination);
        }

        return uniqueCombinations.size();
    }

    //Returns count of all the permutation and combination req to make the sum
    // Like for 3 : [[1,1,1], [1,2], [2,1]]
    // we need unique combination, so use the one below this method
    private static int coinChangeNumberOfCombination(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        if (amount == 0) {
            return 1;
        }

        if (amount < 0) {
            return 0;
        }

        int count = 0;
        for (int coin : coins) {
            int remainingAmount = amount - coin;
            count += coinChangeNumberOfCombination(coins, remainingAmount, memo);
        }
        memo.put(amount, count);
        return count;
    }

    private static List<List<Integer>> allConstructWithTabulation(int[] coins, int amount) {
        Object[] allConstructTable = new Object[amount + 1];
        for (int i = 0; i <= amount; ++i) {
            allConstructTable[i] = new ArrayList<Integer>();
        }
        // making empty combination to represent 1 combination ie empty string can be created by taking nothing from the word bank.
        List<List<Integer>> emptyListOfList = new ArrayList<>();
        emptyListOfList.add(new ArrayList<>());
        allConstructTable[0] = emptyListOfList;

        for (int currentIndex = 0; currentIndex <= amount; ++currentIndex) {
            // This check represents null case as in that combination can not be formed so in that case 1 dimension array remains empty.
            if (!((List<Integer>) allConstructTable[currentIndex]).isEmpty()) {
                for (int coinValue : coins) {
                    // index to look further so as to create a small part of the target string at the position i.
                    int indexToLookAhead = currentIndex + coinValue;
                    if (indexToLookAhead <= amount) {

                        List<List<Integer>> dataToBeAppended = deepCopy2DArrayLists((List<List<Integer>>) allConstructTable[currentIndex]);
                        for (List<Integer> listOfData : dataToBeAppended) {
                            listOfData.add(coinValue);
                        }
                        ((List<List<Integer>>) allConstructTable[indexToLookAhead]).addAll(dataToBeAppended);

                    }
                }
            }
        }
        return ((List<List<Integer>>) allConstructTable[amount]);
    }

    private static List<List<Integer>> deepCopy2DArrayLists(List<List<Integer>> sourceList) {
        List<List<Integer>> targetList = new ArrayList<>();
        for (List<Integer> listData : sourceList) {
            List<Integer> targetData = new ArrayList<>(listData);
            targetList.add(targetData);
        }
        return targetList;
    }

    private static int[] listToArray(List<Integer> data) {
        int[] arr = new int[data.size()];
        int index = 0;
        while(index < arr.length) {
            arr[index] = data.get(index);
            index++;
        }
        return arr;
    }
}
