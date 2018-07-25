package com.jxs.recursiveAndDp;

import org.junit.Test;

public class ChangeMoneyWaysNum {

    /**
     * 暴力递归的方法
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


    @Test
    public void test() {

        int[] arr = {5, 10, 25, 1};
        System.out.println(getChangeMoneyWaysNumSolution1(arr,15));
    }
}
