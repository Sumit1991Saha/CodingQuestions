package dynamicprogramming;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Decision Problem
 * The Function should return true or false if the combination of numbers can be constructed to make the desired sum.
 * Reuse of data is allowed to construct the target.
 */
public class TargetSumWithNonNegativeData {

    public static void main(String[] args) {
        System.out.println(canSum(7, new int[]{3, 4, 5, 7}));
        System.out.println(canSum(7, new int[]{2, 4}));

        long startTime1 = System.currentTimeMillis();
        boolean isPossible1 = canSum(300, new int[]{7, 14});
        System.out.println("Simple approach :- " + isPossible1 + ", time taken :- " + (System.currentTimeMillis()-startTime1));

        long startTime2 = System.currentTimeMillis();
        Map<Integer, Boolean> memo = new HashMap<>();
        boolean isPossible2 = canSumWithMemoization(300, new int[]{7, 14}, memo);
        System.out.println("Memoization approach :- " + isPossible2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        Map<Integer, Boolean> memo1 = new HashMap<>();
        boolean isPossible3 = canSumWithMemoization(30000, new int[]{7, 14, 6}, memo1);
        System.out.println("Memoization approach :- " + isPossible3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

    }

    private static boolean canSum(int targetSum, int[] data) {
        if (targetSum < 0) {
            return false;
        }
        if (targetSum == 0) {
            return true;
        }
        for(int val : data) {
            int remainder = targetSum-val;
            if (canSum(remainder, data)) {
                return true;
            }
        }
        return false;
    }

    private static boolean canSumWithMemoization(int targetSum, int[] data, Map<Integer, Boolean> memo) {
        if (memo == null) {
            memo = new HashMap<>();
        }

        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }

        if (targetSum < 0) {
            return false;
        }
        if (targetSum == 0) {
            return true;
        }
        for(int val : data) {
            int remainder = targetSum-val;
            if (canSumWithMemoization(remainder, data, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }
}
