package dynamicprogramming.tabulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Combinatoric and Optimization Problem
 * The Function should return an array containing the shortest combination of numbers,
 * if there is more than 1 shortest combination then return any.
 * Reuse of data is allowed to construct the target.
 */
public class TargetSumWithShortestPathNonNegativeData {

    public static void main(String[] args) {
        System.out.println(bestSumWithTabulation(7, new int[]{5, 3, 4, 7, 2}));
        System.out.println(bestSum(8, new int[]{3, 5, 2}));
        System.out.println(bestSum(7, new int[]{2, 4}));
        System.out.println(bestSumWithTabulation(28, new int[]{7, 14}));

        //Negative cases
        long startTime1 = System.currentTimeMillis();
        List<Integer> elements1 = bestSum(300, new int[]{7, 14});
        System.out.println("Simple approach :- " + elements1 + ", time taken :- " + (System.currentTimeMillis()-startTime1));

        long startTime2 = System.currentTimeMillis();
        List<Integer> elements2 = bestSumWithTabulation(300, new int[]{7, 14});
        System.out.println("Memoization approach :- " + elements2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        List<Integer> elements3 = bestSumWithTabulation(30000, new int[]{7, 14});
        System.out.println("Memoization approach :- " + elements3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

        //Positive cases
        long startTime4 = System.currentTimeMillis();
        List<Integer> elements4 = bestSumWithTabulation(100, new int[]{1, 2, 5, 25});
        System.out.println("Memoization approach time taken :- " + (System.currentTimeMillis()-startTime4) + ", elements :- " + elements4);

        long startTime5 = System.currentTimeMillis();
        List<Integer> elements5 = bestSumWithTabulation(30002, new int[]{7, 14});
        System.out.println("Memoization approach time taken :- " + (System.currentTimeMillis()-startTime5) + ", elements :- " + elements5);
    }

    private static List<Integer> bestSum(int targetSum, int[] data) {
        if (targetSum < 0) {
            return null;
        }
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        List<Integer> shortestResult = null;
        for(int val : data) {
            int remainder = targetSum-val;
            List<Integer> remainderResult = bestSum(remainder, data);
            if (remainderResult != null) {
                remainderResult = new ArrayList<>(remainderResult);
                remainderResult.add(val);
                if (shortestResult == null || remainderResult.size() < shortestResult.size()) {
                    shortestResult = remainderResult;
                }
            }
        }
        return shortestResult;
    }

    private static List<Integer> bestSumWithTabulation(int targetSum, int[] data) {
        List<List<Integer>> howSumTable = new ArrayList<>();
        howSumTable.add(new ArrayList<>());
        for (int i = 1; i <= targetSum; ++i) {
            howSumTable.add(null);
        }
        for (int i = 0; i <= targetSum; ++i) {
            if (howSumTable.get(i) != null) {
                for (int datum : data) {
                    if (i + datum <= targetSum) {
                        List<Integer> newCombination = new ArrayList<>(howSumTable.get(i));
                        newCombination.add(datum);

                        List<Integer> oldCombination = howSumTable.get(i + datum);
                        if (oldCombination == null || oldCombination.size() > newCombination.size()) {
                            howSumTable.remove(i + datum);
                            howSumTable.add(i + datum, newCombination);
                        }
                    }
                }
            }
        }
        return howSumTable.get(targetSum);
    }
}
