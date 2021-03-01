package com.leetcode.medium;

/**
 * 剑指 Offer 14- I. 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwordPointATOffer14 {
    public int cuttingRope(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 2; i < n + 1; i++) {
            dp[i][i] = 1;
            dp[i][2] = i % 2 == 0 ? (i / 2) * (i / 2) : ((i + 1) / 2) * ((i - 1) / 2);
        }

        int res = dp[n][2];
        // dp[i][j]长度为i的绳子分成j段
        for (int i = 4; i <= n; i++) {
            for (int j = i - 1; j > 2; j--) {
                for (int k = 0; k <= i - j; k++) {
                    dp[i][j] = Math.max(dp[i][j], (k + 1) * dp[i - k - 1][j - 1]);
                }
                res = Math.max(dp[i][j], res);
            }
        }

        return res;
    }
}

// 切分规则：
// 最优： 33 。把绳子尽可能切为多个长度为 33 的片段，留下的最后一段绳子的长度可能为 0,1,20,1,2 三种情况。
// 次优： 22 。若最后一段绳子长度为 22 ；则保留，不再拆为 1+11+1 。
// 最差： 11 。若最后一段绳子长度为 11 ；则应把一份 3 + 13+1 替换为 2 + 22+2，因为 2 \times 2 > 3 \times 12×2>3×1。
//         if(n <= 3) return n - 1;
//         int a = n / 3, b = n % 3;
//         if(b == 0) return (int)Math.pow(3, a);
//         if(b == 1) return (int)Math.pow(3, a - 1) * 4;
//         return (int)Math.pow(3, a) * 2;

