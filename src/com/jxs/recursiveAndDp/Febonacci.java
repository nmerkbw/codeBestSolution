package com.jxs.recursiveAndDp;

import org.junit.Test;

public class Febonacci {

    /**
     * 方法1：暴力递归求解斐波拉契 f(N)=f(N-1)+f(N-2)
     * 时间复杂度：O(2^N)
     * */
    public int getFebonacciRecursive(int n) {

        int num = 0;
        if (n == 1 || n == 2) {
            return 1;
        }
        num = getFebonacciRecursive(n - 1) + getFebonacciRecursive(n - 2);
        return num;
    }

    /**
     * 方法2：使用循环方式实现
     * 时间复杂度：O(N)
     */
    public int getFebonacciLoop(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }
        int num = 0;
        int now = 1;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            num = pre + now;
            pre = now;
            now = num;
        }
        return num;
    }

    /**
     * 方法3：使用矩阵乘法
     * 时间复杂度：O(logN)
     */

    public int getFebonacciMatrixMuli(int n) {

        if (n < 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }
    /**
     * 使用矩阵乘法最终得到矩阵m的p次方
     * */
    public int[][] matrixPower(int[][] m, int p) {

        int[][] res = new int[m.length][m[0].length];
        // 先将res设置为单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] temp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, m);
            }
            temp = muliMatrix(temp, temp);
        }
        return res;
    }
    // 矩阵乘法
    private int[][] muliMatrix(int[][] m1, int[][] m2) {

        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] = m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    @Test
    public void test() {

        int n = 6;
        Febonacci f = new Febonacci();
        System.out.println(f.getFebonacciRecursive(n));
        System.out.println(f.getFebonacciLoop(n));
        System.out.println(f.getFebonacciMatrixMuli(n));
    }
}
