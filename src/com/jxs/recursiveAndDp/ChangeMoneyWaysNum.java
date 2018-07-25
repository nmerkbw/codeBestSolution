package com.jxs.recursiveAndDp;

import org.junit.Test;

public class ChangeMoneyWaysNum {

    /**
     * @description: 暴力递归的方法
     * */
    public int getChangeMoneyWaysNumSolution1(int[] m, int aim) {

        if (m == null || m.length == 0 || aim < 0) {
            return 0;
        }
        return getChangeMoneyWaysNum(m, 0, aim);
    }

    // 暴力递归
    private int getChangeMoneyWaysNum(int[] m, int index, int aim) {

        int sum = 0;
        // 走到数组结束时，当sum=aim=0时，说明结束时也满足构成要求，返回1，表示一种方法
        if (index == m.length) {
            sum = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; i * m[index] <= aim; i++) {
                sum += getChangeMoneyWaysNum(m, index + 1, aim - i * m[index]);
            }
        }
        return sum;
    }

    /**
     * @description: 使用记忆化搜索
     */
    public int getChangeMoneyWaysNumSolution2(int[] m, int aim) {

        if (m == null || m.length == 0 || aim < 0) {
            return 0;
        }
        int n = m.length;
        int[][] map = new int[n + 1][aim + 1];
        return getChangeMoneyWaysNum(m, map, 0, aim);
    }

    // 记忆化搜索的方法
    private int getChangeMoneyWaysNum(int[] m, int[][] map, int index, int aim) {

        int sum = 0;
        if (index == m.length) {
            sum = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; i * m[index] <= aim; i++) {
                // 先对要取的值进行搜索
                mapValue = map[index + 1][aim - i * m[index]];
                if (mapValue != 0) {
                    sum += mapValue == -1 ? 0 : mapValue;
                } else {
                    sum += getChangeMoneyWaysNum(m, map, index + 1, aim - i * m[index]);
                }
            }
        }
        return sum;
    }

    /**
     * 动态规划
     */
    public int getChangeMoneyWaysNumSolution3(int[] m, int aim) {

        if (m == null || m.length == 0 || aim < 0) {
            return 0;
        }
        int n = m.length;
        int[][] dp = new int[n][aim + 1];
        // 数组中组成和为0的方法只有一种，就是不使用各个元素，所以dp数组的第一列为1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        // 推导第一行
        for (int j = 1; j <= aim; j++) {
            if (j - m[0] >= 0) {
                dp[0][j] = dp[0][j - m[0]];
            }
        }
        // 其余行
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                int num = 0;
                for (int k = 0; k*m[i] <= j; k++) {
                    num += dp[i - 1][j - k * m[i]];
                }
                dp[i][j] = num;
            }
        }
        return dp[n - 1][aim];
    }


    @Test
    public void test() {

        int[] arr = {5, 10, 25, 1};
        int[] m = {2, 3, 1};
        System.out.println(getChangeMoneyWaysNumSolution1(m,3));
        System.out.println(getChangeMoneyWaysNumSolution2(m, 3));
        System.out.println(getChangeMoneyWaysNumSolution3(arr, 15));
    }
}
