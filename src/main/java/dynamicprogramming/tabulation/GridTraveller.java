package dynamicprogramming.tabulation;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * No. of possible ways to start from 1,1 and reach m,n. user can move 1 step down and 1 step right.
 */
public class GridTraveller {

    public static void main(String[] args) {
        System.out.println(gridTravelWithTabulation(1,3));
        System.out.println(gridTravelWithTabulation(2,3));
        int m = 16, n = 20;
        long startTime1 = System.currentTimeMillis();
        int val = gridTravel(m,n);
        System.out.println("Simple approach :- " + val + ", time taken :- " + (System.currentTimeMillis()-startTime1));

        long startTime2 = System.currentTimeMillis();
        BigInteger val2 = gridTravelWithTabulation(m,n);
        System.out.println("Tabulation approach with array :- " + val2 + ", time taken :- " + (System.currentTimeMillis()-startTime2));
    }

    private static int gridTravel(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        return gridTravel(m - 1, n) + gridTravel(m, n - 1);
    }

    private static BigInteger gridTravelWithTabulation(int m, int n) {
        BigInteger[][]data = new BigInteger[m+1][n+1];
        for (int i = 0; i <= m; ++i) {
            data[i][0] = BigInteger.ZERO;

        }
        for (int j = 0; j <= n; ++j) {
            data[0][j] = BigInteger.ZERO;
        }
        for (int i = 1; i <= m; ++i) {
            data[i][1] = BigInteger.ONE;

        }
        for (int j = 0; j <= n; ++j) {
            data[1][j] = BigInteger.ONE;
        }

        for (int i = 2; i <= m; ++i) {
            for (int j = 2; j <= n; ++j)
            data[i][j] = data[i][j-1].add(data[i-1][j]);
        }

        return data[m][n];
    }
}
