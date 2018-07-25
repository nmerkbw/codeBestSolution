package com.jxs.recursiveAndDp;

import org.junit.Test;

import java.util.HashMap;

public class MatrixMinRoadSum {

    private HashMap<String, Integer> cache = new HashMap<>();

    /**
     * 暴力递归法
     * 时间复杂度：O(2^N)
     * */
    public int getMatrixMinRoadSumViolence(int[][] matrix) {

        return getMatrixMinRoadSumViolence(matrix, 0, 0);
    }

    /**
     * 暴力递归的方法
     * */
    private int getMatrixMinRoadSumViolence(int[][] matrix, int r, int c) {

        if (r == matrix.length - 1 && c == matrix[0].length - 1) {
            return matrix[r][c];
        }
        if (r == matrix.length - 1) {
            return matrix[r][c] + getMatrixMinRoadSumViolence(matrix, r, c + 1);
        }
        if (c == matrix[0].length - 1) {
            return matrix[r][c] + getMatrixMinRoadSumViolence(matrix, r + 1, c);
        }
        return matrix[r][c] + Math.min(getMatrixMinRoadSumViolence(matrix,
                r + 1, c), getMatrixMinRoadSumViolence(matrix, r, c + 1));
    }

    /**
     * 记忆化搜索
     * 时间复杂度：O(N^2)
     * */
    public int getMatrixMinRoadSumCache(int[][] matrix) {

        return getMatrixMinRoadSumCache(matrix, 0, 0);
    }

    /**
     * 记忆化搜索的方法
     * 加入缓存，先从缓存中取数据，没有再去执行相应的处理
     * 时间复杂度：O(M*N)
     */
    private int getMatrixMinRoadSumCache(int[][] m, int r, int c) {

        int result = 0;
        if (r == m.length - 1 && c == m[0].length - 1) {
            return m[r][c];
        } else if (r == m.length - 1) {
            Integer rightNext;
            String downKey = String.valueOf(r) + "_" + String.valueOf(c + 1);
            rightNext = cache.get(downKey);

            if (rightNext != null) {
                result = m[r][c] + rightNext;
            } else {
                result = m[r][c] + getMatrixMinRoadSumCache(m, r, c + 1);
            }
        } else if (c == m[0].length - 1) {
            Integer downNext;
            String rightKey = String.valueOf(r + 1) + "_" + String.valueOf(c);
            downNext = cache.get(rightKey);

            if (downNext != null) {
                result = m[r][c] + downNext;
            } else {
                result = m[r][c] + getMatrixMinRoadSumCache(m, r + 1, c);
            }
        } else if (r < m.length - 1 && c < m[0].length - 1){
            Integer rightNext;
            String rightKey = String.valueOf(r) + "_" + String.valueOf(c + 1);
            rightNext = cache.get(rightKey);

            Integer downNext;
            String downKey = String.valueOf(r + 1) + "_" + String.valueOf(c);
            downNext = cache.get(downKey);
            if (rightNext != null && downKey != null) {
                result = m[r][c] + Math.min(downNext, rightNext);
            } else {
                result = m[r][c] + Math.min(getMatrixMinRoadSumCache(m, r + 1, c),
                        getMatrixMinRoadSumCache(m, r, c + 1));
            }
        }
        String key = String.valueOf(r) + "_" + String.valueOf(c);
        cache.put(key, result);
        return result;
    }

    /**
     * 动态规划的方法
     * 时间复杂度：O(M*N)
     * 空间复杂度：O(M*N)
     */
    public int getMatrixMinRoadSumDp(int[][] matrix) {

        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        // 第一列中到第i行的和
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        // 第一行中到第j列的和
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        // 除了上面的位置，此时的(i,j)位置都有左边和上面的值
        // 可以根据(i,j)左边和上边的值判断大小，选择小的来计算(i,j)处的和
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 动态规划
     * 压缩空间的做法
     * 时间复杂度：O(M*N)
     * 空间复杂度：O(min{M,N})
     */
    public int getgetMatrixMinRoadSumDpSaveRoom(int[][] m) {

        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        // 判断行数大还是列数大，压缩的一维数组的长度为行数与列数相比较小的数
        // 如果行数大，就按行进行运算，如果列数大，就按照列进行运算
        int more = m.length > m[0].length ? m.length : m[0].length;
        int less = m.length < m[0].length ? m.length : m[0].length;
        boolean isRow = false;
        if (m.length >= m[0].length) {
            isRow = true;
        }
        int[] array = new int[less];
        array[0] = m[0][0];
        // 先计算第一行/列
        for (int i = 1; i < less; i++) {
            array[i] = array[i - 1] + (isRow ? m[0][i] : m[i][0]);
        }
        // 计算其他行/列
        for (int i = 1; i < less; i++) {
            array[0] = array[0] + (isRow ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                array[j] = m[i][j] + ((array[j - 1] < array[j]) ? array[j - 1] : array[j]);
            }
        }
        return array[less - 1];
    }



    @Test
    public void test() {

        int[][] m = {{4, 3, 3}, {4, 2, 1}, {0, 0, 1}};
        int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(getMatrixMinRoadSumViolence(matrix));
        System.out.println(getMatrixMinRoadSumCache(matrix));
        System.out.println(getMatrixMinRoadSumDp(matrix));
        System.out.println(getgetMatrixMinRoadSumDpSaveRoom(matrix));
        System.out.println(Math.round(-24.9));
    }
}
