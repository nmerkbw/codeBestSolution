package com.jxs.recursiveAndDp;

import org.junit.Test;

public class ChangeMoneyLeastNum1 {

    /**
     * 动态规划
     * 数组中的匹配允许重复
     */
    public int getChangeMoneyLeastNum(int[] m, int aim) {

        if (m == null || m.length == 0 || aim < 0) {
            return 0;
        }
        int max = Integer.MAX_VALUE;
        int n = m.length;
        int[][] dp = new int[n][aim + 1];
        // 第一行赋值
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
            if ((j - m[0]) >= 0 && dp[0][j - m[0]] != max) {
                dp[0][j] = dp[0][j - m[0]] + 1;
            }
        }
        // 后面的d[i][j]赋值
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if ((j - m[i]) >= 0 && dp[i][j - m[i]] != max) {
                    left = dp[i][j - m[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] == max ? -1 : dp[n - 1][aim];
    }

    @Test
    public void test() {

        int[] a = {2, 3, 5};
        System.out.println(getChangeMoneyLeastNum(a, 6));
    }
}
