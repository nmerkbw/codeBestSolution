package com.jxs.recursiveAndDp;

import org.junit.Test;

public class LongestIncreaseSequence {

    public int[] getLongestIncreaseSequenceSolution1(int[] array) {

        if (array == null || array.length == 0) {
            return null;
        }
        return genenrateList(array, getDp1(array));
    }

    public int[] getDp1(int[] array) {

        int n = array.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public int[] genenrateList(int[] array, int[] dp) {

        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] list = new int[len];
        list[--len] = array[index];
        for (int i = index; i >= 0; i--) {
            // 如果前一个dp与后一个相差1且数组前一个比目前的这个小，那么久将这个数组
            // 中这个位置的数加入到list当中
            if (array[i] < array[index] && dp[i] == dp[index] - 1) {
                list[--len] = array[i];
                index = i;
            }
        }
        return list;
    }

    @Test
    public void test() {

        int[] array = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        int[] result = getLongestIncreaseSequenceSolution1(array);
        System.out.print("[");
        for (int r : result) {
            System.out.print(r+" ");
        }
        System.out.print("]");
    }
}
