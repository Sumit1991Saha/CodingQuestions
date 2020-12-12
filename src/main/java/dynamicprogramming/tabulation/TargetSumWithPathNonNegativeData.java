package dynamicprogramming.tabulation;

import java.util.ArrayList;
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
        System.out.println(howSumWithTabulation(7, new int[]{5, 3, 4, 7}));
        System.out.println(howSum(7, new int[]{3, 4, 7, 2}));
        System.out.println(howSum(7, new int[]{2, 4}));

        //Negative cases
        long startTime2 = System.currentTimeMillis();
        List<Integer> elements2 = howSumWithTabulation(300, new int[]{7, 14});
        System.out.println("Tabulation approach :- " + elements2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo3 = new HashMap<>();
        List<Integer> elements3 = howSumWithTabulation(30000, new int[]{7, 14});
        System.out.println("Tabulation approach :- " + elements3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

        //Positive cases
        long startTime4 = System.currentTimeMillis();
        Map<Integer, List<Integer>> memo4 = new HashMap<>();
        List<Integer> elements4 = howSumWithTabulation(3010, new int[]{7, 14});
        System.out.println("Tabulation approach time taken :- " + (System.currentTimeMillis()-startTime4) + ", elements :- " + elements4);
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

    private static List<Integer> howSumWithTabulation(int targetSum, int[] data) {
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
                        // removing old combination and inserting new combination.
                        howSumTable.remove(i + datum);
                        howSumTable.add(i + datum, newCombination);
                    }
                }
            }
        }
        return howSumTable.get(targetSum);
    }
}
