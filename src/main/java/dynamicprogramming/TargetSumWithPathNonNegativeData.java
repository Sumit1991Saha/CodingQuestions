package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Combinatoric Problem
 * The Function should return any array, containing the combination of numbers,
 * if there is more than 1 combination then return any.
 * Reuse of data is allowed to construct the target.
 */
public class TargetSumWithPathNonNegativeData {

    public static void main(String[] args) {
        System.out.println(howSum(7, new int[]{5, 3, 4, 7, 2}));
        System.out.println(howSum(7, new int[]{3, 4, 7, 2}));
        System.out.println(howSum(7, new int[]{2, 4}));

        //Negative cases
        long startTime2 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo2 = new HashMap<>();
        List<Integer> elements2 = howSumWithMemoization(300, new int[]{7, 14}, memo2);
        System.out.println("Memoization approach :- " + elements2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo3 = new HashMap<>();
        List<Integer> elements3 = howSumWithMemoization(30000, new int[]{7, 14}, memo3);
        System.out.println("Memoization approach :- " + elements3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

        //Positive cases
        long startTime4 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo4 = new HashMap<>();
        List<Integer> elements4 = howSumWithMemoization(3010, new int[]{7, 14}, memo4);
        System.out.println("Memoization approach time taken :- " + (System.currentTimeMillis()-startTime4) + ", elements :- " + elements4);

    }

    private static List<Integer> howSum(int targetSum, int[] data) {
        if (targetSum < 0) {
            return null;
        }
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        for(int val : data) {
            int remainder = targetSum-val;
            List<Integer> remainderResult = howSum(remainder, data);
            if (remainderResult != null) {
                remainderResult = new ArrayList<>(remainderResult);
                remainderResult.add(val);
                return remainderResult;
            }
        }
        return null;
    }

    private static List<Integer> howSumWithMemoization(int targetSum, int[] data, Map<Integer, List<Integer>> memo) {
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

        for(int val : data) {
            int remainder = targetSum-val;
            List<Integer> remainderResult = howSumWithMemoization(remainder, data, memo);
            if (remainderResult != null) {
                remainderResult = new ArrayList<>(remainderResult);
                remainderResult.add(val);
                memo.put(targetSum, remainderResult);
                return remainderResult;
            }
        }
        memo.put(targetSum, null);
        return null;
    }
}
