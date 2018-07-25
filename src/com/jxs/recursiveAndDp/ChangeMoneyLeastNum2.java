package com.jxs.recursiveAndDp;

import org.junit.Test;

public class ChangeMoneyLeastNum2 {

    /**
     * 动态规划
     * 数组中的匹配不允许重复
     */
    public int getChangeMoneyLeastNum(int[] m, int aim) {

        if (m == null || m.length == 0 || aim < 0) {
            return -1;
        }
        int max = Integer.MAX_VALUE;
        int n = m.length;
        int[][] dp = new int[n][aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
        }
        if (m[0] <= aim) {
            dp[0][m[0]] = 1;
        }
        int leftUp = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                leftUp = max;
                if ((j - m[i]) >= 0 && dp[i - 1][j - m[i]] != max) {
                    leftUp = dp[i - 1][j - m[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i - 1][j], leftUp);
            }
        }
        return dp[n - 1][aim] == max ? -1 : dp[n - 1][aim];
    }

    @Test
    public void test() {

        int[] a = {2, 3, 5};
        System.out.println(getChangeMoneyLeastNum(a,3));
    }
}
