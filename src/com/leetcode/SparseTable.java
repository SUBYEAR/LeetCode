package com.leetcode;

public class SparseTable {
    private static final int LEN = 1000_000_006;
    // 解决静态区间最值问题的数据结构O(nlogn)预处理，单次查询O(1),空间O(nlogn)
    int[][] dp = new int[LEN + 1][25]; // dp[i][j]表示区间[i, i + (2^j) - 1]的最值
    int[] mn = new int[LEN + 1];
    int[] arr; // 外部数据

    // Range Maximum/Minimum Query
    void rmpInit() {
        for (int i = 1; i <= LEN; i++) { // 丢弃了dp[0]
            dp[i][0] = arr[i];
        }

        for (int j = 1; (1 << j) <= LEN; j++) { // 枚举区间的长度
            for (int i = 1; i + (1 << j) - 1 <= LEN; i++) {
                // dp[5, 3] = min(dp[5][2], dp[9][2])
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
            }
        }
        // 询问区间[5, 10] 长度为6，小长度的最大的2次方值是4
        for (int len = 1; len <= LEN; len++) {
            int k = 0;
            while ((1 << (k + 1) <= len)) {
                k++;
            }
            mn[len] = k;
        }
    }

    int rmq(int left, int right) {
        // 询问询价[5， 10] 转换成询问区间min([5,8], [7, 10])因为mn数组中的值这样保证区间拆分时是全覆盖的
        int k = mn[right - left + 1];
        return Math.min(dp[left][k], dp[right - (1 << k) + 1][k]);
    }
}
