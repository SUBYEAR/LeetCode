package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 *
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 10^9 + 7 取余 的结果。
 * 1 <= n <= 10^5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode552 {
    public int checkRecord(int n) { // 可以考虑使用数位dp
        if (n == 1) {
            return 3;
        }
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n + 1][2]; // dp[i][j]表示整数i表示的出勤记录中状态为j可以获得奖励的次数. 0-P, 1-L
        dp[0][0] = 1;
        Arrays.fill(dp[1], 1);
        Arrays.fill(dp[2], 2);
        for (int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][1] = (MOD + dp[i - 1][0] + dp[i - 1][1] - dp[i - 3][0]) % MOD;
        }

        long bound = (dp[n - 1][0] + dp[n - 1][1]) * 2;
        long ans = (dp[n][0] + dp[n][1] + bound % MOD) % MOD;
        for (int i = 2; i < n; i++) {
            long l = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            long r = (dp[n - i][0] + dp[n - i][1]) % MOD;
            ans = (ans + (l * r) % MOD) % MOD;
        }

        return (int) ans;
    }
}
