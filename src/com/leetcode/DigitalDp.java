package com.leetcode;

import java.util.Arrays;

public class DigitalDp {
    // https://blog.csdn.net/wust_zzwh/article/details/52100392
    // 数位dp：统计一个区间[left, right]内满足一些条件个数的个数

    // 给定两个正整数 a 和 b，求在 [a,b] 中的所有整数中，每个数码(digit)各出现了多少次。
    long[][] dp = new long[20][20];
    int[] num = new int[20];
    public long[] countDigitInRange(long a, long b) {
        long[] ans = new long[10]; // 十进制
        for (int i = 0; i < 10; i++) {
            ans[i] = solve(b, i) - solve(a - 1, i);
        }
        return ans;
    }

    private long solve(long x ,int digit) {
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int len = 0;
        while (x != 0) {
            num[++len] = (int) (x % 10);
            x /= 10;
        }
        // 从最高位开始枚举肯定是有限制并且有前导零的
        return dfs(len, true, true, digit, 0);
    }

    /**
     *
     * @param pos     位置
     * @param limit   数位上界变量
     * @param lead    为true表示有前导0
     * @param digit   当前处理的数码
     * @param sum
     * @return
     */
    private long dfs(int pos, boolean limit, boolean lead, int digit, int sum) {
        long ans = 0;
        if (pos <= 0) { // 边界条件
            return sum;
        }
        if (!limit && !lead && dp[pos][sum] != -1) {
            return dp[pos][sum];
        }
        int up = limit ? num[pos] : 9;
        for (int j = 0; j <= up; j++) {
            int add = !(lead && j == 0) && j == digit ? 1 : 0; // 没有前导0且为需要的数码
            ans += dfs(pos - 1, j == num[pos] && limit, lead && j == 0, digit, sum + add);
        }
        if (!limit && !lead) dp[pos][sum] = ans;
        return ans;
    }
}
