package dynamicprogramming.tabulation;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Fibonacci seq generation with Tabulation approach
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(computeFibWithTabulation(6));
        System.out.println(computeFibWithTabulation(7));
        System.out.println(computeFibWithTabulation(8));
        System.out.println(computeFibWithTabulation(50));
    }

    private static BigInteger computeFibWithTabulation(int n) {
        BigInteger[] fibSeq = new BigInteger[n+1];
        fibSeq[0] = BigInteger.ZERO;
        fibSeq[1] = BigInteger.ONE;
        for (int index = 2; index <= n; ++index) {
            fibSeq[index] = fibSeq[index-1].add(fibSeq[index-2]);
        }
        return fibSeq[n];
    }
}
