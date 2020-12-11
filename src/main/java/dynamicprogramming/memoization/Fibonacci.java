package dynamicprogramming.memoization;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fibonacci {


    public static void main(String[] args) {
        System.out.println(computeFibWithMemoziation(6, null));
        System.out.println(computeFibWithMemoziation(7, null));
        System.out.println(computeFibWithMemoziation(8, null));
        System.out.println(computeFibWithMemoziation(50, null));
    }

    private static int computeFib(int n) {
        if (n <= 2) {
            return 1;
        }
        return computeFib(n-1) + computeFib(n-2);
    }

    private static BigInteger computeFibWithMemoziation(int n, Map<Integer, BigInteger> memo) {
        if (memo == null) {
            memo = new HashMap<>();
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        if (n <= 2) {
            return BigInteger.ONE;
        }
        BigInteger val = computeFibWithMemoziation(n-1, memo).add(computeFibWithMemoziation(n-2, memo));
        memo.put(n, val);
        return val;
    }
}
