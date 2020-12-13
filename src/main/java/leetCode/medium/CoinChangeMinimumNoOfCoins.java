package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChangeMinimumNoOfCoins {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2,5}, 11));
    }

    private static int coinChange(int[] coins, int amount) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        List<Integer> coinSet = coinChangeMinimumCount(coins, amount, memo);
        int minimumCoins = -1;
        if (coinSet != null) {
            minimumCoins = coinSet.size();
        }
        return minimumCoins;
    }

    private static List<Integer> coinChangeMinimumCount(int[] coins, int amount, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        if(amount == 0) {
            return new ArrayList<>();
        }

        if (amount < 0) {
            return null;
        }

        List<Integer> shortestList = null;
        for(int coin : coins) {
            int remainingAmount = amount - coin;
            List<Integer> coinsReq = coinChangeMinimumCount(coins, remainingAmount, memo);
            if (coinsReq != null) {
                coinsReq = new ArrayList<>(coinsReq);
                coinsReq.add(coin);
                if (shortestList == null || coinsReq.size() < shortestList.size()) {
                    shortestList = coinsReq;
                }
            }
        }
        memo.put(amount, shortestList);
        return shortestList;
    }
}
