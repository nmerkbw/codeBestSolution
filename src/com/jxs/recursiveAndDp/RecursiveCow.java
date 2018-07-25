package com.jxs.recursiveAndDp;

import org.junit.Test;

public class RecursiveCow {

    /**
     * 方法1：暴力递归，f(N) = f(N-1)+f(N-3)
     * 时间复杂度：O(2^N)
     * */
    public int recursiveCowSolution1(int n) {

        int num = 0;

        if (n <= 4) {
            return n;
        }
        num = recursiveCowSolution1(n - 1) + recursiveCowSolution1(n - 3);
        return num;
    }

    /**
     * 方法2：循环顺序计算
     * 时间复杂度：O(N)
     */
    public int recursiveCowSolution2(int n) {

        if (n <= 3) {
            return n;
        }
        int prepre = 1;
        int pre = 2;
        int now = 3;
        int num = 0;
        for (int i = 4; i <= n; i++) {
            num = now + prepre;
            prepre = pre;
            pre = now;
            now = num;
        }
        return num;
    }

    @Test
    public void test() {

        int n = 16;
        RecursiveCow recursiveCow = new RecursiveCow();
        System.out.println(recursiveCow.recursiveCowSolution1(n));
        System.out.println(recursiveCow.recursiveCowSolution2(n));
    }
}
