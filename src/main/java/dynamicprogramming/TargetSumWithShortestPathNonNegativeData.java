package dynamicprogramming;

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
        System.out.println(bestSum(7, new int[]{5, 3, 4, 7, 2}));
        System.out.println(bestSum(8, new int[]{3, 5, 2}));
        System.out.println(bestSum(7, new int[]{2, 4}));
        System.out.println(bestSum(28, new int[]{7, 14}));

        //Negative cases
        long startTime1 = System.currentTimeMillis();
        List<Integer> elements1 = bestSum(300, new int[]{7, 14});
        System.out.println("Simple approach :- " + elements1 + ", time taken :- " + (System.currentTimeMillis()-startTime1));

        long startTime2 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo2 = new HashMap<>();
        List<Integer> elements2 = bestSumWithMemoization(300, new int[]{7, 14}, memo2);
        System.out.println("Memoization approach :- " + elements2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo3 = new HashMap<>();
        List<Integer> elements3 = bestSumWithMemoization(30000, new int[]{7, 14}, memo3);
        System.out.println("Memoization approach :- " + elements3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

        //Positive cases
        long startTime4 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo4 = new HashMap<>();
        List<Integer> elements4 = bestSumWithMemoization(100, new int[]{1, 2, 5, 25}, memo4);
        System.out.println("Memoization approach time taken :- " + (System.currentTimeMillis()-startTime4) + ", elements :- " + elements4);

        long startTime5 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo5 = new HashMap<>();
        List<Integer> elements5 = bestSumWithMemoization(3010, new int[]{7, 14}, memo5);
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

    private static List<Integer> bestSumWithMemoization(int targetSum, int[] data, Map<Integer, List<Integer>> memo) {
        if (memo == null) {
            memo = new HashMap<>();
        }

        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }

        if (targetSum < 0) {
            return null;
        }
        if (targetSum == 0) {
            return new ArrayList<>();
        }

        List<Integer> shortestResult = null;
        for(int val : data) {
            int remainder = targetSum-val;
            List<Integer> remainderResult = bestSumWithMemoization(remainder, data, memo);
            if (remainderResult != null) {
                remainderResult = new ArrayList<>(remainderResult);
                remainderResult.add(val);
                if (shortestResult == null || remainderResult.size() < shortestResult.size()) {
                    shortestResult = remainderResult;
                }
            }
        }
        memo.put(targetSum, shortestResult);
        return shortestResult;
    }
}
