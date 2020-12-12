package dynamicprogramming.tabulation;

/**
 * Decision Problem
 * The Function should return true or false if the combination of numbers can be constructed to make the desired sum.
 * Reuse of data is allowed to construct the target.
 */
public class TargetSumWithNonNegativeData {

    public static void main(String[] args) {
        System.out.println(canSum(7, new int[]{3, 4, 5, 7}));
        System.out.println(canSum(7, new int[]{2, 4}));

        /*long startTime1 = System.currentTimeMillis();
        boolean isPossible1 = canSum(300, new int[]{7, 14});
        System.out.println("Simple approach :- " + isPossible1 + ", time taken :- " + (System.currentTimeMillis()-startTime1));
*/

        long startTime2 = System.currentTimeMillis();;
        boolean isPossible2 = canSumWithTaulation(300, new int[]{7, 14});
        System.out.println("Tabulation approach :- " + isPossible2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));

        long startTime3 = System.currentTimeMillis();
        boolean isPossible3 = canSumWithTaulation(30000, new int[]{7, 14, 6});
        System.out.println("Tabulation approach :- " + isPossible3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));

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

    private static boolean canSumWithTaulation(int targetSum, int[] data) {
        boolean[] canSumTable = new boolean[targetSum + 1];
        canSumTable[0] = true;
        for (int i = 0; i <= targetSum; ++i) {
            if (canSumTable[i]) {
                for (int datum : data) {
                    if (i + datum <= targetSum) {
                        canSumTable[i + datum] = true;
                    }
                }
            }
        }
        return canSumTable[targetSum];
    }
}
