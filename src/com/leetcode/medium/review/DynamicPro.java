/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

/**
 * 功能描述
 *
 * @since 2020-04-24
 */
public class DynamicPro {
    /*
     * 1）只是要求背包装不下为止的： m_memo[i][0] 和m_memo[0][j!=0] 初始化成0或m_memo[i][j] 全初始化成0。
     * 2）要求恰好装满背包： m_memo[i][0] 初始化成0，除m_memo[i][0] 之外都初始化成一个最小负数或者
     * m_memo[0][j!=0] 都初始化成一个最小负数。
     */
    public int bottomUpZeroOnePackage(int[] weight, int[] value, int n, int packageWeight) {
        // 初始化成0 如果初始化成-1时packageWeight=0会得到-1的解
        int[][] m_memo = new int[n + 1][packageWeight + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= packageWeight; j++) {
                m_memo[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            // 或者for(int j = packageWeight; j >= 1; j--) 内层循环从大到小循环也可以
            for (int j = 1; j <= packageWeight; j++) {
                m_memo[i][j] = m_memo[i - 1][j];
                if (j >= weight[i]) {
                    m_memo[i][j] = Math.max(m_memo[i - 1][j], value[i] + m_memo[i - 1][j - weight[i]]);
                }
            }
        }
        return m_memo[n][packageWeight];
    }

    int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        // m_memo[i][0]初始化成0
        int[][] m_memo = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            m_memo[i][0] = 0;
        }
        // 初始值初始化为 amount + 1
        // 主要因为求最小值,所以把问题和子问题的解初始化成amount + 1,因为面值最小单位都是1
        // 所以amount大小需要的面值张数不会超过amount
        for (int j = 1; j <= amount; j++) {
            m_memo[0][j] = amount + 1;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // j<coins[i]不拿该硬币
                m_memo[i][j] = m_memo[i - 1][j];
                // j>=coins[i]拿该硬币,拿几个
                // m_memo[i - 1][j]也是子问题所以参与min比较,min中m_memo[i][j]初值为m_memo[i - 1][j]
                // 相当于满足拿的条件，但是拿还是不拿该面值币看是否满足子问题中最小
                if (j >= coins[i]) {
                    // 所有子问题中取最小,k*coins[i] != 0防止有面值是0出现死循环
                    for (int k = 1; j >= k * coins[i] && k * coins[i] != 0; k++) {
                        m_memo[i][j] = Math.min(m_memo[i][j], k + m_memo[i - 1][j - k * coins[i]]);
                    }
                }
            }
        }

        // 计算出结果如果大于amount表明没有这种组合
        if (m_memo[coins.length - 1][amount] > amount) {
            return -1;
        } else {
            return m_memo[coins.length - 1][amount];
        }
    }

    int minDistance(String s1, String s2) { // s1字符和S2字符的编辑距离
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1]; // dp(i, j)返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
        // base case 是 i 走完 s1 或 j 走完 s2，可以直接返回另一个字符串剩下的长度。
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // dp(i - 1, j)     删除, 把 s[i] 这个字符删掉 前移 i，继续跟 j 对比
                    // dp(i, j - 1)     插入, 在 s1[i] 插入一个和 s2[j] 一样的字符那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
                    // dp(i - 1, j - 1) 替换, 把 s1[i] 替换成 s2[j]，这样它俩就匹配了同时前移 i，j 继续对比
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // 扔鸡蛋问题：确定当前的鸡蛋个数和最多允许的扔鸡蛋次数，就知道能够确定 F 的最高楼层数
    int superEggDrop(int K, int N) {
        // m 最多不会超过 N 次（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        // Java 默认初始化数组都为 0
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;
    }
    // 把线性搜索改成二分搜索
// for (int m = 1; dp[K][m] < N; m++)
//    int lo = 1, hi = N;
//while (lo < hi) {
//        int mid = (lo + hi) / 2;
//        if (... < N) {
//            lo = ...
//        } else {
//            hi = ...
//        }
//
//        for (int k = 1; k <= K; k++)
//        // 状态转移方程
//    }
}
