package dynamicprogramming.memoization;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * No. of possible ways to start from 1,1 and reach m,n. user can move 1 step down and 1 step right.
 */
public class GridTraveller {

    public static void main(String[] args) {
        System.out.println(gridTravel(1,3));
        System.out.println(gridTravel(2,3));
        int m = 16, n = 20;
        long startTime1 = System.currentTimeMillis();
        int val = gridTravel(m,n);
        System.out.println("Simple approach :- " + val + ", time taken :- " + (System.currentTimeMillis()-startTime1));

        long startTime2 = System.currentTimeMillis();
        BigInteger val2 = gridTravelWithMemoization(m,n, null);
        System.out.println("Memoization approach with array :- " + val2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));


        long startTime3 = System.currentTimeMillis();
        BigInteger val3 = gridTravelWithMemoizationUsingHashmap(m,n, null);
        System.out.println("Memoization approach with map:- " + val3 + ", time taken :- " + (System.currentTimeMillis()-startTime3));


        //System.out.println(gridTravelWithMemoization(3,3, null));

        //System.out.println(gridTravelWithMemoizationUsingHashmap(20,20, null));

    }

    private static int gridTravel(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        return gridTravel(m - 1, n) + gridTravel(m, n - 1);
    }

    private static BigInteger gridTravelWithMemoization(int m, int n, BigInteger[][] memo) {
        if (memo == null) {
            memo = new BigInteger[m+1][n+1];
        }
        if (memo[m][n] != null && !memo[m][n].equals(BigInteger.ZERO)) {
            return memo[m][n];
        }
        if (m <= 1 || n <= 1) {
            return BigInteger.ONE;
        }
        memo[m][n] = gridTravelWithMemoization(m - 1, n, memo).add(gridTravelWithMemoization(m, n - 1, memo));
        return memo[m][n];
    }

    private static BigInteger gridTravelWithMemoizationUsingHashmap(int m, int n, Map<String, BigInteger> memo) {
        if (memo == null) {
            memo = new HashMap<>();
        }
        String key =  m+","+n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (m <= 1 || n <= 1) {
            return BigInteger.ONE;
        }
        BigInteger val = gridTravelWithMemoizationUsingHashmap(m - 1, n, memo)
                .add(gridTravelWithMemoizationUsingHashmap(m, n - 1, memo));
        memo.put(key, val);
        return val;
    }
}
